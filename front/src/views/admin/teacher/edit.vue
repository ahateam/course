<template>
    <div style="margin-left: 5%;width: 85%" class="changeForm">
        <el-form ref="form" :model="form" label-width="72px" label-position="left" :rules="rules">
            <el-row>
                <el-col :span="10">
                    <el-form-item label="姓名:">
                        {{form.name}}
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item label="工号:">
                        {{form.name}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="职称:">
                        {{form.name}}
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item label="入职时间:">
                        {{form.name}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="20">
                    <el-form-item label="权限:">
                        <el-radio-group v-model="form.name" :disabled="disabled.adminId">
                            <el-radio  v-for="(item,index) in adminLabel" :label="item.adminId" :key="index">{{item.name}}</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
                <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                    <el-button type="text"  @click="disabled.adminId=false">修改</el-button>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="20">
                    <el-form-item label="所属学院:" >
                        <el-select v-model="form.region" :disabled="disabled.college" placeholder="请选择学院" >
                            <el-option label="区域二" value="beijing"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                    <el-button type="text"  @click="disabled.college=false">修改</el-button>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="20">
                    <el-form-item label="电话:" prop="phoneNumber"  >
                        <el-input  placeholder="请输入内容" v-model="form.phoneNumber" :disabled="disabled.phone"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="4" style="line-height: 40px;color: #f56c6c">
                    <el-button type="text"  @click="disabled.phone=false">修改</el-button>
                </el-col>
            </el-row>

            <el-form-item label="活动时间">
                <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                    <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
                </el-col>
            </el-form-item>
            <span style="color: red">请谨慎修改</span>
        </el-form>
    </div>
</template>


<script>
    export default {
        name: "edit",
        data(){
            return{
                form: {
                    phoneNumber:123,
                    name: '1',
                },
                adminLabel:[{name:"教师",adminId:'0'},{name:"学院",adminId:'2'},{name:"实验室",adminId:'3'},{name:"教务处",adminId:'1'},],
                disabled:{adminId:true,college:true,phone:true},
                rules:{phoneNumber:[{ min: 11, max: 11, message: '电话长度为11位', trigger: 'blur' },]}
            }
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