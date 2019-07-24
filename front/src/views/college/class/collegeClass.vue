<template>
        <div>
            <page-title title-text="xxx学院班级管理"></page-title>
            <el-button type="primary" class="buttonMarginLeft">添加班级</el-button>
            <el-button type="primary" style="margin-left: 30px">添加专业</el-button>

            <el-table
                    :data="tableData"
                    class="tableWidthMargin">
                <el-table-column
                        label="序号"
                        type="index"
                        width="50">
                </el-table-column>

                <el-table-column
                        prop="className"
                        label="班级名称">
                </el-table-column>

                <el-table-column
                        prop="classGrade"
                        label="级别">
                </el-table-column>

                <el-table-column
                        prop="majorName"
                        label="专业">
                </el-table-column>

                <el-table-column
                        prop="collegeName"
                        label="学院">
                </el-table-column>


                <el-table-column
                        align="center"
                        fixed="right"
                        label="操作">
                    <template slot-scope="scope">
                        <!--<el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
                        <el-button type="text" size="small" >编辑</el-button>

                        <!--<el-button @click="delCourseOutline(scope.row.courseCode)" type="warning" style="float: right;margin: 15px 0 ">确认删除</el-button>-->
                        <el-button  style="margin-left: 10px" type="text" size="mini" >删除</el-button>
                    </template>
                </el-table-column>

            </el-table>
        </div>
</template>

<script>
    export default {
        name: "collegeSemester",
        data(){
            return{
                tableData:[{}]
            }
        }
    }
</script>

<style scoped>

</style>
