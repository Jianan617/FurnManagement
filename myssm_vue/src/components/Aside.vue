
<template>
  <el-menu
      style="width: 150px"
      default-active="2"
      class="el-menu-vertical-demo"
  >
    <el-sub-menu index="1">
      <template #title>
        <el-icon><location /></el-icon>
        <span>Nav one</span>
      </template>
      <el-menu-item-group title="组一">
        <el-menu-item index="1-1">Selection one</el-menu-item>
        <el-menu-item index="1-2">Selection two</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="组二">
        <el-menu-item index="1-3">Selection three</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <el-menu-item index="2">
      <el-icon><icon-menu /></el-icon>
      <span>Nav two</span>
    </el-menu-item>
    <el-menu-item index="3" disabled>
      <el-icon><document /></el-icon>
      <span>Nav three</span>
    </el-menu-item>
    <el-menu-item index="4">
      <el-icon><setting /></el-icon>
      <span>Nav four</span>
    </el-menu-item>
  </el-menu>
</template>

<style scoped>

</style>