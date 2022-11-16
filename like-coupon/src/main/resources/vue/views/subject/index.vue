<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="专题名字" prop="name">
                    <el-input class="w-[280px]" v-model="queryParams.name" />
                </el-form-item>
                <el-form-item label="专题标题" prop="title">
                    <el-input class="w-[280px]" v-model="queryParams.title" />
                </el-form-item>
                <el-form-item label="专题副标题" prop="subTitle">
                    <el-input class="w-[280px]" v-model="queryParams.subTitle" />
                </el-form-item>
                <el-form-item label="显示状态" prop="status">
                    <el-select
                        v-model="queryParams.status"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['subject:add']" type="primary" @click="handleAdd()">
                        <template #icon>
                            <icon name="el-icon-Plus" />
                        </template>
                    新增
                </el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <el-table
                class="mt-4"
                size="large"
                v-loading="pager.loading"
                :data="pager.lists"
                @selection-change="handleSelectionChange"
            >
            <el-table-column type="selection" width="55" header-align="center" align="center"/>
                <el-table-column label="专题名字" prop="name" min-width="100" header-align="center" align="center"/>
                <el-table-column label="专题标题" prop="title" min-width="100" header-align="center" align="center"/>
                <el-table-column label="专题副标题" prop="subTitle" min-width="100" header-align="center" align="center"/>
                <el-table-column label="显示状态" prop="status" min-width="100" header-align="center" align="center"/>
                <el-table-column label="详情连接" prop="url" min-width="100" header-align="center" align="center"/>
                <el-table-column label="排序" prop="sort" min-width="100" header-align="center" align="center"/>
                <el-table-column label="专题图片地址" prop="img" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['subject:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['subject:del']"
                            type="danger"
                            link
                            @click="handleDelete([row.id])"
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
<script lang="ts" setup name="subject">
import { subjectDeleteBatch, subjectLists } from '@/api/coupon/subject'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './subject_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    name: '',
    title: '',
    subTitle: '',
    status: '',
    url: '',
    sort: '',
    img: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: subjectLists,
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
const handleSelectionChange = (val: any[]) => {
    selectData.value = val.map(({ id }) => id)
}
//批量删除
const handleDelete = async (ids: any[] | number) => {
    await feedback.confirm('确定要删除？')
    await subjectDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
