<template>
    <div class="changeDia">
        <page-title title-text="选择教师"></page-title>
        <el-table

                :data="tableData"
                class="tableWidthMargin">
            <el-table-column
                    label="序号"
                    type="index"
                    width="50">
            </el-table-column>

            <el-table-column
                    prop="teacherName"
                    label="姓名">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="teacherName"
                            size="mini"
                            clearable
                            @change="lookupTeacher()"
                            placeholder="姓名"/>
                </template>
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="工号">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="username"
                            size="mini"
                            clearable
                            @change="lookupTeacher()"
                            placeholder="工号"/>
                </template>
            </el-table-column>
            <el-table-column
                    prop="teacherPosition"
                    label="职称">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="140">
                <template slot-scope="scope">
                    <el-button @click="chioce(scope.row)" type="text" size="small">选择</el-button>
                </template>
            </el-table-column>
        </el-table>




        <next-page :setup-count="6" ref="nextPage"  @transferRandom="changePage" />

    </div>
</template>

<script>
    export default {
        name: "adminTeacher",
        data() {
            return {
                page: 1,
                pageOver: false,
                count: 10,
                offset: 0,
                tableData: [{teacherName: "www", username: 187852154654, teacherPhone: '1'}, {
                    teacherName: "www",
                    username: 187852154654,
                    teacherPhone: '3'
                }
                    , {teacherName: "www", username: 187852154654, teacherPhone: '3'}, {
                        teacherName: "www",
                        username: 187852154654,
                        teacherPhone: '3'
                    },
                    {teacherName: "www", username: 187852154654, teacherPhone: '1'}, {
                        teacherName: "www",
                        username: 187852154654,
                        teacherPhone: '3'
                    },{teacherName: "www", username: 187852154654, teacherPhone: '3'}, {
                        teacherName: "www",
                        username: 187852154654,
                        teacherPhone: '3'
                    },{teacherName: "www", username: 187852154654, teacherPhone: '3'}, {
                        teacherName: "www",
                        username: 187852154654,
                        teacherPhone: '3'
                    },.0
                ],
                tableCollege: [],//选择学院
                collegeId: "",  //选择选择器后得到

                username: "",//工号搜索教师
                teacherName: "",//输入框的

                lookup: {
                    username: 0,
                    teacherName: "0",
                    collegeId: 0
                },
            }
        },


        methods: {
            chioce(row) {
                this.$emit('transferRandom', row)
            },


            //获取教师信息

            changePage(nextCnt) {
                //let cnt =nextCnt.cnt
                //如果选择学院后 获取选择学院的教师信息
                //if(this.collegeId!=="") nextCnt.collegeId=this.collegeId
                nextCnt.offset=parseInt(nextCnt.offset)*0.6
                nextCnt.count=6
                //console.log(nextCnt)
                    this.lookupCollegeTeacher(nextCnt)
            },
            lookupTeacher() {

                this.$refs.nextPage.defaultPage()
                let cnt = {
                    count: 6,
                    offset: 0,

                }//查询为空时

                    this.lookupCollegeTeacher(cnt)

            },
            lookupCollegeTeacher(cnt) {
                cnt.collegeId=this.$store.state.teacherInformation.collegeId
                if(this.teacherName===""){cnt.teacherName="0"}else{
                    cnt.teacherName=this.teacherName
                }
                if (this.username === "") {
                    cnt.username = this.lookup.username
                } else {
                    cnt.username = this.username
                }
                //console.log(cnt)

                this.$college.lookupCollegeTeacher(cnt, (res) => {
                    if (res.data.rc === this.$util.RC.SUCCESS) {
                        this.tableData = this.$util.tryParseJson(res.data.c)
                        console.log(this.tableData)
                    } else {
                        this.tableData = []
                    }
                    this.$refs.nextPage.judge(this.tableData.length+4)
                })
            },


        },
        mounted() {

            console.log("选择较上次")
            this.lookupTeacher()

            //获取选择学院

        }
    }
</script>

<style scoped>


    .changeDia/deep/ .el-dialog__body{
        padding: 5px 20px;
    }
</style>
