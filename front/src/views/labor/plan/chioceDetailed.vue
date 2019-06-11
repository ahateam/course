<template>
    <div class="main">

        <p class="texthead">时间范围选择区域</p>
        <div class="time">
            <span class="text">选择学期 </span>
            <el-select v-model="value" filterable placeholder="开始" class="chioce">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
        </div>
        <div class="time">
            <span class="text">选择起始 </span>
            <el-select v-model="value" filterable placeholder="开始周" class="startEnd">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>-
            <el-select v-model="value" filterable placeholder="结束周" class="startEnd">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
        </div>
        <div class="time">
            <span class="text">选择单双 </span>
            <el-select v-model="value" filterable placeholder="不需要则不选" class="chioce">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
        </div>
        <p class="texthead">实验课程选择区域</p>
        <div>

            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                      style="width: 100%;height: 500px"
                      :row-class-name="tableRowClassName"
                      :row-style="selectedHighlight"
                      @row-click="handleCurrentChange"
            >
                <el-table-column
                        label="#"
                        type="index"
                        width="20">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="课程名称"
                        width="110">
                </el-table-column>
                <el-table-column
                        prop="time"
                        label="学时"
                        >
                </el-table-column>
            </el-table>
            <el-pagination
                    small
                    layout="prev, pager, next"
                    :pager-count="3"
                    @current-change="current_change"
                    :total="total">
            </el-pagination>
        </div>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                total:parseInt(20),
                pagesize:11,//每页数据条数
                currentPage:1,//默认第一页
                options: [{
                    value: '选项1',
                    label: '黄金糕'
                }, {
                    value: '选项2',
                    label: '双皮奶'
                }, {
                    value: '选项3',
                    label: '蚵仔煎'
                }, {
                    value: '选项4',
                    label: '龙须面'
                }, {
                    value: '选项5',
                    label: '北京烤鸭'
                }],
                value: '',
                getIndex:"",
                tableData:[{name:"数据结构",time:"36"},{name:"高等数学",time:"36"},
                    {name:"javascript",time:"18"},{name:"数据结构",time:"36"},{name:"高等数学",time:"36"},{name:"javascript",time:"18"},{name:"数据结构",time:"36"},
                    {name:"javascript",time:"18"},{name:"数据结构",time:"36"},{name:"高等数学",time:"36"},{name:"javascript",time:"18"},{name:"数据结构",time:"36"},
                    {name:"javascript",time:"18"},{name:"数据结构",time:"36"},{name:"高等数学",time:"36"},{name:"javascript",time:"18"},{name:"数据结构",time:"36"},
                    {name:"javascript",time:"18"},{name:"数据结构",time:"36"},{name:"高等数学",time:"36"},{name:"javascript",time:"18"},{name:"数据结构",time:"36"},
                    {name:"javascript",time:"18"},{name:"数据结构",time:"36"},{name:"高等数学",time:"36"},{name:"javascript",time:"18"},{name:"数据结构",time:"36"},

                ]
            }
        },
        methods:{
            selectedHighlight({row, rowIndex}) {
                if ((this.getIndex) === rowIndex ) {
                    return {
                        "background-color": "#beead0"
                    };}},
            tableRowClassName ({row, rowIndex}) {
            //把每一行的索引放进row
                row.index = rowIndex;

            },
            handleCurrentChange(row) {
                this.getIndex=row.index
            },
            current_change:function(currentPage){
                this.currentPage = currentPage;
            }
        },
        mounted(){
            let that =this
            console.error(parseFloat((that.options.length)/5)+"+++++++++++++++++++++")
                that.total=that.tableData.length*(10/11)+10


        }

    }
</script>

<style scoped>

.main{
    height: 803px;
    width: 100%;
    border: 0.1px solid #e3c0c0;
    padding: 0 5px;
}
    .texthead{text-align: center;color: #fff;background: #48c1f4;border-radius: 5px}
    .text{color: #47494e;width: 60px}
    .chioce{height: 20px;width: 60%}
    .time{margin-bottom: 20px}
    .startEnd{width: 35%;height: 20px;}

</style>
<style>

</style>