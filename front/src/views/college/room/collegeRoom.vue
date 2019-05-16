<template>
    <div>
        <span style="font-size: 30px">xxx学院实验管理</span><br>

        <el-table
                :data="tableData"
                border
                style="width: 90%;margin-left: 5%;margin-top: 10px">
            <el-table-column
                    label="序号"
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="lecturerName"
                    label="实验室(中心名称)"
            >
            </el-table-column>
            <el-table-column
                    prop="workNum"
                    label="实验室名称"
            >
            </el-table-column>
            <el-table-column
                    prop="position"
                    label="座位数"
            >
            </el-table-column>

            <el-table-column
                    prop="subordinateDepartments"
                    label="地点">
            </el-table-column>
            <el-table-column
                    label="管理部门">
            </el-table-column>
            <el-table-column
                    label="面向专业">
            </el-table-column>
            <el-table-column
                    label="负责人">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                    <el-button type="text" size="small">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-popover
                placement="top-start"
                width="800"
                trigger="click">
            <span style="font-size: 30px">xxx学院新增实验</span><br>
            <el-table
                    :data="tableData"
                    border
                    style="width: 90%;margin-left: 5%;margin-top: 10px">

                <el-table-column
                        prop="lecturerName"
                        label="实验室(中心名称)"
                >
                </el-table-column>
                <el-table-column
                        prop="workNum"
                        label="实验室名称"
                >
                </el-table-column>
                <el-table-column
                        prop="position"
                        label="座位数"
                >
                </el-table-column>

                <el-table-column
                        prop="subordinateDepartments"
                        label="地点">
                    
                    <el-table-column
                            prop="position"
                            label="实验室大楼"
                    >
                    </el-table-column>

                    <el-table-column
                                prop="position"
                                label="房间号"
                    >
                    </el-table-column>
                </el-table-column>


            </el-table>
            <el-table
                    :data="tableData"
                    border
                    style="width: 90%;margin-left: 5%;margin-top: 10px">



                <el-table-column
                        prop="subordinateDepartments"
                        label="面积">
                </el-table-column>
                <el-table-column
                        prop="subordinateDepartments"
                        label="建成时间">
                </el-table-column>
                <el-table-column
                        label="管理部门">
                </el-table-column>
                <el-table-column
                        label="面向专业">
                </el-table-column>
                <el-table-column
                        label="负责人">
                </el-table-column>

            </el-table>
            <el-button  type="primary">新增</el-button>
            <el-button slot="reference" type="primary">新增实验室</el-button>
        </el-popover>
    </div>
</template>

<script>
    export default {
        name: "adminRoom",
        data(){
            return{tableData:[{}],
                form:{
                    name:""
                }
            }
        }
    }
</script>
<style  scoped>

</style>
<style>
    .el-table td, .el-table th{
        text-align: center;
    }
</style>
