<template>
        <div>
            <page-title title-text="xxx学院班级管理"></page-title>
            <el-button type="primary" @click="create()" class="buttonMarginLeft">添加班级</el-button>

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
                        prop="courseAge"
                        label="年纪">
                    <template slot-scope="scope">
                        {{$getCourseAge(scope.row.courseAge)}}
                    </template>

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
                        prop="peoNum"
                        label="人数">
                </el-table-column>




                <el-table-column
                        align="center"
                        fixed="right"
                        label="操作">
                    <template slot-scope="scope">
                        <!--<el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
                        <el-button @click="edit" type="text" size="small" >编辑</el-button>

                        <!--<el-button @click="delCourseOutline(scope.row.courseCode)" type="warning" style="float: right;margin: 15px 0 ">确认删除</el-button>-->
                        <el-button  style="margin-left: 10px" type="text" @click="$refs.delDia.openDel(40)" size="mini" >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <next-page @transferRandom="changePage" />

            <two-dialog ref="createDia" name="新增"  >
                <create></create>
            </two-dialog>

            <two-dialog ref="editDia" name="编辑" >
                <editClass></editClass>
            </two-dialog>

            <two-dialog ref="delDia" name="删除" >
                <del-information></del-information>
            </two-dialog>
        </div>
</template>

<script>
    import create from "./createClass.vue"
    import editClass from "./edit"
    export default {
        name: "collegeSemester",
        data(){
            return{
                tableData:[{className:"网络工程171",courseAge:1,classGrade:2017,majorName:"网络工程",peoNum:46}],
            }
        },
        methods:{
            changePage(nextCnt){
                this.getTeacher(nextCnt)
            },
            create(){
                this.$refs.createDia.openCreate(50)
            },
            edit(){
                this.$refs.editDia.openEdit(50)
            }
        },
        components:{create,editClass}


    }
</script>

<style scoped>

</style>
