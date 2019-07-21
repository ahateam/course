<template>
        <div  style="margin-left:30px">
            <page-title title-text="修改课程大纲"></page-title>
            <el-form ref="editCollegeCourse" :model="editCollegeCourse" label-width="85px" label-position="left" :rules="rules">
                <el-row :gutter="21">
                    <el-col :span="15" >
                        <el-form-item label="课程名称:" prop="courseName" >
                            <el-input  placeholder="请输入内容" v-model="editCollegeCourse.courseName" :disabled="disableForm.disCourseName"></el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :offset="3" :span="4" style="line-height: 40px;color: #f56c6c">
                        <el-button type="text" v-show="disableForm.disCourseName===true"   @click="disableForm.disCourseName=false">修改</el-button>
                    </el-col>
                </el-row>



                <span style="color: red">请谨慎修改</span>
                <el-row>
                    <el-col :offset="14">
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('editCollegeCourse')"
                                       :disabled="disableForm.disCourseName===true&&disableForm.assessmentMode===true&&
                            disableForm.courseMajor===true&&
                            disableForm.courseCredit===true&&disableForm.courseTime===true">
                                确认</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>



</template>

<script>
    export default {
        name: "edit",
        props:['editCollegeCourse'],
        data(){
            return{
                changeData:true,
                form: {
                    courseCode:10,//课程编码
                    courseName: 15,// 课程名称
                    assessmentMode: 10 ,//考核方式
                    courseNature :10 ,//课程性质
                    courseMajor: 10,// 上课专业
                    courseAge: 3 ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                },
                disableForm:{
                    disCourseName:true,
                    assessmentMode:true,
                    courseMajor:true,
                    courseCredit:true,
                    courseTime:true
                },
                rules:{
                    courseCode:[{ required: true, message: '请输入课程编码', trigger: 'blur' },],
                    courseName:[{ required: true, message: '请输入课程名称', trigger: 'blur' }],
                    assessmentMode:[{ required: true, message: '请选择考核方式', trigger: 'blur' }],
                    courseNature:[{ required: true, message: '请选择课程性质', trigger: 'blur' }],
                    courseMajor:[{ required: true, message: '请选上课专业', trigger: 'blur' }],
                    courseAge:[{ required: true, message: '请选上课年纪', trigger: 'blur' }],
                    courseCredit:[{ required: true, message: '请选课程学分', trigger: 'blur' }],
                    courseTime:[{ required: true, message: '请选课程学时', trigger: 'blur' }],
                }

            }
        },
        methods:{
            //表单验证
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                       this.editOpen()
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },

            //修改开设课程
            editOpen(){
                let cnt={
                    courseCode:this.editCollegeCourse.courseCode,
                    courseName:this.editCollegeCourse.courseName,
                    collegeID:"123456"
                };
                this.$college.editCollegeOpen(cnt,(res)=> {
                    if (res.data.rc === this.$util.RC.SUCCESS) {
                        this.$message("新增成功，请等待教务处管理员审核")
                    }
                    else { this.$message("新增失败")}

                })
            },

            //重置表单
            again(){
                this.disableForm={
                        disCourseName:true,
                        assessmentMode:true,
                        courseMajor:true,
                        courseCredit:true,
                        courseTime:true
                };
                this.$refs["editCollegeCourse"].clearValidate();

            }
        },
        mounted(){

        },


        //强制页面渲染
        watch:{
            editCollegeCourse(){
                this.changeData=false
                this.changeData=true
            }
        }
    }
</script>

<style scoped>
    .el-button--text{
        color: red;
    }


</style>
