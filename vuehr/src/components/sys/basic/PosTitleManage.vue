<template>
    <div>
        <div>
            <el-input size="small" placeholder="添加职称..." prefix-icon="el-icon-plus" v-model="posTitle.name"
                      style="width: 300px;margin-right: 8px;">
            </el-input>
            <el-select size="small" v-model="posTitle.titleLevel" placeholder="职称等级">
                <el-option v-for="item in titleLevels" :key="item" :label="item" :value="item">
                </el-option>
            </el-select>
            <el-button size="small" type="primary" icon="el-icon-plus" @click="addJobLevel" style="margin-left: 8px;">
                添加
            </el-button>
        </div>
        <div style="margin-top: 15px;">
            <el-table
                    :data="jobLevels"
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
                        prop="titleLevel"
                        label="职位级别"
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
                title="修改职称"
                :visible.sync="dialogVisible"
                width="30%">
            <div>
                <el-tag style="margin-right: 10px;">职位名称</el-tag>
                <el-input size="small" placeholder="修改职称..." v-model="editJobLevel.name"
                          style="width: 300px;margin-right: 8px;">
                </el-input>
            </div>
            <div style="margin-top: 10px;">
                <el-tag style="margin-right: 10px;">职位等级</el-tag>
                <el-select size="small" v-model="editJobLevel.titleLevel" placeholder="职称等级" style="width: 300px;">
                    <el-option v-for="item in titleLevels" :key="item" :label="item" :value="item">
                    </el-option>
                </el-select>
            </div>
            <div style="margin-top: 10px;">
                <el-tag style="margin-right: 10px;">是否启用</el-tag>
                <el-switch
                        v-model="editJobLevel.enabled"
                        active-text="启用"
                        inactive-text="禁用">
                </el-switch>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="btnEdit">确 定</el-button>
              </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "PosTitleManage",
        data() {
            return {
                posTitle: {
                    name: '',
                    titleLevel: ''
                },
                titleLevels: [],
                jobLevels: [],
                multipleSelection: [],
                dialogVisible: false,
                editJobLevel: {
                    name: '',
                    titleLevel: '',
                    enabled:''
                }
            }
        },
        mounted() {
            this.initSelect();
            this.initJobLevel();
        },
        methods: {
            initSelect() {
                this.getRequest("/system/basic/jobLevel/titleLevels").then(resp => {
                    if (resp) {
                        this.titleLevels = resp;
                    }
                })
            },
            initJobLevel() {
                this.getRequest("/system/basic/jobLevel/").then(resp => {
                    if (resp) {
                        this.jobLevels = resp;
                    }
                })
            },
            addJobLevel() {
                if (!this.posTitle.name) {
                    this.$message.warning("请输入正确的职称!");
                    return;
                }
                if (!this.posTitle.titleLevel) {
                    this.$message.warning("请选择职称等级!");
                    return;
                }
                this.postRequest("/system/basic/jobLevel/", this.posTitle).then(resp => {
                    if (resp) {
                        this.initJobLevel();
                        this.posTitle.name = "";
                        this.posTitle.titleLevel = "";
                    }
                })
            },
            handleEdit(index, data) {
                this.dialogVisible = true;
                Object.assign(this.editJobLevel, data);
            },
            handleDelete(index, data) {
                this.$confirm('此操作将永久删除【' + data.name + '】职位, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/jobLevel/" + data.id).then(resp => {
                        if (resp) {
                            this.initJobLevel();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            btnEdit() {
                if (!this.editJobLevel.name) {
                    this.$message.warning("请输入正确的职称!");
                    return;
                }
                if (!this.editJobLevel.titleLevel) {
                    this.$message.warning("请选择职称等级!");
                    return;
                }
                this.putRequest("/system/basic/jobLevel/", this.editJobLevel).then(resp => {
                    if (resp) {
                        this.initJobLevel();
                        this.editJobLevel.name = "";
                        this.editJobLevel.titleLevel = "";
                        this.dialogVisible = false;
                    }
                })
            },
            handleSelectionChange(val) {//table前面的复选框
                this.multipleSelection = val;
            },
            batchDel() {
                var ids = [];
                this.multipleSelection.forEach(item => {
                    ids.push(item.id);
                })
                this.$confirm('此操作将永久删除【' + ids.length + '】个职位, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/jobLevel/", JSON.stringify(ids)).then(resp => {
                        if (resp) {
                            this.initJobLevel();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
        }
    }
</script>

<style scoped>

</style>
