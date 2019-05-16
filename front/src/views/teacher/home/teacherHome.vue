<template>
    <div class="main-box">
        <pageTitle title-text="我的首页"></pageTitle>
        <myInformation></myInformation>
    </div>
</template>

<script>
    import myInformation from "../components/myInformation"

    export default {
        name: "teacherHome",
        components:{
            myInformation
        }
    }
</script>

<style scoped>
.main-box{
    padding-left: 2%;
}
</style>
