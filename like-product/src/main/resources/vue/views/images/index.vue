<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="sku_id" prop="skuId">
                    <el-input class="w-[280px]" v-model="queryParams.skuId" />
                </el-form-item>
                <el-form-item label="图片地址" prop="imgUrl">
                    <el-input class="w-[280px]" v-model="queryParams.imgUrl" />
                </el-form-item>
                <el-form-item label="排序" prop="imgSort">
                    <el-input class="w-[280px]" v-model="queryParams.imgSort" />
                </el-form-item>
                <el-form-item label="默认图[0 - 不是默认图，1 - 是默认图]" prop="defaultImg">
                    <el-input class="w-[280px]" v-model="queryParams.defaultImg" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['images:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="sku_id" prop="skuId" min-width="100" />
                <el-table-column label="图片地址" prop="imgUrl" min-width="100" />
                <el-table-column label="排序" prop="imgSort" min-width="100" />
                <el-table-column label="默认图[0 - 不是默认图，1 - 是默认图]" prop="defaultImg" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['images:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['images:del']"
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
<script lang="ts" setup name="images">
import { imagesDelete, imagesLists } from '@/api/images'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    skuId: '',
    imgUrl: '',
    imgSort: '',
    defaultImg: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: imagesLists,
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
    await imagesDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
