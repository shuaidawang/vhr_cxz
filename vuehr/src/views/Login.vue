<template>
    <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
        <h3 class="loginTitle">系统登录</h3>
        <el-form-item prop="username">
            <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
            <el-input type="text" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-checkbox v-model="checked" class="loginRemember">记住密码</el-checkbox>
        <el-button type="primary" style="width: 100%;" @click="submitLogin">登录</el-button>
    </el-form>
</template>

<script>
    import {postKeyValueRequest} from "../utils/api";

    export default {
        name: "Login",
        data() {
            return {
                loginForm: {
                    username: 'admin',
                    password: '123'
                },
                checked: true,
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            submitLogin() {
                this.$refs['loginForm'].validate((valid) => {
                    if (valid) {
                        this.postKeyValueRequest('/doLogin', this.loginForm).then(resp => {
                            if (resp) {
                                window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
                                //获取重定向地址，存在则跳转该地址，否则/home
                                let path = this.$route.query.redirect;
                                this.$router.replace((path == '/' || path == undefined) ? '/home' : path);
                                //初始化websocket
                                this.$store.dispatch('connect');
                            }
                        })
                    } else {
                        this.$message.error('用户名或密码错误');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #ffffff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }

    .loginRemember {
        text-align: left;
        margin: 0px 0px 15px 0px;
    }

</style>