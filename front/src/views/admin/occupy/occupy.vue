<template>
    <div class="changeTable">
        <el-table
                class="classScheduleCard"
                :data="information"
                :span-method="objectSpanMethod"
                :cell-class-name="tableRowClassName"
                border
                align="center">
            <el-table-column
                    prop="time"
                    label="时间"
                    width="50"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="section"
                    label="节数"
                    width="50"
                    align="center">
            </el-table-column>

            <el-table-column
                    prop="mon"
                    label="星期一"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="tue"
                    label="星期二"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="wed"
                    label="星期三"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="thu"
                    label="星期四"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="fri"
                    label="星期五"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sat"
                    label="星期六"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sun"
                    label="星期天"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="scope.$index%2===0">
                         <span v-if="tableData[scope.$index].sun!==tableData[scope.$index+1].sun">
                            {{tableData[scope.$index].sun}}<br/>{{tableData[scope.$index+1].sun}}
                         </span>
                        <span v-else >
                            {{tableData[scope.$index].sun}}
                        </span>
                    </span>

                </template>
            </el-table-column>

        </el-table>

        <el-row style="font-size: 14px;margin-left: 2%;text-align: center;width: 96%">

            <!--         表头                 -->
            <el-row style="height:48px;color:#606266;font-weight: 600;line-height: 48px;">
                <el-col style=" " class="headBorder leftBorder timeWidth">时间</el-col>
                <el-col style="" class="headBorder timeWidth">节数</el-col>
                <el-col style="width: 13.1%;min-width: 70px" class="headBorder" v-for="item in week" :key="item">{{item}}</el-col>
            </el-row>
            <!--         内容                 -->
            <el-row style="color:#606266;">
                    <!--      时间    -->
                <el-col style="height: 578px;color:#606266;font-weight: 600" class="timeWidth">
                    <el-row style="height: 192px;line-height: 192px" class="mainBorder leftBorder">早上</el-row>
                    <el-row style="height: 96px;line-height: 96px" class="mainBorder leftBorder">中午</el-row>
                    <el-row style="height: 192px;line-height: 192px" class="mainBorder leftBorder">下午</el-row>
                    <el-row style="height: 96px;line-height: 96px" class="mainBorder leftBorder">晚上</el-row>
                </el-col>
                <!--      第几节    -->
                <el-col style="" class="timeWidth">
                    <el-row style="height: 48px;line-height: 48px" class="mainBorder " v-for="item in 12" :key="item">{{item}}</el-row>
                </el-col>
                <el-col style="width: 13.1%;" v-for="week in 7" :key="week"><!--星期-->
                    <el-row class="mainHeight mainBorder" v-for="section in 6" :key="section"><!--节-->
                        <span v-if="week===1">
                            <div v-if="tableData[2*section-2].mon===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].mon===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===2">
                            <div v-if="tableData[2*section-2].tue===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].tue===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===3">
                            <div v-if="tableData[2*section-2].wed===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].wed===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox" ></div>
                        </span>


                        <span v-if="week===4">
                            <div v-if="tableData[2*section-2].thu===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].thu===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===5">
                            <div v-if="tableData[2*section-2].fri===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox" ></div>

                            <div v-if="tableData[2*section-1].fri===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===6">
                            <div v-if="tableData[2*section-2].sat===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].sat===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===7">
                            <div v-if="tableData[2*section-2].sun===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].sun===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>


                    </el-row>
                </el-col>
            </el-row>
        </el-row>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                week:["星期一","星期二","星期三","星期四","星期五","星期六","星期天"],
                mainWitdh:document.body.clientWidth*0.82111-100+"px",
                information: [
                    {time: '早上', section: '1',},
                    {time: '早上', section: '2',},
                    {time: '早上', section: '3',},
                    {time: '早上', section: '4',},

                    {time: '中午', section: '5',},
                    {time: '中午', section: '6',},

                    {time: '下午', section: '7',},
                    {time: '下午', section: '8',},
                    {time: '下午', section: '9',},
                    {time: '下午', section: '10',},

                    {time: '晚上', section: '11',},
                    {time: '晚上', section: '12',},
                ],
                tableData:[
                    {mon:0,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                    {mon:0,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                    {mon:0,tue:0,wed:0,thu:0,fri:0,sat:0,sun:1},
                    {mon:1,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                    {mon:1,tue:0,wed:0,thu:0,fri:1,sat:0,sun:1},
                    {mon:1,tue:0,wed:0,thu:0,fri:0,sat:1,sun:1},
                    {mon:1,tue:1,wed:1,thu:0,fri:0,sat:0,sun:1},
                    {mon:1,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                    {mon:1,tue:1,wed:1,thu:0,fri:1,sat:1,sun:1},
                    {mon:1,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                    {mon:1,tue:0,wed:1,thu:0,fri:1,sat:1,sun:1},
                    {mon:0,tue:1,wed:1,thu:0,fri:1,sat:0,sun:1},
                ]
            };
        },
        methods: {
            column(){

            },
            tableRowClassName({row, column, rowIndex, columnIndex}) {
                var arr = Object.values(this.tableData[rowIndex]); //对象转化为数组
                if(columnIndex<2) return ""
                if (this.tableData[rowIndex].mon === 1&&columnIndex===2 &&this.tableData[rowIndex].mon) {
                    return 'warning-row';
                } else if(this.tableData[rowIndex].tue === 1 && columnIndex===3 &&this.tableData[rowIndex].tue){
                    return 'warning-row';
                }  else  if(this.tableData[rowIndex].wed === 1 &&columnIndex===4 &&this.tableData[rowIndex].wed){
                    return 'warning-row';
                }  else   if(this.tableData[rowIndex].thu === 1&&columnIndex===5 &&this.tableData[rowIndex].thu){
                    return 'warning-row';
                } else  if(this.tableData[rowIndex].fri === 1&&columnIndex===6 &&this.tableData[rowIndex].fri){
                    return 'warning-row';
                }  else  if(this.tableData[rowIndex].sat === 1&&columnIndex===7 &&this.tableData[rowIndex].sat){
                    return 'warning-row';
                } else   if(this.tableData[rowIndex].sun === 1&&columnIndex===8 &&this.tableData[rowIndex].sun){
                    return 'warning-row';
                } else {
                    return 'success-row';
                }


                return '';
            },

            objectSpanMethod({ row, column, rowIndex, columnIndex }) {
                if (columnIndex === 0) {
                    if (rowIndex  === 0) {
                        return {rowspan: 4, colspan: 1};
                    }
                    if (rowIndex  === 4) {
                        return {rowspan: 2, colspan: 1};
                    }
                    if (rowIndex  === 6) {
                        return {rowspan: 4, colspan: 1};
                    }
                    if (rowIndex  === 10) {
                        return {rowspan: 4, colspan: 1};
                    }
                    else {
                        return {rowspan: 0, colspan: 0};
                    }
                }

                else if(columnIndex >1){
                    return {rowspan: 2, colspan: 1};
                }
            }
        },
        mounted(){

        }
    };
</script>

<style >
     .occupy {

        background: #faa7a1;
    }

     .free {
        background: #f0f9eb;

    }
    .headBorder{
        border-bottom: 1px solid #EBEEF5;;
        border-right: 1px solid #EBEEF5;;
        border-top:1px solid #EBEEF5;;
    }
    .leftBorder{
        border-left:1px solid #EBEEF5;;
    }
    .mainBorder{
        border-bottom: 1px solid #EBEEF5;;
        border-right: 1px solid #EBEEF5;;
    }
    .timeWidth{
        width: 3%;
        min-width: 30px;
    }
    .mainHeight{
        height: 96px;
        line-height: 96px;
    }
    .colorBox{
        width: 100%;
        height: 47.5px;
    }
    .classScheduleCard{
        width: 95%;
        margin-top: 20px;
        margin-left: 2%;
        margin-bottom: 30px;
    }
    .el-table--enable-row-hover .el-table__body tr:hover>td{
        /*background-color: transparent !important;*/
    }
    .el-table--enable-row-hover .el-table__body tr>td:hover{
        /*background-color: #c4d5e6 !important;*/
    }
</style>
