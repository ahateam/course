<template>
    <div>
        校级管理员首页
    </div>
</template>

<script>
    export default {
        name: "adminHome",
        methods:{
            getCollege() {
                let cnt={
                    offset:0,
                    count:20
                }
                //获取实验室
                this.$admin.getDepartments(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        sessionStorage.setItem("tableCollege",res.data.c)
                       // this.$store.state.tableCollege = this.$util.tryParseJson(res.data.c)
                        console.log(this.$store.state.tableCollege)
                    }else{
                        this.tableCollege = []
                    }
                })
            },
        },
        mounted() {
            this.getCollege()
        }
    }
</script>

<style scoped>

</style>
