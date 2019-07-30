<template>
    <div class="elform">
        <page-title title-text="专业管理"></page-title>
        <el-row>
            <el-col>
                <el-button type="primary" class="buttonMarginLeft" @click="dialog.createMajor = true">新增专业</el-button>
                <!--<el-button type="primary" class="buttonMarginLeft" @click="dialogVisible = true">专业转院</el-button>-->
            </el-col>
        </el-row>
        <el-table
                :row-class-name="getindex"

                :data="tableData"
                class="tableWidthMargin">

            <el-table-column
                    label="ID "
                    type="index"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="majorName"
                    label="专业名称">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="lookup.majorName"
                            clearable
                            @change="lookupMajor()"
                            placeholder="专业名称"/>
                </template>
            </el-table-column>
            <el-table-column
                    prop="collegeName"
                    label="所属学院">
            </el-table-column>
            <el-table-column
                    align="center"
                    label="操作">
                <template slot-scope="scope">
                    <el-button type="text" size="small" @click="edits(scope.row)" >编辑</el-button>
                    <el-button @click="delCourse(scope.row)" style="margin-left: 10px" type="text" size="mini" >删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--  删除专业   -->
        <el-dialog
                :visible.sync="dialog.delMajor"
                title="del.collegeName"
                width="30%">

        </el-dialog>

        <!--****  编辑专业  ***-->
        <el-dialog
                :visible.sync="dialog.editMajor"
                width="30%"
                title="修改信息"
                class="change">
            <el-form label-width="90px"  label-position="left" ref="editMajorForm" :model="editMajorForm" :rules="rules">
                <el-form-item label="专业名称:" prop="majorName" >
                    <el-col :span="16">
                        <el-input :disabled="disable.majorName" v-model="editMajorForm.majorName" property="请输入专业名称"></el-input>
                    </el-col>
                    <el-col :offset="1" :span="4" style="line-height: 40px">
                        <el-button type="text"  @click="disable.majorName=false">修改</el-button>
                    </el-col>
                </el-form-item>
                <el-form-item label="管理学院:" prop="collegeId" >
                    <el-col :span="16">
                        <el-select :disabled="disable.collegeId" v-model="editMajorForm.collegeId" :label="editMajorForm.collegeName" :placeholder="editMajorForm.collegeName" size="mini" >

                            <el-option
                                    v-for="item in this.$store.state.tableCollege"
                                    :key="item.collegeName"
                                    :label="item.collegeName"
                                    :value="item.collegeId">
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :offset="1" :span="4" style="line-height: 40px">
                        <el-button type="text"  @click="disable.collegeId=false">修改</el-button>
                    </el-col>
                </el-form-item>
                <el-row style="margin-top: 30px">
                    <el-form-item>
                        <el-button :disabled="disable.majorName===true&&disable.collegeId===true" @click="editMajor" type="primary" >立即创建</el-button>
                        <el-button @click="dialog.editMajor=false">取消</el-button>
                    </el-form-item>
                </el-row>
            </el-form>
        </el-dialog>


        <!--** 新增专业 *-->
        <el-dialog
                :visible.sync="dialog.createMajor"
                title="新增专业"
                width="30%">
            <el-form label-width="90px"  label-position="left" ref="createMajorForm" :model="editMajorForm" :rules="rules">
                <el-form-item label="专业名称:" prop="majorName" >
                    <el-col :span="16">
                        <el-input  v-model="editMajorForm.majorName" property="请输入专业名称"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="管理学院:" prop="collegeId" >
                    <el-col :span="16">
                        <el-select  v-model="editMajorForm.collegeId" :placeholder="editMajorForm.collegeName" size="mini" >
                            <el-option
                                    v-for="item in this.$store.state.tableCollege"
                                    :key="item.collegeName"
                                    :label="item.collegeName"
                                    :value="item.collegeId">
                            </el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
                <el-row style="margin-top: 30px">
                    <el-form-item>
                        <el-button @click="createMajor()" type="primary" >立即创建</el-button>
                        <el-button @click="dialog.createMajor=false">取消</el-button>
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
                tableCollege:"",
                dialog:{//弹框
                    createMajor:false,
                    editMajor:false,
                    delMajor:false,
                },
                editMajorForm:{//编辑弹框内信息
                    majorId:"",
                    majorName:'',
                    collegeId:'',
                    collegeName:""
                },
                rules:{
                    majorName:[{ required: true, message: '请输入专业名称', trigger: 'blur' }],
                    collegeId:[{ required: true, message: '请选择专业ID', trigger: 'blur' }]

                },
                lookup:{
                    majorName:"",
                    collegeId:""
                },
                disable:{
                    majorName:true,
                    collegeId:true
                },
                tableData:[{
                    collegeName:"大数据学院",//学院名称
                    collegeId:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:"人工智能"
                },{
                    collegeName:"理学院",//学院名称
                    collegeId:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:"人工智能"

                },{
                    collegeName:"大数据学院",//学院名称
                    collegeId:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:"人工智能"

                }],
            }
        },
        methods:{
            //查询专业
            lookupMajor(){

            },

            changePage(nextCnt){
                this.getMajor(nextCnt)
            },

            //获取学院
            getMajor(cnt){
                this.$admin.getDepartments(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                        console.log(this.$util.tryParseJson(res))
                    }else{
                        this.tableData = []
                    }
                    //判断是否到达最后一页
                    this.$refs.nextPage.judge(this.tableData.length)
                })
            },

            //获取每行的索引
            getindex({row, rowIndex}){
                row.index = rowIndex;
            },

            // 新增学院
            // createDepartment(){
            //     //表单验证
            //     this.$refs['createCollege'].validate((valid) => {
            //         if (valid) {
            //             let cnt={
            //                 collegeName:this.createCollege.collegeName,
            //                 // collegeId:this.createCollege.collegeId
            //             }
            //             //const loading = this.$loading({lock: true, text: '拼命加载中...', spinner: 'el-icon-loading'})
            //             //添加学院
            //             this.$admin.createDepartment(cnt,(res)=>{
            //                 if(res.data.rc === this.$util.RC.SUCCESS){
            //                     //loading.close()
            //                     this.$message({type:'success',message:"添加成功"})
            //
            //                 }else{
            //                     //loading.close()
            //                     this.$message({type:'warning',message:"添加失败"})
            //                 }
            //             })
            //         } else {
            //             this.$message({type:'warning',message:"请输入完整信息"})
            //
            //             return false;
            //         }
            //     });
            // },

            //点击编辑按钮
            edits(row){
                this.disable.majorName=true//禁用
                this.disable.collegeId=true//禁用
                this.editMajorForm.majorId=row.majorId
                this.editMajorForm.majorName=row.majorName
                this.editMajorForm.collegeName=row.collegeName
                this.editMajorForm.collegeId=row.collegeId
                this.dialog.editMajor=true


            },
            //编辑专业
            editMajor(){
                this.$refs['editMajorForm'].validate((valid) => {
                    if (valid) {
                        console.error(444)
                        let cnt={
                            majorId:this.editMajorForm.majorId,
                            majorName:this.editMajorForm.majorName,
                            collegeId:this.editMajorForm.collegeId
                        }
                        //const loading = this.$loading({lock: true, text: '拼命加载中...', spinner: 'el-icon-loading'})
                        this.$admin.editMajor(cnt,(res)=>{
                            if(res.data.rc === this.$util.RC.SUCCESS){
                                this.$message({type:'success',message:"修改成功"})
                            }
                            else{
                                this.$message({type:'warning',message:"修改失败"})

                            }
                        })
                    }
                    else{console.error(445555555555554)
                        this.$message({type:'warning',message:"请输入完整信息"})
                        return false
                    }
                })
            },
            createMajor(){
                console.log("createMajorForm")
                this.$refs['createMajorForm'].validate((valid) => {
                    console.log("createMajorForm")
                    if (valid) {
                        let cnt={
                            majorId:this.editMajorForm.majorId,
                            majorName:this.editMajorForm.majorName,
                            collegeId:this.editMajorForm.collegeId
                        }
                        //const loading = this.$loading({lock: true, text: '拼命加载中...', spinner: 'el-icon-loading'})
                        this.$admin.editMajor(cnt,(res)=>{
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
                        return false;
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
            this.getMajor(cnt)
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
