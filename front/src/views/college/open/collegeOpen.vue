<!--******   学院工作量   ***********-->
<template>
    <div>
        <page-title title-text="xxx学院开设课程"></page-title>



        <el-row class="row-box" >

            <el-button type="primary" class="buttonMarginLeft" @click="$refs.createDia.openCreate(40)">新增课程</el-button>

        </el-row>
        <el-row class="row-box">
            <el-table
                    :data="tableData"

                    class="tableWidthMargin"
                    >
                <el-table-column
                        label="序号"
                        type="index"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="courseName"
                        label="课程名称"
                >
                    <template slot="header" slot-scope="scope">
                        <el-input placeholder="课程名称" clearable v-model="look.courseName"
                                  @change="lookupOpen()"></el-input>
                    </template>
                </el-table-column>

                <el-table-column
                        prop="courseTime"
                        label="建议课程学时"
                >
                </el-table-column>

                <el-table-column
                        align="center"
                        prop="courseExamStatus"
                        label="审核状态">
                    <template slot="header" slot-scope="scope">
                        <el-select v-model="look.collegeOpenExamStatus" placeholder="审核状态" @change="lookupOpen()">

                            <el-option
                                    v-for="item in openStatus"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.val">
                            </el-option>
                        </el-select>
                    </template>
                    <template slot-scope="scope">
                        <span v-show="scope.row.collegeOpenExamStatus==='agree'" style="color:#67c23a;">
                            通过
                        </span>
                        <span v-show="scope.row.collegeOpenExamStatus==='disagree'" style="color:#f56c6c;">
                            未通过
                        </span>
                        <span v-show="scope.row.collegeOpenExamStatus==='null'" style="color:#ff9800;">
                            审核中
                        </span>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        fixed="right"
                        label="操作">
                    <template slot-scope="scope">
                        <!--<el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
                        <el-button type="text" size="small" @click="edit(scope.row)">编辑</el-button>

                        <!--<el-button @click="delCourseOutline(scope.row.courseCode)" type="warning" style="float: right;margin: 15px 0 ">确认删除</el-button>-->
                        <el-button @click="delCourse(scope.row)" style="margin-left: 10px" type="text" size="mini" >删除</el-button>
                    </template>
                </el-table-column>

            </el-table>
        </el-row>

        <!--新增课程大纲-->
        <two-dialog ref="createDia">
            <new-curriculum></new-curriculum>
        </two-dialog>


        <!--****  删除  *****-->
        <two-dialog ref="delDia">
            <del-information @transferRandom="delForm"/>

        </two-dialog>

        <!--*******   编辑大纲弹窗   ****-->
        <two-dialog ref="editDia">
            <edit ref="edit"  :editCollegeCourse="editCollegeCourse"></edit>
        </two-dialog>


        <next-page ref="nextPage"  @transferRandom="changePage" />

    </div>


</template>

<script>
    import edit from "./edit"
    import newCurriculum from "./newCurriculum"


    export default {
        name:'collegeCourse',
        data() {
            return {
                editCollegeCourse:[],
                form:{
                    name:""
                },
                tableLevel:["大一上","大一下","大二上","大二下","大三上","大三下","大四上","大四下",],
                schoolYear:'',
                schoolMonth:'',
                courseAge:"",
                delCour:false,//删除大纲
                del:"",//获取行
                delRemark:"",//删除备注
                openStatus:[{val:'',label:'全部'},{val:'null',label:'审核中'},{val:'agree',label:'通过'},{val:'disagree',label:'未通过'},],
                lookup:{
                    courseName:"0",
                    collegeOpenExamStatus:"0"
                },
                look:{
                    courseName:"",
                    collegeOpenExamStatus:""
                },
                tableData:[{
                    courseCode:10,//课程编码
                    courseName: '高数1',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    classMajor: "大数据",// 上课专业
                    courseAge: "大一上" ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                    courseExamStatus:"agree"//审核状态
                },{
                    courseCode:10,//课程编码
                    courseName: '高数2',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    classMajor: "大数据",// 上课专业
                    courseAge: "大一下" ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                    courseExamStatus:"disagree"//审核状态

                },{
                    courseCode:10,//课程编码
                    courseName: '线性代数',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    classMajor: "大数据",// 上课专业
                    courseAge: "大二上" ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                    courseExamStatus:"null"//审核状态

                }],
                dialogVisible:false, //新增课程弹出
                page:1,
                pageOver:false,
                count:10,
                offset:0,
                editCourse:false,//编辑大纲弹窗
                clickEditNum:0   //调用子组件函数 清除表单验证
            }
        },
        methods:{

            delForm(delForm){
                let cnt={
                    courseCode:this.del.courseCode
                }
                console.log(cnt)
                this.$college.delCollegeOpen(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.$message({
                            type:"success",
                            message:"删除成功"
                        })
                    }else{
                        this.$message({
                            type:"warning",
                            message:"删除失败"
                        })
                    }
                })
            },
            //分页
            changePage(nextCnt){
                // this.page = page
                // let cnt = {
                //     collegeID:79839873973,
                //     count:this.count,
                //     offset:(this.page-1)*this.count
                // }collegeOpenExamStatus
                if(this.look.courseName===""){nextCnt.courseName=this.lookup.courseName}else{
                    nextCnt.courseName=this.look.courseName
                }

                if(this.look.collegeOpenExamStatus===""){nextCnt.collegeOpenExamStatus=this.lookup.collegeOpenExamStatus}else{
                    nextCnt.collegeOpenExamStatus=this.look.collegeOpenExamStatus
                }
                nextCnt.collegeId=this.$store.state.teacherInformation.collegeId
                this.getCollegeOpen(nextCnt)
            },

            //获取开设课程
            getCollegeOpen(cnt){
                this.$college.getCollegeOpen(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                        console.log(this.tableData)
                    }else{
                        this.tableData = []
                    }
                    //判断是否到达最后一页
                    this.$refs.nextPage.judge(this.tableData.length)
                })
            },
            lookupOpen(){
                this.$refs.nextPage.defaultPage()
                let cnt={
                    collegeId:this.$store.state.teacherInformation.collegeId,
                    offset:0,
                    count:this.$store.state.count,
                }
                if(this.look.courseName===""){cnt.courseName=this.lookup.courseName}else{
                    cnt.courseName=this.look.courseName
                }
                if(this.look.collegeOpenExamStatus===""){cnt.collegeOpenExamStatus=this.lookup.collegeOpenExamStatus}else{
                    cnt.collegeOpenExamStatus=this.look.collegeOpenExamStatus
                }
                console.log(cnt)
                this.getCollegeOpen(cnt)

            },


            //点击删除
            delCourse(row){
                this.del=row
                this.$refs.delDia.openDel()
            },

            //编辑
            edit(row){

                if(this.clickEditNum!==0){ this.$refs.edit.again()}
                this.clickEditNum++
                this.editCollegeCourse=row
                this.$refs.editDia.openEdit(40)

            }
        },
        mounted(){
            this.$refs.nextPage.changePage(1)
            //获取开设课程

            this.lookupOpen()
        },
        components:{newCurriculum,edit}

    }
