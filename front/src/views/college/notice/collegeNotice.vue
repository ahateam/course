
<template>
    <div>
        <el-button
                type="primary"
                @click="openFullScreen"
                v-loading.fullscreen.lock="fullscreenLoading">
            指令方式
        </el-button>
        <el-button
                type="primary"
                @click="openFullScreen">
            服务方式
        </el-button>
        <div style="width: 1000px;height: 200px" id="taa">
            <a download="data.png" id="getData">下载</a>
        </div>
        <img :src="png">
    </div>


</template>

<script>
    const fs=require('fs')
    export default {
        data() {
            return {
                fullscreenLoading: false,
                png:""
            }
        },
        methods: {
            openFullScreen() {
                this.fullscreenLoading = true;
                setTimeout(() => {
                    this.fullscreenLoading = false;
                }, 2000);
            },
            openFullScreen() {
                const loading = this.$loading({
                    target:"#taa",
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                setTimeout(() => {
                    loading.close();
                }, 2000);
            },
            getPng(){
                this.$ajax.get("http://localhost:3000/users/cReadFil").then(res=>{
                    //let ws = fs.createWriteStream("./api/bb.txt") //写入
                    let blob=new Blob([res.data],{type:'image/png'})
                    console.log(blob)
                    console.log(res)
                    this.png="data:image/png;base64,"+res.data
                    window.URL = window.URL || window.webkitURL;
                    document.querySelector("#getData").href = URL.createObjectURL(blob);
                    //res.data.pipe(ws)
                })
            }
        },
        mounted(){
            this.getPng()
        }
    }
</script>
