<template>
    <div>
        <page-title title-text="新增课程"></page-title>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px" style="width: 90%">

            <el-row>
                <el-col :span="12">
                    <el-form-item label="课程名称" style="float: left" prop="courseCode">

                    <el-popover
                            v-model="chioceVisible"
                            placement="left"
                            width="600"
                            trigger="click">
                        <chioceOpen :collegeId="form.collegeId" ref="chioceTea" @transferRandom="chioceOpen" />
                        <el-button  slot="reference" type="text">选择课程</el-button>
                    </el-popover>
                    <!--<el-select :disabled="form.collegeId===''" v-model="form.openCourse" placeholder="请选择课程名称">-->
                    <!--<el-option v-for="(item,index) in courseData" :key="index" :label="item.courseName" :value="item"></el-option>-->
                    <!--</el-select>-->
                        {{form.courseName}}
                    </el-form-item>
                </el-col>

                <el-col :span="12">
                    <el-button  slot="reference" type="text">选择课程</el-button>
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
                        <el-option :value="item" v-for="item in courseNature" :key="item"></el-option>

                    </el-select>
                </el-form-item>
            </el-col>


            </el-row>

            <el-row>
                <el-col :span="12">
                    <el-form-item label="上课年纪" style="float: left"  prop="courseAge">

                        <el-select v-model="form.courseAge" placeholder="请选择上课年纪" >
                            <el-option
                                    v-for="item in 8"
                                    :key="item"
                                    :label="$getCourseAge(item)"
                                    :value="item">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="上课专业" style="float: left"  prop="classMajor">

                        <!--<el-select v-model="form.classMajor" placeholder="请选择上课年纪" size="mini">-->
                        <!--</el-select>-->
                        <el-checkbox-group v-model="form.classMajor" >
                            <el-checkbox v-for="item in tableMajor"  :label="item.majorId" :key="item.majorId">{{item.majorName}}</el-checkbox>
                        </el-checkbox-group>
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
    const cityOptions = ['上海', '北京', '广州', '深圳'];
    import chioceOpen from "./chioceOpen"
    export default {
        data() {
            return {
                tableCollege:"",//选择学院
                courseData:"",//选择学院的课程
                chioceVisible:false,
                form: {
                    collegeId:"",//开课学院
                    courseCode:"",//选择课程
                    courseName:'',//课程名称

                    assessmentMode: "" ,//考核方式
                    courseNature :"" ,//课程性质
                    classMajor: [],// 上课专业
                    courseAge:"" ,//上课年纪
                    courseCredit: 2,// 课程学分
                    courseTime: 36 ,//课程学时
                },
                tableLevel:["大一上","大一下","大二上","大二下","大三上","大三下","大四上","大四下",],
                courseNature:["必修","选修"],
                rules:{
                    courseCode:[{ required: true, message: '选择课程', trigger: 'blur' }],
                    assessmentMode:[{ required: true, message: '请选择考核方式', trigger: 'blur' }],
                    courseNature:[{ required: true, message: '请选择课程性质', trigger: 'blur' }],
                    classMajor:[{ required: true, message: '请选上课专业', trigger: 'blur' }],
                    courseAge:[{ required: true, message: '请选上课年纪', trigger: 'blur' }],
                    courseCredit:[{ required: true, message: '请选课程学分', trigger: 'blur' }],
                    courseTime:[{ required: true, message: '请选课程学时', trigger: 'blur' }],
                },

                tableMajor:[{majorId:123,majorName:"大数据"},{majorId:456,majorName:"网络工程"},],
                array:[],
                //选择专业
                checkAll: false,//是否全选
                checkedCities: [],
                cities: cityOptions,
                isIndeterminate: false

            }
        },
        methods: {
            chioceOpen(row){

                this.chioceVisible=false  //关闭弹框
                this.form.courseName=row.courseName
                this.form.courseCode=row.courseCode
                this.form.courseTime=row.courseTime
                console.log(row)
            },

            //验证表单
            submitForm(formName) {
                //console.log(this.form.classMajor)
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
                let classMajor=this.form.classMajor.join(",");//数组变为字符串
                //let arr=classMajor.split(",")  //字符串变为数组
                //console.log(arr)
                let cnt={
                    //后台自动加 courseId    大纲课程Id
                    collegeId:this.$store.state.teacherInformation.collegeId,//本学院ID 大纲学院
                    courseCode:this.form.courseCode,
                    //courseName:this.form.openCourse.courseName,
                    assessmentMode:0,//this.form.assessmentMode,
                    courseNature:0,//this.form.courseNature,
                    classMajor:classMajor,
                    courseAge:parseInt(this.form.courseAge),
                    courseCredit:this.form.courseCredit,
                    courseTime:this.form.courseTime,
                };
                console.log(cnt)
                this.$college.createCourseOutline(cnt,(res)=> {
                    if (res.data.rc === this.$util.RC.SUCCESS) {
                        this.$message("新增成功，请等待教务处管理员审核")
                    }
                    else { this.$message("新增失败")}

                })
            },

            getMajor(){//获取专业
               let cnt={
                   count: 10,
                   majorName: "0",
                   offset: 0,
                   collegeId:this.$teacherInformation.collegeId,
               }
                //console.log("this.$store.state.teacherInformation")
                //cnt.collegeId= this.$store.state.teacherInformation.collegeId
                //console.log(JSON.parse(sessionStorage.getItem("teacherInformation")))
                this.$admin.lookupSchoolMajor(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableMajor = this.$util.tryParseJson(res.data.c)
                        //console.log(this.$util.tryParseJson(res))
                    }else{
                        this.tableMajor = []
                    }
                })
            },



        },
        mounted(){
            this.getMajor()

        },
        components:{chioceOpen}
    }
</script>
