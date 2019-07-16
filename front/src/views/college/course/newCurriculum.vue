<template>
    <div>
        <page-title title-text="新增课程"></page-title>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px" style="width: 90%">

            <el-row>
                <el-col :span="12">
                    <el-form-item label="开课学院" style="float: left" prop="openCollege">
                        <el-select v-model="form.openCollege" placeholder="选择学院">
                            <el-option @change="getCollegeOpen(item)" v-for="(item,index) in tableCollege" :key="index" :label="item.collegeName" :value="item"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12">
                    <el-form-item label="课程名称" style="float: left" prop="openCourse">
                        <el-select v-model="form.openCourse" placeholder="请选择课程名称">
                            <el-option v-for="(item,index) in courseData" :key="index" :label="item.courseName" :value="item"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>


            </el-row>


            <el-row>
            <el-col :span="12">
            <el-form-item label="考核方式" style="float: left" prop="assessmentMode">
                <el-select v-model="form.assessmentMode" placeholder="请选择考核方式">
                    <el-option label="考察" value="考察"></el-option>
                    <el-option label="考试" value="考试"></el-option>
                </el-select>
            </el-form-item>
            </el-col>

            <el-col :span="12">
                <el-form-item label="课程性质" style="float: left" prop="courseNature">
                    <el-select v-model="form.courseNature" placeholder="请选择课程性质">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
            </el-col>


            </el-row>

            <el-row>
                <!--<el-col :span="12">-->
                    <!--<el-form-item label="上课专业" style="float: left" prop="courseMajor" >-->
                        <!--<el-select v-model="form.courseMajor" placeholder="请选择活动区域">-->
                            <!--<el-option label="区域一" value="shanghai"></el-option>-->
                            <!--<el-option label="区域二" value="beijing"></el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
                <el-col :span="12">
                    <el-form-item label="上课年纪" style="float: left"  prop="courseAge">
                        <el-select v-model="form.courseAge" placeholder="请选择上课年纪">
                            <el-option label="区域一" value="shanghai"></el-option>
                            <el-option label="区域二" value="beijing"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="课程学分" prop="courseCredit">
                        <el-rate v-model="form.courseCredit" style="line-height: 50px" ></el-rate>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-form-item label="课程学时" prop="courseTime">
                <el-slider v-model="form.courseTime" show-input :min="18" :max="72" :step="9"  ></el-slider>
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
                tableCollege:"",//选择学院
                courseData:"",//选择学院的课程

                form: {
                    openCollege:"",//开课学院
                    openCourse:"",//选择课程

                    assessmentMode: "" ,//考核方式
                    courseNature :10 ,//课程性质
                    courseMajor: "",// 上课专业
                    courseAge:"" ,//上课年纪
                    courseCredit: 2,// 课程学分
                    courseTime: 36 ,//课程学时
                },
                rules:{
                    openCollege:[{ required: true, message: '请选择开课学院', trigger: 'blur' },],
                    openCourse:[{ required: true, message: '请选择课程', trigger: 'blur' }],
                    assessmentMode:[{ required: true, message: '请选择考核方式', trigger: 'blur' }],
                    courseNature:[{ required: true, message: '请选择课程性质', trigger: 'blur' }],
                    courseMajor:[{ required: true, message: '请选上课专业', trigger: 'blur' }],
                    courseAge:[{ required: true, message: '请选上课年纪', trigger: 'blur' }],
                    courseCredit:[{ required: true, message: '请选课程学分', trigger: 'blur' }],
                    courseTime:[{ required: true, message: '请选课程学时', trigger: 'blur' }],
                },

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

            //新增大纲
            createCourseOutline(){
                let cnt={
                    collegeId:this.form.openCollege.collegeId,
                    collegeName:this.form.openCollege.collegeName,

                    courseCode:this.form.openCourse.courseCode,
                    courseName:this.form.openCourse.courseName,
                    assessmentMode:this.form.assessmentMode,
                    courseNature:this.form.courseNature,
                    courseMajor:this.form.courseMajor,
                    courseAge:this.form.courseAge,
                    courseCredit:this.form.courseCredit,
                    courseTime:this.form.courseTime,
                    courseCollegeId:"上课学院",
                    courseCollegeName:"上课学院",
                };
                this.$college.createCollegeOpen(cnt,(res)=> {
                    if (res.data.rc === this.$util.RC.SUCCESS) {
                        this.$message("新增成功，请等待教务处管理员审核")
                    }
                    else { this.$message("新增失败")}

                })
            },
            //获取开设课程
            getCollegeOpen(item){
                let cnt={
                    count:50,
                    offset:0,
                    collegeId:item.collegeId
                }

                this.$college.getCollegeOpen(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.courseData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.courseData = []
                    }
                })
            },
        },
        mounted(){
            // 获取选择学院
            let cns ={
                count:20,
                offset:0
            }
            this.$admin.getDepartments(cns,(res)=>{
                if(res.data.rc === this.$util.RC.SUCCESS){
                    this.tableCollege = this.$util.tryParseJson(res.data.c)
                }else{
                    this.tableCollege = []
                }
            })
        }
    }
</script>