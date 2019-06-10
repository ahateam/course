<template>
    <div>
        二级学院首页
        <button @click="getRes"></button>
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
                let cnt= {
                    offset:this.offset,
                    count:this.count
                }
            this.addcollage(cnt)
            },
            addcollage(cnt){
                this.$college.getSchedule(cnt,(res)=>{
                    console.log('2222')
                    console.log(JSON.parse(res.data.c))
                    console.log('222222333')
                    console.error(this.$util.tryParseJson(res.data.c))
                })
            },

            add(){
               this.$ajax.post('http://localhost:3000/api/add',{p1:1,p2:3}).then( (res) => {
                    console.error(res)
               })
            }
        }
    }
</script>

<style scoped>

</style>
