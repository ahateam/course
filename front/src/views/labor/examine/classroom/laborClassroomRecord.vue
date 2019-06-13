<template>
    <el-table
            :data="tableData"
            style="width: 100%"
            :row-class-name="tableRowClassName">
        <el-table-column
                prop="date"
                label="日期"
                width="180">
        </el-table-column>
        <el-table-column
                prop="name"
                label="姓名"
                width="180">
        </el-table-column>
        <el-table-column
                prop="address"
                label="地址">
        </el-table-column>
    </el-table>
</template>

<style>
    .el-table .warning-rows {
        background: oldlace;
    }

    .el-table .success-rows {
        background: #f0f9eb;
    }
</style>

<script>
    export default {
        methods: {
            tableRowClassName({row, rowIndex}) {
                if (rowIndex === 1) {
                    return 'warning-rows';
                } else if (rowIndex === 3) {
                    return 'success-rows';
                }
                return '';
            }
        },
        data() {
            return {
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }]
            }
        },
        beforeCreate:function(){
            console.log('beforeCreate:刚刚new Vue()之后，这个时候，数据还没有挂载呢，只是一个空壳')
            console.log(this.msg)//undefined
            console.log(document.getElementsByClassName("myp")[0])//undefined
        },
        created:function(){
            console.log('created:这个时候已经可以使用到数据，也可以更改数据,在这里更改数据不会触发updated函数')
            this.msg+='!!!'
            console.log('在这里可以在渲染前倒数第二次更改数据的机会，不会触发其他的钩子函数，一般可以在这里做初始数据的获取')
            console.log('接下来开始找实例或者组件对应的模板，编译模板为虚拟dom放入到render函数中准备渲染')
        },
        beforeMount:function(){
            console.log('beforeMount：虚拟dom已经创建完成，马上就要渲染,在这里也可以更改数据，不会触发updated')
            this.msg+='@@@@'
            console.log('在这里可以在渲染前最后一次更改数据的机会，不会触发其他的钩子函数，一般可以在这里做初始数据的获取')
            console.log(document.getElementsByClassName("myp")[0])//undefined
            console.log('接下来开始render，渲染出真实dom')
        },
        // render:function(createElement){
        //     console.log('render')
        //     return createElement('div','hahaha')
        // },
        mounted:function(){
            console.log('mounted：此时，组件已经出现在页面中，数据、真实dom都已经处理好了,事件都已经挂载好了')
            console.log(document.getElementsByClassName("myp")[0])
            console.log('可以在这里操作真实dom等事情...')

            //    this.$options.timer = setInterval(function () {
            //        console.log('setInterval')
            //         this.msg+='!'
            //    }.bind(this),500)
        },
        beforeUpdate:function(){
            //这里不能更改数据，否则会陷入死循环
            console.log('beforeUpdate:重新渲染之前触发')
            console.log('然后vue的虚拟dom机制会重新构建虚拟dom与上一次的虚拟dom树利用diff算法进行对比之后重新渲染')
        },
        updated:function(){
            //这里不能更改数据，否则会陷入死循环
            console.log('updated:数据已经更改完成，dom也重新render完成')
        },
        beforeDestroy:function(){
            console.log('beforeDestory:销毁前执行（$destroy方法被调用的时候就会执行）,一般在这里善后:清除计时器、清除非指令绑定的事件等等...')
            // clearInterval(this.$options.timer)
        },
        destroyed:function(){
            console.log('destroyed:组件的数据绑定、监听...都去掉了,只剩下dom空壳，这里也可以善后')
        }
    }
</script>