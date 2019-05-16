<!--********  调课   *******-->
<template>
    <div>
        <div style="float: left;width: 50%">
            <span style="margin-left: 24%;font-size: 30px">变课前</span>
            <changeIn></changeIn>
        </div>
        <div style="float: left;width: 50%">
            <span style="margin-left: 24%;font-size: 30px">变课后</span>
            <changeIn></changeIn>
        </div>

    </div>

</template>



<script>
    import changeIn from "./changeInformation"
    export default {
        components:{
            changeIn
        }
    }
</script>