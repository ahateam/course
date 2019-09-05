<template>
    <div class="changeTable">
        <page-title title-text="教师档期"></page-title>
        <el-row class="row-box" >
            <el-col style="width: 287px" class="term">
                <el-breadcrumb separator-class="el-icon-arrow-right" style="line-height: 40px;">
                    <el-breadcrumb-item :to="{ path: '/adminTeacher' }">教师管理</el-breadcrumb-item>
                    <el-breadcrumb-item>教师档期 {{teacher.username}} {{teacher.teacherName}}</el-breadcrumb-item>
                </el-breadcrumb>
            </el-col>

            <el-col  style="width: 460px" >
                 <span class="term">
                    <el-select v-model="termId" placeholder="请选学期" style="border:0" @change="getTermChioce">
                        <el-option
                                v-for="item in term"
                                :key="item.termId"
                                :label="item.termName"
                                :value="item.termId">
                        </el-option>
                    </el-select>
                 </span>
                <el-button @click="week--" :disabled="week===1">上一周</el-button>
                <span class="week">
                    <el-select v-model="week" placeholder="选择周">
                    <el-option
                            v-for="item in termChioce[0].weekCount"
                            :key="item"
                            :value="item">
                    </el-option>
                </el-select>
                </span>

                <el-button @click="week++" :disabled="week===termChioce[0].weekCount">下一周</el-button>
            </el-col>
        </el-row>
        <occupy></occupy>
    </div>
</template>

<script>
    import occupy from "../occupy/occupy"
    export default {
        name: "adminOccupyTeacher",
        data(){
            return{
                teacher:{},
                options:20,
                week: '',
                termId:1231321321,//选择学期
                term:[{
                        termId:1231321321,
                        termName:'2019-2020 第二学期',  //学期名称
                        weekCount:20, //周数
                        startDate:'1566748800000',   //开始时间
                        endDate:'',      //结束时间
                        remark:'无',     //备注
                    },
                    {
                        termId:123132213215,
                        termName:'2019-2020 第一学期',  //学期名称
                        weekCount:5, //周数
                        startDate:'1551628800000',   //开始时间
                        endDate:'',      //结束时间
                        remark:'无',     //备注
                    }],
                termChioce:[{
                    termId:1231321321,
                    termName:'2019-2020 第二学期',  //学期名称
                    weekCount:20, //周数
                    startDate:'1566748800000',   //开始时间
                    endDate:'',      //结束时间
                    remark:'无',     //备注
                }]
            }
        },
        methods:{
            getTermChioce(){//选择学期后
                this.termChioce=this.term.filter((val,index,arr)=>{
                        if(val.termId===this.termId) return val
                    })
                this.week=1

                console.log(this.termChioce)
            }
        },
        mounted(){
            this.teacher=JSON.parse(sessionStorage.getItem("occupyTeacher"))


            let date=(new Date()).valueOf()
            this.week=parseInt((date-this.termChioce[0].startDate)/(7*24*60*60*1000)) +1
            console.log(  parseInt((date-this.termChioce[0].startDate)/(7*24*60*60*1000)) +1   )//获取本学期当前周
        },
        components:{
            occupy
        }
    }
</script>

<style scoped lang="scss">

    .term /deep/ .el-input__inner{
        border: 0 !important;
        color:#000000;
        width: 180px;
    }
    .week /deep/ .el-select{
        width: 90px;
    }

    .row-box{
        background: #fff;
        font-size: 1.6rem;

    }
    .nav-btn {
        float: left;
    }

    .row-box1 {
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
    }
    .row-box1{
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
