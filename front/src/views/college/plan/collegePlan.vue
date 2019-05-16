<template>
    <div >
        <pageTitle title-text="排课"></pageTitle>

        <div class="main">
            <div class="chioceTeacher"><teacher-class></teacher-class></div>
            <div class="chioceDetailed"><chioceDetailed></chioceDetailed></div>
            <div class="chioceTime"><chioce-time></chioce-time></div>
        </div>

    </div>
</template>

<script>
    import chioceTime from "./chioceTime"
    import chioceDetailed from "./chioceDetailed"
    import teacherClass from "./teacherClass"

    export default {
        data() {
            return {
            };
        },
        components:{
            chioceTime,chioceDetailed,teacherClass
        },
        methods: {


        }
    };
</script>

<style>
    .chioceTeacher,.chioceTime,.chioceDetailed{
        margin-left: 2%;float: left;
    }
    .chioceTeacher{width: 96.2%;height: 150px;border: 0.1px solid #e3c0c0;margin-bottom: 10px}
.chioceTime{
    width: 77%;
}
.chioceDetailed{
    width: 19%;
}
</style>