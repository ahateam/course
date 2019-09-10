<template>
    <div class="changeTable">
        <div class="changeTable">
            <page-title title-text="教师档期"></page-title>
            <el-row class="row-box" >
                <el-col style="width: 287px" class="term">
                    <el-breadcrumb separator-class="el-icon-arrow-right" style="line-height: 40px;">
                        <el-breadcrumb-item :to="{ path: pass.path }">{{pass.title}}</el-breadcrumb-item>
                        <el-breadcrumb-item>{{pass.id}} {{pass.name}}</el-breadcrumb-item>
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
                    <el-button @click="weeks--" :disabled="weeks===1">上一周</el-button>
                    <span class="week">
                    <el-select v-model="weeks" placeholder="选择周">
                    <el-option
                            v-for="item in termChioce[0].weekCount"
                            :key="item"
                            :value="item">
                    </el-option>
                </el-select>
                </span>

                    <el-button @click="weeks++" :disabled="weeks===termChioce[0].weekCount">下一周</el-button>
                </el-col>
            </el-row>
        </div>

        <el-row style="font-size: 14px;margin:10px 0 0 2%;text-align: center;width: 96%">

            <!--         表头                 -->
            <el-row style="height:48px;color:#606266;font-weight: 600;line-height: 48px;">
                <el-col style=" " class="headBorder leftBorder timeWidth">时间</el-col>
                <el-col style="" class="headBorder timeWidth">节数</el-col>
                <el-col style="width: 13.1%;min-width: 70px" class="headBorder" v-for="item in week" :key="item">{{item}}</el-col>
            </el-row>
            <!--         内容                 -->
            <el-row style="color:#606266;">
                    <!--      时间    -->
                <el-col style="height: 578px;color:#606266;font-weight: 600" class="timeWidth">
                    <el-row style="height: 192px;line-height: 192px" class="mainBorder leftBorder">早上</el-row>
                    <el-row style="height: 96px;line-height: 96px" class="mainBorder leftBorder">中午</el-row>
                    <el-row style="height: 192px;line-height: 192px" class="mainBorder leftBorder">下午</el-row>
                    <el-row style="height: 96px;line-height: 96px" class="mainBorder leftBorder">晚上</el-row>
                </el-col>
                <!--      第几节    -->
                <el-col style="" class="timeWidth">
                    <el-row style="height: 48px;line-height: 48px" class="mainBorder " v-for="item in 12" :key="item">{{item}}</el-row>
                </el-col>
                <el-col style="width: 13.1%;" v-for="week in 7" :key="week"><!--星期-->
                    <el-row class="mainHeight mainBorder" v-for="section in 6" :key="section"><!--节-->
                        <span v-if="week===1">
                            <div v-if="tableData[2*section-2].mon===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].mon===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===2">
                            <div v-if="tableData[2*section-2].tue===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].tue===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===3">
                            <div v-if="tableData[2*section-2].wed===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].wed===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox" ></div>
                        </span>


                        <span v-if="week===4">
                            <div v-if="tableData[2*section-2].thu===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].thu===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===5">
                            <div v-if="tableData[2*section-2].fri===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox" ></div>

                            <div v-if="tableData[2*section-1].fri===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===6">
                            <div v-if="tableData[2*section-2].sat===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].sat===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>

                        <span v-if="week===7">
                            <div v-if="tableData[2*section-2].sun===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>

                            <div v-if="tableData[2*section-1].sun===1"  class="occupy colorBox"></div>
                            <div v-else  class="free colorBox"></div>
                        </span>


                    </el-row>
                </el-col>
            </el-row>
        </el-row>
    </div>
</template>

