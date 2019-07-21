<template>
    <div>
        <el-form label-width="110px" ref="form" :model="form"  label-position="left" :rules="rules" >
            <el-form-item label="实验大楼名称:" prop="labBuildName">
                <el-input v-model="form.labBuildName"></el-input>
            </el-form-item>

            <el-form-item label="楼层数:" prop="floorNum">
                <el-slider
                        :min="0"
                        :max="7"
                        v-model="form.floorNum"
                        show-input>
                </el-slider>
            </el-form-item>

            <el-form-item label="每层房间数:" prop="floorRoomNum">
                <el-slider
                        :min="0"
                        :max="50"
                        v-model="form.floorRoomNum"
                        show-input>
                </el-slider>
            </el-form-item>
        </el-form>
        <el-button type="primary"  @click="submitForm('form')" style="margin-left: 80%">确认</el-button>
    </div>
</template>

<script>
    export default {
        name: "createBuild",
        data(){
            return{
                form:{
                    labBuildName:"",//大楼名称
                    floorNum:0,//楼层数
                    floorRoomNum:0,//每层房间数
                },
                rules:{
                    labBuildName:[{ min: 3, max: 15, message: '名称长度为3-15位', trigger: 'blur' },
                                    { required: true, message: '请输入大楼名称', trigger: 'blur' },],
                    floorNum:[{ required: true, message: '请输入楼层数', trigger: 'blur' },],
                    floorRoomNum:[{ required: true, message: '请输入楼层房间数', trigger: 'blur' },],
                }
            }
        },
        methods:{

            //验证表单
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.createLaborBuild()
                    } else {
                        this.$message({type:'warning',message:"请输入完整信息"})
                        return false;
                    }
                });
            },
            createLaborBuild(){
                let cnt={
                    labBuildName:this.form.labBuildName,
                    floorNum:parseInt(this.form.floorNum),
                    floorRoomNum:parseInt(this.form.floorRoomNum)
                }
                this.$admin.createLaborBuild(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.$message({type:'success',message:"创建成功"})
                    }else{
                        this.$message({type:'warning',message:"创建失败"})
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>