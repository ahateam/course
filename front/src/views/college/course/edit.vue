<template>
        <div v-show="changeData">
            <page-title title-text="修改课程大纲"></page-title>
            <el-form ref="editCollegeCourse" :model="editCollegeCourse" label-width="85px" label-position="left" :rules="rules">
                <el-row :gutter="21">
                    <el-col :span="9" >
                        <el-form-item label="课程名称:" prop="courseName" >
                            <el-input  placeholder="请输入内容" v-model="editCollegeCourse.courseName" :disabled="disableForm.disCourseName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="9">
                        <el-form-item label="课程编号:" prop="courseCode">
                            <el-input  placeholder="请输入内容" v-model="editCollegeCourse.courseCode" :disabled="disableForm.disCourseName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                        <el-button type="text" v-show="disableForm.disCourseName===true"   @click="disableForm.disCourseName=false">修改</el-button>
                    </el-col>
                </el-row>

                <el-row :gutter="21">
                    <el-col :span="9" >
                        <el-form-item label="考核方式:" prop="assessmentMode" >
                            <el-select v-model="editCollegeCourse.assessmentMode" placeholder="请选择活动区域" :disabled="disableForm.assessmentMode">
                                <el-option label="考察" value="考察"></el-option>
                                <el-option label="考试" value="考试"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="9">
                        <el-form-item label="课程性质:" prop="courseNature">
                            <el-select v-model="editCollegeCourse.courseNature" placeholder="请选择活动区域" :disabled="disableForm.assessmentMode">
                                <el-option label="考察" value="考察"></el-option>
                                <el-option label="考试" value="考试"></el-option>
                            </el-select>                        </el-form-item>
                    </el-col>
                    <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                        <el-button type="text" v-show="disableForm.assessmentMode===true"   @click="disableForm.assessmentMode=false">修改</el-button>
                    </el-col>
                </el-row>

                <el-row :gutter="21">
                    <el-col :span="9">
                        <el-form-item label="上课专业:" prop="courseMajor" >
                            <el-select v-model="editCollegeCourse.courseMajor" placeholder="请选择活动区域" :disabled="disableForm.courseMajor">
                                <el-option label="考察" value="考察"></el-option>
                                <el-option label="考试" value="考试"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="9">
                        <el-form-item label="上课年纪:" prop="courseAge">
                            <el-select v-model="editCollegeCourse.courseAge" placeholder="请选择活动区域" :disabled="disableForm.courseMajor">
                                <el-option label="考察" value="考察"></el-option>
                                <el-option label="考试" value="考试"></el-option>
                            </el-select>                        </el-form-item>
                    </el-col>
                    <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                        <el-button type="text" v-show="disableForm.courseMajor===true"   @click="disableForm.courseMajor=false">修改</el-button>
                    </el-col>
                </el-row>

                <el-row :gutter="21">
                    <el-col :span="18">
                        <el-form-item label="课程学分" prop="courseCredit">
                            <el-rate v-model="editCollegeCourse.courseCredit" style="line-height: 50px" :disabled="disableForm.courseCredit" ></el-rate>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                        <el-button type="text" v-show="disableForm.courseCredit===true"   @click="disableForm.courseCredit=false">修改</el-button>
                    </el-col>
                </el-row>

                <el-row :gutter="21">
                    <el-col :span="18">
                        <el-form-item label="课程学时" prop="courseTime">
                            <el-slider v-model="editCollegeCourse.courseTime" show-input :min="18" :max="72" :step="9" :disabled="disableForm.courseTime" ></el-slider>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                        <el-button type="text" v-show="disableForm.courseTime===true"   @click="disableForm.courseTime=false">修改</el-button>
                    </el-col>
                </el-row>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('editCollegeCourse')">立即创建</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
                <span style="color: red">请谨慎修改</span>
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
                       this.editCourseOutline()
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },

            //修改大纲
            editCourseOutline(){
                let cnt={
                    courseCode:this.editCollegeCourse.courseCode,
                    courseName:this.editCollegeCourse.courseName,
                    assessmentMode:this.editCollegeCourse.assessmentMode,
                    courseNature:this.editCollegeCourse.courseNature,
                    courseMajor:this.editCollegeCourse.courseMajor,
                    courseAge:this.editCollegeCourse.courseAge,
                    courseCredit:this.editCollegeCourse.courseCredit,
                    courseTime:this.editCollegeCourse.courseTime,
                    courseCollege:"大数据"
                };
                console.log(cnt)
                this.$college.editCourseOutline(cnt,(res)=> {
                    if (res.data.rc === this.$util.RC.SUCCESS) {
                        this.$message("新增成功，请等待教务处管理员审核")
                    }
                    else { this.$message("新增失败")}

                })
            }
        },
        mounted(){},

        //强制页面渲染
        watch:{
            editCollegeCourse(){}
        }
    }
</script>

<style scoped>
    .el-button--text{
        color: red;
    }


</style>