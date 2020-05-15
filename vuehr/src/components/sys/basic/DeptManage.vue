<template>
    <div>
        <el-input
                style="width: 472px;"
                placeholder="输入关键字进行过滤"
                v-model="filterText">
        </el-input>
        <el-tree
                class="filter-tree"
                style="width: 472px;margin-top: 10px;"
                :data="depts"
                :props="defaultProps"
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
    </div>
</template>

<script>
    export default {
        name: "DeptManage",
        data() {
            return {
                filterText: '',
                depts: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
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
                return data.label.indexOf(value) !== -1;
            },
            initTree() {
                this.getRequest("/system/basic/dept/").then(resp => {
                    if (resp) {
                        this.depts = resp;
                    }
                })
            },
            addDept(node,data){
                const newChild = { id: id++, label: 'testtest', children: [] };
                if (!data.children) {
                    this.$set(data, 'children', []);
                }
                data.children.push(newChild);
            },
            delDept(node,data){
                console.log(node)
                console.log(data)
                this.$confirm('此操作将永久删除【' + data.name + '】部门, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/dept/"+data.id).then(resp=>{
                        if(resp){
                            const parent = node.parent;
                            const children = parent.data.children || parent.data;
                            const index = children.findIndex(d => d.id === data.id);
                            children.splice(index, 1);
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
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