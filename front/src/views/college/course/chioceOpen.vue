<template>
    <div>
        <page-title title-text="选择课程"></page-title>
        <el-table
                v-show="disableTable"
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
                    prop="collegeId"
                    label="开课学院">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="look.collegeId" placeholder="管理学院"  @change="lookupOpen()">
                        <el-option
                                value=""
                                label="全部">
                        </el-option>
                        <el-option
                                v-for="item in $store.state.tableCollege"
                                :key="item.collegeId"
                                :label="item.collegeName"
                                :value="item.collegeId">
                        </el-option>
                    </el-select>
                </template>
                <template slot-scope="scope">
                    {{$getCollegeName(scope.row.collegeId)}}
                </template>
            </el-table-column>
            <el-table-column
                    align="center"
                    fixed="right"
                    width="50"
                    label="操作">
                <template slot-scope="scope">
                    <!--<el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
                    <el-button type="text" size="small" @click="chioce(scope.row)">选择</el-button>

                </template>
            </el-table-column>
        </el-table>
        <next-page ref="nextPage"  @transferRandom="changePage" />
    </div>
</template>

<script>
    export default {
        name: "chioceOpen",
        data(){
            return{
                tableData:[{courseName:"高数",courseCode:123,courseTime:36,collegeId: 456},{},{},{},{},{},{},{},{},{}],
                lookup:{
                    courseName:"0",
                    collegeId:0
                },
                look:{
                    courseName:"",
                    collegeId:""
                },
                disableTable:true
            }
        },
        methods:{
            changePage(nextCnt){
                // this.page = page
                // let cnt = {
                //     collegeID:79839873973,
                //     count:this.count,
                //     offset:(this.page-1)*this.count
                // }
                if(this.look.courseName===""){nextCnt.courseName=this.lookup.courseName}else{
                    nextCnt.courseName=this.look.courseName
                }
                if(this.look.collegeId===""){nextCnt.collegeId=this.lookup.collegeId}else{
                    nextCnt.collegeId=this.look.collegeId
                }
                nextCnt.collegeOpenExamStatus="agree"
                this.getCollegeOpen(nextCnt)
            },

            //获取开设课程
            getCollegeOpen(cnt){
                this.$college.getCollegeOpen(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = [...this.$util.tryParseJson(res.data.c)]
                        console.log(this.tableData)
                        console.log(this.tableData.length  +"changdu")
                        this.disableTable=false
                        this.disableTable=true
                    }else{
                        this.tableData = []
                    }
                    //判断是否到达最后一页
                    // setTimeout(()=>{
                    //     this.$refs.nextPage.judge(this.tableData.length)
                    // },1)
                    //console.log(typeof this.tableData)

                })
            },
            lookupOpen(){
                this.$refs.nextPage.defaultPage()
                let cnt={
                    offset:0,
                    count:this.$store.state.count,
                }
                if(this.look.collegeId===""){cnt.collegeId=this.lookup.collegeId}else{
                    cnt.collegeId=this.look.collegeId
                }
                if(this.look.courseName===""){cnt.courseName=this.lookup.courseName}else{
                    cnt.courseName=this.look.courseName
                }
                cnt.collegeOpenExamStatus="agree"
                //console.log(cnt)
                this.getCollegeOpen(cnt)

            },
            chioce(row){
                this.$emit('transferRandom', row)
            }
        },
        mounted(){
            this.lookupOpen()
        }
    }
</script>

<style scoped>

</style>
