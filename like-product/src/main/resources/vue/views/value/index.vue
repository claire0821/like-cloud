<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="商品id" prop="spuId">
                    <el-input class="w-[280px]" v-model="queryParams.spuId" />
                </el-form-item>
                <el-form-item label="属性id" prop="attrId">
                    <el-input class="w-[280px]" v-model="queryParams.attrId" />
                </el-form-item>
                <el-form-item label="属性名" prop="attrName">
                    <el-input class="w-[280px]" v-model="queryParams.attrName" />
                </el-form-item>
                <el-form-item label="属性值" prop="attrValue">
                    <el-input class="w-[280px]" v-model="queryParams.attrValue" />
                </el-form-item>
                <el-form-item label="顺序" prop="attrSort">
                    <el-input class="w-[280px]" v-model="queryParams.attrSort" />
                </el-form-item>
                <el-form-item label="快速展示【是否展示在介绍上；0-否 1-是】" prop="quickShow">
                    <el-input class="w-[280px]" v-model="queryParams.quickShow" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['value:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="商品id" prop="spuId" min-width="100" />
                <el-table-column label="属性id" prop="attrId" min-width="100" />
                <el-table-column label="属性名" prop="attrName" min-width="100" />
                <el-table-column label="属性值" prop="attrValue" min-width="100" />
                <el-table-column label="顺序" prop="attrSort" min-width="100" />
                <el-table-column label="快速展示【是否展示在介绍上；0-否 1-是】" prop="quickShow" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['value:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['value:del']"
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
<script lang="ts" setup name="value">
import { valueDelete, valueLists } from '@/api/value'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    spuId: '',
    attrId: '',
    attrName: '',
    attrValue: '',
    attrSort: '',
    quickShow: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: valueLists,
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
    await valueDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
