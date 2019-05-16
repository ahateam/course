<template>
    <el-form    label-width="100px"  label-position="left" size="mini" class="form"  >
        <el-form-item   :label=tableName[0].label label-width="80px">
            <el-select  :value="tableForm.teacher.label"  disabled></el-select>
        </el-form-item>

        <el-form-item   :label=tableName[1].label label-width="80px">
            <el-select  :label="tableForm.starWeek.label" v-model="starNum" @change="numChange(starNum)">
                <el-option  v-for="(num,index) in 18" :key="index" :label=num :value=num ></el-option>
            </el-select>
        </el-form-item>

        <el-form-item   :label=tableName[2].label label-width="80px">
            <el-select  :value="tableForm.endWeek.label" v-model="endNum"  >
                <el-option  v-for="(num,index) in endStarNum" :key="index" :label=num :value=num ></el-option>
            </el-select>
        </el-form-item>

        <el-form-item   :label=tableName[3].label label-width="80px">
            <el-select  :value="tableForm.weekNum.label"  disabled ></el-select>
        </el-form-item>

        <el-form-item   :label=tableName[4].label label-width="80px">
            <el-select  :value="tableForm.sectionsNum.label"  disabled ></el-select>
        </el-form-item>

        <el-form-item   :label=tableName[5].label label-width="80px">
            <el-select  :value="tableForm.SDWeek.label"  disabled ></el-select>
        </el-form-item>

        <el-form-item   :label=tableName[6].label label-width="80px">
            <el-select  :value="tableForm.courseLength.label"  disabled ></el-select>
        </el-form-item>

        <el-form-item   :label=tableName[7].label label-width="80px">
            <el-select  :value="tableForm.classroom.label"  disabled ></el-select>
        </el-form-item>

    </el-form>
</template>

<style scoped >
    .el-input__icon{
        height: 150%;
    }
    .el-input__inner{
        height: 20px;
    }

    .form{
        border: 0.1px solid #f1e7e7;width: 90%;padding-left: 10%;
    }
</style>

<script>
    export default {
        data() {
            return {
                starNum:"",
                endNum:"",
                endStarNum:[],
                tableForm:{
                    teacher:{value:"ssssss", label:"小名"},
                    starWeek:{value:"222", label:"2"},
                    endWeek:{value:"ss", label:"10"},
                    weekNum:{value:"ss", label:"xx"},
                    sectionsNum:{value:"ss", label:"xx"},
                    SDWeek:{value:"ss", label:"xx"},
                    courseLength:{value:"ss", label:"xx"},
                    classroom:{value:"ss", label:"xx"},
                },
                tableName:[
                    {label:"上课教师",value:"teacher"},
                    {label:"起始周",value:"starWeek"},
                    {label:"结束周",value:"endWeek"},
                    {label:"星期几",value:"weekNum"},
                    {label:"第几节",value:"sectionsNum"},
                    {label:"单双周",value:"SDWeek"},
                    {label:"课程长度",value:"courseLength"},
                    {label:"教室",value:"classroom"},
                    //"起始周","结束周","星期几","第几节","单双周","课程长度","教室"
                ],
            };
        },
        methods: {
            numChange(starNum){
              //  console.error("***************************************")

                for(;starNum<19;starNum++){
                    this.endStarNum.push(starNum)
                   // console.error("***************************************")
                }
            },
            // tableform(tablename){
            //     for(let i=0;this.tableName.length;i++)
            //     {
            //         if(tablename==="上课教师"){return this.tableForm.teacher.label}
            //         if(tablename==="起始周"){this.starNum= this.tableForm.starWeek.label}
            //         if(tablename==="结束周"){return this.tableForm.endWeek.label}
            //         if(tablename==="星期几"){return this.tableForm.weekNum.label}
            //         if(tablename==="第几节"){return this.tableForm.sectionsNum.label}
            //         if(tablename==="单双周"){return this.tableForm.SDWeek.label}
            //         if(tablename==="课程长度"){return this.tableForm.courseLength.label}
            //         if(tablename==="教室"){return this.tableForm.classroom.label}
            //     }
            // }
        },
        mounted(){
            this.starNum=this.tableForm.starWeek.label
            this.endNum=this.tableForm.endWeek.label
        }
    }
</script>