<script>
    export default {
        props:["pass"],
        data() {
            return {
                week:["星期一","星期二","星期三","星期四","星期五","星期六","星期天"],//表头星期
                information: [
                    {time: '早上', section: '1',},
                    {time: '早上', section: '2',},
                    {time: '早上', section: '3',},
                    {time: '早上', section: '4',},

                    {time: '中午', section: '5',},
                    {time: '中午', section: '6',},

                    {time: '下午', section: '7',},
                    {time: '下午', section: '8',},
                    {time: '下午', section: '9',},
                    {time: '下午', section: '10',},

                    {time: '晚上', section: '11',},
                    {time: '晚上', section: '12',},
                ],
                tableData:[
                    {mon:0,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                    {mon:0,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                    {mon:1,tue:0,wed:0,thu:0,fri:0,sat:0,sun:0},
                    {mon:1,tue:0,wed:0,thu:0,fri:0,sat:0,sun:0},
                    {mon:0,tue:0,wed:0,thu:0,fri:1,sat:1,sun:1},
                    {mon:0,tue:0,wed:0,thu:0,fri:1,sat:1,sun:1},
                    {mon:1,tue:1,wed:1,thu:0,fri:0,sat:1,sun:0},
                    {mon:1,tue:1,wed:1,thu:0,fri:0,sat:1,sun:0},
                    {mon:1,tue:1,wed:0,thu:0,fri:1,sat:0,sun:0},
                    {mon:1,tue:0,wed:0,thu:0,fri:1,sat:0,sun:0},
                    {mon:0,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                    {mon:0,tue:0,wed:1,thu:0,fri:1,sat:0,sun:1},
                ],
                teacher:{},
                options:20,
                weeks: '',//当前第几周周
                termId:1231321321,//选择学期
                term: [{
                    termId:1231321321,
                    termName:'2019-2020 第二学期',  //学期名称
                    weekCount:20, //周数
                    startDate:'1566748800000',   //开始时间
                    endDate:'',      //结束时间
                    remark:'无',     //备注
                    thisTerm:1
                    },
                    {
                        termId:123132213215,
                        termName:'2019-2020 第一学期',  //学期名称
                        weekCount:5, //周数
                        startDate:'1551628800000',   //开始时间
                        endDate:'',      //结束时间
                        remark:'无',     //备注
                        thisTerm:0
                    },{
                        termId:12313221325,
                        termName:'2019-2020 第一学期',  //学期名称
                        weekCount:5, //周数
                        startDate:'1551628800000',   //开始时间
                        endDate:'',      //结束时间
                        remark:'无',     //备注
                        thisTerm:0
                    }],
                termChioce:""
            };
        },
        methods: {
            //获取学期
            getTerms(){
                let cnt={
                        count:20,
                        offset:0
                }
                this.$admin.getTerms(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.term = this.$util.tryParseJson(res.data.c)
                        this.getNowTerm()
                    }else{
                        this.term = []
                    }
                })
            },

            getTermChioce(){//选择学期后
                this.termChioce=this.term.filter((val,index,arr)=>{
                    if(val.termId===this.termId) return val
                })
                this.weeks=1
                this.getOccupy()
            },

            //获取当前学期
            getNowTerm(){
                if(this.termChioce===""||!this.termChioce){
                    this.termChioce=this.term.filter((val,index,arr)=>{
                        if(val.thisTerm===1) return val
                    })
                    this.termId=this.termChioce[0].termId
                }
                let date=(new Date()).valueOf()
                this.weeks=parseInt((date-this.termChioce[0].startDate)/(7*24*60*60*1000)) +1//获取本学期当前第几周
            },
            //获取档期
            getOccupy(){
                let cnt={
                    termId:this.termId,
                    week:this.weeks,
                }
                if(this.pass.type==="teacher"){
                    this.getOccupyTeacher(cnt)
                }else if(this.pass.type==="class"){
                    this.getOccupyClass(cnt)
                }else if(this.pass.type==="lab"){
                    this.getOccupyLab(cnt)
                }
            },
            getOccupyTeacher(cnt){
                cnt.username=this.pass.id
                this.$admin.getOccupyTeacher(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    this.$refs.nextPage.judge(this.tableData.length)
                })
            },
            getOccupyClass(cnt){
                cnt.classId=this.pass.id
                this.$admin.getOccupyClass(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    this.$refs.nextPage.judge(this.tableData.length)
                })
            },
            getOccupyLab(cnt){
                cnt.labId=this.pass.id
                this.$admin.getOccupyLab(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    this.$refs.nextPage.judge(this.tableData.length)
                })
            }
        },
        beforeMount(){
            this.getTerms()
            this.getNowTerm()
            console.log(this.pass)

        },
        mounted(){
            //this.getOccupy()
        },
        watch:{
            weeks(){
                this.getOccupy()
            },

        }

    };
</script>

<style >
    .term /deep/ .el-input__inner{
        border: 0 !important;
        color:#000000;
        width: 180px;
    }
    .week /deep/ .el-select{
        width: 90px;
    }
     .occupy {

        background: #faa7a1;
    }

     .free {
        background: #fff;

    }
    .headBorder{
        border-bottom: 1px solid #EBEEF5;;
        border-right: 1px solid #EBEEF5;;
        border-top:1px solid #EBEEF5;;
    }
    .leftBorder{
        border-left:1px solid #EBEEF5;;
    }
    .mainBorder{
        border-bottom: 1px solid #EBEEF5;;
        border-right: 1px solid #EBEEF5;;
    }
    .timeWidth{
        width: 3%;
        min-width: 30px;
    }
    .mainHeight{
        height: 96px;
        line-height: 96px;
    }
    .colorBox{
        width: 99.5%;
        height: 47px;
    }
    .classScheduleCard{
        width: 95%;
        margin-top: 20px;
        margin-left: 2%;
        margin-bottom: 30px;
    }

</style>
