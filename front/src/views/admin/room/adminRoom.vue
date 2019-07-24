<template>
    <div>
        <page-title title-text="实验室管理" />
        <el-button class="buttonMarginLeft" @click="createBuild=true" type="primary" size="small">新增实验楼</el-button>
        <el-table
                :data="tableData"
                class="tableWidthMargin">
            <el-table-column
                    label="详情"
                    type="expand"
                    width="50">
                <template slot-scope="scope">
                    <el-row>
                            <el-form class="elform" label-width="110px" label-position="left">
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="实验中心名称:">
                                            {{scope.row.lecturerName}}
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="建成时间:">
                                            {{new Date(parseInt(scope.row.labTime)).toLocaleDateString()}}

                                        </el-form-item>
                                    </el-col>

                                    <el-col :span="6">
                                        <el-form-item label="面积:">
                                             {{scope.row.labArea}}   m<sup>2</sup>
                                        </el-form-item>
                                    </el-col>
                                </el-row>

                                <el-row>
                                    <el-col :span="6">
                                        <el-form-item label="负责人电话:">
                                            {{scope.row.teacherPhone}}
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="负责人工号:">
                                            {{scope.row.username}}
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                    </el-row>
                </template>

            </el-table-column>
            <el-table-column
                    label="序号"
                    type="index"
                    width="50">
            </el-table-column>

            <el-table-column
                    prop="labName"
                    label="实验室名称"
            >
            </el-table-column>
            <el-table-column
                    prop="labSeat"
                    label="座位数"
            >
            </el-table-column>

            <el-table-column
                    label="地点">
                <template slot-scope="scope">
                    {{scope.row.labBuildName}}-{{scope.row.labRoomNum}}
                </template>
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="collegeName"
                    label="管理部门">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="collegeId" placeholder="管理学院" size="mini" @change="lookopLab()">
                        <el-option
                                v-for="item in tableCollege"
                                :key="item.collegeName"
                                :lable="item.collegeName"
                                :value="item.collegeId">
                        </el-option>
                    </el-select>
                </template>
                <template slot-scope="scope">
                    {{scope.row.collegeName}}
                </template>
            </el-table-column>
            <!--<el-table-column-->
                    <!--label="面向专业">-->
            <!--</el-table-column>-->
            <el-table-column
                    prop="teacherName"
                    label="负责人">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">

                    <el-button @click="editData(scope.row)" type="text" size="small">编辑</el-button>
                    <!--<el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>-->
                </template>
            </el-table-column>
        </el-table>
        <next-page ref="nextPage"  @transferRandom="changePage" />


        <!--*****   修改实验室信息  *****-->
        <el-dialog
                :visible.sync="editBuild"
                width="60%">
            <div style="">
                <page-title title-text="修改信息"></page-title>
                <edit :rule-form="editTable"></edit>
            </div>

        </el-dialog>


       <!--***** 新增实验楼  ***-->
        <el-dialog
                :visible.sync="createBuild"
                width="40%">
            <div style="">
                <page-title title-text="新增实验楼"></page-title>
                <createBuild></createBuild>
            </div>

        </el-dialog>
    </div>
</template>

<script>
    import edit from "./edit"
    import createBuild from "./createBuild"
    export default {
        name: "adminRoom",
        data(){
            return{
                tableData:[{labBuildName:"第三实验楼",labRoomNum:301,teacherName:'王诚',labSeat:'34',labTime:1561996800000,
                    teacherPhone:18786085146,username:2017250190,labArea:168.73,
                    lecturerName:"大数据实验中心",labName:"CAD 实验室",collegeName:"大数据"},

                    {labBuildName:"第三实验楼",labRoomNum:301,teacherName:'玩完',labSeat:'25',labTime:1561996800000,
                        teacherPhone:18786085146,username:2017250190,labArea:123.73,
                        lecturerName:"大数据实验中心",labName:"信息 实验室",collegeName:"理学院"}],//存储教室信息

                tableCollege:[],//获取的学院
                collegeId:"",//选择的学院
                page:1,
                pageOver:false,
                count:10,
                offset:0,
                createBuild:false,//新增实验楼
                editBuild:false,//修改实验室 弹框
                editTable:[],//传给修改页面的值


            }
        },
        methods:{


            //获取全校实验室情况
            getSchoolLabor(cnt){
                //如果未选择学院
                if(this.collegeId===""){
                    this.$admin.getSchoolLabor(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                            console.log(this.$util.tryParseJson(res.data.c))
                        }else{
                            this.tableData = []
                        }
                        //判断是否到达最后一页
                        if(this.tableData.length <this.count){
                            this.pageOver= true
                        }else{
                            this.pageOver = false
                        }
                    })
                }

                else{

                    //查询某个学院的实验室
                    this.$labor.getCollegeLabor(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                            console.log(this.$util.tryParseJson(res.data.c))
                        }else{
                            this.tableData = []
                        }
                        //判断是否到达最后一页
                        if(this.tableData.length <this.count){
                            this.pageOver= true
                        }else{
                            this.pageOver = false
                        }
                    })
                }
            },

            lookopLab(){
                let nextCnt={
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                this.changePage(nextCnt)

            },
            changePage(nextCnt){

                //如果选择学院后 获取选择学院的教师信息
                if(this.collegeId!=="") nextCnt.collegeId=this.collegeId
                this.getSchoolLabor(nextCnt)
            },

            //点击编辑按钮
            editData(row){
                this.editTable.splice(0,1)
                this.editTable[0]=row
                this.editBuild=true //修改实验室弹框

            }


        },
        mounted(){
            let cnt = {
                count:this.count,
                offset:(this.page-1)*this.count
            }
            this.getSchoolLabor(cnt)
        },
        components: {createBuild,edit}
    }
</script>

<style scoped>
    .elform /deep/ .el-form-item__label{
        color:#9e9e9e
    }
</style>
