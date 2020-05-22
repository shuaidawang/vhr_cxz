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
                                @change="enableChange(hr)"
                                inactive-text="禁用">
                        </el-switch>
                    </div>
                    <div>用户角色:
                        <el-tag style="margin-left: 4px;" type="success" v-for="(role,rindex) in hr.roles"
                                :key="rindex">{{role.nameZh}}
                        </el-tag>
                        <el-popover
                                placement="right"
                                title="角色列表"
                                width="200"
                                trigger="click">
                            <el-select
                                    v-model="selectedRoles"
                                    multiple
                                    style="margin-left: 20px;"
                                    placeholder="请选择">
                                <el-option
                                        v-for="(item,index) in hr.roles"
                                        :key="index"
                                        :label="item.nameZh"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                            <el-button slot="reference" type="text" icon="el-icon-more"
                                       style="margin-left: 4px;"></el-button>
                        </el-popover>
                    </div>
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
                listHrs: [],
                selectedRoles: []
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
            },
            enableChange(hr) {
                var obj = {
                    id: hr.id,
                    enabled: hr.enabled
                }
                this.putRequest("/system/hr/", obj).then(resp => {
                    if (resp) {
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