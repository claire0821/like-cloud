<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="order_sn" prop="orderSn">
                    <el-input class="w-[280px]" v-model="queryParams.orderSn" />
                </el-form-item>
                <el-form-item label="收货人" prop="consignee">
                    <el-input class="w-[280px]" v-model="queryParams.consignee" />
                </el-form-item>
                <el-form-item label="收货人电话" prop="consigneeTel">
                    <el-input class="w-[280px]" v-model="queryParams.consigneeTel" />
                </el-form-item>
                <el-form-item label="订单备注" prop="orderComment">
                    <el-input class="w-[280px]" v-model="queryParams.orderComment" />
                </el-form-item>
                <el-form-item label="付款方式【 1:在线付款 2:货到付款】" prop="paymentWay">
                    <template #default="scope">
                        <el-icon v-if="scope.row.paymentWay == 1"><SuccessFilled /></el-icon>
                        <el-icon v-else><CircleCloseFilled /></el-icon>
                    </template>
                </el-form-item>
                <el-form-item label="任务状态" prop="taskStatus">
                    <el-select
                        v-model="queryParams.taskStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="订单描述" prop="orderBody">
                    <el-input class="w-[280px]" v-model="queryParams.orderBody" />
                </el-form-item>
                <el-form-item label="物流单号" prop="trackingNo">
                    <el-input class="w-[280px]" v-model="queryParams.trackingNo" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['task:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="order_id" prop="orderId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="order_sn" prop="orderSn" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货人" prop="consignee" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货人电话" prop="consigneeTel" min-width="100" header-align="center" align="center"/>
                <el-table-column label="配送地址" prop="deliveryAddress" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订单备注" prop="orderComment" min-width="100" header-align="center" align="center"/>
                <el-table-column label="付款方式【 1:在线付款 2:货到付款】" prop="paymentWay" min-width="100" header-align="center" align="center"/>
                <el-table-column label="任务状态" prop="taskStatus" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订单描述" prop="orderBody" min-width="100" header-align="center" align="center"/>
                <el-table-column label="物流单号" prop="trackingNo" min-width="100" header-align="center" align="center"/>
                <el-table-column label="create_time" prop="createTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="仓库id" prop="wareId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="工作单备注" prop="taskComment" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['task:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['task:del']"
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
<script lang="ts" setup name="task">
import { taskDeleteBatch, taskLists } from '@/api/wave/task'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './task_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    orderId: '',
    orderSn: '',
    consignee: '',
    consigneeTel: '',
    deliveryAddress: '',
    orderComment: '',
    paymentWay: '',
    taskStatus: '',
    orderBody: '',
    trackingNo: '',
    wareId: '',
    taskComment: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: taskLists,
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
    await taskDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
