<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="用户名" prop="username">
                    <el-input class="w-[280px]" v-model="queryParams.username" />
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input class="w-[280px]" v-model="queryParams.password" />
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input class="w-[280px]" v-model="queryParams.nickname" />
                </el-form-item>
                <el-form-item label="手机号码" prop="mobile">
                    <el-input class="w-[280px]" v-model="queryParams.mobile" />
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input class="w-[280px]" v-model="queryParams.email" />
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <template #default="scope">
                        <el-icon v-if="scope.row.gender == 1"><SuccessFilled /></el-icon>
                        <el-icon v-else><CircleCloseFilled /></el-icon>
                    </template>
                </el-form-item>
                <el-form-item label="生日" prop="birth">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="职业" prop="job">
                    <el-input class="w-[280px]" v-model="queryParams.job" />
                </el-form-item>
                <el-form-item label="个性签名" prop="sign">
                    <el-input class="w-[280px]" v-model="queryParams.sign" />
                </el-form-item>
                <el-form-item label="用户来源" prop="sourceType">
                    <el-select
                        v-model="queryParams.sourceType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="启用状态" prop="status">
                    <el-select
                        v-model="queryParams.status"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['member:add']" type="primary" @click="handleAdd()">
                        <template #icon>
                            <icon name="el-icon-Plus" />
                        </template>
                    新增
                </el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <el-table
                class="mt-4"
                size="large"
                v-loading="pager.loading"
                :data="pager.lists"
                @selection-change="handleSelectionChange"
            >
            <el-table-column type="selection" width="55" header-align="center" align="center"/>
                <el-table-column label="会员等级id" prop="levelId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="用户名" prop="username" min-width="100" header-align="center" align="center"/>
                <el-table-column label="密码" prop="password" min-width="100" header-align="center" align="center"/>
                <el-table-column label="昵称" prop="nickname" min-width="100" header-align="center" align="center"/>
                <el-table-column label="手机号码" prop="mobile" min-width="100" header-align="center" align="center"/>
                <el-table-column label="邮箱" prop="email" min-width="100" header-align="center" align="center"/>
                <el-table-column label="头像" prop="header" min-width="100" header-align="center" align="center"/>
                <el-table-column label="性别" prop="gender" min-width="100" header-align="center" align="center"/>
                <el-table-column label="生日" prop="birth" min-width="100" header-align="center" align="center"/>
                <el-table-column label="所在城市" prop="city" min-width="100" header-align="center" align="center"/>
                <el-table-column label="职业" prop="job" min-width="100" header-align="center" align="center"/>
                <el-table-column label="个性签名" prop="sign" min-width="100" header-align="center" align="center"/>
                <el-table-column label="用户来源" prop="sourceType" min-width="100" header-align="center" align="center"/>
                <el-table-column label="积分" prop="integration" min-width="100" header-align="center" align="center"/>
                <el-table-column label="成长值" prop="growth" min-width="100" header-align="center" align="center"/>
                <el-table-column label="启用状态" prop="status" min-width="100" header-align="center" align="center"/>
                <el-table-column label="注册时间" prop="createTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['member:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['member:del']"
                            type="danger"
                            link
                            @click="handleDelete([row.id])"
                        >
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="flex justify-end mt-4">
                <pagination v-model="pager" @change="getLists" />
            </div>
        </el-card>
        <edit-popup
            v-if="showEdit"
            ref="editRef"
            @success="getLists"
            @close="showEdit = false"
        />
    </div>
</template>
<script lang="ts" setup name="member">
import { memberDeleteBatch, memberLists } from '@/api/member/member'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './member_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    levelId: '',
    username: '',
    password: '',
    nickname: '',
    mobile: '',
    email: '',
    header: '',
    gender: '',
    birthStart: '',
    birthEnd: '',
    city: '',
    job: '',
    sign: '',
    sourceType: '',
    integration: '',
    growth: '',
    status: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: memberLists,
    params: queryParams
})


const handleAdd = async () => {
    showEdit.value = true
    await nextTick()
    editRef.value?.open('add')
}

const handleEdit = async (data: any) => {
    showEdit.value = true
    await nextTick()
    editRef.value?.open('edit')
    editRef.value?.getDetail(data)
}
const handleSelectionChange = (val: any[]) => {
    selectData.value = val.map(({ id }) => id)
}
//批量删除
const handleDelete = async (ids: any[] | number) => {
    await feedback.confirm('确定要删除？')
    await memberDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
