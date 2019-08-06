<template>
    <div>
        二级学院首页
        <button @click="getRes">组件</button>
        <two-dialog ref="dialogs" dia-name="del-information" >
            <del-information></del-information>
        </two-dialog>
    </div>
</template>

<script>
    export default {
        name: "collegeHome",
        data(){
          return{
              offset:0,
              count:10
          }
        },
        methods:{
            getRes(){
                this.$refs.dialogs.openDel(50)
            },

        }
    }
</script>

<style scoped>

</style>
