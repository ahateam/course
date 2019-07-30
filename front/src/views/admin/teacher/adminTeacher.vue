<template>
    <div class="changeDia">
        <page-title title-text="教师信息"></page-title>
        <el-button class="buttonMarginLeft" type="primary">新增教师</el-button>
        <el-table
                :data="tableData"
                class="tableWidthMargin">
            <el-table-column
                    label="序号"
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="date"
                    width="200">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="collegeId" placeholder="选择学院" size="mini" @change="lookupTeacher()">
                        <el-option
                                v-for="item in tableCollege"
                                :key="item.collegeName"
                                :label="item.collegeName"
                                :value="item.collegeId">
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column
                    prop="teacherName"
                    label="姓名">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="teacherName"
                            size="mini"
                            clearable
                            @change="lookupTeacher()"
                            placeholder="姓名"/>
                </template>
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="工号">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="username"
                            size="mini"
                            clearable
                            @change="lookupTeacher()"
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
                    width="120"
                    :formatter="changeAdminId">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="140">
                <template slot-scope="scope">
                    <el-button @click="details=true" type="text" size="small">详情</el-button>
                    <el-button @click="edits(scope.row)" type="text" size="small"
                                :disabled="scope.row.adminId==='1'">编辑</el-button>
                    <el-button @click="del(scope.row)" type="text" size="small" :disabled="scope.row.adminId==='1'">
                        删除</el-button>
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
            <edit @transferRandom="closeEdit" ref="editTeacher" :form="editref"></edit>

        </el-dialog>

        <el-dialog
                title="删除教师"
                :visible.sync="delTeacher"
                width="40%">
            <del-information @transferRandom="delForm"/>

        </el-dialog>

        <next-page ref="nextPage"  @transferRandom="changePage" />

    </div>
</template>

<script>
    import edit from "./edit"

    export default {
        name: "adminTeacher",
        data(){
            return{
                page:1,
                pageOver:false,
                count:10,
                offset:0,
                tableData:[{teacherName:"www",teacherPhone:187852154654,adminId:'1'},{teacherName:"www",teacherPhone:187852154654,adminId:'3'}
                ,{teacherName:"www",teacherPhone:187852154654,adminId:'3'},{teacherName:"www",teacherPhone:187852154654,adminId:'3'}
                ],
                tableCollege:[],//选择学院
                collegeId:"",  //选择选择器后得到
                details:false,//详情弹框
                edit:false,//编辑弹框
                username:"",//工号搜索教师
                teacherName:"",//输入框的
                editref:"",//传值给edit界面
                delTeacher:false,//删除教师
                delUsername:"",//删除教师的工号
                lookup:{
                    username:"any(select username from table_name)",
                    teacherName:"",
                    collegeId:"any(select collegeId from table_name)"
                },
            }
        },
        components:{
            edit
        },

        methods:{

            //关闭编辑页面
            closeEdit(){
                this.edit=false
            },
            editPage(){
                this.$refs.editTeacher.editSchoolTeacher()
            },
            //获取教师信息
            getTeacher(cnt){
                    this.$admin.getSchoolTeacher(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                        }else{
                            this.tableData = []
                        }
                            this.$refs.nextPage.judge(this.tableData.length)
                    })
            },
            //选择学院后
            // choiceCollege(){
            //     let nextCnt={
            //         collegeId:this.collegeId,
            //         offset:this.offset,
            //         count:this.count,
            //     }
            //     this.changePage(nextCnt)
            //     this.$refs.nextPage.defaultPage()
            // },

            changePage(nextCnt){
                //let cnt =nextCnt.cnt
                //如果选择学院后 获取选择学院的教师信息
                //if(this.collegeId!=="") nextCnt.collegeId=this.collegeId
                if(this.username===""&&this.collegeId===""&&this.collegeId==="") {
                    this.getTeacher(nextCnt)
                }
                else{
                    this.lookupCollegeTeacher(nextCnt)
                }
            },
            lookupTeacher(){

                this.$refs.nextPage.defaultPage()
                let cnt ={
                    count:this.$store.state.count,
                    offset:0,
                }//查询为空时
                if(this.username===""&&this.collegeId===""&&this.collegeId===""){
                    this.getTeacher(cnt)
                }
                else{
                    this.lookupCollegeTeacher(cnt)
                    //查询输入工号的教师
                }
            },
            lookupCollegeTeacher(cnt){
                cnt.teacherName=this.teacherName
                if(this.username===""){cnt.username=this.lookup.username}
                else{cnt.username=this.username}
                if(this.collegeId===""){cnt.collegeId=this.lookup.collegeId}
                else{cnt.collegeId=this.collegeId}


                this.$college.lookupCollegeTeacher(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    this.$refs.nextPage.judge(this.tableData.length)
                })
            },


            //点击删除按钮
            del(row){
                this.delTeacher=true
                this.delUsername=row.username
            },

            //删除教师
            delForm(delForm){
                let cnt={
                    username:this.delUsername,
                    delRemark:delForm.del
                }
                this.$college.delCollegeTeacher(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.$message({
                            type:"success",
                            message:"删除成功"
                        })
                        this.delUsername=""
                    }else{
                        this.$message({
                            type:"warning",
                            message:"删除失败"
                        })
                    }
                })
            },

            //传值给edit界面
            edits(row){
                this.edit=true
                this.editref=row
                this.$refs.editTeacher.again()
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
