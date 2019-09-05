<template>
    <div class="changeTable">
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
        data() {
            return {
                week:["星期一","星期二","星期三","星期四","星期五","星期六","星期天"],
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
                ]
            };
        },
        methods: {


        },
        mounted(){

        }
    };
</script>

<style >
     .occupy {

        background: #faa7a1;
    }

     .free {
        background: #f0f9eb;

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
        border-bottom: 1px solid #fff;;
        border-right: 1px solid #fff;;
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
