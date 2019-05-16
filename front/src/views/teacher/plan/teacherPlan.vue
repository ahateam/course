<template>
    <div>
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
                    prop="sections"
                    label="星期一"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sections"
                    label="星期二"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sections"
                    label="星期三"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sections"
                    label="星期四"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sections"
                    label="星期五"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sections"
                    label="星期六"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sections"
                    label="星期天"
                    align="center">
            </el-table-column>

        </el-table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [
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

<style>
    .classScheduleCard{
        width: 95%;
        margin-top: 20px;
        margin-left: 2%;
        margin-bottom: 30px;
    }
</style>