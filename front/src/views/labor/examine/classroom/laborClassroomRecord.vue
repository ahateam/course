<template>
    <div>
        <el-table
                ref="filterTable"
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    prop="tag"
                    label="标签"
                    width="100"
                    :filters="[{ text: '家', value: '家' }, { text: '公司', value: '公司' }]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                            :type="scope.row.tag === '家' ? 'primary' : 'success'"
                            disable-transitions>{{scope.row.tag}}</el-tag>
                </template>
            </el-table-column>
        </el-table>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                tableData: [{
                    tag: '家'
                }, {
                    tag: '公司'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1519 弄',
                    tag: '家'
                }, {
                    tag: '公司'
                }]
            }
        },
        methods: {
            filterTag(value, row) {
                return row.tag === value;
            },

        }
    }
</script>