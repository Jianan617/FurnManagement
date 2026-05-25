package com.hjn.furn.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 后端返回给前端的JSON数据的对象
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {
    //状态码[200:成功，400：失败]
    private Integer code;
    //信息说明
    private String description;
    //返回给客户端/浏览器的数据
    private Map<String, Object> extend = new HashMap<>();
    
    //返回一个成功的Message
    public static Message success(){
        Message message = new Message();
        message.setCode(200);
        message.setDescription("success");
        return message;
    }
    
    //返回一个失败的Message
    public static Message failed(){
        Message message = new Message();
        message.setCode(400);
        message.setDescription("failed");
        return message;
    }
    
    //给返回的Message设置数据
    public Message addData(String key, Object value){
        extend.put(key, value);
        return this;
    }
}
