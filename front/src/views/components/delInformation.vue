<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
                style="width: 80%">

        <el-form-item label="删除备注" prop="del">
            <el-input type="textarea" v-model="ruleForm.del" :autosize="{ minRows: 4, maxRows: 4}" maxlength="30"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="delForm('ruleForm')">删除</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    del:""
                },
                rules: {
                    del: [
                        { required: true, message: '请输入备注', trigger: 'blur' },
                        { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
                    ],
                }
            };
        },
        methods: {
            delForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$emit('transferRandom',this.ruleForm)
                    } else {
                        this.$message({
                            type:"warning",
                            message:"请输入备注"
                        })
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

        }
    }
</script>
