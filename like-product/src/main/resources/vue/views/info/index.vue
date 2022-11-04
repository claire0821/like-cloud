<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="spuId" prop="spuId">
                    <el-input class="w-[280px]" v-model="queryParams.spuId" />
                </el-form-item>
                <el-form-item label="sku名称" prop="skuName">
                    <el-input class="w-[280px]" v-model="queryParams.skuName" />
                </el-form-item>
                <el-form-item label="所属分类id" prop="catalogId">
                    <el-input class="w-[280px]" v-model="queryParams.catalogId" />
                </el-form-item>
                <el-form-item label="品牌id" prop="brandId">
                    <el-input class="w-[280px]" v-model="queryParams.brandId" />
                </el-form-item>
                <el-form-item label="默认图片" prop="skuDefaultImg">
                    <el-input class="w-[280px]" v-model="queryParams.skuDefaultImg" />
                </el-form-item>
                <el-form-item label="标题" prop="skuTitle">
                    <el-input class="w-[280px]" v-model="queryParams.skuTitle" />
                </el-form-item>
                <el-form-item label="价格" prop="price">
                    <el-input class="w-[280px]" v-model="queryParams.price" />
                </el-form-item>
                <el-form-item label="销量" prop="saleCount">
                    <el-input class="w-[280px]" v-model="queryParams.saleCount" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['info:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="spuId" prop="spuId" min-width="100" />
                <el-table-column label="sku名称" prop="skuName" min-width="100" />
                <el-table-column label="sku介绍描述" prop="skuDesc" min-width="100" />
                <el-table-column label="所属分类id" prop="catalogId" min-width="100" />
                <el-table-column label="品牌id" prop="brandId" min-width="100" />
                <el-table-column label="默认图片" prop="skuDefaultImg" min-width="100" />
                <el-table-column label="标题" prop="skuTitle" min-width="100" />
                <el-table-column label="副标题" prop="skuSubtitle" min-width="100" />
                <el-table-column label="价格" prop="price" min-width="100" />
                <el-table-column label="销量" prop="saleCount" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['info:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['info:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.skuId)"
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
<script lang="ts" setup name="info">
import { infoDelete, infoLists } from '@/api/info'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    spuId: '',
    skuName: '',
    skuDesc: '',
    catalogId: '',
    brandId: '',
    skuDefaultImg: '',
    skuTitle: '',
    skuSubtitle: '',
    price: '',
    saleCount: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: infoLists,
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

const handleDelete = async (skuId: number) => {
    await feedback.confirm('确定要删除？')
    await infoDelete({ skuId })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
