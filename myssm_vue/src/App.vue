<template>

  <!--1.App.vue页面可以用于布局界面
      2.<router-view/>是路由指令，会把路由到的页面内容展示到<router-view/>位置
      <nav>
        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link>
      </nav>
  <router-view/>-->
  <div>
    <!--头部-->
    <Header/>
    <!--主体-->
    <div style="display: flex">
      <!--侧边栏-->
      <Aside/>
      <!--内容区域，通过路由展示-->
      <router-view style="flex: 1"/>
    </div>
  </div>

</template>
<script>
  import Header from "@/components/Header.vue";
  import Aside from "@/components/Aside.vue";

  export default {
    name: "Layout",
    components: {
      Aside,
      Header,
    }
  }
</script>