<template>
    <div class="elform">
        <page-title title-text="学院管理"></page-title>
        <el-row>
            <el-col>
                <el-button type="primary" style="margin-left: 5%" @click="dialogVisible = true">新增学院</el-button>
            </el-col>
        </el-row>
        <el-table
                :data="tableData"
                style="width: 90%;margin-left: 5%;margin-top: 10px">
            <el-table-column
                    label="详情"
                    type="expand">
                <template slot-scope="scope">
                    <el-row>
                        <el-col :span="8">
                            <el-form >
                                <el-form-item label="学院名称:">
                                    {{scope.row.collegeName}}
                                </el-form-item>
                                <el-form-item label="学院ID:">
                                    {{scope.row.collegeID}}
                                </el-form-item>
                                <el-form-item label="专业:">
                                    <span v-for="(item,index) in scope.row.majorName" :key="index">
                                        {{item}}
                                    </span>

                                </el-form-item>
                                <el-form-item label="班级数量:">
                                    {{scope.row.className.length}}
                                </el-form-item>
                                <el-form-item label="教师数量:">
                                    {{scope.row.teacherNum}}
                                </el-form-item>
                                <el-form-item label="实验室数量:">
                                    {{scope.row.laboratoryNum}}
                                </el-form-item>
                            </el-form>
                        </el-col>

                        <el-col :span="15" :offset="1">
                            <el-form>
                                <el-form-item  label="班级:">
                                    <br>
                                    <div style="width: 16%;float: left" v-for="(item,index) in scope.row.className" :key="index">
                                        {{item}}
                                    </div>

                                </el-form-item>
                            </el-form>
                        </el-col>
                    </el-row>

                </template>
            </el-table-column>
            <el-table-column
                    label=" "
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="collegeID"
                    label="学院ID"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="collegeName"
                    label="学院名称"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="majorName.length"
                    label="专业数量">
            </el-table-column>
            <el-table-column
                    prop="className.length"
                    label="班级数量">
            </el-table-column>
            <el-table-column
                    prop="teacherNum"
                    label="教师数量">
            </el-table-column>
            <el-table-column
                    prop="laboratoryNum"
                    label="实验室数量">
            </el-table-column>
        </el-table>


        <el-dialog
                :visible.sync="dialogVisible"
                width="50%">
        </el-dialog>


        <div class="page-btn " style=" float: right; font-size: 16px;color: #666;margin: 10px 5% 0 0">
            <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
            <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
            <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
        </div>

    </div>
</template>

<script>
    export default {
        name: "adminCollege",
        data() {
            return {
                editCollegeCourse:[],
                form:{
                    name:""
                },
                schoolYear:'',
                schoolMonth:'',
                tableData:[{
                    collegeName:"大数据学院",//学院名称
                    collegeID:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:["网络工程","数据科学与科学计算","人工智能"]
                },{
                    collegeName:"大数据学院",//学院名称
                    collegeID:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:["网络工程","数据科学与科学计算","人工智能","网络工程","网络工程"]

                },{
                    collegeName:"大数据学院",//学院名称
                    collegeID:"1005",//学院ID
                    majorNum: 5,// 专业数量
                    classNum: 18 ,//班级数量
                    teacherNum :41 ,//教师数量
                    laboratoryNum: 7,// 实验室数量
                    className:["网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",
                        "网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171","网工171",],
                    majorName:["网络工程","数据科学与科学计算","人工智能"]

                }],

                dialogVisible:false, //新增课程弹出
                page:1,
                pageOver:false,
                count:10,
                offset:0,
            }
        },
        methods:{
            changePage(page){
                this.page = page
                let cnt = {
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                //this.getCourseOutlineByTermId(cnt)
            },
        },
        mounted(){

            //获取课程大纲
            let cnt={
                courseCollege:"大数据",
                offset:this.offset,
                count:this.count
            }
           // this.getCourseOutlineByTermId(cnt)
        },
    }
</script>

<style >
    .elform .el-form-item__label{
        color:#9e9e9e
    }
    .elform .el-form-item{
        margin-bottom: 10px;
    }
 </style>
