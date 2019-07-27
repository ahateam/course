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
                                    <!--<el-col :span="6">-->
                                        <!--<el-form-item label="实验室ID:">-->
                                            <!--{{scope.row.labId}}-->
                                        <!--</el-form-item>-->
                                    <!--</el-col>-->
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
                <template slot="header" slot-scope="scope">
                    <el-input placeholder="实验室名称" clearable v-model="look.labName"
                              @change="lookupLab()"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    prop="labSeat"
                    label="座位数"
            >
            </el-table-column>

            <el-table-column
                    align="center"
                    label="地点">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="look.labBuildId" placeholder="位置" size="mini" @change="lookupLab()">
                        <el-option
                                value=""
                                label="全部">
                        </el-option>

                        <el-option
                                v-for="item in tableLabBuild"
                                :key="item.collegeName"
                                :label="item.labBuildName"

                                :value="item.labBuildId">
                        </el-option>
                    </el-select>
                </template>
                <template slot-scope="scope">
                    {{scope.row.labBuildName}}-{{scope.row.labRoomNum}}
                </template>
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="collegeName"
                    label="管理部门">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="look.collegeId" placeholder="管理学院" size="mini" @change="lookupLab()">
                        <el-option
                                value=""
                                label="全部">
                        </el-option>
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
                width="70%">
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
                    teacherPhone:18786085146,username:2017250190,labArea:168.73,labId:13151651,collegeId:1251515,
                    lecturerName:"大数据实验中心",labName:"CAD 实验室",collegeName:"大数据"},

                    {labBuildName:"第三实验楼",labRoomNum:301,teacherName:'玩完',labSeat:'25',labTime:1561996800000,
                        teacherPhone:18786085146,username:2017250190,labArea:123.73,labId:516151561,collegeId:1251515,
                        lecturerName:"大数据实验中心",labName:"信息 实验室",collegeName:"理学院"}],//存储教室信息

                tableCollege:[],//获取的学院
                tableLabBuild:[],
                collegeId:"",//选择的学院
                page:1,
                pageOver:false,
                count:10,
                offset:0,
                createBuild:false,//新增实验楼
                editBuild:false,//修改实验室 弹框
                editTable:[],//传给修改页面的值
                lookup:{
                    labName:"",
                    labBuildId:"any(select labBuildId from table_name)",
                    collegeId:"any(select collegeId from table_name)",
                },//默认查询条件
                look:{
                    labName:"",
                    labBuildId:"",
                    collegeId:""

                },//是否改变查询条件


            }
        },
        methods:{


            //获取全校实验室情况
            getSchoolLabor(cnt){
                //如果未选择学院
                //if(this.collegeId===""){
                    this.$admin.getSchoolLabor(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                            console.log(this.$util.tryParseJson(res.data.c))
                        }else{
                            this.tableData = []
                        }
                        //判断是否到达最后一页
                        this.$refs.nextPage.judge(this.tableData.length)
                    })
               // }

                // else{
                //
                //     //查询某个学院的实验室
                //     this.$labor.getCollegeLabor(cnt,(res)=>{
                //         if(res.data.rc === this.$util.RC.SUCCESS){
                //             this.tableData = this.$util.tryParseJson(res.data.c)
                //             console.log(this.$util.tryParseJson(res.data.c))
                //         }else{
                //             this.tableData = []
                //         }
                //         //判断是否到达最后一页
                //         if(this.tableData.length <this.count){
                //             this.pageOver= true
                //         }else{
                //             this.pageOver = false
                //         }
                //     })
                // }
            },



            lookupLab(){
                this.$refs.nextPage.defaultPage()
                let nextCnt={
                    count:this.$store.state.count,
                    offset:0
                }
                console.log(nextCnt)
                if(this.look.collegeId===""&&this.look.labId===""&&this.look.labBuildId===""){
                    this.getSchoolLabor(nextCnt)
                }
                else{
                    this.lookupLabor(nextCnt)
                }
            },
            lookupLabor(cnt){
                let collegeId =   this.look.collegeId
                let labName =     this.look.labName
                let labBuildId= this.look.labBuildId

                if(collegeId==="") cnt.collegeId=this.lookup.collegeId
                if(labName==="") cnt.labName=this.lookup.labName
                if(labBuildId==="") cnt.labBuildId=this.lookup.labBuildId

                this.$admin.lookupLabor(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                        console.log(this.$util.tryParseJson(res.data.c))
                    }else{
                        this.tableData = []
                    }
                    //判断是否到达最后一页
                    this.$refs.nextPage.judge(this.tableData.length)
                })
            },
            changePage(nextCnt) {
                if (this.look.collegeId === "" && this.look.labId === "" && this.look.labBuildId === "") {
                    this.getSchoolLabor(nextCnt)
                } else {
                    this.lookupLabor(nextCnt)
                }
            },
            //点击编辑按钮
            editData(row){
                this.editTable.splice(0,1)
                this.editTable[0]=row
                this.editBuild=true //修改实验室弹框

            },
            getLaborBuild(){
                let cnt={}
                this.$admin.getLaborBuild(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableLabBuild = this.$util.tryParseJson(res.data.c)
                        console.log(this.$util.tryParseJson(res.data.c))
                    }else{
                        this.tableLabBuild = []
                    }
                  //  this.$refs.nextPage.judge(this.tableData.length)

                })
            }
        },
        mounted(){
            let cnt = {
                count:this.count,
                offset:(this.page-1)*this.count
            }
            this.getSchoolLabor(cnt)
            this.getLaborBuild()
        },
        components: {createBuild,edit}
    }
</script>

<style scoped>
    .elform /deep/ .el-form-item__label{
        color:#9e9e9e
    }
</style>
