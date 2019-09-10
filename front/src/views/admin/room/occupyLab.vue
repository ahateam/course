<template>
    <div class="changeTable">
        <occupy ref="occupy" :pass="pass"></occupy>
    </div>
</template>

<script>
    import occupy from "../occupy/occupy"
    export default {
        name: "adminOccupyTeacher",
        data(){
            return{
                pass:{
                    path:"/adminRoom",
                    title:"实验室管理",
                    id:JSON.parse(sessionStorage.getItem("occupyLab")).labId,
                    name:JSON.parse(sessionStorage.getItem("occupyLab")).labName,
                    type:"lab",
                }
            }
        },
        methods:{

        },
        mounted(){
        },
        components:{
            occupy
        }
    }
</script>

<style scoped lang="scss">


</style>
