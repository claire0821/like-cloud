<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="品牌名" prop="name">
                    <el-input class="w-[280px]" v-model="queryParams.name" />
                </el-form-item>
                <el-form-item label="显示状态[0-不显示；1-显示]" prop="showStatus">
                    <el-select
                        v-model="queryParams.showStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="检索首字母" prop="firstLetter">
                    <el-input class="w-[280px]" v-model="queryParams.firstLetter" />
                </el-form-item>
                <el-form-item label="排序" prop="sort">
                    <el-input class="w-[280px]" v-model="queryParams.sort" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['brand:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="品牌名" prop="name" min-width="100" />
                <el-table-column label="品牌logo地址" prop="logo" min-width="100" />
                <el-table-column label="介绍" prop="descript" min-width="100" />
                <el-table-column label="显示状态[0-不显示；1-显示]" prop="showStatus" min-width="100" />
                <el-table-column label="检索首字母" prop="firstLetter" min-width="100" />
                <el-table-column label="排序" prop="sort" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['brand:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['brand:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.brandId)"
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
<script lang="ts" setup name="brand">
import { brandDelete, brandLists } from '@/api/brand'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    name: '',
    logo: '',
    descript: '',
    showStatus: '',
    firstLetter: '',
    sort: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: brandLists,
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

const handleDelete = async (brandId: number) => {
    await feedback.confirm('确定要删除？')
    await brandDelete({ brandId })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
