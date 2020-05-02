<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">微人事</div>
                <el-dropdown class="userInfo" @command="dropDownClick">
                    <span class="el-dropdown-link">
                        {{user.name}}<i><img :src="user.userface"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown" command="dropDown">
                        <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
                        <el-dropdown-item command="settings">设置</el-dropdown-item>
                        <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <el-container>
                <el-aside width="200px">Aside</el-aside>
                <el-main>Main</el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data(){
            return{
                user:JSON.parse(window.sessionStorage.getItem("user"))
            }
        },
        methods:{
            dropDownClick(command){
                if(command == 'logout'){
                    this.getRequest("/logout").then(resp=>{
                        if(resp && resp.msg){
                            this.$message.success({message: resp.msg})
                            window.sessionStorage.removeItem("user");
                            this.$router.replace("/");
                        }
                    });
                }
            }
        }
    }
</script>

<style>
    .homeHeader {
        background-color: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 15px;
        box-sizing: border-box;
    }

    .homeHeader .title {
        font-size: 30px;
        font-family: 华文行楷;
        color: #ffffff;
    }
    .userInfo {
        cursor: pointer;
    }
    .el-dropdown-link img{
        width:48px;
        height:48px;
        border-radius: 24px;
        margin-left: 8px;
    }
    .el-dropdown-link{
        display: flex;
        align-items: center;
    }

</style>