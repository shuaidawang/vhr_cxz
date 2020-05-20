<template>
    <div>
        <el-input
                style="width: 472px;"
                placeholder="请输入部门名称进行搜索..."
                prefix-icon="el-icon-search"
                v-model="filterText">
        </el-input>
        <el-tree
                class="filter-tree"
                style="width: 472px;margin-top: 10px;"
                :data="deptTree"
                :props="defaultProps"
                :expand-on-click-node="false"
                :filter-node-method="filterNode"
                ref="tree">
            <span class="custom-tree-node" slot-scope="{ node, data }">
                <span>{{ node.label }}</span>
                <span>
                  <el-button
                          size="mini"
                          type="primary"
                          @click="() => addDept(data)">
                    添加部门
                  </el-button>
                  <el-button
                          type="danger"
                          size="mini"
                          @click="() => delDept(node, data)">
                    删除部门
                  </el-button>
                </span>
              </span>
        </el-tree>
        <el-dialog
                title="添加部门"
                :visible.sync="dialogVisible"
                width="30%">
                <div>
                    <el-tag>上级部门</el-tag>
                    <el-select v-model="dept.parentId" placeholder="请选择" style="width: 300px;margin-left: 8px;">
                        <el-option
                                v-for="item in deptList"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </div>
                <div style="margin-top: 10px;">
                    <el-tag>部门名称</el-tag>
                    <el-input v-model="dept.name" placeholder="请输入部门名称..."
                              style="width: 300px;margin-left: 8px;" size="small"></el-input>
                </div>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="btnAdd">确 定</el-button>
                </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "DeptManage",
        data() {
            return {
                filterText: '',
                deptTree: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                deptList:[],
                dialogVisible:false,
                dept:{
                    parentId:'',
                    name:''
                }
            }
        },
        watch: {
            filterText(val) {
                this.$refs.tree.filter(val);
            }
        },
        mounted() {
            this.initTree();
        },
        methods: {
            filterNode(value, data) {
                if (!value) return true;
                return data.name.indexOf(value) !== -1;
            },
            initTree() {
                this.getRequest("/system/basic/dept/").then(resp => {
                    if (resp) {
                        this.deptTree = resp;
                    }
                })
            },
            addDept(node,data){
                this.initDeptSelect();
                this.dept.parentId = node.id;
                this.dialogVisible = true;
            },
            btnAdd(){
                this.postRequest("/system/basic/dept/",this.dept).then(resp=>{
                    if(resp){
                        this.initTree();
                        this.dept= {parentId:'',name:''};
                        this.dialogVisible = false;
                    }
                })
            },
            delDept(node,data){
                this.$confirm('此操作将永久删除【' + data.name + '】部门, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/dept/"+data.id).then(resp=>{
                        if(resp){
                            this.initTree();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            initDeptSelect(){
                this.getRequest("/system/basic/dept/list").then(resp=>{
                    if(resp){
                        this.deptList = resp;
                    }
                })
            }
        },
    }
</script>

<style>
    .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding-right: 8px;
        padding-top: 5px;
    }
    .el-tree-node__content{
        margin-top: 8px!important;
    }
</style>