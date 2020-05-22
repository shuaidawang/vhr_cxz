<template>
    <el-tabs v-model="activeName" type="card">
        <el-tab-pane label="部门管理" name="first"><DeptManage></DeptManage></el-tab-pane>
        <el-tab-pane label="职位管理" name="second"><PosManage></PosManage></el-tab-pane>
        <el-tab-pane label="职称管理" name="third"><PosTitleManage></PosTitleManage></el-tab-pane>
        <el-tab-pane label="奖惩规则" name="fourth"><EcManage></EcManage></el-tab-pane>
        <el-tab-pane label="权限组" name="fifth"><PermissionManage></PermissionManage></el-tab-pane>
    </el-tabs>
</template>

<script>
    import DeptManage from "../../components/sys/basic/DeptManage";
    import EcManage from "../../components/sys/basic/EcManage";
    import PermissionManage from "../../components/sys/basic/PermissionManage";
    import PosManage from "../../components/sys/basic/PosManage";
    import PosTitleManage from "../../components/sys/basic/PosTitleManage";

    export default {
        name: "SysCfg",
        data(){
            return{
                activeName:'first'
            }
        },
        components:{
            DeptManage,
            EcManage,
            PermissionManage,
            PosManage,
            PosTitleManage
        }
    }
</script>

<style scoped>

</style>