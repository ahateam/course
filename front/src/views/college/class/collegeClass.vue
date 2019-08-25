<template>
        <div class="changeInput">
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
                        prop="classAge"
                        label="年纪">

                    <template slot-scope="scope">
                        {{$getCourseAge(scope.row.classAge)}}
                    </template>

                </el-table-column>
                <el-table-column
                        prop="classGrade"
                        label="级别">
                    <template slot="header" slot-scope="scope">
                        <el-select v-model="look.classGrade" placeholder="级别"  @change="lookupClass()">
                            <el-option
                                    label="全部"
                                    value="0">
                            </el-option>
                            <el-option
                                    v-for="item in getDates()"
                                    :key="item"
                                    :value="item">
                            </el-option>
                        </el-select>
                    </template>
                </el-table-column>

                <el-table-column
                        prop="majorName"
                        label="专业">
                    <template slot="header" slot-scope="scope">
                        <el-select v-model="look.majorId" placeholder="专业"  @change="lookupClass()">
                            <el-option
                                    label="全部"
                                    value="0">
                            </el-option>
                            <el-option
                                    v-for="item in tableMajor"
                                    :key="item.majorId"
                                    :label="item.majorName"
                                    :value="item.majorId">
                            </el-option>
                        </el-select>
                    </template>
                    <template slot-scope="scope">
                        {{getMajorName(scope.row.majorId)}}
                    </template>
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
            <next-page @transferRandom="changePage" ref="nextPage" />

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
                tableData:[{className:"网络工程171",classAge:1,classGrade:2017,majorId:123456,peoNum:46}],
                tableMajor:[{majorId:123456,majorName:"大数据",collegeId:43},{majorId:1234564,majorName:"网络工程",collegeId:42},{majorId:1234567,majorName:"智能",collegeId:41}],
                look:{
                    classGrade:"",
                    collegeId:"",
                    majorId:"",
                },
                lookup:{
                    classGrade:0,
                    collegeId:0,
                    majorId:0,
                },

            }
        },
        methods:{

            create(){
                this.$refs.createDia.openCreate(40)
            },
            edit(){
                this.$refs.editDia.openEdit(40)
            },


            changePage(nextCnt){
                let majorId=this.look.majorId
                let classGrade=this.look.classGrade

                if(majorId===""){nextCnt.majorId=this.lookup.majorId}else{
                    nextCnt.majorId=majorId
                }
                if(classGrade===""){nextCnt.classGrade=this.lookup.classGrade}else{
                    nextCnt.classGrade=classGrade
                }

                nextCnt.collegeId=this.$teacherInformation.collegeId
                console.log(nextCnt)
                this.getClass(nextCnt)
            },
            lookupClass(){
                this.$refs.nextPage.defaultPage()
                let cnt={
                    offset:0,
                    count:this.$store.state.count,
                    collegeId:this.$teacherInformation.collegeId
                }

                let majorId=this.look.majorId
                let classGrade=this.look.classGrade

                if(majorId===""){cnt.majorId=this.lookup.majorId}else{
                    cnt.majorId=majorId
                }
                if(classGrade===""){cnt.classGrade=this.lookup.classGrade}else{
                    cnt.classGrade=classGrade
                }
                console.log(cnt)
                this.getClass(cnt)
            },
            getClass(cnt){
                this.$college.getCollegeClass(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                        console.log(this.$store.state.tableCollege)
                    }else{
                        this.tableData = []
                    }
                })
                this.$refs.nextPage.judge(this.tableData.length)
            },
            getDates(){//获取年份
                let date=new Date()
                let year = date.getFullYear()
                return [year, year - 1, year - 2, year - 3, year - 4]
                //let classGrade=[year,year-1,year-2,year-3,year-4]
                //return classGrade
            },
            getMajorName(majorId){//获取专业名
                console.log(majorId)
                if(majorId===null || !majorId ||majorId==="") {return ""}
                let majorName
                const table=this.tableMajor
                for(let i=0;i<table.length;i++){
                    if(table[i].majorId===majorId){
                        majorName= table[i].majorName
                        break
                    }
                }
                return majorName
             },
            getMajor(){//获取专业
                let cnt={
                    count: 10,
                    majorName: "0",
                    offset: 0,
                    collegeId:this.$teacherInformation.collegeId,
                }
                console.log(cnt)
                //console.log("this.$store.state.teacherInformation")
                //cnt.collegeId= this.$store.state.teacherInformation.collegeId
                //console.log(JSON.parse(sessionStorage.getItem("teacherInformation")))
                this.$admin.lookupSchoolMajor(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableMajor = this.$util.tryParseJson(res.data.c)
                        //console.log(this.$util.tryParseJson(res))
                    }else{
                        this.tableMajor = []
                    }
                })
            },

        },

        mounted(){
            this.getMajor()
            this.lookupClass()
        },
        components:{create,editClass}
    }
</script>

<style scoped lang="scss">
    .changeInput/deep/ {
        .el-select{
            padding-left: 0;
        }
        .el-input{
            padding-left: 0;
        }
    }
</style>
