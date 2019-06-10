<template>
  <div>
    <table class="table">
      <tr>
        <td>申请时间</td>
        <td>教学大楼</td>
        <td>房间号</td>
        <td>实验室名称</td>
        <td>面向专业</td>
        <td>容纳人数</td>
        <td>状态</td>
        <td>负责人</td>
        <td>申请人</td>
        <td>申请原因</td>
        <td></td>
      </tr>
      <tr v-for="(item,index) in list" :key="index">
        <td>{{item.time}}</td>
        <td>{{item.jx}}</td>
        <td>{{item.fj}}</td>
        <td>{{item.name}}</td>
        <td>{{item.zy}}</td>
        <td>{{item.rs}}</td>
        <td>{{item.zt}}</td>
        <td>{{item.fz}}</td>
        <td>{{item.zx}}</td>
        <td>{{item.zv}}</td>

        <td>
        	<div class="agree">
               <div> <button class="button">同意</button></div>
               <div><label style="margin-left:5px;margin-right: 5px ">|</label></div>
               <div><button class="button">不同意</button></div>
			</div>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: 'classInquire',
  data () {
    return {
      list: [
        {time: '2019-01-01', jx: '三实验楼', fj: '308', name: '综合实验室', zy: '全校各专业', rs: '25', zt: '空闲', fz: '王越',zx:'',zv:''},
        {time: '2019-01-01', jx: '三实验楼', fj: '308', name: '综合实验室', zy: '全校各专业', rs: '25', zt: '空闲', fz: '王越',zx:'',zv:''},

        {time: '', jx: '', fj: '', name: '', zy: '', rs: '', zt: '', fz: '',zx:'',zv:''},

      ]
    }
  }
}
</script>

<style scoped>
  .table{
    margin-left: 5%;
    width: 90%;
    min-height: 25px;
    line-height: 25px;
    text-align: center;
    border-collapse: collapse;
  }
  .table td{
    width: 9.09090990%;
    border:1px solid #000;
    padding-left: 5px;

  }
  .button{
    background: aqua;
    border:none;
    border-radius: 5px;
    height: 80%;
    width: 100%;
    text-align: center;
    text-size: 10px;
  }
  .agree{
  	display: flex;

  }
</style>

