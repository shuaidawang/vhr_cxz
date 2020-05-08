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
                    style="width: 70%">
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
        </div>
        <el-dialog
                title="修改职位"
                :visible.sync="dialogVisible"
                width="30%">
            <label>职位名称</label><el-input v-model="editpos.name" size="small" placeholder="请输入职位名称..."
                                         style="width: 300px;margin-left: 8px;"></el-input>
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
                editpos:{
                    name:""
                }
            }
        },
        mounted() {
            this.initPostion();
        },
        methods: {
            initPostion() {//初始化表格数据
                this.getRequest("/system/basic/").then(resp => {
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
                this.postRequest("/system/basic/", this.pos).then(resp => {
                    if (resp) {
                        this.initPostion();
                        this.pos.name = "";
                    }
                })
            },
            handleEdit(index, data) {
                this.dialogVisible =true;
                Object.assign(this.editpos,data);
            },
            handleDelete(index, data) {
                this.$confirm('此操作将永久删除【' + data.name + '】职位, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/" + data.id).then(resp => {
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
            confirmEdit(){
                if(!this.editpos.name){
                    this.$message.warning("请输入正确的职位名称!");
                    return;
                }
                this.putRequest("/system/basic/",this.editpos).then(resp => {
                    if (resp) {
                        this.initPostion();
                        this.dialogVisible = false;
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>