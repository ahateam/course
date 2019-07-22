<!--******   学院工作量   ***********-->
<template>
    <div>
        <page-title title-text="xxx学院课程大纲"></page-title>

        <!--新增课程大纲-->
        <el-dialog
                :visible.sync="dialogVisible"
                width="40%">
            <new-curriculum></new-curriculum>
        </el-dialog>

        <el-row class="row-box" >

            <el-button type="primary" class="buttonMarginLeft" @click="dialogVisible = true">新增课程</el-button>

        </el-row>
        <el-row class="row-box">
            <el-table
                    :data="tableData"
                    border
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
                </el-table-column>
                <el-table-column
                        prop="courseCode"
                        label="课程编码"
                >
                </el-table-column>

                <el-table-column
                        align="center"
                        prop="courseExamStatus"
                        label="审核状态">
                    <template slot-scope="scope">
                        <span v-show="scope.row.courseExamStatus==='agree'" style="color:#67c23a;">
                            通过
                        </span>
                        <span v-show="scope.row.courseExamStatus==='disagree'" style="color:#f56c6c;">
                            未通过
                        </span>
                        <span v-show="scope.row.courseExamStatus==='null'" style="color:#ff9800;">
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

        <el-dialog
                :visible.sync="delCour"
                :title="del.courseName"
                width="30%">
            <div style="height: 140px">
                <el-input
                        type="textarea"
                        placeholder="删除备注 30字以内"
                        v-model="delRemark"
                        maxlength="30"
                        resize="none"
                        :autosize="{minRows:3,maxRows:5}"
                        show-word-limit>
                </el-input>
                <el-button @click="delCourseOutline(del)" type="danger" style="float: right;margin: 30px 0 0 0">确认删除</el-button>
            </div>

        </el-dialog>

        <!--*******   编辑大纲弹窗   ****-->
        <el-dialog
                :visible.sync="editCourse"
                width="40%">
            <edit ref="edit"  :editCollegeCourse="editCollegeCourse"></edit>

        </el-dialog>


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
                tableData:[{
                    courseCode:10,//课程编码
                    courseName: '高数1',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    courseMajor: "大数据",// 上课专业
                    courseAge: "大一上" ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                    courseExamStatus:"agree"//审核状态
                },{
                    courseCode:10,//课程编码
                    courseName: '高数2',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    courseMajor: "大数据",// 上课专业
                    courseAge: "大一下" ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                    courseExamStatus:"disagree"//审核状态

                },{
                    courseCode:10,//课程编码
                    courseName: '线性代数',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    courseMajor: "大数据",// 上课专业
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


            //分页
            changePage(nextCnt){
                // this.page = page
                // let cnt = {
                //     collegeID:79839873973,
                //     count:this.count,
                //     offset:(this.page-1)*this.count
                // }
                nextCnt.collegeId="555"
                this.getCollegeOpen(nextCnt)
            },

            //获取开设课程
            getCollegeOpen(cnt){
                this.$college.getCollegeOpen(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    //判断是否到达最后一页
                    if(this.tableData.length <this.count){
                        this.$refs.nextPage.chagnePageOver()
                    }else{
                        this.pageOver = false
                    }
                })
            },


            //点击删除
            delCourse(row){
                this.del=row
                this.delCour=true
            },

            //删除大纲
            delCourseOutline(row){
                let cnt={
                    courseCode:row.code,
                }
                this.$college.delCollegeOpen(cnt,(res)=> {
                    if (res.data.rc === this.$util.RC.SUCCESS) {
                        this.$message("删除成功，等待管理员审核")
                    } else {
                    }
                })
            },


            //编辑
            edit(row){

                if(this.clickEditNum!==0){ this.$refs.edit.again()}
                this.clickEditNum++
                this.editCollegeCourse=row
                this.editCourse=true

            }
        },
        mounted(){
            this.$refs.nextPage.changePage(1)
            //获取开设课程
            let cnt={
                collegeId:"大数据",
                offset:this.offset,
                count:this.count
            }
            this.getCollegeOpen(cnt)
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
