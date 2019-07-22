<template>
    <div class="elform">
        <page-title title-text="学院管理"></page-title>
        <el-row>
            <el-col>
                <el-button type="primary" class="buttonMarginLeft" @click="dialogVisible = true">新增学院</el-button>
                <el-button type="primary" class="buttonMarginLeft" @click="dialogVisible = true">专业转院</el-button>

            </el-col>
        </el-row>
        <el-table

                :row-class-name="getindex"
                @expand-change="clickGet"
                :data="tableData"
                class="tableWidthMargin">
            <el-table-column
                    label="详情"
                    type="expand"
                    width="100"
                    >
                <template slot-scope="scope">
                    <el-row>
                        <el-col :span="8">
                            <el-form >
                                <el-form-item label="学院名称:">
                                    {{scope.row.collegeName}}
                                </el-form-item>
                                <el-form-item label="学院ID:">
                                    {{scope.row.collegeId}}
                                </el-form-item>
                                <el-form-item label="专业:">
                                    <span v-for="(item,index) in scope.row.majorName" :key="index">
                                        {{item}}
                                    </span>

                                </el-form-item>
                                <el-form-item label="班级数量:">
                                    {{scope.row.className.length}}
                                </el-form-item>
                                <el-form-item label="教师数量:">
                                    {{scope.row.teacherNum}}
                                </el-form-item>
                                <el-form-item label="实验室数量:">
                                    {{scope.row.laboratoryNum}}
                                </el-form-item>
                            </el-form>
                        </el-col>

                        <el-col :span="15" :offset="1">
                            <el-form>
                                <el-form-item  label="班级:">
                                    <br>
                                    <div style="width: 16%;float: left" v-for="(item,index) in scope.row.className" :key="index">
                                        {{item}}
                                    </div>

                                </el-form-item>
                            </el-form>
                        </el-col>
                    </el-row>

                </template>
            </el-table-column>
            <el-table-column
                    label=" "
                    type="index"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="collegeId"
                    label="学院Id">
            </el-table-column>
            <el-table-column
                    prop="collegeName"
                    label="学院名称">
            </el-table-column>
            <!--<el-table-column-->
                    <!--prop="majorName.length"-->
                    <!--label="专业数量">-->
            <!--</el-table-column>-->
            <!--<el-table-column-->
                    <!--prop="className.length"-->
                    <!--label="班级数量">-->
            <!--</el-table-column>-->
            <!--<el-table-column-->
                    <!--prop="teacherNum"-->
                    <!--label="教师数量">-->
            <!--</el-table-column>-->
            <!--<el-table-column-->
                    <!--prop="laboratoryNum"-->
                    <!--label="实验室数量">-->
            <!--</el-table-column>-->
            <el-table-column
                    align="center"
                    label="操作">
                <template slot-scope="scope">
                    <el-button type="text" size="small" @click="edits(scope.row)" >编辑</el-button>
                    <el-button @click="delCourse(scope.row)" style="margin-left: 10px" type="text" size="mini" >删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--删除学院-->
        <el-dialog
                :visible.sync="delCollege"
                :title="del.collegeName"
                width="30%">
            <div style="height: 140px">
                <el-input
                        type="textarea"
                        placeholder="删除备注 30字以内"
                        v-model="delRemark"
                        maxlength="30"
                        resize="none"
                        :autosize="{minRows:3,maxRows:5}"
                        show-word-limit>
                </el-input>
                <el-button @click="delDepartment(del)" type="danger" style="float: right;margin: 30px 0 0 0">确认删除</el-button>
            </div>

        </el-dialog>

        <!--****  编辑学院  ***-->
        <el-dialog
                :visible.sync="edit"
                width="30%"
                label="修改信息"
                class="change">
            <el-form label-width="90px"  label-position="left" ref="editCollege" :model="editCollege" :rules="rules">
                <!--<el-row style="margin-bottom: 10px">-->
                    <!--<el-form-item label="学院ID:" prop="collegeId">-->
                        <!--<el-col :span="16">-->
                            <!--<span disabled  >{{editCollege.collegeId}}</span>-->
                        <!--</el-col>-->

                    <!--</el-form-item>-->
                <!--</el-row>-->
                <el-form-item label="学院名称:" prop="collegeName" >
                    <el-col :span="16">
                        <el-input :disabled="editCollege.changeName" v-model="editCollege.collegeName" property="请输入学院名称"></el-input>
                    </el-col>
                    <el-col :offset="1" :span="4" style="line-height: 40px">
                        <el-button type="text"  @click="editCollege.changeName=false">修改</el-button>
                    </el-col>
                </el-form-item>
                <el-row style="margin-top: 30px">
                    <el-form-item>
                        <el-button :disabled="editCollege.changeName===true" type="primary" @click="editDepartment(editCollege.collegeId,editCollege.collegeName)">立即创建</el-button>
                        <el-button @click="edit=false">取消</el-button>
                    </el-form-item>
                </el-row>
            </el-form>
        </el-dialog>


        <!--** 新增学院 *-->
        <el-dialog
                :visible.sync="dialogVisible"
                width="30%">
            <page-title title-text="新增学院"></page-title>
            <el-form label-width="90px" ref="createCollege" :model="createCollege" :rules="rules">
                <!--<el-row style="margin-bottom: 10px">-->
                        <!--<el-form-item label="学院编码:" prop="collegeId">-->
                            <!--<el-col :span="16">-->
                                <!--<el-input v-model="createCollege.collegeId" property="请输入学院编码"></el-input>-->
                            <!--</el-col>-->
                        <!--</el-form-item>-->
                <!--</el-row>-->
                    <el-form-item label="学院名称:" prop="collegeName" >
                        <el-col :span="16">
                            <el-input v-model="createCollege.collegeName" property="请输入学院名称"></el-input>
                        </el-col>
                    </el-form-item>
                <el-row style="margin-top: 30px">
                    <el-form-item>
                        <el-button type="primary" @click="createDepartment">立即创建</el-button>
                        <el-button @click="dialogVisible=false">取消</el-button>
                    </el-form-item>
                </el-row>
            </el-form>
        </el-dialog>


        <next-page ref="nextPage"  @transferRandom="changePage" />

    </div>
