<template>
    <div class="workTime">
        <pageTitle title-text="我的工作总量"></pageTitle>
        <div style="margin-bottom: 50px;margin-left: 5%">
            <myInformation></myInformation>
            <el-table
                    stripe
                    :data="tableData"
                    style="width:95%"
                    border>
                <el-table-column type="index" label="序号"></el-table-column>
                <el-table-column prop="curriculumName" label="课程名称" ></el-table-column>
                <el-table-column prop="curriculumNum" label="课程编号"  ></el-table-column>
                <el-table-column prop="class" label="班级" ></el-table-column>
                <el-table-column prop="curriculumNature" label="独立/课带" ></el-table-column>
                <el-table-column prop="executiveHours" label="执行学时"  ></el-table-column>
                <el-table-column prop="nowHours" label="已上学时"  ></el-table-column>
                <el-table-column prop="repetitions" label="重复次数" ></el-table-column>
                <el-table-column  label="工作量=执行学时*(1+1*重复次数)" >
                    <template slot-scope="scope"  >
                        {{tableData[scope.$index].executiveHours*(1+tableData[scope.$index].repetitions)}}
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
    import myInformation from "../components/myInformation"
    export default {
        name: "teacherWork",
        data() {
            return {
                tableData:[],
            }
        },
        methods: {
            tableWorkload({row, rowIndex}) {
                if (rowIndex === 1) {
                    return 'warning-row';
                } else if (rowIndex === 3) {
                    return 'success-row';
                }
                return '';
            },
        },
        mounted(){
            let that =this
            this.$ajax.post("/lecturerInfromation").
            then(function(res){
                that.tableData=res.data.data
                let add=0
                for(let i=0;i<that.tableData.length;i++)
                {
                    add +=that.tableData[i].executiveHours*(1+that.tableData[i].repetitions)
                }that.$store.state.ownWorkTime=add
            })
        },
        components:{
            myInformation
        }
    }
</script>

<style scoped>
.workTime{
    width: 100%;
}
</style>
