<template>
    <div>
        <el-form label-width="110px" ref="form" :model="form"  label-position="left" :rules="rules" >
            <el-form-item label="实验大楼名称:" prop="labBuildName">
                <el-input v-model="form.labBuildName"></el-input>
            </el-form-item>
                <el-row>
                    <el-col :span="22">
                        <el-form-item label="楼层数:" prop="floorNum">
                            <el-slider
                                    :min="0"
                                    :max="7"
                                    v-model="form.floorNum"
                                    show-input>
                            </el-slider>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="22">
                        <el-form-item label="每层房间数:" prop="floorRoomNum">
                            <el-slider
                                    :min="0"
                                    :max="50"
                                    v-model="form.floorRoomNum"
                                    show-input>
                            </el-slider>
                        </el-form-item>
                    </el-col>
                </el-row>




            <el-form-item label="建成日期:" prop="labTime">
                <el-date-picker
                        v-model="form.labTime"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="timestamp">
                </el-date-picker>
            </el-form-item>
            {{form.labTime}}

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
                    labTime:"1564934400000",//建成日期
                    labBuildName:"",//大楼名称
                    floorNum:0,//楼层数
                    floorRoomNum:0,//每层房间数
                },
                rules:{
                    labBuildName:[{ min: 3, max: 15, message: '名称长度为3-15位', trigger: 'blur' },
                                    { required: true, message: '请输入大楼名称', trigger: 'blur' },],
                    floorNum:[{ required: true, message: '请输入楼层数', trigger: 'blur' },],
                    floorRoomNum:[{ required: true, message: '请输入楼层房间数', trigger: 'blur' },],
                    labTime:[{ required: true, message: '选择建成日期', trigger: 'blur' },],
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
                    floorRoomNum:parseInt(this.form.floorRoomNum),
                    labTime:this.form.labTime,
                }
                this.$admin.createLaborBuild(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.$message({type:'success',message:"创建成功"})
                    }else{
                        this.$message({type:'warning',message:"创建失败"})
                    }
                })
            }
        },
        mounted(){
        }
    }
</script>

<style scoped>

</style>
