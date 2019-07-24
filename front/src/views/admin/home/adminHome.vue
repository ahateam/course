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
                    offset:20,
                    count:0
                }

                //获取实验室
                this.$admin.getDepartments(cnt,(res)=>{
                    if(res.data.rc === this.$util.RC.SUCCESS){
                        this.tableCollege = this.$util.tryParseJson(res.data.c)
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
