const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {
    port: 10000, //启动端口，因为tomcat占用了8080端口号，所以要更改为其他端口号，避免冲突
    //解决跨域问题
    proxy:{
      //设置拦截器，格式：/ + 拦截器名字（名字自定）
      '/api':{
        target:'http://localhost:8080/MySSM', //请求地址中的/api会被替换为http://localhost:8080/my_ssm_project/MySSM
        changeOrigin:true, //设置是否同源，true代表允许浏览器跨域访问指定的target这个地址
        pathRewrite:{
          '/api':'' //选择忽略拦截器里面的单词
        }
      }
    }
  }
}