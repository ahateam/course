<template>
    <div class="changeDia">
        <page-title title-text="教师信息"></page-title>
        <el-button class="buttonMarginLeft" @click="$refs.createDia.openCreate(50)" type="primary">新增教师</el-button>
        <el-table
                :data="tableData"
                class="tableWidthMargin">
            <el-table-column
                    label="序号"
                    type="index"
                    width="50">
            </el-table-column>

            <el-table-column
                    prop="teacherName"
                    label="姓名">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="teacherName"

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

                            clearable
                            @change="lookupTeacher()"
                            placeholder="工号"/>
                </template>
            </el-table-column>
            <!--<el-table-column-->
                    <!--prop="date"-->
                    <!--width="200">-->
                <!--<template slot="header" slot-scope="scope">-->
                    <!--<el-select v-model="collegeId" placeholder="选择学院"  @change="lookupTeacher()">-->
                        <!--<el-option-->
                                <!--value=""-->
                                <!--label="全部学院">-->
                        <!--</el-option>-->
                        <!--<el-option-->
                                <!--v-for="item in $store.state.tableCollege"-->
                                <!--:key="item.collegeId"-->
                                <!--:label="item.collegeName"-->
                                <!--:value="item.collegeId">-->
                        <!--</el-option>-->
                    <!--</el-select>-->
                <!--</template>-->
                <!--<template  slot-scope="scope">-->
                    <!--{{scope.row.collegeName}}-->
                <!--</template>-->
            <!--</el-table-column>-->
            <el-table-column
                    prop="teacherPosition"
                    label="职称">
            </el-table-column>
            <el-table-column
                    prop="workTime"
                    label="入职时间">
                <template slot-scope="scope">
                    {{new Date(parseInt(scope.row.workTime)).toLocaleDateString()}}
                </template>
            </el-table-column>

            <el-table-column
                    prop="teacherPhone"
                    label="联系方式">
            </el-table-column>
            <!--<el-table-column-->
                    <!--prop="adminId"-->
                    <!--label="权限"-->
                    <!--width="120"-->
                    <!--:formatter="changeAdminId">-->
            <!--</el-table-column>-->
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="140">
                <template slot-scope="scope">
                    <el-button @click="edits(scope.row)" type="text" size="small">编辑</el-button>

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

        <two-dialog ref="editDia">
            <edit @transferRandom="closeEdit" ref="editTeacher" :editForm="editref"></edit>

        </two-dialog>

        <!--<two-dialog ref="delDia">-->
            <!--<del-information @transferRandom="delForm"/>-->
        <!--</two-dialog>-->

        <two-dialog ref="createDia">
            <create></create>
        </two-dialog>
        <next-page ref="nextPage"  @transferRandom="changePage" />

    </div>
</template>

<script>
    import edit from "./edit"
    import create from "./create"
    export default {
        name: "adminTeacher",
        data(){
            return{

                tableData:[{teacherName:"www",teacherPhone:187852154654,adminId:'1',workTime:1560355200000},{teacherName:"www",teacherPhone:187852154654,adminId:'3',workTime:1560355200000}
                ,{teacherName:"www",teacherPhone:187852154654,adminId:'0',workTime:1560355200000},{teacherName:"www",teacherPhone:187852154654,adminId:'3',workTime:1560355200000}
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
                    username:0,//"any(select username from tb_teacher_user)",
                    teacherName:"0",
                    collegeId:0,//"any(select collegeId from tb_teacher_user)"
                },
            }
        },
        components:{
            create,
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

            changePage(nextCnt){
                //let cnt =nextCnt.cnt
                //如果选择学院后 获取选择学院的教师信息
                //if(this.collegeId!=="")
                // nextCnt.collegeId=this.$store.state.teacherInformation.collegeId
                // if(this.username===""&&this.teacherName==="") {
                //     this.getTeacher(nextCnt)
                // }
                // else{
                    this.lookupCollegeTeacher(nextCnt)
               // }
            },
            lookupTeacher(){

                this.$refs.nextPage.defaultPage()
                let cnt ={
                    count:this.$store.state.count,
                    offset:0,
                    collegeId:this.$store.state.teacherInformation.collegeId,
                }//查询为空时
                    this.lookupCollegeTeacher(cnt)
                    //查询输入工号的教师
            },
            lookupCollegeTeacher(cnt){
                if(this.teacherName===""){cnt.teacherName="0"}else{
                    cnt.teacherName=this.teacherName
                }

                if(this.username===""){cnt.username=this.lookup.username}
                else{cnt.username=this.username}


                this.$college.lookupCollegeTeacher(cnt,(res)=>{

                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                        console.log(typeof this.$util.tryParseJson(res.data.c)[0].username)
                    }else{
                        this.tableData = []
                    }
                    this.$refs.nextPage.judge(this.tableData.length)
                })
            },


            //点击删除按钮
            // del(row){
            //     this.$refs.delDia.openDel(40)
            //     //this.delTeacher=true
            //     this.delUsername=row.username
            // },
            //
            // //删除教师
            // delForm(delForm){
            //     let cnt={
            //         username:this.delUsername,
            //         delRemark:delForm.del
            //     }
            //     this.$college.delCollegeTeacher(cnt,(res)=>{
            //         if(res.data.rc === this.$util.RC.SUCCESS){
            //             this.$message({
            //                 type:"success",
            //                 message:"删除成功"
            //             })
            //             this.delUsername=""
            //         }else{
            //             this.$message({
            //                 type:"warning",
            //                 message:"删除失败"
            //             })
            //         }
            //     })
            // },

            //传值给edit界面
            edits(row){
                //this.edit=true
                this.editref=row

                this.$refs.editDia.openEdit(50)
                this.$refs.editTeacher.again()

            },
            //权限id变为权限名称
            changeAdminId(row,col,val){
                if(val==='0') return "教务处管理员"
                if(val==='1') return "学院管理员"
                if(val==='2') return "实验室管理员"
                if(val==='3') return "教师"
            }
        },
        mounted(){
            // let cnt = {
            //     count:this.$store.state.count,
            //     offset:this.$store.state.offset,
            //     collegeId:this.$store.state.teacherInformation.collegeId,
            // }
            // this.lookupCollegeTeacher(cnt)
            this.lookupTeacher()

            // let cns ={
            //     count:20,
            //     offset:0
            // }
            // //获取选择学院
            // this.$admin.getDepartments(cns,(res)=>{
            //     if(res.data.rc === this.$util.RC.SUCCESS){
            //         this.tableCollege = this.$util.tryParseJson(res.data.c)
            //     }else{
            //         this.tableCollege = []
            //     }
            // })
        }

    }
</script>

<style scoped lang="scss">


    .changeDia/deep/ .el-dialog__body{
        padding: 5px 20px;
    }
    .changeDia/deep/ {
    .el-select{
        padding-left: 0;
    }
    .el-input{
        padding-left: 0;
    }
    }
</style>
