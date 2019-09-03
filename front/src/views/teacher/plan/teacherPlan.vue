<template>
    <div class="changeTable">
        <pageTitle title-text="我的课表"></pageTitle>
        <el-table
                class="classScheduleCard"
                :data="tableData"
                :span-method="objectSpanMethod"
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
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [
                    {time: '早上', section: '1',sun:1212},
                    {time: '早上', section: '2',sun:456789},
                    {time: '早上', section: '3',sun:456789},
                    {time: '早上', section: '4',sun:456789},

                    {time: '中午', section: '5',sun:456789},
                    {time: '中午', section: '6',sun:456789},

                    {time: '下午', section: '7',sun:456789},
                    {time: '下午', section: '8',sun:456789},
                    {time: '下午', section: '9',sun:456789},
                    {time: '下午', section: '10',sun:456789},

                    {time: '晚上', section: '11',sun:456789},
                    {time: '晚上', section: '12',sun:456789},



                ]
            };
        },
        methods: {

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
        }
    };
</script>

<style >
    .classScheduleCard{
        width: 95%;
        margin-top: 20px;
        margin-left: 2%;
        margin-bottom: 30px;
    }
    .el-table--enable-row-hover .el-table__body tr:hover>td{
        background-color: #fff !important;
    }
    .el-table--enable-row-hover .el-table__body tr>td:hover{
        background-color: #c4d5e6 !important;
    }
</style>
