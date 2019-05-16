<template>
    <div>
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
        <el-select v-model="college" placeholder="学院" >
            <el-option
                    v-for="item in month"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
        </el-select>
        <div style="text-align: center">

            <span style="font-size: 30px">xxx学院实验课程工作量统计</span><br>
            <span style="margin-left:45%">应有工作总量</span><span>.......</span>
            <span>实际工作总量</span><span>.......</span>
            <el-table
                    :data="workTime"
                    border
                    style="width: 90%;margin-left: 5%;margin-top: 10px">
                <el-table-column
                        prop="lecturerName"
                        label="姓名"
                >
                </el-table-column>
                <el-table-column
                        prop="workNum"
                        label="工号"
                >
                </el-table-column>
                <el-table-column
                        prop="position"
                        label="职称"
                >
                </el-table-column>

                <el-table-column
                        prop="subordinateDepartments"
                        label="所属学院/部门">
                </el-table-column>
                <el-table-column
                        label="实验课工作量">{{this.$store.state.ownWorkTime}}
                </el-table-column>

                <el-table-column
                        label="详情">详情
                </el-table-column>

            </el-table>
            <el-button type="primary" style="margin-top: 10px">审核通过</el-button>
            <el-button type="danger">审核异常</el-button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "adminWork",
        data() {
            return {
                year: [{
                    value: '2018',
                }, {
                    value: '2019',
                }],
                month: [{
                    label:"第一学期",
                    value: 'one',
                }, {
                    label:"第二学期",
                    value: 'two',
                }],
                college: [{
                    label:"大数据",
                    value: 'one',
                }, {
                    label:"材料",
                    value: 'two',
                }],
                workTime: [{
                    lecturerName: '王小虎',
                    workNum: 123456,
                    position: '副教授',
                    subordinateDepartments: 'xxx学院',
                },
                    {
                        lecturerName: '王3',
                        workNum: 123456,
                        position: '副教授',
                        subordinateDepartments: 'xxx学院',
                    },
                    {
                        lecturerName: '王2',
                        workNum: 123456,
                        position: '副教授',
                        subordinateDepartments: 'xxx学院',
                    }
                ],
                schoolYear: '',
                schoolMonth:""
            }
        }
    }
</script>

<style scoped>

</style>