</script>
<style scoped lang="scss">
    .row-box{
        margin-top: 15px;
    }
    .nav-btn{
        float: left;
        margin-left: 15px;
    }
    .row-box1{
        margin-top: 20px;
        padding: 15px;
        background: #fff;

    }
    .title-box{
        font-size: 16px;
        line-height: 30px;
        padding: 0 15px;

    }
    .content-box{
        margin-top: 10px;
    }
    .info-text{
        font-size: 1.4rem;
        color: #666;

    }

    .box{
        background: #fff;
        padding: 15px;
    }
    .el-tree-node__content{
        width: 100%;
        height: 32px !important;
        line-height: 32px;
    }
    .custom-tree-node{
        width: 100%;
        line-height: 32px;
        height: 32px;
        margin: 10px 0;
    }
    .label-box{
        float: left;
        width: 150px;
        height: 32px;
        line-height: 32px;
        font-size: 14px;
        font-weight: 600;
    }
    .icon-box{
        float: right;
        width: 50px ;
        height: 32px;
        z-index: 100;
        background: #fff;
    }

    .el-tree-node:focus>.el-tree-node__content{
        background:#00a0e9 !important;
        border-bottom: 1px solid #ddd;
    }

    .page-text{
        line-height: 4rem;
        margin-left: 2rem;
        margin-right: 2rem;
        font-size: 1.6rem;
        color: #555;
    }
    .page-btn{
        margin-top: 2rem;
        margin-bottom: 1rem;
        float: right;
        margin-right: 5rem;
    }
    .line-title{
        float: left;
        width: 5rem;
        height: 3rem;
        line-height: 3rem;
        font-size: 1.4rem;
        color: #666;

    }
    .line{
        width: 90%;
        height: 3rem;
        margin-top: 2rem;
        margin: 0 auto;
    }
    .line-box{
        width: auto;
        padding-top: 1.2rem;
        margin-left: 6rem;
    }
    .line-text{
        width: auto;
        height: 3rem;
        color:rgb(76, 174, 76);
        margin-left: 6rem;
        line-height: 3rem;
        font-size: 1.4rem;
    }


    .file-msg{
        width: 90%;
        margin: 0 auto;
        height: 2rem;
        line-height: 2rem;
        font-size: 1.4rem;
        color: #f60;
        margin-bottom:1rem;
        overflow: hidden;
        text-overflow:ellipsis;
        white-space: nowrap;
    }
    .fine-name{
        width: 90%;
        margin: 0 auto;
        margin-top: 2rem;
        font-size: 1.4rem;
        height: 2rem;
        line-height: 2rem;
        overflow: hidden;
        text-overflow:ellipsis;
        white-space: nowrap;

    }
    .upload-btn{
        margin-left: 5%;
        margin-top: 1rem;
    }
    .input-file-box{
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 5rem;
        opacity: 0;
    }

    .icon-text{
        display: block;
        float: left;
        width: 50%;
        height: 5rem;
        text-align: left;
        line-height: 5rem;
    }
    .file-box{
        margin: 0 auto;
        width: 90%;
        height: 5rem;
        border: 1px dashed #f4516c;
        position: relative;

    }
    .col-title-box{
        padding:15px;
    }
    .tab-btn{
        margin-left: 15px;
        line-height: 50px;
        padding: 5px 15px;
        border-radius: 5px;
        font-size: 1.4rem;
        border: 1px solid #409EFF;
        color: #409EFF;
        cursor: pointer;
        background: #fff;
    }
    .tab-btn-active{
        background:#409EFF;
        color: #fff;
    }
    .groups-box {
        width: 100%;
        line-height: 2rem;
        font-size: 1.6rem;
        color: #666;
    }
    .post-list{
        width: auto;
        height: 2rem;
        padding: 1rem;
        border-bottom: 1px solid #eee;
        line-height: 2rem;
        color: #333;
        font-size: 1.4rem;
        font-weight: 500;
    }
    .post-list:hover{
        background: rgb(236,245,255);
    }
    .post-list:active{
        color: #333;
        font-weight: 600;
    }
    .post-active{
        color: #333;
        font-weight: 600;
        background: rgb(236,245,255);
    }
</style>
