<template>
    <div class="pageTitle">
        <span style="font-size:30px">{{titleText}}</span>

    </div>
</template>

<script>
    export default {
        name: "pageTitle",
        props:["titleText"],
        data(){
            return{
            }
        }
    }
</script>

<style scoped>
    .pageTitle{
        text-align: center;
        margin-bottom: 10px;
    }
</style>