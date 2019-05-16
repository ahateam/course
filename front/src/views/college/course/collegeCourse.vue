<!--******   学院工作量   ***********-->
<template>
    <div>
            <el-row class="row-box" style="text-align: center">
                <el-select v-model="schoolYear" placeholder="学年" >
                    <el-option
                            v-for="item in year"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
                <el-select v-model="schoolMonth" placeholder="学期" >
                    <el-option
                            v-for="item in month"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
                <el-select v-model="schoolMonth" placeholder="专业" >
                    <el-option
                            v-for="item in month"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
                <el-select v-model="schoolMonth" placeholder="年纪" >
                    <el-option
                            v-for="item in month"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </el-row>
            <el-row class="row-box" style="text-align: center">
                <span style="font-size: 30px">xxx学院实验课程大纲</span><br>
            </el-row>
            <el-row class="row-box" >
                <el-col :span="20" :offset="1">
                    <el-button type="primary">新增课程</el-button>
                    <el-button type="primary" style="margin-left: 30px"  @click="importModal =true">批量导入</el-button>
                </el-col>
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
                        prop="lecturerName"
                        label="课程名称"
                >
                </el-table-column>
                <el-table-column
                        prop="workNum"
                        label="课程编码"
                >
                </el-table-column>
                <el-table-column
                        prop="position"
                        label="课程学分"
                >
                </el-table-column>

                <el-table-column
                        prop="subordinateDepartments"
                        label="学时">
                </el-table-column>
                <el-table-column
                        label="考核方式">
                </el-table-column>
                <el-table-column
                        label="课程性质">
                </el-table-column>
                <el-table-column
                        label="上课年纪">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                        <el-button type="text" size="small">编辑</el-button>
                    </template>
                </el-table-column>

            </el-table>
            </el-row>
            <!--<el-popover-->
                    <!--placement="top"-->
                    <!--width="600"-->
                    <!--trigger="click">-->
                <!--<span style="font-size: 30px">新增实验课程</span>-->
                <!--<el-form ref="form" :model="form" label-width="80px" class="form">-->
                    <!--<el-form-item label="课程名称" >-->
                        <!--<el-input v-model="form.name"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="课程学分">-->
                        <!--<el-input v-model="form.name"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="课程学时">-->
                        <!--<el-input v-model="form.name"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="面向专业">-->
                        <!--<el-input v-model="form.name"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="考核方式">-->
                        <!--<el-input v-model="form.name"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="课程性质">-->
                        <!--<el-input v-model="form.name"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item size="large">-->
                        <!--<el-button type="primary" @click="onSubmit">立即创建</el-button>-->
                        <!--<el-button>取消</el-button>-->
                    <!--</el-form-item>-->
                <!--</el-form>-->
            <!--</el-popover>-->


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


</template>

<script>
    import ossAuth from '@/assets/oss/ossAuth'
    let client = ossAuth.client

    export default {
        name:'collegeCourse',
        data() {
            return {
                form:{
                    name:""
                },
                schoolYear:'',
                schoolMonth:'',
                tableData:[],
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
        },
        mounted(){
            //获取年月日
            let date = new Date()
            let year =''+date.getFullYear()
            let month =''+date.getMonth()+1
            if(month.length<2){
                month = '0'+month
            }
            let day = ''+date.getDate()
            if(day.length<2){
                day ='0'+ day
            }
            //拼接文件名
            this.address = 'teachProgram/college/'+year+month+day+'/'
        }

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
