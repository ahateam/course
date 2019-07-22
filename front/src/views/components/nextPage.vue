<template>
    <div class="page-btn " style=" float: right; font-size: 16px;color: #666;padding: 0;margin: 10px 5% 0 0">
        <span class="page-text">当前页码：第 <span style="color: #f60;">{{page}}</span> 页</span>
        <el-button type="primary" :disabled="page===1"   @click="changePage(page-1)">上一页</el-button>
        <el-button type="primary" :disabled="pageOver ===true"  @click="changePage(page+1)">下一页</el-button>
    </div>
</template>

<script>
    export default {
        name: "nextPage",
        data(){
            return{
                page:1,
                pageOver:false,
                count:10,
                offset:0,
            }
        },
        methods:{
            changePage(page){
                this.page=page
                let cnt = {
                    count:this.count,
                    offset:(this.page-1)*this.count
                }
                this.$emit('transferRandom',cnt)
            },
            chagnePageOver(){
                this.pageOver=true
            }
        }
    }
</script>

<style scoped>

</style>
