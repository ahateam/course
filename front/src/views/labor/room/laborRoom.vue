<template>
    <div>
        <page-title title-text="xxx学院实验管理"></page-title>
        <el-button type="primary" class="buttonMarginLeft" @click="dialogVisible = true">新增实验室</el-button>

        <el-table
                :data="tableData"
                border
                class="tableWidthMargin">
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
                    label="地点">
                <template slot-scope="scope">
                    {{scope.row.labBuildName}}-{{scope.row.labRoomNum}}
                </template>
            </el-table-column>
            <!--<el-table-column-->
                    <!--label="管理部门">-->
            <!--</el-table-column>-->
            <!--<el-table-column-->
                    <!--prop=""-->
                    <!--label="面向专业">-->
            <!--</el-table-column>-->
            <el-table-column
                    prop="labLiable"
                    label="负责人"
            >
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                    <el-button @click="editInterface(scope.row)" type="text" size="small">编辑</el-button>
                </template>
            </el-table-column>

        </el-table>

        <next-page ref="nextPage"  @transferRandom="changePage" />
        <!--编辑弹框-->
        <el-dialog
                :visible.sync="editVisible"
                width="70%"
                top="40px"
        >
            <span style="font-size: 30px">xxx学院新增实验室</span><br>
            <edit  />
            <span slot="footer" class="dialog-footer">
                <el-button  type="primary" @click="dialogVisible = false" >取消</el-button>
                <el-button slot="reference" type="primary">申请</el-button>
            </span>
        </el-dialog>

        <!--新增实验室的弹出框-->
        <el-dialog
                :visible.sync="dialogVisible"
                width="70%"
                top="40px">
            <new-room  />
            <span slot="footer" class="dialog-footer">
                <el-button  type="primary" @click="dialogVisible = false" >取消</el-button>
                <el-button slot="reference" type="primary">申请</el-button>
            </span>
        </el-dialog>



    </div>
</template>

<script>
    import edit from "./edit"
    import newRoom from "./newRoom"
    export default {
        name: "adminRoom",
        data(){
            return{
                tableData:[{labBuildName:"第三实验楼",labRoomNum:301,labLiable:123,labSeat:34}],
                form:{
                    name:""
                },
                dialogVisible: false,//新增
                editVisible:false,//编辑弹框
                edit:"",//存储值传给edit
                page:1,
                count:10,
                offset:0,
                pageOver:false,

            }
        },
        methods:{
            handleClick(row){},
            handleClose(){},
            changePage(nextCnt){

                this.getCollegeLabor(nextCnt)
            },

            //获取实验室
            getCollegeLabor(cnt){
                this.$labor.getCollegeLabor(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableData = this.$util.tryParseJson(res.data.c)
                    }else{
                        this.tableData = []
                    }
                    if(this.tableData.length <this.count){
                        this.pageOver= true
                    }else{
                        this.pageOver = false
                    }
                })
            },

            //点击编辑按钮
            editInterface(row){
                this.editVisible=true
            }
        },
        components:{newRoom,edit},
        mounted(){
            let cnt={
                offset:this.offset,
                count:this.count
            }
            this.getCollegeLabor(cnt)

        }


    }
</script>
<style  scoped>

</style>
<style>
    .el-table td, .el-table th{
        text-align: center;
    }

    .el-dialog__header /deep/{
        padding: 0;
    }
</style>
