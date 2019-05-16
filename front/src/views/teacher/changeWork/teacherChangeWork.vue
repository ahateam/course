<template>
    <el-form ref="form" :model="form" label-width="80px"  id="elform" size="mini" >
        <pageTitle title-text="申请变课"></pageTitle>

        <div style="height: 50px">
            <el-form-item label="学期" style="width:30% ;float: left">
                <el-cascader
                        v-model="Semester"
                        @change="handleChange">
                </el-cascader>
            </el-form-item>
            <el-form-item label="变课类型" style="width:30% ;float: left">
                <el-select
                        v-model="form.changeCurriculum"
                        @change="CurriculumChange(form.changeCurriculum)">
                    <el-option label="调课" value="adjustCurriculum"></el-option>
                    <el-option label="补课" value="repairCurriculum"></el-option>
                    <el-option label="停课" value="stopCurriculum"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="编号" style="width:30%;float: left  ">

            </el-form-item>
        </div>

        <div style="height: 50px">
            <el-form-item label="课程" style="width:30% ;float: left">
                <el-cascader
                        v-model="Semester"
                        @change="onSubmit">
                </el-cascader>
            </el-form-item>
            <el-form-item label="班级" style="width:30% ;float: left">
                <el-cascader
                        expand-trigger="hover"
                        v-model="Semester"
                        @change="onSubmit">
                </el-cascader>
            </el-form-item>
            <el-form-item label="时间" style="width:30%;float: left  ">
                <el-cascader
                        v-model="Semester"
                        @change="onSubmit">
                </el-cascader>
            </el-form-item>
        </div>

        <div style="width: 85%;;margin-left: 80px;height:440px;border:0.1px solid #f1e7e7;margin-bottom: 10px">
            <adjust v-if="adjustCurriculum"></adjust>
            <repair v-if="repairCurriculum"></repair>
            <stop v-if="stopCurriculum"></stop>
        </div>
        <el-form-item label="变动原因" >
            <el-input type="textarea" v-model="form.desc"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button>取消</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
    import adjust from "./adjustCurriculum"
    import repair from "./repairCurriculum"
    import stop from "./stopCurriculum"
    export default {
        data() {
            return {
                Semester:"",
                form: {
                    changeCurriculum:"adjustCurriculum",
                    Semester:"ss",
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                adjustCurriculum:true,
                repairCurriculum:false,
                stopCurriculum:false,
            }
        },
        methods: {
            onSubmit() {
               // console.log('submit!');
            },
            CurriculumChange(changeCurriculum){
                console.error()
                if(changeCurriculum==="adjustCurriculum") {
                    this.adjustCurriculum=true
                    this.repairCurriculum=false
                    this.stopCurriculum=false
                }
                if(changeCurriculum==="repairCurriculum") {
                    this.repairCurriculum=true
                    this.adjustCurriculum=false
                    this.stopCurriculum=false
                }
                if(changeCurriculum==="stopCurriculum") {
                    this.stopCurriculum=true
                    this.adjustCurriculum=false
                    this.repairCurriculum=false
                }
        },

        },
        components:{
            adjust,repair,stop
        }
    }
</script>
<style scoped>
    .el-form-item {
        margin-bottom: 10px;
    }
    .el-form-item__content{
        position:initial;
    }

    #elform{
        width:90%;border:0.1px solid #f1e7e7;margin-left: 5%
    }
</style>