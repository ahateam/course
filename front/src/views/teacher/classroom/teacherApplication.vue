<template>
    <div  class="main-box"  >


        <el-table
                border
                :data="tableData1"
                style="width: 100%;"
               >
           <el-table-column v-for="(frequency,index) in liu" :key="index" :label="frequency">

           </el-table-column>

       </el-table>
        <el-table
                border
                :data="tableData2"
                style="width: 100%">
            <el-table-column v-for="(frequency,index) in liu" :key="index" :label="frequency+6">

            </el-table-column>

        </el-table>
        <el-select v-model="one" placeholder="1-12" style="margin-right:10px ">
            <el-option
                    v-for="(item,index) in liu2"
                    :key="index"
                    :value="item">
            </el-option>
        </el-select>
        <el-select v-model="two" placeholder="请选择" style="margin-right:10px ">
            <el-option
                    >
            </el-option>
            <el-option
                    >
            </el-option>

        </el-select>
        <el-button type="primary" >
            申请
        </el-button>
    </div>
</template>
<script>
    import Res from './res'
    export default {
        data() {
            return {
                liu:[1,2,3,4,5,6],
                liu2:[1,2,3,4,5,6,7,8,9,10,11,12],
                one:"",
                two:"",
                tableData1:[],
                tableData2:[],
                res:""

            }
        },
        methods: {
            onSubmit() {
            }
        },
        props:['item'],
        mounted:{
            function(){
                Res.$on('transmits', (data) => {
                    console.log(data+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
                })
            }

        },

    }
</script>
<style scoped >
    .span{
        font-size: 18px;
        font-weight: 600;
    }
    .main-box{
        width: 80%;
        margin: 0 0 0 10%;
    }
</style>