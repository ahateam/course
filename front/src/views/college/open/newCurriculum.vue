<template>
    <div>
        <page-title title-text="新增课程"></page-title>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px" label-position="left" style="width: 90%">


            <el-form-item label="课程名称" prop="courseName">
                <el-input v-model="form.courseName"></el-input>
            </el-form-item>
            <el-form-item label="建议课程学时:" prop="courseTime" >
                <el-select v-model="form.courseTime" >
                    <el-option
                            v-for="item in 72"
                            :key="item"
                            :value="item">
                    </el-option>
                </el-select>

            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>


</template>
<script>
    export default {
        data() {
            return {
                form: {
                    courseCode:"",//课程编码
                    courseName: "",// 课程名称
                    assessmentMode: "" ,//考核方式
                    courseNature :10 ,//课程性质
                    classMajor: "",// 上课专业
                    courseAge:"" ,//上课年纪
                    courseCredit: 2,// 课程学分
                    courseTime: 36 ,//课程学时
                },
                rules:{
                    courseCode:[{ required: true, message: '请输入课程编码', trigger: 'blur' },],
                    courseName:[{ required: true, message: '请输入课程名称', trigger: 'blur' }],
                    assessmentMode:[{ required: true, message: '请选择考核方式', trigger: 'blur' }],
                    courseNature:[{ required: true, message: '请选择课程性质', trigger: 'blur' }],
                    classMajor:[{ required: true, message: '请选上课专业', trigger: 'blur' }],
                    courseAge:[{ required: true, message: '请选上课年纪', trigger: 'blur' }],
                    courseCredit:[{ required: true, message: '请选课程学分', trigger: 'blur' }],
                    courseTime:[{ required: true, message: '请选课程学时', trigger: 'blur' }],
                }
            }
        },
        methods: {

            //验证表单
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //alert('submit!');
                        this.createCourseOutline()
                    } else {
                        console.log('请填写完整');
                        return false;
                    }
                });
            },

            //新增课程
            createCourseOutline(){
                let cnt={
                   // courseCode:parseInt(this.form.courseCode),
                    courseName:this.form.courseName,
                    collegeId:this.$teacherInformation.collegeId,
                    courseTime:this.form.courseTime
                    //collegeOpenExamStatus:"null"
                };
                console.log(cnt)
                this.$college.createCollegeOpen(cnt,(res)=> {
                    if (res.data.rc === this.$util.RC.SUCCESS) {
                        this.$message("新增成功，请等待教务处管理员审核")
                    }
                    else { this.$message("新增失败")}

                })
            }
        },
        mounted(){
            // console.error(this.form.type)
        }
    }
</script>
