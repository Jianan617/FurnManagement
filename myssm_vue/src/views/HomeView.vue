<template>
  <div>
    <div style="margin: 10px 5px">
      <!--<el-button type="primary">新增</el-button>-->
      <!--@click="add"表示点击就可以触发-->
      <el-button plain @click="add">
        +New
      </el-button>
      <el-button>Other</el-button>
    </div>
    <div style="margin: 10px 5px">
      <el-input v-model="search" style="width: 30%" placeholder="Please enter keyword"/>
      <el-button @click="list" style="margin-left: 10px" type="primary">Search</el-button>
    </div>
    <el-table :data="tableData" stripe style="width: 900px">
      <el-table-column prop="id" label="ID"/>
      <el-table-column prop="name" label="Furn Name"/>
      <el-table-column prop="maker" label="Maker"/>
      <el-table-column prop="price" label="Price"/>
      <el-table-column prop="sales" label="Sales"/>
      <el-table-column prop="stock" label="Stock"/>
      <el-table-column fixed="right" label="Actions" min-width="100">
        <template #default="scope">
          <!--scope.row获取当前行数据-->
          <el-button link type="primary" @click="handleEdit(scope.row)">Edit</el-button>
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small">Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" title="Form" width="500">
      <el-form :model="form" :rules="rules" ref="form" label-width="auto" style="max-width: 600px">
        <!--form表单的字段需要和后端封装的Furn的字段保持一致-->
        <el-form-item label="Furn Name:" prop="name">
          <!--前端的对象的属性可以自动生成-->
          <el-input v-model="form.name" style="width: 80%"/>
          {{serverValidErrors.name}}
        </el-form-item>
        <el-form-item label="Maker:" prop="maker">
          <el-input v-model="form.maker" style="width: 80%"/>
          {{serverValidErrors.maker}}
        </el-form-item>
        <el-form-item label="Price:" prop="price">
          <el-input v-model="form.price" style="width: 80%"/>
          {{serverValidErrors.price}}
        </el-form-item>
        <el-form-item label="Sales:" prop="sales">
          <el-input v-model="form.sales" style="width: 80%"/>
          {{serverValidErrors.sales}}
        </el-form-item>
        <el-form-item label="Stock:" prop="stock">
          <el-input v-model="form.stock" style="width: 80%"/>
          {{serverValidErrors.stock}}
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="save">Create</el-button>
        </div>
      </template>
    </el-dialog>
    <!--分页导航-->
    <div style="margin: 10px 0">
      <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 25, 50]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handlePageSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "HomeView",
  components: {},
  data() {
    return {
      //存放后端校验信息
      serverValidErrors:{},

      //分页相关的数据绑定
      currentPage: 1,
      pageSize: 5,
      total: 10, //共有多少记录

      search: '',
      dialogVisible: false,
      form: {},
      tableData: [],

      //添加表单的校验规则
      rules: {
        name: [
          {
            required: true,
            message: "Please enter furniture's name",
            trigger: 'blur'
          }
        ],
        maker: [
          {
            required: true,
            message: "Please enter furniture's maker",
            trigger: 'blur'
          }
        ],
        price: [
          {
            required: true,
            message: "Please enter furniture's price",
            trigger: 'blur'
          },
          {
            //使用正则表达式对输入数据进行校验
            pattern: /^([1-9]\d*|0)(\.\d+)?$/,
            message: "Please enter number",
            trigger: 'blur'
          }
        ],
        sales: [
          {
            required: true,
            message: "Please enter furniture's sales",
            trigger: 'blur'
          },
          {
            //使用正则表达式对输入数据进行校验
            pattern: /^([1-9]\d*|0)$/,
            message: "Please enter 0 or positive number",
            trigger: 'blur'
          }
        ],
        stock: [
          {
            required: true,
            message: "Please enter furniture's stock",
            trigger: 'blur'
          },
          {
            //使用正则表达式对输入数据进行校验
            pattern: /^([1-9]\d*|0)$/,
            message: "Please enter number",
            trigger: 'blur'
          }
        ],
      }
    }
  },
  //钩子函数
  created() {
    this.list();//调用list方法
  },
  methods: {
    //显示添加对话框
    add() {
      this.form = {};//清空添加表单数据
      // this.$refs['form'].resetFields();//清空校验
      this.dialogVisible = true;//显示对话框
    },
    //正式添加家具
    save() {
      // console.log("id",this.form.id)
      if (this.form.id) {//修改
        request.put("/api/update", this.form).then(res => {
          // console.log("修改 - res:",res);
          if (res.code === 200) {
            //修改成功
            this.$message({
              type: "success",
              message: "Update successfully!"
            })
          } else if (res.code === 400){//后端校验失败
            this.serverValidErrors.name = res.extend.errors.name;
            this.serverValidErrors.maker = res.extend.errors.maker;
            this.serverValidErrors.price = res.extend.errors.price;
            this.serverValidErrors.sales = res.extend.errors.sales;
            this.serverValidErrors.stock = res.extend.errors.stock;
          }
          this.dialogVisible = false;
          this.list();//刷新表格显示
        })
      } else {//添加
        //验证表单校验是否通过
        this.$refs['form'].validate(isValid => {
          if(isValid){
            request.post("/api/save", this.form).then(res => {
              // console.log("res:",res);
              this.dialogVisible = false;
              this.list();//刷新tableData
            })
          }else {
            this.dialogVisible = true;
            this.$message({
              type:'error',
              message:'Validation failed!'
            });
          }
        })


      }


    },
    //请求返回家具信息
    list() {
      // console.log("this:", this);
      // request.get("/api/furns").then(res => {
      //   console.log("res", res);
      //   this.tableData = res.extend.furnList;//需要去浏览器控制台查看数据封装的层次(这里经过拦截器统一处理了res，res这里等同于res.data，见request.js)
      // })
      request.get("/api/furnsByConditionPage", {
        //请求中携带的参数
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        this.tableData = res.extend.pageInfo.list;
        this.total = res.extend.pageInfo.total;
      })
    },
    //更新家具
    handleEdit(row) {
      // console.log("row:", row);
      //先将row对象转换为JSON字符串，再将JSON字符串转换为JSON对象
      // this.form = JSON.parse(JSON.stringify(row));
      // this.dialogVisible = true;
      request.get("/api/furn/" + row.id).then(res => {
        console.log("find furn by id:", res);
        this.form = res.extend.furn;
        this.dialogVisible = true;
      })

    },
    //删除家具
    handleDelete(id) {
      console.log("row id:", id);
      request.delete("/api/delete/" + id).then(res => {
        console.log("delete - res:", res);
        if (res.code === 200) {
          this.$message({
            type: "success",
            message: "Deletion successfully!"
          })
        } else {
          this.$message({
            type: "error",
            message: "Deletion failed!"
          })
        }
        this.list();
      })
    },
    //处理分页请求
    handleCurrentChange(pageNum){
      this.currentPage = pageNum;
      this.list();
    },
    //处理页数
    handlePageSizeChange(pageSize){
      this.pageSize = pageSize;
      this.list();
    }
  }
}
</script>
