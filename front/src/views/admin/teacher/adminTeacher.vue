<template>
    <div class="changeDia">
        <page-title title-text="教师信息"></page-title>
        <el-table
                border
                :data="tableData"
                style="width: 90%;margin: 10px 5%">
            <el-table-column
                    label="序号"
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="所属学院"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="工号">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="职称">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="联系方式">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="权限">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="details=true" type="text" size="small">详情</el-button>
                    <el-button @click="edit=true" type="text" size="small">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog
                title="详情"
                :visible.sync="details"
                width="80%">
            <teacherDetails></teacherDetails>
            <span slot="footer" class="dialog-footer">
                <el-button @click="details = false">取 消</el-button>
                <el-button type="primary" @click="details = false">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog
                title="修改教师信息"
                :visible.sync="edit"
                width="50%">
            <edit></edit>
            <span slot="footer" class="dialog-footer">
                <el-button @click="edit = false">取 消</el-button>
                <el-button type="primary" @click="edit = false">确 定</el-button>
            </span>
        </el-dialog>

        <el-row>
        <el-col :span="24" style="margin-top: 20px">
            <div class="page-btn " style=" float: right; font-size: 16px;color: #666;">
                <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
                <el-button type="primary" :disabled="page==1"   @click="changePage(page-1)">上一页</el-button>
                <el-button type="primary" :disabled="pageOver ==true"  @click="changePage(page+1)">下一页</el-button>
            </div>
        </el-col>
        </el-row>
    </div>
</template>

<script>
    import teacherDetails from "./details"
    import edit from "./edit"

    export default {
        name: "adminTeacher",
        data(){
            return{
                page:1,
                pageOver:false,
                count:10,
                offset:0,
                tableData:[{}],
                details:false,
                edit:false
            }
        },
        components:{
            teacherDetails,edit
        },

        methods:{
            getTeacherInformation(){
                this.$admin.getTerms(cnt,(res)=>{
                    if(res.data.rc == this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    console.log(this.tableData)
                    if(this.tableData.length <this.count){
                        this.pageOver= true
                    }else{
                        this.pageOver = false
                    }

                })
            },
            changePage(page){
                this.page = page
                let cnt = {
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                this.getTeacherInformation(cnt)
            },
        },
        mounted(){
            let cnt = {
                count:this.count,
                offset:(this.page-1)*this.count
            }
            this.getTeacherInformation(cnt)
        }

    }
</script>

<style scoped>


    .changeDia/deep/ .el-dialog__body{
        padding: 5px 20px;
    }
</style>
