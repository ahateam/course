<template>
    <div>
        <span style="font-size: 30px;">xxx学院实验课程大纲</span><br>

        <el-select v-model="schoolMonth" placeholder="学院" style="margin-left: 5%" >
            <el-option
                    v-for="item in month"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
        </el-select>
        <router-link to="addCurriculumExamine"><el-button type="primary">学院新增课程大纲</el-button></router-link>
        <router-link to="addCurriculumExamine"><el-button type="primary">学院删除课程大纲</el-button></router-link>
        <router-link to="addCurriculumExamine"><el-button type="primary">学院变更实验室审核</el-button></router-link>

        <div>
            <el-table
                    :data="tableData"
                    border
                    style="width: 90%;margin-left: 5%;margin-top: 10px">
                <el-table-column
                        label="序号"
                        type="index"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="lecturerName"
                        label="课程名称"
                >
                </el-table-column>
                <el-table-column
                        prop="workNum"
                        label="课程编码"
                >
                </el-table-column>
                <el-table-column
                        prop="position"
                        label="课程学分"
                >
                </el-table-column>

                <el-table-column
                        prop="subordinateDepartments"
                        label="学时">
                </el-table-column>
                <el-table-column
                        label="考核方式">
                </el-table-column>
                <el-table-column
                        label="课程性质">
                </el-table-column>
                <el-table-column
                        label="上课年纪">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                        <el-button type="text" size="small">编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "adminExamine",
        data(){
            return{
                tableData:[{},{}]
            }
        }
    }
</script>

<style scoped>

</style>
