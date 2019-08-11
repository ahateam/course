<template>
    <div style="margin-left: 5%;width: 85%" class="changeForm">
        <el-form ref="form" :model="editForm" label-width="72px" label-position="left" :rules="rules">
            <el-row>
                <el-col :span="10">
                    <el-form-item label="姓名:">
                        {{editForm.teacherName}}
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item label="工号:">
                        {{editForm.username}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="职称:">
                        {{editForm.teacherPosition}}
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item label="入职时间:">
                        {{new Date(parseInt(editForm.workTime)).toLocaleDateString()}}
                    </el-form-item>
                </el-col>
            </el-row>
            <!--<el-row>-->
                <!--<el-col :span="20">-->
                    <!--<el-form-item label="权限:">-->
                        <!--<el-radio-group v-model="editForm.adminId" :disabled="disabled.adminId">-->
                            <!--<el-radio  v-for="(item,index) in adminLabel" :label="item.adminId" :key="index">{{item.name}}</el-radio>-->
                        <!--</el-radio-group>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-col :span="4" style="line-height: 40px;color: #f56c6c">-->
                    <!--&lt;!&ndash;<el-button type="text"  @click="disabled.adminId=false">修改</el-button>&ndash;&gt;-->
                <!--</el-col>-->
            <!--</el-row>-->

            <el-row>
                <el-col :span="20">
                    <el-form-item label="所属学院:" >
                        {{$getCollegeName(editForm.collegeId)}}
                    </el-form-item>
                </el-col>
                <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                    <!--<el-button type="text"  @click="disabled.college=false">修改</el-button>-->
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="20">
                    <el-form-item label="电话:" prop="phoneNumber"  >
                        <el-input  placeholder="请输入内容" v-model="editForm.teacherPhone" :disabled="disabled.phone"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                    <el-button type="text"  @click="disabled.phone=false">修改</el-button>
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
        props:['editForm'],
        data(){
            var teacherPhone=(rule, value, callback) => {
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
                editForm:{},
                collegeId:"",
                tableCollege:"",
                adminLabel:[{name:"教务处",adminID:'0'},{name:"教务科",adminID:'1'},{name:"实验室",adminID:'2'},{name:"教师",adminID:'3'}],
                disabled:{adminId:true,college:true,phone:true},
                rules:{
                    teacherPhone:[ { validator: teacherPhone, trigger: 'blur' },],
                }
            }
        },
        computed:{
            getCollege(){
                return this.disabled.college
            },

        },
        methods:{
            onSubmit(){
                    this.$refs['form'].validate((valid) => {
                        if (valid) {
                           this.editSchoolTeacher()
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
            editSchoolTeacher(){
                let cnt={
                    collegeId:this.editForm.collegeId,
                    teacherName: this.editForm.teacherName,
                    username:this.editForm.username,
                    teacherPosition:this.editForm.teacherPosition,
                    teacherPhone:this.editForm.teacherPhone,
                    adminID:this.editForm.adminID,
                    email:"123@qq.com"
                }
                console.log(cnt)
                this.$admin.editSchoolTeacher(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.$message({
                            type:'success',
                            message:"修改成功"
                        })
                    }else{
                        this.$message({
                            type:'warning',
                            message:"修改失败"
                        })
                    }
                })
                this.close()

            },
            again(){
                this.editForm=this.form
                this.disabled.adminId=true
                this.disabled.college=true
                this.disabled.phone=true
            }
        },
        watch:{
            getCollege(){
               if(this.tableCollege===""){
                   let cnt ={
                       count:20,
                       offset:0
                   }
                   //获取选择学院
                   this.$admin.getDepartments(cnt,(res)=>{
                       if(res.data.rc === this.$util.RC.SUCCESS){
                           this.tableCollege = this.$util.tryParseJson(res.data.c)
                       }else{
                           this.tableCollege = []
                       }
                   })
               }

            }
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
