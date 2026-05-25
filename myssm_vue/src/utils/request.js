import axios from "axios";

//request用于发送请求到后端
const request = axios.create({
    timeout: 5000,//5s超时时间
})

//request拦截器:对请求做统一处理
//如：统一加token、ContentType
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config;
},err => {
    return Promise.reject(err);
})

//response拦截器：可以在调用接口响应后，统一处理返回结果
request.interceptors.response.use(response => {
    let res = response.data;
    //如果返回是文件，则直接返回（'blob'表示是文件格式）
    if(response.config.responseType === 'blob'){
        return res;
    }
    //如果是String，就转成json对象
    if(typeof res === 'string'){
        //如果res不为null，就转换成json对象
        res = res ? JSON.parse(res) : res;
    }
    return res;
},err => {
    console.log("err:", err);
    return Promise.reject(err);
})

export default request;
