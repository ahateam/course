<template>
        <div  style="margin-left:30px">
            <page-title title-text="修改课程大纲"></page-title>
            <el-form ref="editCollegeCourse" :model="editCollegeCourse" label-width="85px" label-position="left" :rules="rules">
                <el-row :gutter="21">
                    <el-col :span="18" >
                        <el-form-item label="课程名称:"  >
                            {{editCollegeCourse.courseName}}
                        </el-form-item>
                    </el-col>

                    <!--<el-col :span="4" style="line-height: 40px;color: #f56c6c">-->
                        <!--<el-button type="text" v-show="disableForm.disCourseName===true"   @click="disableForm.disCourseName=false">修改</el-button>-->
                    <!--</el-col>-->
                </el-row>

                <el-row :gutter="21">
                    <el-col :span="9" >
                        <el-form-item label="考核方式:" prop="assessmentMode" >
                            <el-select v-model="editCollegeCourse.assessmentMode" placeholder="请选择考核方式" :disabled="disableForm.assessmentMode">
                                <el-option label="考察" value="考察"></el-option>
                                <el-option label="考试" value="考试"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="9">
                        <el-form-item label="课程性质" style="float: left" prop="courseNature" :disabled="disableForm.assessmentMode">
                            <el-select v-model="editCollegeCourse.courseNature" placeholder="请选择课程性质" :disabled="disableForm.assessmentMode">
                                <el-option :value="item" v-for="item in courseNature" :key="item"></el-option>

                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                        <el-button type="text" v-show="disableForm.assessmentMode===true"   @click="disableForm.assessmentMode=false">修改</el-button>
                    </el-col>
                </el-row>

                <el-row :gutter="21">
                    <!--<el-col :span="12">-->
                        <!--<el-form-item label="上课专业:" prop="courseMajor" >-->
                            <!--<el-select v-model="editCollegeCourse.courseMajor" placeholder="请选择活动区域" :disabled="disableForm.courseMajor">-->
                                <!--<el-option label="考察" value="考察"></el-option>-->
                                <!--<el-option label="考试" value="考试"></el-option>-->
                            <!--</el-select>-->
                        <!--</el-form-item>-->
                    <!--</el-col>-->
                    <el-col :span="18">
                        <el-form-item label="上课年纪:" prop="courseAge">
                            <el-select v-model="editCollegeCourse.courseAge" placeholder="请选择上课年纪" :disabled="disableForm.courseMajor">
                                <el-option
                                        v-for="item in 8"
                                        :key="item"
                                        :label="$getCourseAge(item)"
                                        :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                        <el-button type="text" v-show="disableForm.courseMajor===true"   @click="disableForm.courseMajor=false">修改</el-button>
                    </el-col>
                </el-row>
                <el-row :gutter="21">
                    <el-col :span="21">
                        <el-form-item label="上课专业:" prop="courseMajor" >
                            <el-checkbox-group v-model="editCollegeCourse.courseMajor" >
                                <el-checkbox v-for="item in tableMajor"  :label="item.majorId" :key="item.majorId" :disabled="disableForm.courseMajor">{{item.majorName}}</el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
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

                    assessmentMode: 10 ,//考核方式
                    courseNature :10 ,//课程性质
                    courseMajor: [123,456],// 上课专业
                    courseAge: 3 ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                },
                tableLevel:["大一上","大一下","大二上","大二下","大三上","大三下","大四上","大四下",],
                courseNature:["必修","选修"],
                disableForm:{
                    disCourseName:true,
                    assessmentMode:true,
                    courseMajor:true,
                    courseCredit:true,
                    courseTime:true
                },
                tableMajor:[{majorId:123,majorName:"大数据"},{majorId:456,majorName:"网络工程"},],
                rules:{
                    courseCode:[{ required: true, message: '请输入课程编码', trigger: 'blur' },],

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

            //修改开设课程
            editCourseOutline(){
                let cnt={
                    courseCode:this.editCollegeCourse.courseCode,
                    tableMajor:[{majorId:123,majorName:"大数据"},{majorId:456,majorName:"网络工程"},],
                    assessmentMode:this.editCollegeCourse.assessmentMode,
                    courseNature:this.editCollegeCourse.courseNature,
                    courseMajor:this.editCollegeCourse.courseMajor,
                    courseAge:this.editCollegeCourse.courseAge,
                    courseCredit:this.editCollegeCourse.courseCredit,
                    courseTime:this.editCollegeCourse.courseTime,
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

            },
            getMajor(){//获取专业
                let cnt={
                    collegeId: this.$store.state.teacherInformation.collegeId,
                    count: 10,
                    majorName: "0",
                    offset: 0
                }
                this.$admin.lookupSchoolMajor(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableMajor = this.$util.tryParseJson(res.data.c)
                        console.log(this.$util.tryParseJson(res))
                    }else{
                        this.tableMajor = []
                    }
                })
            },
        },
        mounted(){
            this.getMajor()

            let array=[]
            let a=this.editCollegeCourse.courseMajor.split(',')
            console.log( this.editCollegeCourse.courseMajor.split(','))
            a.map((val,index,arr)=>{
                array.push(parseInt(val))
            })
            this.editCollegeCourse.courseMajor=array
            //console.log(this.editCollegeCourse)
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
