<template>
	<div>
		<div>
			<el-input size="small" placeholder="添加职称..." prefix-icon="el-icon-plus" v-model="posTitle.name" style="width: 300px;margin-right: 8px;">
			</el-input>
			<el-select size="small" v-model="selectTitle" placeholder="请选择">
				<el-option v-for="item in titleLevels" :key="item.value" :label="item.label" :value="item.value">
				</el-option>
			</el-select>
			<el-button size="small" type="primary" icon="el-icon-plus" @click="addJobLevel" style="margin-left: 8px;">添加</el-button>
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
	</div>
</template>

<script>
	export default {
		name: "PosTitleManage",
		data() {
			return {
				posTitle: {
					name: ''
				},
				selectTitle: '',
				titleLevels: [],
				jobLevels:[],
				multipleSelection:[],
			}
		},
		mounted() {
			this.initSelect();
			this.initJobLevel();
		},
		methods:{
			initSelect(){
				this.getRequest("/system/basic/jobLevel/titleLevels").then(resp=>{
					console.log("resp",resp);
					if(resp){
						resp.forEach(item=>{
							let obj={};
							obj.label=item;
							obj.value=item;
							this.titleLevels.push(obj);
						})
					}
				})
			},
			initJobLevel(){
				this.getRequest("/system/basic/jobLevel/").then(resp=>{
					if(resp){
						this.jobLevels=resp;
					}
				})
			},
			addJobLevel(){
				
			},
			handleEdit(){
				
			},
			handleDelete(index,data){
				this.deleteRequest("/system/basic/jobLevel/"+data.id).then(resp=>{
					if(resp){
						this.initJobLevel();
					}
				})
			},
			batchDel(){
				
			},
		}
	}
</script>

<style scoped>

</style>
