<!--******   学院工作量   ***********-->
<template>
    <div class="changeInput">
        <page-title title-text="xxx学院课程大纲"></page-title>



            <el-row class="row-box" >

                    <el-button type="primary" class="buttonMarginLeft" @click="$refs.createDia.openCreate(40)">新增课程</el-button>
                    <el-button type="primary" style="margin-left: 30px"  @click="importModal =true">批量导入</el-button>

            </el-row>
            <el-row class="row-box">
            <el-table
                    :data="tableData"
                    class="tableWidthMargin">
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
                        label="课程学分"
                        width="90">
                </el-table-column>
                <el-table-column
                        prop="courseTime"
                        label="学时"
                        width="90">
                </el-table-column>
                <el-table-column
                        prop="assessmentMode"
                        label="考核方式">
                </el-table-column>
                <!--<el-table-column-->
                        <!--prop="courseNature"-->
                        <!--label="课程性质">-->
                    <!--<template slot="header" slot-scope="scope">-->
                        <!--<el-select v-model="courseAge" placeholder="课程性质" size="mini" @change="changePage(1)">-->
                            <!--<el-option-->
                                    <!--v-for="item in tableLevel"-->
                                    <!--:key="item.collegeName"-->
                                    <!--:value="item">-->
                            <!--</el-option>-->
                        <!--</el-select>-->
                    <!--</template>-->
                <!--</el-table-column>-->
                <el-table-column
                        style="margin-left: 20px"
                        prop="courseMajor"
                        label="上课年纪">
                    <template slot="header" slot-scope="scope">
                        <el-select v-model="courseAge" placeholder="年纪" size="mini" @change="changePage(1)">
                            <el-option
                                    v-for="item in 8"
                                    :key="item"
                                    :label="$getCourseAge(item)"
                                    :value="item">
                            </el-option>
                        </el-select>
                    </template>
                    <template slot-scope="scope">
                            <!--{{scope.row.courseMajor}} - -->
                        {{$getCourseAge(scope.row.courseAge)}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="collegeName"
                        label="开课学院"
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

        <two-dialog ref="delDia">
            <div>
                <el-input
                        type="textarea"
                        placeholder="删除备注 30字以内"
                        v-model="delRemark"
                        maxlength="30"
                        resize="none"
                        :autosize="{minRows:3,maxRows:5}"
                        show-word-limit>
                </el-input>
                <el-button @click="delCourseOutline(del)" type="danger" style="margin: 30px 0 0 80%">确认删除</el-button>

            </div>

        </two-dialog>

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
        <two-dialog ref="editDia">
                <edit ref="edit"  :editCollegeCourse="editCollegeCourse"></edit>

        </two-dialog>

        <!--新增课程大纲-->
        <two-dialog ref="createDia">
            <new-curriculum></new-curriculum>
        </two-dialog>

        <next-page ref="nextPage"  @transferRandom="changePage" />


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
                tableLevel:["大一上","大一下","大二上","大二下","大三上","大三下","大四上","大四下",],
                schoolYear:'',
                schoolMonth:'',
                courseAge:"",
                delCour:false,//删除大纲
                del:"",//获取行
                delRemark:"",//删除备注
                tableData:[{
                    collegeName:"大数据",
                    courseCode:10,//课程编码
                    courseName: '高数1',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    courseMajor: "123,456",// 上课专业
                    courseAge: 1 ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                    courseExamStatus:"agree"//审核状态
                },{
                    collegeName:"大数据",
                    courseCode:10,//课程编码
                    courseName: '高数2',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    courseMajor: "大数据",// 上课专业
                    courseAge: 2 ,//上课年纪
                    courseCredit: 3,// 课程学分
                    courseTime: 36 ,//课程学时
                    courseExamStatus:"disagree"//审核状态

                },{
                    collegeName:"大数据",
                    courseCode:10,//课程编码
                    courseName: '线性代数',// 课程名称
                    assessmentMode: '考试' ,//考核方式
                    courseNature :"通时必修" ,//课程性质
                    courseMajor: "大数据",// 上课专业
                    courseAge: 2 ,//上课年纪
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
                //editCourse:false,//编辑大纲弹窗
                clickEditNum:0,   //调用子组件函数 清除表单验证
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


            changePage(nextCnt){
                nextCnt.collegeId="sssss"
                this.getCourseOutlineByTermId(nextCnt)
            },

            //获取开设课程
            getCourseOutlineByTermId(cnt){
                this.$college.getCourseOutlineByTermId(cnt,(res)=>{
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




            //点击删除
            delCourse(row){
                this.del=row
                this.$refs.delDia.openDel(40)
            },

            //删除大纲
            delCourseOutline(row){
                let cnt={
                    courseCode:row.code,
                    delRemark:this.delRemark
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
                this.$refs.editDia.openEdit(40)

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
                collegeId:"123",
                offset:this.offset,
                count:this.count
            }
            this.getCourseOutlineByTermId(cnt)

            //获取选择学院

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
    .changeInput/deep/ {
        .el-select{
            padding-left: 0;
        }
        .el-input{
            padding-left: 0;
        }
    }
</style>
