<template>
    <div>
        <div>
            <el-input
                    size="small"
                    style="width: 300px;margin-right: 10px;"
                    placeholder="请输入职位名称..."
                    prefix-icon="el-icon-plus"
                    v-model="pos.name">
            </el-input>
            <el-button size="small" type="primary" icon="el-icon-plus" @click="addPosition">添加</el-button>
        </div>
        <div style="margin-top: 15px;">
            <el-table
                    :data="positions"
                    size="small"
                    border
                    style="width: 70%"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="id"
                        label="编号"
                        width="60">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="职位名称"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="创建时间">
                </el-table-column>
                <el-table-column label="是否启用">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
                        <el-tag type="danger" v-else>未启用</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-button type="danger" :disabled="this.multipleSelection.length==0" style="margin-top: 8px;"
                       @click="batchDel">批量删除
            </el-button>
        </div>
        <el-dialog
                title="修改职位"
                :visible.sync="dialogVisible"
                width="30%">
            <div>
                <el-tag>职位名称</el-tag>
                <el-input v-model="editpos.name" size="small" placeholder="请输入职位名称..."
                          style="width: 300px;margin-left: 8px;"></el-input>
            </div>
            <div style="margin-top: 10px;">
                <el-tag style="margin-right: 10px;">是否启用</el-tag>
                <el-switch
                        v-model="editpos.enabled"
                        active-text="启用"
                        inactive-text="禁用">
                </el-switch>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirmEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "PosManage",
        data() {
            return {
                pos: {
                    name: ""
                },
                positions: [],
                dialogVisible: false,
                editpos: {
                    name: "",
                    enabled:""
                },
                multipleSelection: []
            }
        },
        mounted() {
            this.initPostion();
        },
        methods: {
            initPostion() {//初始化表格数据
                this.getRequest("/system/basic/pos/").then(resp => {
                    if (resp) {
                        this.positions = resp;
                    }
                })
            },
            addPosition() {//添加
                if (!this.pos.name) {
                    this.$message.warning("请输入正确的职位名称!");
                    return;
                }
                this.postRequest("/system/basic/pos/", this.pos).then(resp => {
                    if (resp) {
                        this.initPostion();
                        this.pos.name = "";
                    }
                })
            },
            handleEdit(index, data) {//操作中的编辑
                this.dialogVisible = true;
                Object.assign(this.editpos, data);
            },
            handleDelete(index, data) {//操作中的删除
                this.$confirm('此操作将永久删除【' + data.name + '】职位, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/pos/" + data.id).then(resp => {
                        if (resp) {
                            this.initPostion();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            confirmEdit() {//修改对话框的确定按钮
                if (!this.editpos.name) {
                    this.$message.warning("请输入正确的职位名称!");
                    return;
                }
                this.putRequest("/system/basic/pos/", this.editpos).then(resp => {
                    if (resp) {
                        this.initPostion();
                        this.dialogVisible = false;
                    }
                })
            },
            handleSelectionChange(val) {//table前面的复选框
                this.multipleSelection = val;
            },
            batchDel() {//批量删除
                let ids = '?';
                this.multipleSelection.forEach(i => {
                    ids+='ids='+i.id+'&';
                })
                this.deleteRequest("/system/basic/pos/"+ids).then(resp => {
                    if (resp) {
                        this.initPostion();
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>