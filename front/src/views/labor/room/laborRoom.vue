<template>
    <div>
        <page-title title-text="xxx学院实验管理"></page-title>
        <el-button type="text" @click="dialogVisible = true">新增实验室</el-button>

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

        <!--新增实验室的弹出框-->
        <el-dialog
                :visible.sync="dialogVisible"
                width="70%"
                top="40px"
                >
            <span style="font-size: 30px">xxx学院新增实验室</span><br>
            <new-room  />
            <span slot="footer" class="dialog-footer">
                <el-button  type="primary" @click="dialogVisible = false" >取消</el-button>
                <el-button slot="reference" type="primary">申请</el-button>
            </span>
        </el-dialog>

        <div class="page-btn " style=" float: right; font-size: 16px;color: #666;margin:20px 50px 0 0">
            <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
            <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
            <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
        </div>
    </div>
</template>

<script>
    import newRoom from "./newRoom"
    export default {
        name: "adminRoom",
        data(){
            return{
                tableData:[{}],
                form:{
                    name:""
                },
                dialogVisible: false,
                page:1,
                count:10,
                offset:0,
                pageOver:false,

            }
        },
        methods:{
            handleClick(row){},
            handleClose(){},
            changePage(page){
                this.page = page
                // let cnt = {
                //     count: this.count, // Integer
                //     offset: (this.page-1)*this.count, // Integer
                // }


            },
        },
        components:{newRoom}


    }
</script>
<style  scoped>

</style>
<style>
    .el-table td, .el-table th{
        text-align: center;
    }


</style>
