<template>
    <div>
        <page-title title-text="新增课程"></page-title>
        <el-form ref="form" :model="form" label-width="80px" style="width: 90%">
            <el-form-item label="课程名称">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="课程编码">
                <el-input v-model="form.name"></el-input>
            </el-form-item>



            <el-row>
            <el-col :span="12">
            <el-form-item label="考核方式" style="float: left" >
                <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
            </el-col>

            <el-col :span="12">
                <el-form-item label="课程性质" style="float: left" >
                    <el-select v-model="form.region" placeholder="请选择活动区域">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
            </el-col>


            </el-row>

            <el-row>
                <el-col :span="12">
                    <el-form-item label="上课年纪" style="float: left" >
                        <el-select v-model="form.region" placeholder="请选择活动区域">
                            <el-option label="区域一" value="shanghai"></el-option>
                            <el-option label="区域二" value="beijing"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12">
                    <el-form-item label="课程学分">
                        <el-rate v-model="form.type" style="line-height: 50px" ></el-rate>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-form-item label="课程学时">
                <el-slider v-model="form.resource" show-input :min="18" :max="72" :step="9"  ></el-slider>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
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
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: 0,
                    resource:18,
                    desc: ''
                }
            }
        },
        methods: {
            onSubmit() {
                // console.log('submit!');
                // console.error(this.form.type)

            }
        },
        mounted(){
            // console.error(this.form.type)
        }
    }
</script>