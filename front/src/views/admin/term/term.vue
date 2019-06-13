<template>
    <div>
        啊实打实大苏打
        <el-row class="row-box">
            <el-col :span="24">
                <el-button type="primary" @click="dialogFormVisible =true"> 新增学期</el-button>
            </el-col>
        </el-row>
        <el-row class="row-box1">
            <el-row class="row-box1">
                <el-col :span="24">
                    <template>
                        <el-table
                                :data="tableData"
                                border
                                style="width: 100%"
                        >
                            <el-table-column
                                    prop="name"
                                    label="学期名称">
                            </el-table-column>
                            <el-table-column
                                    prop="weekCount"
                                    label="学期周数"
                            >
                            </el-table-column>
                            <el-table-column
                                    prop="createTime"
                                    label="开始时间"
                                    :formatter="timerFilter"
                            >
                            </el-table-column>
                            <el-table-column
                                    prop="endDate"
                                    label="结束时间"
                                    :formatter="timerFilter"
                            >
                            </el-table-column>
                            <el-table-column
                                    label="操作"
                                    width="250">
                                <template slot-scope="scope">

                                    <!--<el-button @click="editVoteBtn(scope.row)" type="text" size="small">编辑</el-button>-->
                                    <!--<el-button @click="editStatus(scope.row)" type="text" size="small">更改状态</el-button>-->
                                    <!--&lt;!&ndash;<el-button @click="putRes(scope.row)" type="text" size="small">结论打印</el-button>&ndash;&gt;-->
                                    <el-button @click="delModal(scope.row)" type="text" size="small" style="color: #f44;">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </template>
                </el-col>
                <el-col :span="24" style="margin-top: 20px">
                    <div class="page-btn " style=" float: right; font-size: 16px;color: #666;">
                        <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
                        <el-button type="primary" :disabled="page==1"   @click="changePage(page-1)">上一页</el-button>
                        <el-button type="primary" :disabled="pageOver ==true"  @click="changePage(page+1)">下一页</el-button>
                    </div>
                </el-col>
            </el-row>
        </el-row>

        <!--新增弹窗-->
        <el-dialog title="学增学期" :visible.sync="dialogFormVisible">
            <el-form>
                <el-form-item label="学期名称" label-width="150px">
                    <el-input v-model="termName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="学期周数" label-width="150px">
                    <el-input v-model="weekCount" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="开始时间" label-width="150px">
                    <el-date-picker
                            v-model="startDate"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>

                </el-form-item>
                <el-form-item label="结束时间" label-width="150px">
                    <el-date-picker
                            v-model="endDate"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>

                </el-form-item>
                <el-form-item label="备注信息" label-width="150px">
                    <el-input v-model="remark" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="createTermBtn">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "term",
        data(){
            return{
                page:1,
                pageOver:false,
                count:10,
                offset:0,
                tableData:[],
                dialogFormVisible:false,
                //新增学期所需变量
                termName:'',
                weekCount:'',
                startDate:'',
                endDate:'',
                remark:'无',


            }
        },
        methods:{
            //请求列表
            getTerms(cnt){
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

            timerFilter(row,col,val){
                let timer = new Date(val)
                return timer.toLocaleDateString()
            },


            //创建学期
            createTermBtn(){
                if(this.termName == ''|| this.weekCount == '' || this.startDate == '' || this.endDate == '' ){
                    this.$message.error('请先将信息输入完整');
                }else {
                    let cnt = {
                        termName:this.termName,
                        weekCount:parseInt(this.weekCount),
                        startDate:new Date(this.startDate).getTime(),
                        endDate:new Date(this.endDate).getTime(),
                        remark :this.remark
                    }
                    this.$admin.createTerm(cnt,(res)=>{
                       if(res.data.rc == this.$util.RC.SUCCESS){
                           this.$message.success('创建成功')
                       }else{
                           this.$message.error('操作失败')
                       }
                    })
                    this.$router.push('/page')



                }
            },
            //分页请求
            changePage(page){
                this.page = page
                let cnt = {
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                this.getTerms(cnt)
            },
            //删除一个学期
            delModal(row){
                this.$confirm('是否确认删除?')
                    .then(_ => {
                        let cnt ={
                            termId:row.id
                        }
                        this.$admin.delTerm(cnt,(res)=>{
                            if(res.data.rc == this.$util.RC.SUCCESS){
                                this.$message.success('删除成功')
                            }else{
                                this.$message.error('操作失败')
                            }
                        })
                        this.$router.push('/page')
                    })
                    .catch(_ => {});
            }
        },
        mounted() {
            let cnt = {
                count:this.count,
                offset:this.offset
            }
            this.getTerms(cnt)

        }
    }
</script>

<style scoped lang="scss">
    .row-box{
        background: #fff;
        padding: 15px;
        font-size: 1.6rem;
        color: #666;
    }
    .nav-btn {
        float: left;
    }

    .row-box1 {
        padding: 15px;
        background: #fff;
    }
    .el-tag{
        margin-left: 10px;
    }
    .button-new-tag {
        margin-left: 10px;
        height: 32px;
        line-height: 30px;
        padding-top: 0;
        padding-bottom: 0;
    }
    .input-new-tag {
        width: 90px;
        margin-left: 10px;
        vertical-align: bottom;
    }
    .row-box2{

    }

    .row-box{

    }
    .nav-btn{
        float: left;
        margin-left: 15px;
    }
    .row-box1{

        padding: 15px;
        background: #fff;

    }
    .title-box{
        font-size: 16px;
        line-height: 30px;
        padding: 0 15px;

    }
    .content-box{

    }
    .info-text{
        font-size: 1.4rem;
        color: #666;

    }

    .box{
        background: #fff;
        padding: 15px;
    }


</style>