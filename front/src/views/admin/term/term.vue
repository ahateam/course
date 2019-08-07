<template>
    <div>
        <page-title title-text="学期管理"></page-title>

        <el-button class="buttonMarginLeft" type="primary" @click="dialogFormVisible =true"> 新增学期</el-button>

        <el-row class="row-box1">
            <el-row class="row-box1">
                <el-col :span="24">
                    <template>
                        <el-table
                                :data="tableData"
                                class="tableWidthMargin"
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
                                   >
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
            </el-row>
        </el-row>
        <next-page ref="nextPage"  @transferRandom="changePage" />
        <!--新增弹窗-->
        <el-dialog title="新增学期" :visible.sync="dialogFormVisible">
            <el-form style="width: 90%" ref="addTerm" :model="addTerm" :rules="rules" >
                <el-form-item label="学期名称"  prop="termName" label-width="150px">
                    <!--<el-input v-model="addTerm.termName" autocomplete="off"></el-input>-->
                    <el-row  class="rowbox">
                        <el-col :span="6" >
                                <el-date-picker
                                        clear-icon
                                        v-model="startYear"
                                        type="year"
                                        value-format="yyyy"
                                        placeholder="">
                                </el-date-picker>
                        </el-col>

                        <el-col :span="6">
                           -{{endYear}}
                        </el-col>
                        <el-col :span="8" :offset="1">
                            第<el-select v-model="semester" placeholder="请选择" :disabled="disSemester">
                            <el-option
                                    value="一">
                            </el-option>
                            <el-option
                                    value="二">
                            </el-option>
                        </el-select> 学期
                        </el-col>
                    </el-row>

                </el-form-item>
                <el-form-item label="学期周数" label-width="150px" prop="weekCount">
                    <el-select v-model="addTerm.weekCount" placeholder="请选择" >
                        <el-option
                                v-for="(item,index) in 25"
                                :key="index"
                                :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="开始时间" label-width="150px" prop="startDate">
                    <el-date-picker
                            v-model="addTerm.startDate"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>

                </el-form-item>
                <el-form-item label="结束时间" label-width="150px" prop="endDate">
                    <el-date-picker
                            v-model="addTerm.endDate"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>

                </el-form-item>
                <el-form-item label="备注信息" label-width="150px" prop="remark">
                    <el-input v-model="addTerm.remark" autocomplete="off"></el-input>
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
                tableData:[{}],
                dialogFormVisible:false,
                startYear:"",//选择学期开始年
                endYear:"",  //选择学期结束年
                semester:"",//选择学期
                disSemester:true,
                //新增学期所需变量
                addTerm:{
                    termName:'',  //学期名称
                    weekCount:'20', //周数
                    startDate:'',   //开始时间
                    endDate:'',      //结束时间
                    remark:'无',     //备注
            },
                rules:{
                    termName:[{ required: true, message: '请输入学期名称', },],
                    weekCount:[{ required: true, message: '请输入周数', trigger: 'blur' }],
                    startDate:[{ required: true, message: '请选择开始时间', trigger: 'blur' }],
                    endDate:[{ required: true, message: '请选择结束时间', trigger: 'blur' }],
                    remark:[{  max: 30, message: '长度小于30 个字符', trigger: 'blur' }],
                }



            }
        },
        methods:{
            //请求列表
            getTerms(cnt){
                this.$admin.getTerms(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    console.log(this.tableData)
                    this.$refs.nextPage.judge(this.tableData.length)

                })
            },

            timerFilter(row,col,val){
                let timer = new Date(val)
                return timer.toLocaleDateString()
            },


            //创建学期
            createTermBtn(){
                this.$refs['addTerm'].validate((valid) => {
                    if (valid) {
                        let cnt = {
                            termName:this.addTerm.termName,
                            weekCount:parseInt(this.addTerm.weekCount),
                            startDate:new Date(this.addTerm.startDate).getTime(),
                            endDate:new Date(this.addTerm.endDate).getTime(),
                            remark :this.addTerm.remark
                        }
                        this.$admin.createTerm(cnt,(res)=>{
                            if(res.data.rc === this.$util.RC.SUCCESS){
                                this.$message.success('创建成功')
                                this.$router.push('/page')

                            }else{
                                this.$message.error('操作失败')
                            }
                        })
                    } else {
                        this.$message("表单错误")
                        return false;
                    }
                });
                },
            //分页请求
            changePage(nextCnt){

                this.getTerms(nextCnt)
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
            },



        },
        mounted(){
            let cnt = {
                count:this.count,
                offset:this.offset
            }
            this.getTerms(cnt)

        },
        watch:{
            startYear(){
                this.endYear=parseInt(this.startYear)+1
                this.disSemester=false
            },

            //拼接学期名称
            semester(){
                this.addTerm.termName=this.startYear+"-"+this.endYear+" 第"+this.semester+"学期"
                console.log(this.addTerm.termName)
            }
        }
    }
</script>

<style scoped lang="scss">
    .rowbox .el-date-editor{
        width: 100%;
    }
    .rowbox .el-select{
        width: 50%;
    }
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
