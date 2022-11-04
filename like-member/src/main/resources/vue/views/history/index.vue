<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="member_id" prop="memberId">
                    <el-input class="w-[280px]" v-model="queryParams.memberId" />
                </el-form-item>
                <el-form-item label="变化的值" prop="changeCount">
                    <el-input class="w-[280px]" v-model="queryParams.changeCount" />
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-input class="w-[280px]" v-model="queryParams.note" />
                </el-form-item>
                <el-form-item label="来源[0->购物；1->管理员修改;2->活动]" prop="sourceTyoe">
                    <el-input class="w-[280px]" v-model="queryParams.sourceTyoe" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['history:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="create_time" prop="createTime" min-width="100" />
                <el-table-column label="变化的值" prop="changeCount" min-width="100" />
                <el-table-column label="备注" prop="note" min-width="100" />
                <el-table-column label="来源[0->购物；1->管理员修改;2->活动]" prop="sourceTyoe" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['history:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['history:del']"
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
<script lang="ts" setup name="history">
import { historyDelete, historyLists } from '@/api/history'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    memberId: '',
    changeCount: '',
    note: '',
    sourceTyoe: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: historyLists,
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
    await historyDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
