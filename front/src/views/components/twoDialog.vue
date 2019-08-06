<template>
    <div>
        <el-dialog

                :visible.sync="del"
                :width="diaWidth">
            <slot></slot>
        </el-dialog>

        <el-dialog
                :visible.sync="edit"
                :width="diaWidth">
            <slot></slot>
        </el-dialog>

        <el-dialog
                :visible.sync="create"
                :width="diaWidth">
            <slot></slot>
        </el-dialog>


        <el-dialog
                :visible.sync="det"
                :width="diaWidth">
            <slot></slot>
        </el-dialog>


    </div>
</template>

<script>
    export default {
        name: "twoDialog",
        data(){
            return{
                del:false, //删除
                create:false,//新增
                edit:false,//编辑
                det:false,//查看
                diaWidth:"",

            }
        },
        props:["diaName"],
        methods:{
            widthDia(diaWidth){
                if(!diaWidth){this.diaWidth="30%"}
                else{this.diaWidth=diaWidth+"%"}
            },
            openDel(diaWidth){
                this.widthDia(diaWidth)
                this.del=true
            },
            openCreate(diaWidth){
                this.widthDia(diaWidth)
                this.create=true
            },
            openEdit(diaWidth){
                this.widthDia(diaWidth)
                this.edit=true
            },
            openDet(diaWidth){
                this.widthDia(diaWidth)
                this.det=true
            },
            closeDia(){
                this.del=false
            }
        },
        mounted(){
        },



    }
</script>

<style scoped>

</style>
