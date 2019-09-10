<template>
    <div class="changeDia">
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
                    width="80"
            >
            </el-table-column>

            <el-table-column
                    width="300"
                    label="地点">
                <template slot="header" slot-scope="scope">
                    <el-row>
                        <el-col :span="16">
                            <el-select v-model="look.labBuildId" placeholder="大楼"  @change="getFloorNum(look.labBuildId)">
                                <el-option
                                        value=""
                                        label="全部">
                                </el-option>
                                <el-option
                                        v-for="item in tableLabBuild"
                                        :key="item.labBuildId"
                                        :label="item.labBuildName"
                                        :value="item.labBuildId">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="8" :offset="-1">
                            <el-select v-model="look.floor" placeholder="楼层"  @change="lookupLab()" :disabled="look.labBuildId===''">
                                <el-option
                                        value=""
                                        label="全部">
                                </el-option>
                                <el-option
                                        v-for="item in tableFloorNum"
                                        :key="item"
                                        :label="'第'+item+'楼'"
                                        :value="item">
                                </el-option>
                            </el-select>
                        </el-col>
                    </el-row>


                </template>
                <template slot-scope="scope">
                    {{scope.row.labBuildName}}-{{scope.row.labRoomNum}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="collegeName"
                    label="管理部门">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="look.collegeId" placeholder="管理学院"  @change="lookupLab()">
                        <el-option
                                value=""
                                label="全部">
                        </el-option>
                        <el-option
                                v-for="item in $store.state.tableCollege"
                                :key="item.collegeId"
                                :label="item.collegeName"
                                :value="item.collegeId">
                        </el-option>
                    </el-select>
                </template>
                <template slot-scope="scope">
                    {{$getCollegeName(scope.row.collegeId)}}
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
                    <el-button  type="text" size="small" @click="detailOccupy(scope.row)">档期</el-button>
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
                tableLabBuild:[
                    {labBuildId:123,labBuildName:"555sssssssssssssss"},{labBuildId:1234,labBuildName:"555"},{labBuildId:1235,labBuildName:"555"},{labBuildId:1236,labBuildName:"555"},{labBuildId:1237,labBuildName:"555"},
                ],//实验室大楼
                tableFloorNum:0,//楼层
                jiegou:[{
                    value:0,
                    label:"全部"
                }],
                collegeId:"",//选择的学院

                createBuild:false,//新增实验楼
                editBuild:false,//修改实验室 弹框
                editTable:[],//传给修改页面的值
                lookup:{
                    labName:"",
                    labBuildId:"",
                    collegeId:"",
                    floor:""
                },//默认查询条件
                look:{
                    labName:"",
                    labBuildId:"",
                    collegeId:"",
                    floor:"",//楼层


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
            handleChange(val){
               console.log(val)
            },

            lookupLab(){
                console.log("查询")

                this.$refs.nextPage.defaultPage()
                let nextCnt={
                    count:this.$store.state.count,
                    offset:0,
                }
               // console.log(nextCnt)
                if(this.look.collegeId===""&&this.look.labName===""&&this.look.labBuildId===""){
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
                let floor=this.look.floor
                if(labName==="") {cnt.labName=this.lookup.labName} else{
                    cnt.labName=labName}

                if(collegeId==="") {cnt.collegeId=this.lookup.collegeId} else{
                    cnt.collegeId=collegeId}


                if(labBuildId==="") {cnt.labBuildId=this.lookup.labBuildId} else{
                    cnt.labBuildId=labBuildId
                }

                if(floor==="") {cnt.floor=this.lookup.floor} else{
                    cnt.floor=floor}



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
                if (this.look.collegeId === "" && this.look.labName === "" && this.look.labBuildId === "") {
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
                let cnt={
                    offset:0,
                    count:15,
                }
                this.$admin.getLaborBuild(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableLabBuild = this.$util.tryParseJson(res.data.c)
                        console.log(this.$util.tryParseJson(res.data.c))
                    }else{
                        this.tableLabBuild = 0
                    }
                  //  this.$refs.nextPage.judge(this.tableData.length)

                })
            },
            getFloorNum(buildId){
                this.look.floor=""
                let cnt={
                    offset:0,
                    count:10,//最多10楼
                    labBuildId:buildId
                }
                if(buildId!==""){
                    this.$admin.getFloorNum(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableFloorNum = this.$util.tryParseJson(res.data.c)
                            console.log(this.$util.tryParseJson(res.data.c))
                        }else{
                            this.tableFloorNum = []
                        }
                        //  this.$refs.nextPage.judge(this.tableData.length)

                    })
                }

                this.lookupLab()
            },
            detailOccupy(row){
                let lab=JSON.stringify(row)
                sessionStorage.setItem("occupyLab",lab)
                this.$router.push("/adminOccupyLab")
            }
        },
        mounted(){
            //获取实验室
            let cnt = {
                count:this.$store.state.count,
                offset:0
            }
            this.getSchoolLabor(cnt)

            //获取实验室大楼
            this.getLaborBuild()

            //结构赋值labbuild数组
            // this.tableLabBuild.forEach((val,index,arr)=>{
            //
            //     //let {value,label} =val
            //     let value=val.labBuildId
            //     let label=val.labBuildName
            //     console.log(val.labBuildName)
            //     this.jiegou.push({value,label,children:[{
            //          value:"0",
            //          label:"全部"
            //         }]})
            //
            // })
            //console.log(this.jiegou)
        },
        components: {createBuild,edit}
    }



</script>

<style scoped lang="scss">
    .elform /deep/ .el-form-item__label{
        color:#9e9e9e
    }

    .changeDia/deep/ {
    .el-select{
        padding-left: 0;
    }
    .el-input{
        padding-left: 0;
        width: 90%;
    }
        .el-cascader__label{
            //padding: 0 25px 0 45px;
        }
        .el-table .cell, .el-table th div{
            padding-right: 0;
            padding-left: 0;
        }
        .el-table th div{
            line-height: 15px;
        }
    }
</style>
