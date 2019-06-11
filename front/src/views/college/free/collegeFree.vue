<template>
    <div><page-title title-text="xxx学院工作量统计"></page-title>
        <div>
            <el-row class="row-box" >
                <el-col :span="24">
                        <el-button type="primary" class="nav-btn"  @click="importModal =true">批量导入档期</el-button>
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
                                        prop="grade"
                                        label="年级">
                                </el-table-column>
                                <el-table-column
                                        prop="courseNode"
                                        label="课程名称"
                                >
                                </el-table-column>
                                <el-table-column
                                        prop="courseType"
                                        label="课程类别"
                                        >
                                </el-table-column>
                                <el-table-column
                                        prop="teacherName"
                                        label="教师姓名"
                                >
                                </el-table-column>
                                <el-table-column
                                        prop="studentNumber"
                                        label="学生数">
                                </el-table-column>

                                <el-table-column
                                        prop="college"
                                        label="上课地点"
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

            <el-dialog
                    title="删除信息"
                    :visible.sync="dialogVisible"
                    width="30%">
                <span>是否确认删除该信息</span>
                <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary"  @click="delBtn" >确 定</el-button>
            </span>
            </el-dialog>


            <!--批量导入弹窗-->
            <el-dialog
                    title="上传课程大纲表格"
                    :visible.sync="importModal"
                    width="30%"
                    :before-close="handleClose"
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


        </div>
   </div>
</template>

<script>
    import ossAuth from '@/assets/oss/ossAuth'
    let client = ossAuth.client
    export default {
        name: "free",
        data(){
            return{
                tableData:[],
                page:1,
                count:10,
                offset:0,
                pageOver:false,

                delId:'',
                dialogVisible:false,

                //上传文件相关
                importModal:false,
                fileName:'',    //文件上传
                fileData:[],
                url:'',         //文件的线上地址
                num:0,          //上传进度
                multipleSelection:[],
                address:'',     //导入地址
            }
        },
        methods:{



            //对接接口
            //请求数据列表
            getSchedule(cnt){
              this.$college.getSchedule(cnt,(res)=>{

                  if(res.data.rc == this.$util.RC.SUCCESS){
                      this.tableData = this.$util.tryParseJson(res.data.c)
                  }else{
                      this.tableData = []
                  }
                  if(this.tableData.length<this.count){
                      this.pageOver = true
                  }else{
                      this.pageOver = false
                  }
              })
            },


            //删除单个数据
            delModal(info){
                this.delId = info.id
                this.dialogVisible = true
            },
            delBtn(){
                let cnt = {
                    schId: this.delId, // Long 课表id
                };
                this.$college.deleteSchedule(cnt,(res)=>{
                    if(res.data.rc == this.$util.RC.SUCCESS){
                        this.$message.success('删除成功')
                    }else{
                        this.$message.error('删除失败')
                    }
                    this.$router.push('/page')
                })
            },
            //分页
            changePage(page){
                this.page = page
                let cnt = {
                    count: this.count, // Integer
                    offset: (this.page-1)*this.count, // Integer
                }
                this.getSchedule(cnt)

            },


            //导入表格到后端数据库
            importSchedules(cnt){
                this.$college.importSchedules(cnt,(res)=>{
                    if(res.data.rc == this.$util.RC.SUCCESS){
                        this.$message.success('导入成功')
                        this.$router.push('/page')
                    }else{
                        this.$message.error('导入失败')
                        this.$router.push('/page')
                    }
                })
            },

            //上传文件相关
            closeBtn() {
                this.fileName = ''
                this.fileData = []
                this.url = ''
            },
            //讲已经导入到oss的文件传递给服务端进行数据库导入
            importFile( ){
                const loading = this.$loading({
                    lock: true,
                    text: '正在导入数据库，请耐心等待...',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                if(this.url != '' || this.url != undefined){
                    let cnt = {
                        url:this.url
                    }
                    this.importSchedules(cnt)
                    loading.close()
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
        },
        mounted(){

            //获取第一页的数据列表
            let cnt = {
                count: this.count, // Integer
                offset: this.offset, // Integer
            }
            this.getSchedule(cnt)






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
            this.address = 'free/college/'+year+month+day+'/'
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
