<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="分类名称" prop="name">
                    <el-input class="w-[280px]" v-model="queryParams.name" />
                </el-form-item>
                <el-form-item label="父分类id" prop="parentCid">
                    <el-input class="w-[280px]" v-model="queryParams.parentCid" />
                </el-form-item>
                <el-form-item label="层级" prop="catLevel">
                    <el-input class="w-[280px]" v-model="queryParams.catLevel" />
                </el-form-item>
                <el-form-item label="是否显示[0-不显示，1显示]" prop="showStatus">
                    <el-select
                        v-model="queryParams.showStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="排序" prop="sort">
                    <el-input class="w-[280px]" v-model="queryParams.sort" />
                </el-form-item>
                <el-form-item label="图标地址" prop="icon">
                    <el-input class="w-[280px]" v-model="queryParams.icon" />
                </el-form-item>
                <el-form-item label="计量单位" prop="productUnit">
                    <el-input class="w-[280px]" v-model="queryParams.productUnit" />
                </el-form-item>
                <el-form-item label="商品数量" prop="productCount">
                    <el-input class="w-[280px]" v-model="queryParams.productCount" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['category:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="分类名称" prop="name" min-width="100" />
                <el-table-column label="父分类id" prop="parentCid" min-width="100" />
                <el-table-column label="层级" prop="catLevel" min-width="100" />
                <el-table-column label="是否显示[0-不显示，1显示]" prop="showStatus" min-width="100" />
                <el-table-column label="排序" prop="sort" min-width="100" />
                <el-table-column label="图标地址" prop="icon" min-width="100" />
                <el-table-column label="计量单位" prop="productUnit" min-width="100" />
                <el-table-column label="商品数量" prop="productCount" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['category:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['category:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.catId)"
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
<script lang="ts" setup name="category">
import { categoryDelete, categoryLists } from '@/api/category'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    name: '',
    parentCid: '',
    catLevel: '',
    showStatus: '',
    sort: '',
    icon: '',
    productUnit: '',
    productCount: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: categoryLists,
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

const handleDelete = async (catId: number) => {
    await feedback.confirm('确定要删除？')
    await categoryDelete({ catId })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
