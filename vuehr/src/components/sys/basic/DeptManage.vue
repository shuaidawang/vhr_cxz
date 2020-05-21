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
                          class="depBtn"
                          @click="() => addDept(data)">
                    添加部门
                  </el-button>
                  <el-button
                          type="danger"
                          size="mini"
                          class="depBtn"
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
            addDept(data){
                this.initDeptSelect();
                this.dept.parentId = data.id;
                this.dialogVisible = true;
            },
            btnAdd(){
                this.postRequest("/system/basic/dept/",this.dept).then(resp=>{
                    if(resp){
                        this.addDep2Deps(this.deptTree, resp.obj);
                        this.dept= {parentId:'',name:''};
                        this.dialogVisible = false;
                    }
                })
            },
            delDept(node,data){
                if (data.isParent) {
                    this.$message.error("该部门下有子部门，无法删除!");
                    return;
                }
                this.$confirm('此操作将永久删除【' + data.name + '】部门, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/dept/"+data.id).then(resp=>{
                        if(resp){
                            this.removeDepFromDeps(null,this.deptTree,data.id);
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
            },
            addDep2Deps(deps, dep) {
                for (let i = 0; i < deps.length; i++) {
                    let d = deps[i];
                    if (d.id == dep.parentId) {
                        d.children = d.children.concat(dep);
                        if (d.children.length > 0) {
                            d.isParent = true;
                        }
                        return;
                    } else {
                        if(d.children && d.children.length > 0){
                            this.addDep2Deps(d.children, dep);
                        }
                    }
                }
            },
            removeDepFromDeps(p,deps, id) {
                for(let i=0;i<deps.length;i++){
                    let d = deps[i];
                    if (d.id == id) {
                        deps.splice(i, 1);
                        if (deps.length == 0) {
                            p.isParent = false;
                        }
                        return;
                    }else{
                        this.removeDepFromDeps(d,d.children, id);
                    }
                }
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
    .depBtn {
        padding: 2px;
    }
</style>