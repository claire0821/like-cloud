<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="member_id" prop="memberId">
                    <el-input class="w-[280px]" v-model="queryParams.memberId" />
                </el-form-item>
                <el-form-item label="ip" prop="ip">
                    <el-input class="w-[280px]" v-model="queryParams.ip" />
                </el-form-item>
                <el-form-item label="city" prop="city">
                    <el-input class="w-[280px]" v-model="queryParams.city" />
                </el-form-item>
                <el-form-item label="登录类型[1-web，2-app]" prop="loginType">
                    <el-select
                        v-model="queryParams.loginType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['log:add']" type="primary" @click="handleAdd()">
                    <template #icon>
                        <icon name="el-icon-Plus" />
                    </template>
                    新增
                </el-button>
            </div>
            <el-table
                class="mt-4"
                size="large"
                v-loading="pager.loading"
                :data="pager.lists"
            >
                <el-table-column label="member_id" prop="memberId" min-width="100" />
                <el-table-column label="创建时间" prop="createTime" min-width="100" />
                <el-table-column label="ip" prop="ip" min-width="100" />
                <el-table-column label="city" prop="city" min-width="100" />
                <el-table-column label="登录类型[1-web，2-app]" prop="loginType" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['log:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['log:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.id)"
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
<script lang="ts" setup name="log">
import { logDelete, logLists } from '@/api/log'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    memberId: '',
    ip: '',
    city: '',
    loginType: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: logLists,
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

const handleDelete = async (id: number) => {
    await feedback.confirm('确定要删除？')
    await logDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
