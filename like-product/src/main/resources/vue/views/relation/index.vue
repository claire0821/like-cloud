<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="品牌id" prop="brandId">
                    <el-input class="w-[280px]" v-model="queryParams.brandId" />
                </el-form-item>
                <el-form-item label="分类id" prop="catelogId">
                    <el-input class="w-[280px]" v-model="queryParams.catelogId" />
                </el-form-item>
                <el-form-item label="" prop="brandName">
                    <el-input class="w-[280px]" v-model="queryParams.brandName" />
                </el-form-item>
                <el-form-item label="" prop="catelogName">
                    <el-input class="w-[280px]" v-model="queryParams.catelogName" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['relation:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="品牌id" prop="brandId" min-width="100" />
                <el-table-column label="分类id" prop="catelogId" min-width="100" />
                <el-table-column label="" prop="brandName" min-width="100" />
                <el-table-column label="" prop="catelogName" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['relation:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['relation:del']"
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
<script lang="ts" setup name="relation">
import { relationDelete, relationLists } from '@/api/relation'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    brandId: '',
    catelogId: '',
    brandName: '',
    catelogName: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: relationLists,
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
    await relationDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
