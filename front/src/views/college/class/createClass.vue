<template>
    <div>
        <page-title title-text="添加班级" />
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" style="width: 90%">
            <el-form-item label="班级名称" prop="className">
                <el-input v-model="ruleForm.className"></el-input>
            </el-form-item>

            <el-form-item label="所属专业" prop="majorId">
                <el-radio-group v-model="ruleForm.majorId" >
                    <el-row >
                        <el-col v-for="item in tableMajor" :key="item.majorId" :span="24">
                            <el-radio :label="item.majorId" style="margin-top: 5px">{{item.majorName}}</el-radio>
                        </el-col>
                    </el-row>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="级别" prop="classGrade">
                <el-radio-group v-model="ruleForm.classGrade">
                    <el-row >
                        <el-col :span="8" v-for="item in getDates()" :key="item">
                            <el-radio :label="item" style="margin-top: 5px">{{item}}</el-radio>
                        </el-col>
                    </el-row>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="人数" prop="peoNum">
                <el-input-number v-model="ruleForm.peoNum" size="small" :min="1" :max="55" label="描述文字"></el-input-number>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "create",
        data(){
            return{
                ruleForm:{
                    className:"",//班级名称
                    classAge:1,  //年纪
                    classGrade:"",//级别
                    majorName:"网络工程",//专业名称
                    majorId:"",//专业Id
                    peoNum:45//人数
                },
                rules:{
                    className: [{ required: true, message: '请输入班级名称', trigger: 'blur' },],
                    classAge: [{ required: true, message: '请输入班级名称', trigger: 'blur' },],
                    classGrade: [{ required: true, message: '请选择级别', trigger: 'blur' },],
                    majorId: [{ required: true, message: '请选择专业名称', trigger: 'blur' },],
                    peoNum: [{ required: true, message: '请输入人数', trigger: 'blur' },],
                },
                tableMajor:[{majorId:123456,majorName:"大数据科学与数据计算",collegeId:43},{majorId:1234564,majorName:"网络工程",collegeId:42},{majorId:1234567,majorName:"智能",collegeId:41},],
            }
        },
        methods:{

            getDates(){//获取年份
                let date=new Date()
                let year = date.getFullYear()
                return [year, year - 1, year - 2, year - 3, year - 4]
                //let classGrade=[year,year-1,year-2,year-3,year-4]
                //return classGrade
            },
            getMajorName(){

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



            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.createClass()
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            createClass(){
                let date=new Date()
                let month = date.getMonth()+1
                if(month>=9){
                    //判断classAge.
                }

                let cnt={
                    collegeId:this.$teacherInformation.collegeId,
                    className:this.ruleForm.className,
                    classGrade:this.ruleForm.classGrade,
                    majorId:this.ruleForm.majorId,
                    peoNum:this.ruleForm.peoNum,
                    classAge:this.ruleForm.classAge,
                }
                this.$college.createCollegeClass(cnt,(res)=> {
                    if (res.data.rc === this.$util.RC.SUCCESS) {
                        this.$message("新增成功，请等待教务处管理员审核")
                    }
                    else { this.$message("新增失败")}

                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
        },
        mounted(){
            this.getMajor()
        }
    }
</script>

<style scoped>

</style>
