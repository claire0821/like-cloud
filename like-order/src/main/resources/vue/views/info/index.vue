<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="退款交易流水号" prop="refundSn">
                    <el-input class="w-[280px]" v-model="queryParams.refundSn" />
                </el-form-item>
                <el-form-item label="退款状态" prop="refundStatus">
                    <el-select
                        v-model="queryParams.refundStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="退款渠道[1-支付宝，2-微信，3-银联，4-汇款]" prop="refundChannel">
                    <template #default="scope">
                        <el-icon v-if="scope.row.refundChannel == 1"><SuccessFilled /></el-icon>
                        <el-icon v-else><CircleCloseFilled /></el-icon>
                    </template>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['info:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="退款的订单" prop="orderReturnId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退款金额" prop="refund" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退款交易流水号" prop="refundSn" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退款状态" prop="refundStatus" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退款渠道[1-支付宝，2-微信，3-银联，4-汇款]" prop="refundChannel" min-width="100" header-align="center" align="center"/>
                <el-table-column label="" prop="refundContent" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
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
<script lang="ts" setup name="info">
import { infoDeleteBatch, infoLists } from '@/api/order/info'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './info_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    orderReturnId: '',
    refund: '',
    refundSn: '',
    refundStatus: '',
    refundChannel: '',
    refundContent: '',
})
const selectData = ref<any[]>([])
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
const handleSelectionChange = (val: any[]) => {
    selectData.value = val.map(({ id }) => id)
}
//批量删除
const handleDelete = async (ids: any[] | number) => {
    await feedback.confirm('确定要删除？')
    await infoDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
