<!--******   学院工作量   ***********-->
<template>
    <div>
        <page-title title-text="xxx学院课程大纲"></page-title>
            <el-row class="row-box" style="text-align: left">


                    <el-select v-model="schoolMonth" placeholder="专业" style="margin-left: 5%" >
                        <el-option
                                v-for="item in month"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>


                    <el-select v-model="schoolMonth" placeholder="年纪" style="margin-left: 30px" >
                        <el-option
                                v-for="item in month"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>

            </el-row>

        <!--新增课程大纲-->
            <el-dialog
                :visible.sync="dialogVisible"
                width="40%"
                :before-close="handleClose">
                <new-curriculum></new-curriculum>
        </el-dialog>

            <el-row class="row-box" >

                    <el-button type="primary" style="margin-left: 5%" @click="dialogVisible = true">新增课程</el-button>
                    <el-button type="primary" style="margin-left: 30px"  @click="importModal =true">批量导入</el-button>

            </el-row>
            <el-row class="row-box">
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
                        prop="courseName"
                        label="课程名称"
                >
                </el-table-column>
                <el-table-column
                        prop="courseCredit"
                        label="课程学分">
                </el-table-column>
                <el-table-column
                        prop="courseTime"
                        label="学时">
                </el-table-column>
                <el-table-column
                        prop="assessmentMode"
                        label="考核方式">
                </el-table-column>
                <el-table-column
                        prop="courseNature"
                        label="课程性质">
                </el-table-column>
                <el-table-column
                        prop="courseMajor"
                        label="上课专业-年纪">
                    <template slot-scope="scope">
                            {{scope.row.courseMajor}} - {{scope.row.courseAge}}
                    </template>
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
                        <el-button type="text" size="small" @click="delCourseOutline(scope.row.courseCode)">删除</el-button>
                    </template>
                </el-table-column>

            </el-table>
            </el-row>



        <!--批量导入弹窗-->
        <el-dialog
                title="上传课程大纲表格"
                :visible.sync="importModal"
                width="30%"
                center
                @close="closeBtn">
            <span>
                        <div class="file-msg">
                            目前只支持后缀为 '.xlsx'的Excl文件.
                        </div>
                        <div class="file-box">
                            <span class="icon-box"><i class="el-icon-upload" style="font-size: 50px"></i></span> <span class="icon-text">上传文件</span>
                             <input type="file" class="input-file-box" @change="fileBtn($event)">
                        </div>

                        <div class="fine-name" v-if="fileName != ''">
                            文件名：{{fileName}}
                        </div>

                        <div class="line" v-if="num >0 && num<100">
                            <div class="line-title">上传进度:</div>
                            <div class="line-box">
                                <el-progress :percentage="num" type="success"></el-progress>
                            </div>
                        </div>

                        <div class="line" v-if="num == 100">
                            <div class="line-title">上传进度:</div>
                            <div class="line-text">
                                上传成功！
                            </div>
                        </div>



            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="importModal = false">取 消</el-button>
                <el-button type="primary"   @click="doUpload"  >确认上传数据表格</el-button>
            </span>
        </el-dialog>

         <!--*******   编辑大纲弹窗   ****-->
        <el-dialog
                :visible.sync="editCourse"
                width="50%">
                <edit ref="edit"  :editCollegeCourse="editCollegeCourse"></edit>

        </el-dialog>


        <div class="page-btn " style=" float: right; font-size: 16px;color: #666;">
            <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
            <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
            <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
        </div>

    </div>


</template>

<script>
    import edit from "./edit"
    import newCurriculum from "./newCurriculum"
    import ossAuth from '@/assets/oss/ossAuth'
    let client = ossAuth.client

    export default {
        name:'collegeCourse',
        data() {
            return {
                editCollegeCourse:[],
                form:{
                    name:""
                },
                schoolYear:'',
                schoolMonth:'',
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
                year:[],
                month:[],

                //上传文件相关
                importModal:false,
                fileName:'',    //文件上传
                fileData:[],
                url:'',         //文件的线上地址
                num:0,          //上传进度
                multipleSelection:[],
                address:'',     //导入地址
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
            onSubmit(){

            },

            //对接接口
            importAssets(cnt){
                console.log(cnt)
            },

            //上传文件相关
            closeBtn() {
                this.fileName = ''
                this.fileData = []
                this.url = ''
            },
            //讲已经导入到oss的文件传递给服务端进行数据库导入
            importFile( ){
                if(this.url != '' || this.url != undefined){
                    let cnt = {
                        url:this.url
                    }
                    this.importAssets(cnt)
                }

            },

            //进度条
            getProgress(p) {
                this.num = p
            },
            //开始导入到oss
            doUpload() {

                let files = []
                files[0] = this.fileData[0]
                this.getProgress(0)
                let file =files
                this.size = file[0].size
                let timer = new Date().getTime()
                let tmpName =timer+ encodeURIComponent(file[0].name)
                tmpName =this.address+ tmpName
                this.multipartUpload(tmpName, file[0])
            },
            //分片上传
            multipartUpload(upName, upFile) {
                //Vue中封装的分片上传方法（详见官方文档）
                let _this = this
                const progress = async function (p) {
                    //项目中需获取进度条，故调用进度回调函数（详见官方文档）
                    // _this.$emit('getProgress', Math.round(p * 100))
                    _this.getProgress(Math.round(p * 100))
                }
                try {
                    let result = client.multipartUpload(upName, upFile, {
                        progress,
                        meta: {
                            year: 2017,
                            people: 'test'
                        }
                    }).then(res => {

                        let address = res.res.requestUrls[0]
                        let _index =address.indexOf('?')
                        if(_index == -1){
                            _this.url = address
                        }else{
                            _this.url = address.substring(0,_index)
                        }
                        console.log(_this.url)
                        _this.importFile()
                        // this.$router.push('/page')
                    }).catch(err => {
                        console.log(result)
                        console.log(err)
                    });

                } catch (e) {
                    // 捕获超时异常
                    if (e.code === 'ConnectionTimeoutError') {
                        console.log("Woops,超时啦!");
                    }
                    console.log(e)
                }
            },
            // 获取文件名显示
            fileBtn(ev) {
                this.fileData = ev.target.files
                this.fileName = this.fileData[0].name
            },
            handleClose(done) {
                done();
            },


            //分页
            changePage(page){
                this.page = page
                let cnt = {
                    courseCollege:"大数据",
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                this.getCourseOutlineByTermId(cnt)
            },

            //获取课程大纲
            getCourseOutlineByTermId(cnt){
                this.$college.getCourseOutlineByTermId(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                    this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    //判断是否到达最后一页
                    if(this.tableData.length <this.count){
                        this.pageOver= true
                    }else{
                        this.pageOver = false
                    }
                })
            },

            //删除大纲
            delCourseOutline(code){
                let cnt={
                    courseCode:code
                }
                this.$college.delCourseOutline(cnt,(res)=> {
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
            //获取年月日
            let date = new Date()
            let year =''+date.getFullYear()
            let month =''+(parseInt(date.getMonth()+1))
            if(month.length<2){
                month = '0'+month
            }
            let day = ''+date.getDate()
            if(day.length<2){
                day ='0'+ day
            }
            //拼接文件名
            this.address = 'teachProgram/college/'+year+month+day+'/'


            //获取课程大纲
            let cnt={
                courseCollege:"大数据",
                offset:this.offset,
                count:this.count
            }
            this.getCourseOutlineByTermId(cnt)
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
