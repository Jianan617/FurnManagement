<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">Furn Management</div>
    <div style="flex: 1"></div>
    <el-dropdown>
    <span class="el-dropdown-link">
      Username
      <el-icon class="el-icon--right">
        <arrow-down/>
      </el-icon>
    </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>Personal information</el-dropdown-item>
          <el-dropdown-item>Log out</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script>

</script>