<template>
    <div>
        <div style="display: flex;justify-content: space-between;margin-top: 20px;">
            <div>
                <el-input size="mini" placeholder="通过员工号搜索..." prefix-icon="el-icon-search"
                          style="width: 300px;margin-right: 8px;"></el-input>
                <el-button size="mini" type="primary" icon="el-icon-search">搜索</el-button>
                <el-button size="mini" type="primary"><i class="fa fa-angle-double-down" aria-hidden="true"></i>高级搜索</el-button>
            </div>
            <div>
                <el-button size="mini" type="success" icon="el-icon-upload2">导入数据</el-button>
                <el-button size="mini" type="success" icon="el-icon-download">导出数据</el-button>
                <el-button size="mini" type="primary" icon="el-icon-plus">添加员工</el-button>
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
                        width="80">
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
                        <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
                        <el-button type="text" size="small">查看高级资料</el-button>
                        <el-button type="text" size="small">删除</el-button>
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
    </div>
</template>

<script>
    export default {
        name: "EmpBasic",
        data() {
            return {
                tableData: [],
                total: null,
                curPage:1,
                size:10
            }
        },
        mounted() {
            this.initTable();
        },
        methods: {
            initTable() {
                this.getRequest("/emp/basic/?curPage="+this.curPage+"&size="+this.size).then(resp=>{
                    if(resp){
                        this.tableData = resp.data;
                        this.total = resp.total;
                    }
                })
            },
            handleCurrentChange(e){
                this.curPage = e;
                this.initTable();
            },
            handleSizeChange(e){
                this.size = e;
                this.initTable();
            }
        }
    }
</script>

<style scoped>

</style>