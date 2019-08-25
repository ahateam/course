<template>
    <div>
        <!--<video :src="video" controls="controls" preload  style="height: 200px;width: 200px">-->
            <!--您的浏览器不支持 video 标签。-->
        <!--</video>-->

        <video id="example_video_1" src="http://course-lg.oss-cn-hangzhou.aliyuncs.com/free/college/20190821/1566367770107long.mp4"  class="video-js vjs-default-skin" controls preload width="640" height="264"
               poster="http://vjs.zencdn.net/v/oceans.png">
        </video>
        <a href="" download="文件.mp4" @click="down()" id="btn">下载</a>

        <video :src="'data:video/mp4;base64,'+video" class="video-js vjs-default-skin" controls preload="none" width="640" height="264"
                         poster="http://vjs.zencdn.net/v/oceans.png">
        <source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4">
    </video>



    </div>
</template>

<script>
    export default {
        name: "collegeHome",
        data(){
          return{
              imgData:[],
              video:"",
              data:"",
              offset:1
          }
        },
        methods:{
            add(){
               this.$ajax.get('http://localhost:3000/users/sqlmp',{params:{offset:this.offset}}).then( (res) => {
                   this.video +=res.data[0].src
                   this.data=res.data
                   console.log(res)
               })
            },
            down(){
                console.log(this.data.type)
                 window.URL.createObjectURL(new Blob([this.data]));
            }
        },
        watch:{
            video(){
                console.log("change")
                this.offset++
                this.add()
            }
        },
        mounted(){
            this.add()
        }
    }
</script>

<style scoped>

</style>
