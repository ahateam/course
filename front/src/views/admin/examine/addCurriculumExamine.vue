    <!--*******  新增课程 ****-->

<template>
    <div>
        <el-select v-model="schoolMonth" placeholder="学院" >
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
        </el-select><br>
        <span style="font-size: 30px">xxx学院新增实验课程大纲</span>

    </div>
</template>

<script>
    export default {
        name: "addCurriculumExamine"
    }
</script>

<style scoped>

</style>