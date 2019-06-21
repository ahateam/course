<template>
    <div>
        <div class="table">
            <div class="tableHead">
                <div v-for="(item,index) in tableHead " :key="index">{{item}}</div>
            </div>

            <div class="tableBody" >
                <div v-for="(item,index) in 12 " :key="index">
                    <div>第{{item}}节</div>
                    <div class="content" v-for="(ite,ind) in 7 " :key="ind"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableHead: [
                    "时间","星期一","星期二","星期三","星期四","星期五","星期六","星期日"
                ],
                tableWitdh:50
            };
        },
        methods: {


        }
    };
</script>

<style>
    .table{
        width: 100%;
        height: 690px;
    }

    .tableHead{
    }
    .tableHead div,.tableBody>div>div{
        float: left;
        width: 12%;
        height: 60px;
        text-align: center;
        border: 1px solid #e5d8d8;

        line-height: 55px;
    }
   .content:hover{
       background: #beead0;
   }
</style>