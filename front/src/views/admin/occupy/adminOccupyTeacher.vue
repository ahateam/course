<template>
    <div>
        <page-title title-text="教师档期"></page-title>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/adminTeacher' }">教师管理</el-breadcrumb-item>
            <el-breadcrumb-item>教师档期</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
</template>

<script>
    export default {
        name: "adminOccupyTeacher",
        props:["username"]
    }
</script>

<style scoped>

</style>
