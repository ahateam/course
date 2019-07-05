<template>
    <div>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"   >
            <el-table
                    :data="tableData"
                    border
                    style="width: 90%;margin-left: 5%;margin-top: 10px">

                <el-table-column
                        prop="lecturerName"
                        label="实验室(中心名称)">
                </el-table-column>
                <el-table-column
                        prop="workNum"
                        label="实验室名称">
                </el-table-column>
                <el-table-column
                        prop="position"
                        label="座位数(最大容纳人数)">
                    <template slot-scope="scope">
                    <span v-if="!scope.row.ifShow">
                        <el-select v-model="ruleForm.peopleNum" placeholder="请选择" size="mini" v-if="show">
                            <el-option
                                    v-for="item in peopleNum"
                                    :key="item.value"
                                    :value="item">
                            </el-option>
                        </el-select>
                    </span>
                    </template>
                </el-table-column>

                <el-table-column
                        prop="subordinateDepartments"
                        label="面积">
                    <el-input size="mini"></el-input>
                </el-table-column>

                <el-table-column
                        prop="subordinateDepartments"
                        label="地点"
                        header-align="center">

                    <el-table-column
                            prop="position"
                            label="实验室大楼"
                    >
                        无法改变
                    </el-table-column>

                    <el-table-column
                            prop="position"
                            label="房间号"
                    >
                        <template slot-scope="scope">
                    <span v-if="!scope.row.ifShow">
                       无法改变
                    </span>
                        </template>
                    </el-table-column>
                </el-table-column>


            </el-table>

            <el-table
                    
                    :data="tableData"
                    border
                    style="width: 90%;margin-left: 5%;margin-top: 10px">
                <el-table-column
                        prop="subordinateDepartments"
                        label="建成时间">
                    <template slot-scope="scope">
                    <span v-if="!scope.row.ifShow">
                    <el-date-picker
                            value-format="timestamp"
                            size="mini"
                            v-model="ruleForm.time"
                            type="date"
                            format="yyyy-MM-dd"
                            placeholder="选择日期">
                    </el-date-picker>
                    </span>
                    </template>
                </el-table-column>
                <el-table-column
                        label="管理部门">
                    自动变为本学院
                </el-table-column>
                <el-table-column
                        label="面向专业">
                    <el-input size="mini"></el-input>
                </el-table-column>

                <el-table-column
                        label="负责人"
                        header-align="center">

                    <el-table-column
                            label="姓名">
                        <template slot-scope="scope">
                            <el-form-item  prop="pass" >
                                <el-input  v-model="ruleForm.pass"  size="mini"></el-input>
                            </el-form-item>
                        </template>
                    </el-table-column>

                    <el-table-column
                            label="工号">
                        <template slot-scope="scope">
                            <el-form-item  prop="pass" >
                                <el-input  v-model="ruleForm.pass" size="mini"></el-input>
                            </el-form-item>
                        </template>
                    </el-table-column>

                    <el-table-column
                            label="手机号">
                        <template slot-scope="scope">
                            <el-form-item  prop="pass" >
                                <el-input  v-model="ruleForm.pass"  size="mini"></el-input>
                            </el-form-item>
                        </template>
                    </el-table-column>
                </el-table-column>

            </el-table>
        </el-form>
        <el-button type="primary">修改</el-button>
    </div>
</template>

<script>
    export default {
        name: "newRoom",
        data(){
            return{
                show:true,
                tableData:[{}],
                ruleForm:{
                    pass:"",
                    time:"",//建成时间
                    peopleNum:"",
                    roomNum:"",
                    floorNum:"",
                    floorRoomNum:""
                },
                peopleNum:[15,25,50],
                rules: {
                    pass: [
                        {  required: true, message: '请输入活动名称', trigger: 'blur' }
                    ],
                }
            }

        },
        methods:{


            //如07   则需把7变为07
            roomNums(item){
                if(item<10){
                    this.ruleForm.roomNum="0"+item
                }
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
</style>