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
                    width="200">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="collegeId" placeholder="选择学院" size="mini" @change="changePage(1)">
                        <el-option
                                v-for="item in tableCollege"
                                :key="item.collegeName"
                                :lable="item.collegeName"
                                :value="item.collegeId">
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column
                    prop="teacherName"
                    label="姓名">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="工号">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="username"
                            size="mini"
                            clearable
                            @change="changeUsername()"
                            placeholder="工号"/>
                </template>
            </el-table-column>
            <el-table-column
                    prop="teacherPosition"
                    label="职称">
            </el-table-column>
            <el-table-column
                    prop="teacherPhone"
                    label="联系方式">
            </el-table-column>
            <el-table-column
                    prop="adminId"
                    label="权限"
                    :formatter="changeAdminId">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="details=true" type="text" size="small">详情</el-button>
                    <el-button @click="edits(scope.row)" type="text" size="small"
                                :disabled="scope.row.adminId==='1'">编辑</el-button>
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
            <edit :form="editref"></edit>
            <span slot="footer" class="dialog-footer">
                <el-button @click="edit = false">取 消</el-button>
                <el-button type="primary" @click="edit = false">确 定</el-button>
            </span>
        </el-dialog>

        <el-row>
            <el-col :span="24" style="margin-top: 20px">
                <div class="page-btn " style=" margin-right: 5%;float: right; font-size: 16px;color: #666;">
                    <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
                    <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
                    <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
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
                tableData:[{teacherName:"www",teacherPhone:187852154654,adminID:'1'},{teacherName:"www",teacherPhone:187852154654,adminID:'3'}],
                tableCollege:[],//选择学院
                collegeId:"",  //选择选择器后得到
                details:false,//详情弹框
                edit:false,//编辑弹框
                username:"",//工号搜索教师
                editref:"",//传值给edit界面
            }
        },
        components:{
            teacherDetails,edit
        },

        methods:{

            //获取教师信息
            getTeacher(cnt){
                //默认获取所有学院
                if(this.collegeId===""){
                    this.$admin.getSchoolTeacher(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                        }else{
                            this.tableData = []
                        }
                        if(this.tableData.length <this.count){
                            this.pageOver= true
                        }else{
                            this.pageOver = false
                        }
                    })
                }

                //获取选择的学院教师信息
                else{
                    this.$college.getCollegeTeacher(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                        }else{
                            this.tableData = []
                        }
                        if(this.tableData.length <this.count){
                            this.pageOver= true
                        }else{
                            this.pageOver = false
                        }
                    })
                }


            },
            changePage(page){
                this.page = page
                let cnt = {
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                //如果选择学院后 获取选择学院的教师信息
                if(this.collegeId!=="") cnt.collegeId=this.collegeId
                this.getTeacher(cnt)
            },
            changeUsername(){
                //查询为空时
                this.page=1
                if(this.username===""){
                    let cnt = {
                        count:this.count,
                        offset:(this.page-1)*this.count
                    }
                    this.getTeacher(cnt)
                }
                else{
                    let cnt ={
                        username:this.username
                    }

                    //查询输入工号的教师
                    this.$college.lookupCollegeTeacher(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                        }else{
                            this.tableData = []
                        }
                    })
                }
            },

            //传值给edit界面
            edits(row){
                this.edit=true
                this.editref=row
            },
            //权限id变为权限名称
            changeAdminId(row,col,val){
                if(val==='0') return "教师"
                if(val==='1') return "教务处管理员"
                if(val==='2') return "学院管理员"
                if(val==='3') return "实验室管理员"
            }
        },
        mounted(){
            let cnt = {
                count:this.count,
                offset:(this.page-1)*this.count
            }
            this.getTeacher(cnt)

            let cns ={
                count:20,
                offset:0
            }
            //获取选择学院
            this.$admin.getDepartments(cns,(res)=>{
                if(res.data.rc === this.$util.RC.SUCCESS){
                    this.tableCollege = this.$util.tryParseJson(res.data.c)
                }else{
                    this.tableCollege = []
                }
            })
        }

    }
</script>

<style scoped>


    .changeDia/deep/ .el-dialog__body{
        padding: 5px 20px;
    }
</style>
