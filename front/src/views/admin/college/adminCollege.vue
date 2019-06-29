<template>
    <div class="elform">
        <page-title title-text="学院管理"></page-title>
        <el-row>
            <el-col>
                <el-button type="primary" style="margin-left: 5%" @click="dialogVisible = true">新增学院</el-button>
                <el-button type="primary" style="margin-left: 5%" @click="dialogVisible = true">专业转院</el-button>

            </el-col>
        </el-row>
        <el-table

                :row-class-name="getindex"
                @expand-change="clickGet"
                :data="tableData"
                style="width: 90%;margin-left: 5%;margin-top: 10px">
            <el-table-column
                    label="详情"
                    type="expand"
                    >
                <template slot-scope="scope">
                    <el-row>
                        <el-col :span="8">
                            <el-form >
                                <el-form-item label="学院名称:">
                                    {{scope.row.collegeName}}
                                </el-form-item>
                                <el-form-item label="学院ID:">
                                    {{scope.row.collegeID}}
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
                    prop="collegeID"
                    label="学院ID">
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
                    fixed="right"
                    label="操作">
                <template slot-scope="scope">
                    <el-button type="text" size="small" @click="edits(scope.row)" >编辑</el-button>
                    <el-popover
                            placement="bottom"
                            width="200"
                            trigger="click">
                            <span style="text-align: center;font-size: 20px" >删除{{scope.row.collegeName}}</span><br>
                            <el-button @click="delDepartment(scope.row)" type="warning" style="float: right;margin-bottom: 15px ">确认删除</el-button>
                        <el-button style="margin-left: 10px" slot="reference" type="text" size="small" >删除</el-button>
                    </el-popover>

                </template>
            </el-table-column>
        </el-table>

        <!--****  编辑学院  ***-->
        <el-dialog
                :visible.sync="edit"
                width="30%"
                label="修改信息"
                class="change">
            <el-form label-width="90px" ref="editCollege" :model="editCollege" :rules="rules">
                <el-row style="margin-bottom: 10px">
                    <el-form-item label="学院编码:" prop="collegeID">
                        <el-col :span="16">
                            <el-input :disabled="editCollege.changeID" v-model="editCollege.collegeID" property="请输入学院编码"></el-input>
                        </el-col>
                        <el-col :offset="1" :span="4" style="line-height: 40px">
                            <el-button type="text"  @click="editCollege.changeID=false">修改</el-button>
                        </el-col>
                    </el-form-item>
                </el-row>
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
                        <el-button :disabled="editCollege.changeName===true&&editCollege.changeID===true" type="primary" @click="editDepartment(editCollege.collegeID,editCollege.collegeName)">立即创建</el-button>
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
                <el-row style="margin-bottom: 10px">
                        <el-form-item label="学院编码:" prop="collegeID">
                            <el-col :span="16">
                                <el-input v-model="createCollege.collegeID" property="请输入学院编码"></el-input>
                            </el-col>
                        </el-form-item>
                </el-row>
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


        <div class="page-btn " style=" float: right; font-size: 16px;color: #666;margin: 10px 5% 0 0">
            <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
            <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
            <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
        </div>

    </div>
</template>

<script>
    export default {
        name: "adminCollege",
        data() {
            return {
                rules:{
                    collegeName:[{ required: true, message: '请输入学院名称', trigger: 'blur' }],
                    collegeID:[{ required: true, message: '请输入学院ID', trigger: 'blur' }]

                },
                //新增学院
                createCollege:{
                    collegeName:"",
                    collegeID:""

                },

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
                    collegeID:"",
                    changeID:true,
                    changeName:true
                }
            }
        },
        methods:{
            changePage(page){
                this.page = page
                let cnt = {
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                //this.getCourseOutlineByTermId(cnt)
            },
            getDepartments(cnt){
                this.$admin.getDepartments(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
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
                    collegeID:row.collegeID
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
                            collegeID:this.createCollege.collegeID
                        }
                        //const loading = this.$loading({lock: true, text: '拼命加载中...', spinner: 'el-icon-loading'})
                        //添加学院
                        this.$admin.createDepartment(cnt,(res)=>{
                            if(res.data.rc === this.$util.RC.SUCCESS){
                                loading.close()
                                this.$message({type:'success',message:"添加成功"})

                            }else{
                                loading.close()
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
                this.editCollege.changeID=true
                this.editCollege.changeName=true
                this.edit=true
                this.editCollege.collegeName=row.collegeName
                this.editCollege.collegeID=row.collegeID

            },
            //编辑学院
            editDepartment(ID,NAME){
                this.$refs['editCollege'].validate((valid) => {
                        if (valid) {
                            let cnt={
                                collegeName:NAME,
                                collegeID:ID
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
            delDepartment(row){
                let cnt={
                    collegeName:row.collegeName,
                    collegeID:row.collegeID
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
