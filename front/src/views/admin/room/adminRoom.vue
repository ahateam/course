<template>
    <div>
        <el-select v-model="schoolMonth" placeholder="学院" >
            <el-option
                    v-for="item in month"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
        </el-select>
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
    </div>
</template>

<script>
    export default {
        name: "adminRoom",
        data(){
            return{tableData:[{},{}],
                form:{
                    name:""
                }
            }
        }
    }
</script>

<style scoped>

</style>
