<!--************    教室使用申请    ************-->
<template>
    <div class="changeDia">
        <pageTitle title-text="教室审核记录"></pageTitle>
        <el-cascader
                style="margin: 10px 5%"
                v-model="value"
                :options="options"
                :props="{ expandTrigger: 'hover' }"
                placeholder="选择学期">

        </el-cascader>
        <div style="margin: 0 5%" class="changeDia">
            <el-table
                    :row-style="tableRowClassName"
                    ref="filterTable"
                    border
                    :data="tableData"
                    width="80%">
                <el-table-column
                        type="index"
                        label="序号">
                </el-table-column>
                <el-table-column
                        label="使用日期"
                        prop="time"
                        align="left"
                        sortable>

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
                        :filters="[{ text: '同意', value: '同意' }, { text: '不同意', value: '不同意' },
                                    {text:'未执行',value:''}]"
                        :filter-method="filterTag"
                        filter-placement="bottom-end">
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
                                v-else
                        >未处理
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        label="操作"
                        width="120">
                    <template slot-scope="scope" >

                        <el-button
                                slot="reference"
                                @click="dialog(scope.$index)"
                                type="text"
                                size="small">详情
                        </el-button>
                        <!--*****  拒绝理由   ****-->
                        <el-dialog
                                top="150px"
                                title="拒绝申请教室"
                                :visible.sync="refuse"
                                width="30%">
                            <el-form ref="form"  label-width="80px">
                                <el-form-item label="拒绝理由">
                                    <el-input type="textarea" v-model="tableData.desc" placeholder="请输10-50字"></el-input>
                                </el-form-item>
                            </el-form>
                            <span slot="footer" class="dialog-footer">
                                <el-button @click="refuse = false" size="small">取 消</el-button>
                                <el-button type="primary" @click="refuse = false" size="small">确 定</el-button>
                            </span>
                        </el-dialog>




                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div class="page-btn " style=" float: right; font-size: 16px;color: #666;margin:20px 50px 50px 0">
            <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
            <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
            <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
        </div>

        <!--******** 详情  *******-->
        <el-dialog
                title="详情"
                :visible.sync="dialogVisible"
                width="80%">
            <laborDetails  :row="detailsRow"></laborDetails>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false" size="small">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    import laborDetails from "./laborDetails";
    export default {

        name: "classroomApply",
        data(){
            return{
                tableData:[{time:"1560355200000",dis:'同意'},{time:"1560355200000",dis:'同意'},
                    {time:"1560355200000",dis:'不同意'},
                    {time:"1560355200000",dis:''},{time:"1560441600000",dis:'同意'},{time:"1560355200000",dis:''},],
                page:1,
                count:10,
                offset:0,
                pageOver:false,
                dialogVisible:false,
                detailsRow:[],
                refuse:false,

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

            //是否同意赛选
            filterTag(value, row) {
                return row.dis === value;
            },

            //隔行换颜色
            tableRowClassName({row, rowIndex}) {
                if (row.dis === "") {
                    return { "background-color": "oldlace" } //#f0f9eb
                }
                return '';
            },

            // 筛选使用日期
            screenTime(future){
                console.log(future)
            },


            dialog(rowIndex){
                this.detailsRow.splice(0,1)
                this.detailsRow[0]=this.tableData[rowIndex]
                this.dialogVisible = true
            }
        },
        components:{laborDetails}
    }
</script>

<style scoped>
    .el-table .warning-row  {
        background: oldlace;
    }

    .el-table .success-row  {
        background: #f0f9eb;
    }

    .el-button+.el-button  {
        margin-left: 5px;
    }
    .changeDia /deep/ .el-dialog__body {padding: 15px 20px;}
    .changeDia /deep/ .el-form-item {margin-bottom: 5px;}
</style>