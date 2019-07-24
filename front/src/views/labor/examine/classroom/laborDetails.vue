<template>
    <div>
        <el-table
                v-show="changeRow"
                ref="filterTable"
                border
                :data="row"
                width="80%"
        >
            <el-table-column
                    type="index"
                    label="序号">
            </el-table-column>

            <el-table-column
                    label="使用日期"
                    prop="time"
                    align="left">
                <!--******** 表格内容  *******-->
                <template slot-scope="scope">
                    {{new Date(parseInt(scope.row.time)).toLocaleDateString()}}
                </template>
            </el-table-column>
            <el-table-column
                    label="提出申请日期"
                    prop="time"
                    align="left"

            >
                <!--******** 表格内容  *******-->
                <template slot-scope="scope">
                    {{new Date(parseInt(scope.row.time)).toLocaleDateString()}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="laboratoryBuild"
                    label="第几节">
            </el-table-column>
            <el-table-column
                    prop="roomNum"
                    label="地点">三实验楼+307
            </el-table-column>
            <el-table-column
                    prop="roomName"
                    label="实验室名称">
            </el-table-column>
            <el-table-column
                    prop="face"
                    label="使用人">
                职称+姓名+工号/学号+手机号
            </el-table-column>
            <el-table-column
                    prop="peopleNum"
                    label="说明">
            </el-table-column>
            <el-table-column
                    prop="dis"
                    label="是否同意"
                    >
                <template slot-scope="scope">
                    <el-tag
                            v-if="scope.row.dis !==''"
                            effect="dark"
                            :type="scope.row.dis === '同意' ? '' : 'success'"
                            disable-transitions>{{scope.row.dis}}
                    </el-tag>
                    <el-tag
                            effect="dark"
                            type="warning"
                            v-else>
                        未处理
                    </el-tag>
                </template>
            </el-table-column>

            <el-table-column
                    v-show="false"
                    prop="peopleNum"
                    label="执行人">
            </el-table-column>


            <div v-if="row[0].dis==='不同意'">
                <el-table-column
                        prop="peopleNum"
                        label="拒绝理由">
                </el-table-column>
            </div>
        </el-table>
    </div>
</template>

<script>

    export default {
        name: "details",
        props:['row'],
        data(){
            return{
                tableData:"sssss",
                changeRow:true
            }
        },
        methods:{
            filterTag(value, row) {
                return row.dis === value;
            },
        },
        mounted(){

            //this.disTable=true
        },
        watch:{
            row(){
                //强制重新渲染表格
                this.changeRow=false
                this.changeRow=true
            }
        }
    }
</script>

<style scoped>

</style>
