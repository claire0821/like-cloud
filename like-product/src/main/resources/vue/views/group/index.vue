<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="组名" prop="attrGroupName">
                    <el-input class="w-[280px]" v-model="queryParams.attrGroupName" />
                </el-form-item>
                <el-form-item label="排序" prop="sort">
                    <el-input class="w-[280px]" v-model="queryParams.sort" />
                </el-form-item>
                <el-form-item label="描述" prop="descript">
                    <el-input class="w-[280px]" v-model="queryParams.descript" />
                </el-form-item>
                <el-form-item label="组图标" prop="icon">
                    <el-input class="w-[280px]" v-model="queryParams.icon" />
                </el-form-item>
                <el-form-item label="所属分类id" prop="catelogId">
                    <el-input class="w-[280px]" v-model="queryParams.catelogId" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['group:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="组名" prop="attrGroupName" min-width="100" />
                <el-table-column label="排序" prop="sort" min-width="100" />
                <el-table-column label="描述" prop="descript" min-width="100" />
                <el-table-column label="组图标" prop="icon" min-width="100" />
                <el-table-column label="所属分类id" prop="catelogId" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['group:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['group:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.attrGroupId)"
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
<script lang="ts" setup name="group">
import { groupDelete, groupLists } from '@/api/group'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    attrGroupName: '',
    sort: '',
    descript: '',
    icon: '',
    catelogId: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: groupLists,
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

const handleDelete = async (attrGroupId: number) => {
    await feedback.confirm('确定要删除？')
    await groupDelete({ attrGroupId })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
