<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="属性名" prop="attrName">
                    <el-input class="w-[280px]" v-model="queryParams.attrName" />
                </el-form-item>
                <el-form-item label="是否需要检索[0-不需要，1-需要]" prop="searchType">
                    <el-select
                        v-model="queryParams.searchType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="属性图标" prop="icon">
                    <el-input class="w-[280px]" v-model="queryParams.icon" />
                </el-form-item>
                <el-form-item label="可选值列表[用逗号分隔]" prop="valueSelect">
                    <el-input class="w-[280px]" v-model="queryParams.valueSelect" />
                </el-form-item>
                <el-form-item label="属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]" prop="attrType">
                    <el-select
                        v-model="queryParams.attrType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="启用状态[0 - 禁用，1 - 启用]" prop="enable">
                    <el-input class="w-[280px]" v-model="queryParams.enable" />
                </el-form-item>
                <el-form-item label="所属分类" prop="catelogId">
                    <el-input class="w-[280px]" v-model="queryParams.catelogId" />
                </el-form-item>
                <el-form-item label="快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整" prop="showDesc">
                    <el-input class="w-[280px]" v-model="queryParams.showDesc" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['attr:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="属性名" prop="attrName" min-width="100" />
                <el-table-column label="是否需要检索[0-不需要，1-需要]" prop="searchType" min-width="100" />
                <el-table-column label="属性图标" prop="icon" min-width="100" />
                <el-table-column label="可选值列表[用逗号分隔]" prop="valueSelect" min-width="100" />
                <el-table-column label="属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]" prop="attrType" min-width="100" />
                <el-table-column label="启用状态[0 - 禁用，1 - 启用]" prop="enable" min-width="100" />
                <el-table-column label="所属分类" prop="catelogId" min-width="100" />
                <el-table-column label="快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整" prop="showDesc" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['attr:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['attr:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.attrId)"
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
<script lang="ts" setup name="attr">
import { attrDelete, attrLists } from '@/api/attr'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    attrName: '',
    searchType: '',
    icon: '',
    valueSelect: '',
    attrType: '',
    enable: '',
    catelogId: '',
    showDesc: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: attrLists,
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

const handleDelete = async (attrId: number) => {
    await feedback.confirm('确定要删除？')
    await attrDelete({ attrId })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
