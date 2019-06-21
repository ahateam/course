<template>
    <div class="main">
        <div class="teacher">
            <div class="text">选择教师</div><br>
            <div class="contents">
                <div>ssss</div>     <div>嗡嗡嗡</div> <div>嗡嗡嗡</div> <div>嗡嗡嗡</div> <div>嗡嗡嗡</div>   <div>嗡嗡嗡</div> <div>嗡嗡嗡</div> <div>嗡嗡嗡</div> <div>嗡嗡嗡</div> <div>嗡嗡嗡</div>          <div>嗡嗡嗡</div>
                <div>ssss</div>

            </div>
        </div>
        <div class="teacher">
            <div class="text">选择班级</div>

        </div>
        <div class="class">
            <div class="text">选择实验室</div>

        </div>
    </div>
</template>

<script>
    export default {
        name: "teacherClass"
    }
</script>

<style scoped>
.teacher,.class{width: 32.5%;height: 130px; margin: 10px 0.25%;float: left }
    .teacher{border-right:1px solid #f4eded }
    .text{text-align: center;width: 60%;margin-left:20%;color: #fff;background: #e7628a;border-radius: 5px}
    .head{width: 40px;height: 16px}
    .teacher>div,.contents>div {float: left;margin-right: 5px}

</style>