      <!--************    教室使用申请    ************-->
<template>
    <div>
        <pageTitle title-text="教室审核"></pageTitle>
        <div style="margin: 0 5%">
            <el-table
                    ref="filterTable"
                    border
                    :data="tableData"
                    class="table"
                    width="80%"
            >
                <el-table-column
                        type="index"
                        label="序号">
                </el-table-column>
                <el-table-column
                        prop="time"
                        label="使用日期">
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
                        :filters="[{ text: '同意', value: '同意' }, { text: '不同意', value: '不同意' },
                                    {text:'未执行',value:''}]"
                        :filter-method="filterTag"
                        filter-placement="bottom-end">
                    <template slot-scope="scope">
                        <el-tag
                                :type="scope.row.dis === '同意' ? 'success' : 'danger'"
                                disable-transitions>{{scope.row.dis}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        label="操作"
                        width="120">
                    <template slot-scope="scope">

                        <el-button
                                slot="reference"
                                @click="applyRoom(tableData[scope.$index])"
                                type="text"
                                size="small">详情
                        </el-button>
                        <el-button
                                @click.native.prevent="deleteRow(scope.$index, tableData)"
                                type="text"
                                size="small">同意
                        </el-button>
                        <el-button
                                @click.native.prevent="deleteRow(scope.$index, tableData)"
                                type="text"
                                size="small">拒绝
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div class="page-btn " style=" float: right; font-size: 16px;color: #666;margin:20px 50px 50px 0">
            <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
            <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
            <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "classroomApply",
        data(){
            return{
                tableData:[{dis:'同意'},{dis:'同意'},{dis:'不同意'},{dis:''},{dis:'同意'}],
                page:1,
                count:10,
                offset:0,
                pageOver:false,
            }
        },
        methods:{
            changePage(page){
                this.page = page
                // let cnt = {
                //     count: this.count, // Integer
                //     offset: (this.page-1)*this.count, // Integer
                // }
            },

            filterTag(value, row) {
                return row.dis === value;
            },


        }
    }
</script>

<style scoped>

</style>