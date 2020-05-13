<template>
    <div>
        <div>
            <el-input placeholder="请输入角色英文名称..." v-model="role.name" style="width: 300px;" size="small">
                <template slot="prepend">ROLE_</template>
            </el-input>
            <el-input v-model="role.nameZh" placeholder="请输入角色中文名称..." style="width: 300px;margin-left: 10px;"
                      size="small"></el-input>
            <el-button type="primary" style="margin-left: 10px;" size="small" class="el-icon-plus">添加角色</el-button>
        </div>
        <div style="margin-top: 10px;width: 610px">
            <el-collapse accordion @change="change">
                <el-collapse-item :title="r.nameZh" :name="r.id" v-for="(r,index) in roles" :key="index">
                    <div>
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <span>可访问的资源</span>
                                <el-button style="float: right; padding: 3px 0;color: red;" type="text" icon="el-icon-delete"/>
                            </div>
                            <el-tree :data="allMenus" :props="defaultProps"></el-tree>
                        </el-card>
                    </div>
                </el-collapse-item>
            </el-collapse>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PermissionManage",
        data() {
            return {
                role: {
                    name: '',
                    nameZh: ''
                },
                roles: [],
                defaultProps:{
                  label:'name',
                  children: 'children'
                },
                allMenus:[]
            }
        },
        mounted() {
            this.listRoles();
            this.listMenus();
        },
        methods: {
            listRoles() {
                this.getRequest("/system/basic/permission/listRoles").then(resp => {
                    if (resp) {
                        this.roles = resp;
                    }
                })
            },
            change(rid){
                if(rid){
                    //TODO
                }
            },
            listMenus(){
                this.getRequest("/system/basic/permission/listMenus").then(resp => {
                    if (resp) {
                        this.allMenus = resp;
                    }
                })
            }
        }
    }
</script>

<style>
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
    }
</style>