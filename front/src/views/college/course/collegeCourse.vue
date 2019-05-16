<!--******   学院工作量   ***********-->
<template>
    <div style="text-align: center">
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
        <el-select v-model="schoolMonth" placeholder="专业" >
            <el-option
                    v-for="item in month"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
        </el-select>
        <el-select v-model="schoolMonth" placeholder="年纪" >
            <el-option
                    v-for="item in month"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
        </el-select>
        <br>
        <span style="font-size: 30px">xxx学院实验课程大纲</span><br>

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
                    label="课程名称"
            >
            </el-table-column>
            <el-table-column
                    prop="workNum"
                    label="课程编码"
            >
            </el-table-column>
            <el-table-column
                    prop="position"
                    label="课程学分"
            >
            </el-table-column>

            <el-table-column
                    prop="subordinateDepartments"
                    label="学时">
            </el-table-column>
            <el-table-column
                    label="考核方式">
            </el-table-column>
            <el-table-column
                    label="课程性质">
            </el-table-column>
            <el-table-column
                    label="上课年纪">
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
        <el-popover
                placement="top"
                width="600"
                trigger="click">
            <span style="font-size: 30px">新增实验课程</span>
            <el-form ref="form" :model="form" label-width="80px" class="form">
                <el-form-item label="课程名称" >
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="课程学分">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="课程学时">
                <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="面向专业">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="考核方式">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="课程性质">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item size="large">
                    <el-button type="primary" @click="onSubmit">立即创建</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>

            <el-button slot="reference" type="primary">新增课程</el-button>
        </el-popover>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                form:{
                    name:""
                },
                tableData: [{
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
            }
        }
    }
</script>
<style>
    .el-form-item__content {
        width:50%
    }
    .form{
        margin-left: 5%;
    }
</style>