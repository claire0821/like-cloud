<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="订阅时间" prop="subcribeTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="发送时间" prop="sendTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="通知方式[0-短信，1-邮件]" prop="noticeType">
                    <el-select
                        v-model="queryParams.noticeType"
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
                    <el-button v-perms="['notice:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="member_id" prop="memberId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="sku_id" prop="skuId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="活动场次id" prop="sessionId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订阅时间" prop="subcribeTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="发送时间" prop="sendTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="通知方式[0-短信，1-邮件]" prop="noticeType" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['notice:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['notice:del']"
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
<script lang="ts" setup name="notice">
import { noticeDeleteBatch, noticeLists } from '@/api/coupon/notice'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './notice_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    memberId: '',
    skuId: '',
    sessionId: '',
    subcribeTimeStart: '',
    subcribeTimeEnd: '',
    sendTimeStart: '',
    sendTimeEnd: '',
    noticeType: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: noticeLists,
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
    await noticeDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
