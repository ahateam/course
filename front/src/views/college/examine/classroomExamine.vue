<template>
    <div class="hello">
        <app-table/>
    </div>
</template>

<script>
    import Table from './Table.vue'
    export default {
        name: 'HelloWorld',
        components:{
            "app-table":Table
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
