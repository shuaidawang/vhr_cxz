<template>
    <div>
        <div style="display: flex;justify-content: center;">
            <el-input
                    style="width: 400px;margin-right: 8px;"
                    placeholder="请输入用户名..."
                    prefix-icon="el-icon-search"
                    v-model="keyword">
            </el-input>
            <el-button icon="el-icon-search" type="primary">搜索</el-button>
        </div>
        <div class="cardContainer">
            <el-card class="cardClass" v-for="(hr,i) in listHrs" :key="i">
                <div slot="header" class="clearfix">
                    <span>{{hr.name}}</span>
                    <el-button style="float: right; padding: 3px 0;color: #ff0000" type="text"
                               icon="el-icon-delete"></el-button>
                </div>
                <div class="headImgContainer">
                    <img :src="hr.userface" :alt="hr.name" :title="hr.name" class="headImg">
                </div>
                <div class="userinfo-container">
                    <div>用户名:{{hr.name}}</div>
                    <div>手机号码:{{hr.phone}}</div>
                    <div>电话号码:{{hr.telephone}}</div>
                    <div>地址:{{hr.address}}</div>
                    <div>用户状态:
                        <el-switch
                                v-model="hr.enabled"
                                active-color="#13ce66"
                                inactive-color="#ff4949"
                                active-text="启用"
                                inactive-text="禁用">
                        </el-switch>
                    </div>
                    <div>用户角色:</div>
                    <div>备注:{{hr.remark}}</div>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SysCfg",
        data() {
            return {
                keyword: '',
                listHrs: []
            }
        },
        mounted() {
            this.initCards();
        },
        methods: {
            initCards() {
                this.getRequest("/system/hr/").then(resp => {
                    if (resp) {
                        this.listHrs = resp;
                    }
                })
            }
        }
    }
</script>

<style>
    .cardContainer {
        margin-top: 8px;
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }

    .cardClass {
        width: 360px;
        margin-right: 4px;
    }

    .headImgContainer {
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .headImg {
        width: 70px;
        height: 70px;
        border-radius: 70px;
    }

    .userinfo-container div {
        font-size: 12px;
        color: #09C0F6;
        margin-top: 2px;
    }

    .userinfo-container {
        margin-top: 20px;
    }
</style>