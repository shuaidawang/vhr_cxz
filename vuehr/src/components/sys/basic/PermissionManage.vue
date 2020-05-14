<template>
    <div>
        <div>
            <el-input placeholder="请输入角色英文名称..." v-model="role.name" style="width: 300px;" size="small">
                <template slot="prepend">ROLE_</template>
            </el-input>
            <el-input v-model="role.nameZh" placeholder="请输入角色中文名称..." style="width: 300px;margin-left: 10px;"
                      size="small"></el-input>
            <el-button type="primary" style="margin-left: 10px;" size="small" class="el-icon-plus" @click="btnAdd">添加角色</el-button>
        </div>
        <div style="margin-top: 10px;width: 610px">
            <el-collapse v-model="activeName" accordion @change="change">
                <el-collapse-item :title="r.nameZh" :name="r.id" v-for="(r,index) in roles" :key="index">
                    <div>
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <span>可访问的资源</span>
                                <el-button style="float: right; padding: 3px 0;color: red;" type="text" icon="el-icon-delete" @click="btnDelete(r.id,r.nameZh)"/>
                            </div>
                            <el-tree
                                    :data="allMenus"
                                    show-checkbox
                                    node-key="id"
                                    :key="index"
                                    ref="tree"
                                    :default-checked-keys="selectMenus"
                                    :props="defaultProps"></el-tree>
                            <div style="display: flex;justify-content: flex-end">
                                <el-button @click="btnCancel">取消修改</el-button>
                                <el-button type="primary" @click="btnUpdate(r.id,index)">确认修改</el-button>
                            </div>

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
                allMenus:[],
                selectMenus:[],
                activeName:-1
            }
        },
        mounted() {
            this.listRoles();

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
                    this.listMenus();
                    this.listSelectMenus(rid);
                }
            },
            listMenus(){
                this.getRequest("/system/basic/permission/listMenus").then(resp => {
                    if (resp) {
                        this.allMenus = resp;
                    }
                })
            },
            listSelectMenus(rid){
                this.getRequest("/system/basic/permission/listMenus/"+rid).then(resp=>{
                    if(resp){
                        this.selectMenus = resp;
                    }
                })
            },
            btnUpdate(rid,index){
                let selectKeys = this.$refs.tree[index].getCheckedKeys(true);
                var url = "/system/basic/permission/updateRoleMenus?rid="+rid;
                selectKeys.forEach(item=>{
                    url+="&menuIds="+item;
                })
                this.putRequest(url).then(resp=>{
                    if(resp){
                        this.activeName = -1;
                    }
                })
            },
            btnCancel(){
                this.activeName = -1;
            },
            btnAdd(){
                if (!this.role.name || !this.role.nameZh) {
                    this.$message.warning("角色名称不能为空!");
                    return;
                }
                this.postRequest("/system/basic/permission/",this.role).then(resp=>{
                    if(resp){
                        this.role.name="";
                        this.role.nameZh="";
                        this.listRoles();
                    }
                })
            },
            btnDelete(id,nameZh){
                this.$confirm('此操作将永久删除【' + nameZh + '】角色, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/permission/"+id).then(resp=>{
                        if(resp){
                            this.listRoles();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
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