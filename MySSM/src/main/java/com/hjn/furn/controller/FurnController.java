package com.hjn.furn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjn.furn.bean.Furn;
import com.hjn.furn.bean.Message;
import com.hjn.furn.service.FurnService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
public class FurnController {
    @Resource
    private FurnService furnService;
    
    /**
     * @ResponseBody 表明返回JSON格式的数据（底层是按照http协议）
     * @RequestBody 会自动把前端提交的JSON格式的数据封装成Furn furn对象
     */
    //相应客户端的添加请求
    @PostMapping("/save")
    @ResponseBody
    public Message save(@Valid @RequestBody Furn furn,
                        Errors errors){
        HashMap<String, Object> errorsMap = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        if(!errorsMap.isEmpty()){//发生校验错误
            return Message.failed().addData("errors", errorsMap);
        }
        
        furnService.save(furn);
        
        return Message.success();
    }
    
    @GetMapping("/furns")
    @ResponseBody
    public Message findAllFurns(){
        List<Furn> furnList = furnService.findAllFurns();
        Message message = Message.success();
        message.addData("furnList", furnList);
        return message;
    }
    
    //按id查询指定家具
    @GetMapping("/furn/{id}")
    @ResponseBody
    public Message findFurnById(@PathVariable Integer id){
        Furn furn = furnService.findFurnById(id);
        Message message = Message.success();
        message.addData("furn", furn);
        return message;
    }
    
    
    @PutMapping("/update")
    @ResponseBody
    public Message update(@RequestBody Furn furn){
        furnService.update(furn);
        return Message.success();
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Message delete(@PathVariable Integer id){
        System.out.println("delete controller ... ... ... ...");
        furnService.delete(id);
        return Message.success();
    }
    
    @RequestMapping("/furnsByPage")
    @ResponseBody
    public Message listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "5") Integer pageSize){
        //1.设置分页参数
        PageHelper.startPage(pageNum,pageSize);
        //2.调用findAllFurns，底层是物理分页，不是逻辑分页，会根据分页参数计算limit值（因为已写入配置文件）
        List<Furn> allFurns = furnService.findAllFurns();
        //3.将分页查询的结果封装到PageInfo中
        PageInfo<Furn> pageInfo = new PageInfo<>(allFurns, pageSize);
        //4.将pageInfo封装到Message返回即可
        return Message.success().addData("pageInfo", pageInfo);
    }
    
    //根据家具名分页查询
    @RequestMapping("/furnsByConditionPage")
    @ResponseBody
    public Message listFurnsByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                            @RequestParam(defaultValue = "") String search){
        
        PageHelper.startPage(pageNum,pageSize);
        List<Furn> allFurns = furnService.findFurnByCondition(search);
        PageInfo<Furn> pageInfo = new PageInfo<>(allFurns, pageSize);
        return Message.success().addData("pageInfo", pageInfo);
    }
}
