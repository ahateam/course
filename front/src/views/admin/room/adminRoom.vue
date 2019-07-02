<template>
    <div>
        <page-title title-text="实验室管理" />
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
                    label="实验室(中心名称)"
            >
            </el-table-column>
            <el-table-column
                    prop="labName"
                    label="实验室名称"
            >
            </el-table-column>
            <el-table-column
                    prop="labSeat"
                    label="座位数"
            >
            </el-table-column>

            <el-table-column
                    prop="labPlace"
                    label="地点">
            </el-table-column>
            <el-table-column
                    prop="collegeName"
                    label="管理部门">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="collegeId" placeholder="学院" size="mini" @change="changePage(1)">
                        <el-option
                                v-for="item in tableCollege"
                                :key="item.collegeName"
                                :lable="item.collegeName"
                                :value="item.collegeId">
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
            <!--<el-table-column-->
                    <!--label="面向专业">-->
            <!--</el-table-column>-->
            <el-table-column
                    label="负责人">
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
        <el-row>
            <el-col :span="24" style="margin-top: 20px">
                <div class="page-btn " style=" margin-right: 5%;float: right; font-size: 16px;color: #666;">
                    <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
                    <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
                    <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "adminRoom",
        data(){
            return{
                tableData:[{}],//存储教室信息
                tableCollege:[],//获取的学院
                collegeId:"",//选择的学院
                page:1,
                pageOver:false,
                count:10,
                offset:0,
            }
        },
        methods:{

            getSchoolLabor(cnt){
                //如果未选择学院
                if(this.collegeId===""){
                    this.$admin.getSchoolLabor(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                            console.log(this.$util.tryParseJson(res.data.c))
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
                }

                else{
                    
                    //查询某个学院的实验室
                    this.$labor.getCollegeLabor(cnt,(res)=>{
                        if(res.data.rc === this.$util.RC.SUCCESS){
                            this.tableData = this.$util.tryParseJson(res.data.c)
                            console.log(this.$util.tryParseJson(res.data.c))
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
                }
            },
            changePage(page){
                this.page = page
                let cnt = {
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                //如果选择学院后 获取选择学院的教师信息
                if(this.collegeId!=="") cnt.collegeId=this.collegeId
                this.getSchoolLabor(cnt)
            },
          getCollege() {
              let cnt={
                  offset:20,
                  count:0
              }
              this.$admin.getDepartments(cnt,(res)=>{
                  if(res.data.rc === this.$util.RC.SUCCESS){
                      this.tableCollege = this.$util.tryParseJson(res.data.c)
                  }else{
                      this.tableCollege = []
                  }
              })
          }
        },
    mounted(){
        let cnt = {
            count:this.count,
            offset:(this.page-1)*this.count
        }
        this.getSchoolLabor(cnt)
        this.getCollege()
    }
    }
</script>

<style scoped>

</style>
