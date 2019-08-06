<template>
    <div style="margin-left: 5%;width: 85%" class="changeForm">
        <page-title title-text="新增教师"></page-title>
        <el-form ref="createForm" :model="createForm" label-width="100px" label-position="left" :rules="rules">
            <el-row>
                <el-col :span="9">
                    <el-form-item label="姓名:" prop="teacherName">
                        <el-input  placeholder="请输入内容" v-model="createForm.teacherName" ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9" :offset="2">
                    <el-form-item label="工号:" prop="username">
                        <el-input  placeholder="请输入内容" v-model="createForm.username" ></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="9">
                    <el-form-item label="职称:" prop="teacherPosition">
                        <el-input  placeholder="请输入内容" v-model="createForm.teacherPosition" ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9" :offset="2">
                    <el-form-item label="入职时间:" prop="workTime">
                        <el-input  placeholder="请输入内容" v-model="createForm.workTime" ></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="20">
                    <el-form-item label="权限:" prop="adminId">
                        <el-radio-group v-model="createForm.adminId" >
                            <el-radio  v-for="(item,index) in adminLabel" :label="item.adminId" :key="index">{{item.name}}</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="20">
                    <el-form-item label="所属学院:" prop="collegeId">
                        <el-select v-model="createForm.collegeId"  placeholder="选择学院"  >
                            <el-option
                                    v-for="item in $store.state.tableCollege"
                                    :key="item.collegeName"
                                    :label="item.collegeName"
                                    :value="item.collegeId">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

            </el-row>

            <el-row>
                <el-col :span="20">
                    <el-form-item label="电话:" prop="teacherPhone"  >
                        <el-input  placeholder="请输入内容" v-model.number="createForm.teacherPhone" ></el-input>
                    </el-form-item>
                </el-col>

            </el-row>
            <span style="color: red">请谨慎修改</span>
            <el-form-item>
                <el-button style="float: right" type="primary" @click="onSubmit">修改</el-button>
                <el-button style="float: right;margin-right: 20px" @click="close()">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>


<script>
    export default {
        name: "edit",
        props:['form'],
        data(){
            var teacherPhone=(rule, value, callback) => {
                console.log(typeof  value)
                if (value === '') {
                    callback(new Error('请输电话'));
                } else if(value.toString().length!==11) {
                    callback(new Error('电话长度为11位'));
                }else if(typeof  value !=="number"){
                    callback(new Error('电话为数字'))
                }else{
                    callback()
                }
            }
            return{
                createForm:{
                    teacherPosition:"",
                    username:"",
                    teacherName:"",
                    workTime:"",
                    adminId:"",
                    teacherPhone: "",
                    collegeId:""
                },
                collegeId:"",
                tableCollege:"",
                adminLabel:[{name:"教师",adminId:'0'},{name:"学院",adminId:'2'},{name:"实验室",adminId:'3'},{name:"教务处",adminId:'1'},],
                rules:{
                    teacherPosition:[ { required: true, message: '请选择教师职称', trigger: 'blur' },],

                    username:[ { required: true, message: '请输入工号', trigger: 'blur' },
                        { min: 6, max: 11, message: '工号为6-11位', trigger: 'blur' },],

                    teacherName:[ { required: true, message: '请输入电话', trigger: 'blur' },
                        { min: 2, max: 5, message: '教师名称为2-5位', trigger: 'blur' },],

                    workTime:[ { required: true, message: '请选择入职时间', trigger: 'blur' },],

                    adminId:[ { required: true, message: '请选择权限', trigger: 'blur' },],

                    teacherPhone:[ { validator: teacherPhone, trigger: 'blur' },],

                    collegeId:[ { required: true, message: '请选择学院', trigger: 'blur' },],
                }
            }
        },
        computed:{

        },
        methods:{
            onSubmit(){
                this.$refs['createForm'].validate((valid) => {
                    if (valid) {
                        this.createCollegeTeacher()
                    } else {
                        this.$messages.message("warning","请填写完整")
                        return false;
                    }
                });
            },
            //关闭admin界面edit弹框
            close(){
                this.$emit('transferRandom')
            },
            createCollegeTeacher(){
                let cnt={
                    collegeId:this.createForm.collegeId,
                    teacherName: this.createForm.teacherName,
                    username:this.createForm.username,
                    teacherPosition:this.createForm.teacherPosition,
                    teacherPhone:this.createForm.teacherPhone,
                    adminId:this.createForm.adminId,

                }
                console.log(cnt)
                this.$college.createCollegeTeacher(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.$message({
                            type:'success',
                            message:"新增成功"
                        })
                    }else{
                        this.$message({
                            type:'warning',
                            message:"新增失败"
                        })
                    }
                })
                this.close()

            },
            again(){

            }
        },
        watch:{

        },
        mounted() {
            //disabled:{adminId:true,college:true,phone:true},


        }

    }
</script>

<style scoped>
    .changeForm/deep/ .el-form-item__label{
        color: #818691;
    }

    .el-button--text{
        color: red;
    }
</style>
