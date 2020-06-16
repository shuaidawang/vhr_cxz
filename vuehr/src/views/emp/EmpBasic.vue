<template>
    <div>
        <div style="display: flex;justify-content: space-between;margin-top: 20px;">
            <div>
                <el-input size="mini" placeholder="通过员工姓名搜索..." prefix-icon="el-icon-search"
                          style="width: 300px;margin-right: 8px;"
                          v-model="keyword"
                          clearable @clear="initTable"
                          @keydown.enter.native="initTable"></el-input>
                <el-button size="mini" type="primary" icon="el-icon-search" @click="initTable">搜索</el-button>
                <el-button size="mini" type="primary"><i class="fa fa-angle-double-down" aria-hidden="true"></i>高级搜索
                </el-button>
            </div>
            <div>
                <el-upload style="display: inline-flex;margin-right: 8px;"
                           :show-file-list="false"
                           :on-success="importSuccess"
                           :on-error="importError"
                           :before-upload="beforeUpload"
                           :disabled="importDataDisabled"
                           action="/employee/basic/import">
                    <el-button size="mini" type="success" :icon="importDataBtnIcon" :disabled="importDataDisabled">
                        {{importLabel}}
                    </el-button>
                </el-upload>
                <el-button size="mini" type="success" icon="el-icon-download" @click="exportData">导出数据</el-button>
                <el-button size="mini" type="primary" icon="el-icon-plus" @click="showAddDialog">添加员工</el-button>
            </div>
        </div>
        <div style="margin-top: 20px;">
            <el-table
                    :data="tableData"
                    border
                    style="width: 100%">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        fixed
                        prop="name"
                        label="姓名"
                        width="90">
                </el-table-column>
                <el-table-column
                        prop="workId"
                        label="工号"
                        width="90">
                </el-table-column>
                <el-table-column
                        prop="gender"
                        label="性别"
                        width="60">
                </el-table-column>
                <el-table-column
                        prop="birthday"
                        label="出生日期"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="idCard"
                        label="身份证号码"
                        width="160">
                </el-table-column>
                <el-table-column
                        prop="wedlock"
                        label="婚姻状况"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="nation.name"
                        label="民族"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="nativePlace"
                        label="籍贯"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="politicsstatus.name"
                        label="政治面貌"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="email"
                        label="电子邮件"
                        width="140">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="电话号码"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="联系地址"
                        width="160">
                </el-table-column>
                <el-table-column
                        prop="department.name"
                        label="所属部门"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="position.name"
                        label="职位"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="jobLevel.name"
                        label="职称"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="engageForm"
                        label="聘用形式"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="beginDate"
                        label="入职日期"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="conversionTime"
                        label="转正日期"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="beginContract"
                        label="合同起始日期"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="endContract"
                        label="合同截止日期"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="contractTerm"
                        label="合同期限"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="tiptopDegree"
                        label="最高学历"
                        width="80">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button @click="showEditDialog(scope.row)" type="text" size="small">编辑</el-button>
                        <el-button type="text" size="small">查看高级资料</el-button>
                        <el-button @click="delEmployee(scope.row)" type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div style="display: flex;justify-content: space-between;">
            <el-button type="danger">批量删除</el-button>
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="curPage"
                    :page-sizes="[10, 50, 100, 200]"
                    :page-size="10"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
        <el-dialog
                :title="title"
                :visible.sync="dialogVisible"
                width="80%">
            <el-row :gutter="20">
                <el-form :model="emp" :rules="rules" ref="empForm">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="姓名:" prop="name">
                                <el-input v-model="emp.name" prefix-icon="el-icon-edit" size="mini"
                                          placeholder="请输入员工姓名" style="width: 150px;"></el-input>
                            </el-form-item>

                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="性别:" prop="gender">
                                <el-radio-group v-model="emp.gender">
                                    <el-radio label="男">男</el-radio>
                                    <el-radio label="女">女</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="出生日期:" prop="birthday">
                                <el-date-picker
                                        v-model="emp.birthday"
                                        size="mini"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        style="width: 150px;"
                                        placeholder="出生日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7">
                            <el-form-item label="政治面貌:" prop="politicId">
                                <el-select v-model="emp.politicId" placeholder="请选择政治面貌" size="mini"
                                           style="width: 150px;">
                                    <el-option
                                            v-for="item in politicsstatus"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="民族:" prop="nationId">
                                <el-select v-model="emp.nationId" placeholder="请选择民族" size="mini" style="width: 150px;">
                                    <el-option
                                            v-for="item in nations"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="籍贯:" prop="nativePlace">
                                <el-input v-model="emp.nativePlace" placeholder="员工籍贯" size="mini"
                                          style="width: 150px;"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="电子邮箱:" prop="email">
                                <el-input v-model="emp.email" placeholder="电子邮箱" size="mini"
                                          style="width: 150px;"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7">
                            <el-form-item label="联系地址:" prop="address">
                                <el-input v-model="emp.address" placeholder="联系地址" size="mini"
                                          style="width: 150px;"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="职位:" prop="posId">
                                <el-select v-model="emp.posId" placeholder="请选择职位" size="mini" style="width: 150px;">
                                    <el-option
                                            v-for="item in positions"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="职称:" prop="jobLevelId">
                                <el-select v-model="emp.jobLevelId" placeholder="请选择职称" size="mini"
                                           style="width: 150px;">
                                    <el-option
                                            v-for="item in joblevels"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="所属部门:" prop="departmentId">
                                <el-popover
                                        title="请选择部门"
                                        width="200"
                                        v-model="depPopVisible"
                                        placement="right"
                                        trigger="manual">
                                    <el-tree :data="allDeps" :props="defaultProps"
                                             :default-expand-all="true"
                                             :expand-on-click-node="false"
                                             @node-click="handleNodeClick"></el-tree>
                                    <div slot="reference" style="display: inline-flex;width: 150px;height: 26px;border-radius:4px;border: 1px solid #ededed;
                                                align-items: center;padding:0px 15px;box-sizing: border-box;font-size: 12px;"
                                         v-model="emp.departmentId" size="mini" @click="showDepPop">{{departmentName}}
                                    </div>
                                </el-popover>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7">
                            <el-form-item label="电话号码:" prop="phone">
                                <el-input v-model="emp.phone" placeholder="电话号码" size="mini"
                                          style="width: 150px;"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="工号:" prop="workId">
                                <el-input v-model="emp.workId" size="mini" style="width: 150px;" disabled></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="学历:" prop="tiptopDegree">
                                <el-select v-model="emp.tiptopDegree" placeholder="请选择学历" size="mini"
                                           style="width: 150px;">
                                    <el-option
                                            v-for="item in tiptopDegrees"
                                            :key="item"
                                            :label="item"
                                            :value="item">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="毕业院校:" prop="school">
                                <el-input v-model="emp.school" placeholder="毕业院校名称" size="mini"
                                          style="width: 150px;"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7">
                            <el-form-item label="专业名称:" prop="specialty">
                                <el-input v-model="emp.specialty" placeholder="专业名称" size="mini"
                                          style="width: 150px;"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="入职日期:" prop="beginDate">
                                <el-date-picker
                                        v-model="emp.beginDate"
                                        size="mini"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        style="width: 150px;"
                                        placeholder="入职日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="转正日期:" prop="conversionTime">
                                <el-date-picker
                                        v-model="emp.conversionTime"
                                        size="mini"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        style="width: 150px;"
                                        placeholder="转正日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="合同起始日期:" prop="beginContract">
                                <el-date-picker
                                        v-model="emp.beginContract"
                                        size="mini"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        style="width: 150px;"
                                        placeholder="合同起始日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="7">
                            <el-form-item label="合同终止日期:" prop="endContract">
                                <el-date-picker
                                        v-model="emp.endContract"
                                        size="mini"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        style="width: 150px;"
                                        placeholder="合同终止日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="身份证号码:" prop="idCard">
                                <el-input v-model="emp.idCard" placeholder="请输入身份证号码" size="mini"
                                          style="width: 150px;"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="聘用形式:" prop="engageForm">
                                <el-radio-group v-model="emp.engageForm">
                                    <el-radio label="劳动合同">劳动合同</el-radio>
                                    <el-radio label="劳务合同">劳务合同</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="婚姻状况:" prop="wedlock">
                                <el-radio-group v-model="emp.wedlock">
                                    <el-radio label="已婚">已婚</el-radio>
                                    <el-radio label="未婚">未婚</el-radio>
                                    <el-radio label="离异">离异</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                    </el-row>

                </el-form>
            </el-row>
            <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="btnAdd">确 定</el-button>
          </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "EmpBasic",
        data() {
            return {
                importLabel: '导入数据',
                importDataBtnIcon: 'el-icon-upload2',
                importDataDisabled: false,
                title: '',
                tableData: [],
                total: null,
                curPage: 1,
                size: 10,
                keyword: '',
                depPopVisible: false,
                dialogVisible: false,
                departmentName: '',
                emp: {
                    name: "",
                    gender: "",
                    birthday: "",
                    idCard: "",
                    wedlock: "",
                    nationId: null,
                    nativePlace: "",
                    politicId: null,
                    email: "",
                    phone: "",
                    address: "",
                    departmentId: null,
                    jobLevelId: null,
                    posId: null,
                    engageForm: "",
                    tiptopDegree: "",
                    specialty: "",
                    school: "",
                    beginDate: "",
                    workId: "",
                    contractTerm: null,
                    conversionTime: "",
                    notworkDate: null,
                    beginContract: "",
                    endContract: "",
                    workAge: null
                },
                popVisible: false,
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                allDeps: [],
                nations: [],
                joblevels: [],
                politicsstatus: [],
                positions: [],
                tiptopDegrees: ['本科', '大专', '硕士', '博士', '高中', '初中', '小学', '其他'],
                rules: {
                    name: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    gender: [{required: true, message: '请输入性别', trigger: 'blur'}],
                    birthday: [{required: true, message: '请输入出生日期', trigger: 'blur'}],
                    idCard: [{required: true, message: '请输入身份证号码', trigger: 'blur'}, {
                        pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
                        message: '身份证号码格式不正确',
                        trigger: 'blur'
                    }],
                    wedlock: [{required: true, message: '请输入婚姻状况', trigger: 'blur'}],
                    nationId: [{required: true, message: '请输入您组', trigger: 'blur'}],
                    nativePlace: [{required: true, message: '请输入籍贯', trigger: 'blur'}],
                    politicId: [{required: true, message: '请输入政治面貌', trigger: 'blur'}],
                    email: [{required: true, message: '请输入邮箱地址', trigger: 'blur'}, {
                        type: 'email',
                        message: '邮箱格式不正确',
                        trigger: 'blur'
                    }],
                    phone: [{required: true, message: '请输入电话号码', trigger: 'blur'}],
                    address: [{required: true, message: '请输入员工地址', trigger: 'blur'}],
                    departmentId: [{required: true, message: '请输入部门名称', trigger: 'blur'}],
                    jobLevelId: [{required: true, message: '请输入职称', trigger: 'blur'}],
                    posId: [{required: true, message: '请输入职位', trigger: 'blur'}],
                    engageForm: [{required: true, message: '请输入聘用形式', trigger: 'blur'}],
                    tiptopDegree: [{required: true, message: '请输入学历', trigger: 'blur'}],
                    specialty: [{required: true, message: '请输入专业', trigger: 'blur'}],
                    school: [{required: true, message: '请输入毕业院校', trigger: 'blur'}],
                    beginDate: [{required: true, message: '请输入入职日期', trigger: 'blur'}],
                    workState: [{required: true, message: '请输入工作状态', trigger: 'blur'}],
                    workId: [{required: true, message: '请输入工号', trigger: 'blur'}],
                    contractTerm: [{required: true, message: '请输入合同期限', trigger: 'blur'}],
                    conversionTime: [{required: true, message: '请输入转正日期', trigger: 'blur'}],
                    notworkDate: [{required: true, message: '请输入离职日期', trigger: 'blur'}],
                    beginContract: [{required: true, message: '请输入合同起始日期', trigger: 'blur'}],
                    endContract: [{required: true, message: '请输入合同结束日期', trigger: 'blur'}],
                    workAge: [{required: true, message: '请输入工龄', trigger: 'blur'}],
                }
            }
        },
        mounted() {
            this.initTable();
        },
        methods: {
            initTable() {
                this.getRequest("/employee/basic/?curPage=" + this.curPage + "&size=" + this.size + "&keyword=" + this.keyword).then(resp => {
                    if (resp) {
                        this.tableData = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            handleCurrentChange(e) {
                this.curPage = e;
                this.initTable();
            },
            handleSizeChange(e) {
                this.size = e;
                this.initTable();
            },
            showAddDialog() {
                this.emptyEmp();
                this.title = '添加员工';
                this.dialogVisible = true;
                this.initData();
                this.getMaxWorkID();
            },
            showEditDialog(data) {
                console.log(data)
                this.title = '编辑员工';
                this.emp = data;
                this.dialogVisible = true;
                this.initData();
                this.departmentName = data.department.name;
            },
            initData() {
                this.initPoliticsStatus();
                this.initNations();
                this.initPositions();
                this.initJobLevels();
                this.getAllDeps();
            },
            //政治面貌
            initPoliticsStatus() {
                this.getRequest("/employee/basic/listPoliticsStatus").then(resp => {
                    if (resp) {
                        this.politicsstatus = resp;
                    }
                })
            },
            //民族
            initNations() {
                this.getRequest("/employee/basic/listNations").then(resp => {
                    if (resp) {
                        this.nations = resp;
                    }
                })
            },
            //职位
            initPositions() {
                this.getRequest("/employee/basic/listPositions").then(resp => {
                    if (resp) {
                        this.positions = resp;
                    }
                })
            },
            //职称
            initJobLevels() {
                this.getRequest("/employee/basic/listJobLevels").then(resp => {
                    if (resp) {
                        this.joblevels = resp;
                    }
                })
            },
            getMaxWorkID() {
                this.getRequest("/employee/basic/getMaxWorkID").then(resp => {
                    if (resp) {
                        this.emp.workId = resp;
                    }
                })
            },
            //初始化所有部门
            getAllDeps() {
                this.getRequest("/employee/basic/listAllDeps").then(resp => {
                    if (resp) {
                        this.allDeps = resp;
                    }
                })
            },
            //所属部门pop
            showDepPop() {
                this.depPopVisible = !this.depPopVisible;
            },
            handleNodeClick(data) {
                this.departmentName = data.name;
                this.emp.departmentId = data.id;
                this.depPopVisible = !this.depPopVisible;
            },
            btnAdd() {
                if (this.emp.id) {//编辑
                    this.$refs['empForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/employee/basic/", this.emp).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initTable();
                                }
                            })
                        }
                    })
                } else {//添加
                    this.$refs['empForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/employee/basic/", this.emp).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initTable();
                                }
                            })
                        }
                    })
                }

            },
            delEmployee(data) {
                this.$confirm('此操作将永久删除用户【' + data.name + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/employee/basic/" + data.id).then(resp => {
                        if (resp) {
                            this.initTable();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            emptyEmp() {
                this.emp = {
                    name: "",
                    gender: "",
                    birthday: "",
                    idCard: "",
                    wedlock: "",
                    nationId: 1,
                    nativePlace: "",
                    politicId: 13,
                    email: "",
                    phone: "",
                    address: "",
                    departmentId: null,
                    jobLevelId: 9,
                    posId: 29,
                    engageForm: "",
                    tiptopDegree: "",
                    specialty: "",
                    school: "",
                    beginDate: "",
                    workId: "",
                    contractTerm: 2,
                    conversionTime: "",
                    notworkDate: null,
                    beginContract: "",
                    endContract: "",
                    workAge: null
                }
                this.departmentName = '';
            },
            exportData() {
                window.open('/employee/basic/export', '_parent');
            },
            beforeUpload() {
                this.importLabel = '正在导入';
                this.importDataBtnIcon = 'el-icon-loading';
                this.importDataDisabled = true;
            },
            importSuccess(response, file, fileList) {
                this.importLabel = "导入数据";
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
                this.$message.success("上传成功!");
            },
            importError(err, file, fileList) {
                this.importLabel = "导入数据";
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
                this.$message.error(err);
            }

        }
    }
</script>

<style scoped>

</style>