</template>

<script>
    export default {
        name: "adminCollege",
        data() {
            return {
                rules:{
                    collegeName:[{ required: true, message: '请输入学院名称', trigger: 'blur' }],
                    collegeId:[{ required: true, message: '请输入学院ID', trigger: 'blur' }]

                },
                //新增学院
                createCollege:{
                    collegeName:"",
                    collegeId:""

                },

                delCollege:false,//删除学院
                del:"",//获取行
                delRemark:"",//删除备注

                editCollegeCourse:[],
                form:{
                    name:""
                },
                schoolYear:'',
                schoolMonth:'',
                tableData:[{
                    collegeName:"大数据学院",//学院名称
                    collegeID:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:["网络工程","数据科学与科学计算","人工智能"]
                },{
                    collegeName:"理学院",//学院名称
                    collegeID:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:["网络工程","数据科学与科学计算","人工智能","网络工程","网络工程"]

                },{
                    collegeName:"大数据学院",//学院名称
                    collegeID:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:["网络工程","数据科学与科学计算","人工智能"]

                }],

                dialogVisible:false, //新增课程弹出
                page:1,
                pageOver:false,
                count:10,
                offset:0,

                edit:false,
                editCollege:{
                    collegeName:"",
                    collegeId:"",
                    changeId:true,
                    changeName:true
                }
            }
        },
        methods:{
            changePage(nextCnt){
                this.getDepartments(nextCnt)
            },

            //获取学院
            getDepartments(cnt){
                this.$admin.getDepartments(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                        console.log(this.$util.tryParseJson(res))
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
            },

            //获取每行的索引
            getindex({row, rowIndex}){
                row.index = rowIndex;
            },

            //展开行时获取 教师 实验室 班级等详细情况
            clickGet(row,expandedRows){
                let cnt={
                    collegeID:row.collegeId
                }

                //获取班级
                this.$admin.getDepartmentClass(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData[row.index].className = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData[row.index].className = []
                    }
                })

                //获取实验室数量
                this.$admin.getDepartmentLaboratory(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData[row.index].laboratoryNum = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData[row.index].laboratoryNum = []
                    }
                })

                //获取专业
                this.$admin.getDepartmentMajor(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData[row.index].majorName = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData[row.index].majorName = []
                    }
                })

                //获取教师数量
                this.$admin.getDepartmentTeacher(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData[row.index].teacherNum = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData[row.index].teacherNum = []
                    }
                })
            },

            // 新增学院
            createDepartment(){
                //表单验证
                this.$refs['createCollege'].validate((valid) => {
                    if (valid) {
                        let cnt={
                            collegeName:this.createCollege.collegeName,
                           // collegeId:this.createCollege.collegeId
                        }
                        //const loading = this.$loading({lock: true, text: '拼命加载中...', spinner: 'el-icon-loading'})
                        //添加学院
                        this.$admin.createDepartment(cnt,(res)=>{
                            if(res.data.rc === this.$util.RC.SUCCESS){
                                //loading.close()
                                this.$message({type:'success',message:"添加成功"})

                            }else{
                                //loading.close()
                                this.$message({type:'warning',message:"添加失败"})
                            }
                        })
                    } else {
                        this.$message({type:'warning',message:"请输入完整信息"})

                        return false;
                    }
                });
            },

            //点击编辑按钮
            edits(row){
                this.editCollege.changeId=true
                this.editCollege.changeName=true
                this.edit=true
                this.editCollege.collegeName=row.collegeName
                this.editCollege.collegeId=row.collegeId

            },
            //编辑学院
            editDepartment(ID,NAME){
                this.$refs['editCollege'].validate((valid) => {
                        if (valid) {
                            let cnt={
                                collegeName:NAME,
                                collegeId:ID
                            }
                            //const loading = this.$loading({lock: true, text: '拼命加载中...', spinner: 'el-icon-loading'})
                            this.$admin.editDepartment(cnt,(res)=>{
                                if(res.data.rc === this.$util.RC.SUCCESS){
                                    this.$message({type:'success',message:"修改成功"})
                                }
                                else{
                                    this.$message({type:'warning',message:"修改失败"})
                                }
                            })
                        }
                        else{
                            this.$message({type:'warning',message:"请输入完整信息"})
                        }
                })
            },

        //删除学院
            delCourse(row){
                this.del=row
                this.delCollege=true
            },

            delDepartment(row){
                let cnt={
                   // collegeName:row.collegeName,
                    collegeId:row.collegeId,
                  //  delRemark: row.delRemark
                }
                //const loading = this.$loading({lock: true, text: '拼命加载中...', spinner: 'el-icon-loading'})
                this.$admin.delDepartment(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.$message({type:'success',message:"删除成功"})
                    }
                    else{
                        this.$message({type:'warning',message:"删除失败"})
                    }
                })
            },

        },
        mounted(){
            //获取学院信息
            let cnt={
                offset:this.offset,
                count:this.count
            }
            this.getDepartments(cnt)
        },
    }
</script>

<style >
    .elform .el-form-item__label{
        color:#9e9e9e
    }
    .elform .el-form-item{
        margin-bottom: 10px;
    }

    .change .el-button--text{
        color:red
    }
 </style>
