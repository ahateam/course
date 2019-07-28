<template>
    <div class="change" v-show="displayTable">
        <el-form :model="ruleForm[0]" status-icon :rules="rules" ref="ruleForm" v-show="displayTable"  >
            <el-table
                    :data="ruleForm"
                    border
                    class="tableWidthMargin">

                <el-table-column
                        prop="lecturerName"
                        label="实验室(中心名称)">
                    <template slot-scope="scope">
                        <el-form-item prop="lecturerName"  :rules="rules.lecturerName">
                            <el-input size="small" v-model="ruleForm[0].lecturerName" >
                            </el-input>
                        </el-form-item>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="labName"
                        label="实验室名称">
                    <template slot-scope="scope">
                        <el-form-item prop="labName"  :rules="rules.labName">
                            <el-input size="small" v-model="ruleForm[0].labName" :placeholder="scope.row.labName"></el-input>
                        </el-form-item>

                    </template>
                </el-table-column>
                <el-table-column
                        prop="labSeat"
                        label="座位数(最大容纳人数)">
                    <template slot-scope="scope">
                    <span v-if="!scope.row.ifShow">
                        <el-form-item prop="labSeat"  :rules="rules.labSeat">
                            <el-select v-model="ruleForm[0].labSeat" :placeholder="ruleForm[0].labSeat" size="mini" v-if="show">
                                <el-option
                                        v-for="item in peopleNum"
                                        :key="item.value"
                                        :value="item">
                                </el-option>
                            </el-select>
                        </el-form-item>

                    </span>
                    </template>
                </el-table-column>

                <el-table-column
                        prop="labArea"
                        label="面积">
                    <template slot="header" slot-scope="scope">
                        面积/m<sup>2</sup>
                    </template>
                    <el-form-item prop="labArea"  :rules="rules.labArea">
                        <el-input size="small" v-model="ruleForm[0].labArea" >
                        </el-input>
                    </el-form-item>

                </el-table-column>


                <el-table-column
                        label="地点"
                        header-align="center">

                    <el-table-column
                            prop="labBuildName"
                            label="实验室大楼" >

                    </el-table-column>

                    <el-table-column
                            prop="labRoomNum"
                            label="房间号"
                    >
                    </el-table-column>
                </el-table-column>


            </el-table>

            <el-table
                    class="tableWidthMargin"
                    :data="ruleForm"
                    border
                  >
                <el-table-column
                        prop="labTime"
                        label="建成时间">
                    <template slot-scope="scope">
                        {{new Date(parseInt(scope.row.labTime)).toLocaleDateString()}}
                    </template>

                </el-table-column>
                <el-table-column
                        prop="collegeName"
                        label="管理部门">
                    <template  slot-scope="scope">
                        <span v-if="!scope.row.ifShow">
                            <el-select v-model="scope.row.collegeId" :placeholder="scope.row.collegeName" size="mini" >
                                <el-option
                                        v-for="item in tableCollege"
                                        :key="item.collegeName"
                                        :label="item.collegeName"
                                        :value="item.collegeId">
                                </el-option>
                            </el-select>
                        </span>
                    </template>
                </el-table-column>
                <el-table-column
                        label="面向专业">
                    <!--<el-input size="mini"></el-input>-->
                </el-table-column>

                <el-table-column
                        label="负责人"
                        header-align="center">

                    <el-table-column
                            label="姓名" prop="teacherName">

                    </el-table-column>

                    <el-table-column
                            label="工号" prop="username">

                    </el-table-column>

                    <el-table-column
                            label="手机号" prop="teacherPhone">

                    </el-table-column>
                </el-table-column>

            </el-table>
        </el-form>
        <el-row>
            <el-col :span="2" :offset="18">
                <el-button  type="primary" @click="submitForm()">修改</el-button>
            </el-col>
            <el-col :span="2" :offset="1">
                <el-button >取消</el-button>
            </el-col>
        </el-row>

    </div>
</template>

<script>
    export default {
        name: "newRoom",
        props:['ruleForm'],
        data(){
            return{
                show:true,
                tableData:[{}],
                // ruleForm:{
                //     pass:"",
                //     time:"",//建成时间
                //     peopleNum:"",
                //     roomNum:"",
                //     floorNum:"",
                //     floorRoomNum:""
                // },
                peopleNum:['10','15','20','25','30','35','40','45','50'],
                rules: {
                    labName: [
                        {  required: true, message: '实验室名称',},
                        {  max: 15, message: '长度在15 个字符内', trigger: 'blur' }

                    ],
                    labArea:[
                        {  required: true, message: '实验室面积',},

                    ],
                    lecturerName:[
                        {  required: true, message: '实验中心名称',},

                    ],
                    labSeat:[
                        {  required: true, message: '座位数',},

                    ],
                },
                displayTable:true,//强制刷新界面
                tableCollege:""
            }

        },
        methods:{

            submitForm() {
                this.$refs["ruleForm"].validate((valid) => {
                    if (valid) {
                        this.editSchoolLabor()
                    } else {
                        this.$message({
                            type:"warning",
                            message:"请输入完整"
                        })
                        return false;
                    }
                });
            },
            editSchoolLabor(){

                let cnt={
                    labName:this.ruleForm[0].labName,
                    labId: this.ruleForm[0].labId,
                    labSeat: this.ruleForm[0].labSeat,
                    labArea:this.ruleForm[0].labArea,
                    lecturerName:this.ruleForm[0].lecturerName,
                }
                console.log(this.ruleForm)
                console.log(cnt)

                this.$admin.editSchoolLabor(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.$message({
                            type:"success",
                            message:"编辑成功"
                        })
                    }else{
                        this.$message({
                            type:"warning",
                            message:"编辑失败"
                        })
                    }

                })
            },



            //如07   则需把7变为07
            // roomNums(item){
            //     if(item<10){
            //         this.ruleForm.roomNum="0"+item
            //     }
            // }
        },
        mounted(){
        },
        watch:{
            ruleForm(){
                //强制渲染表格
                this.displayTable=false
                this.displayTable=true
            }

        }
    }
</script>

<style scoped>
    .el-date-editor.el-input, .el-date-editor.el-input__inner /deep/ {
        width: 128px;
    }

    .el-input__icon /deep/ {
        width: 0;
        height: 0;
    }

    .el-input--prefix .el-input__inner /deep/ {
        padding-left: 30px 0;
    }

    .change /deep/ .el-input__inner{
        padding: 0 0 0 7px;
    }
</style>
