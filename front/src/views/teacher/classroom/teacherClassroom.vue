<template>
    <div class="applyRoom">
        <pageTitle title-text="申请实验室"></pageTitle>
        <div class="screen">
            <el-date-picker
                    v-model="timeApply"
                    type="date"
                    placeholder="选择日期">
            </el-date-picker>—

            <el-select v-model="buildNumber" placeholder="实验室大楼">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>—

            <el-select v-model="roomNumber" placeholder="房间号">
                <el-option>
                </el-option>
            </el-select>
        </div>

        <el-table
                border
                :data="tableData"
                class="table"
                max-height="250">
            <el-table-column
                    type="index"
                    label="序号"
                    width="70">
            </el-table-column>
            <el-table-column
                    prop="time"
                    label="时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="laboratoryBuild"
                    label="实验大楼"
                    width="140">
            </el-table-column>
            <el-table-column
                    prop="roomNum"
                    label="房间号"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="roomName"
                    label="实验室名称"
                    width="140">
            </el-table-column>
            <el-table-column
                    prop="face"
                    label="面向专业"
                    width="140">
            </el-table-column>
            <el-table-column
                    prop="peopleNum"
                    label="容纳人数"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="person"
                    label="负责人"
                    >
            </el-table-column>
            <el-table-column
                    align="center"
                    label="操作"
                    width="120">
                <template slot-scope="scope">

                    <el-button
                            slot="reference"
                            @click="applyRoom(tableData[scope.$index])"
                            type="text"
                            size="small">申请
                    </el-button>
                    <el-button
                            @click.native.prevent="deleteRow(scope.$index, tableData)"
                            type="text"
                            size="small">详情
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import Res from './res'
    import application from "./teacherApplication"
    export default{
        data(){
            return{
                timeApply:"",  //申请时间
                buildNumber:"",//实验室大楼
                roomNumber:"",//房间号
                tableData:[
                    {
                        laboratoryBuild:"第三实验楼",
                        roomNum:"309",
                        roomName:"物理实验室",
                        face:"全校各专业",
                        peopleNum:"25",
                        person:"王越"
                    },
                    {}],
                res:""
            }
        },
        methods: {
            deleteRow(index, rows) {
                //rows.splice(index, 1);
            },

            //申请教室
            applyRoom(res){
                let {$router} = this
                $router.push("/teacherApplication")
                Res.$emit('transmit', res)
            }
        },
        components:{
            application
        }
    }
</script>
<style scoped>
    .applyRoom{
    }

    .pageTitle{
        text-align: center;

    }

    .screen{
        margin-left: 5%;
        height: 50px;
        width: 70%;
    }

    .table{
        width:90%;
        margin-left: 5%;
    }
</style>