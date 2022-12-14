<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="订单号" prop="orderSn">
                    <el-input class="w-[280px]" v-model="queryParams.orderSn" />
                </el-form-item>
                <el-form-item label="用户名" prop="memberUsername">
                    <el-input class="w-[280px]" v-model="queryParams.memberUsername" />
                </el-form-item>
                <el-form-item label="支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】" prop="payType">
                    <el-select
                        v-model="queryParams.payType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="订单来源[0->PC订单；1->app订单]" prop="sourceType">
                    <el-select
                        v-model="queryParams.sourceType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】" prop="status">
                    <el-select
                        v-model="queryParams.status"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="物流公司(配送方式)" prop="deliveryCompany">
                    <el-input class="w-[280px]" v-model="queryParams.deliveryCompany" />
                </el-form-item>
                <el-form-item label="物流单号" prop="deliverySn">
                    <el-input class="w-[280px]" v-model="queryParams.deliverySn" />
                </el-form-item>
                <el-form-item label="发票类型[0->不开发票；1->电子发票；2->纸质发票]" prop="billType">
                    <el-select
                        v-model="queryParams.billType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="发票抬头" prop="billHeader">
                    <el-input class="w-[280px]" v-model="queryParams.billHeader" />
                </el-form-item>
                <el-form-item label="收票人电话" prop="billReceiverPhone">
                    <el-input class="w-[280px]" v-model="queryParams.billReceiverPhone" />
                </el-form-item>
                <el-form-item label="收票人邮箱" prop="billReceiverEmail">
                    <el-input class="w-[280px]" v-model="queryParams.billReceiverEmail" />
                </el-form-item>
                <el-form-item label="收货人姓名" prop="receiverName">
                    <el-input class="w-[280px]" v-model="queryParams.receiverName" />
                </el-form-item>
                <el-form-item label="收货人电话" prop="receiverPhone">
                    <el-input class="w-[280px]" v-model="queryParams.receiverPhone" />
                </el-form-item>
                <el-form-item label="收货人邮编" prop="receiverPostCode">
                    <el-input class="w-[280px]" v-model="queryParams.receiverPostCode" />
                </el-form-item>
                <el-form-item label="省份/直辖市" prop="receiverProvince">
                    <el-input class="w-[280px]" v-model="queryParams.receiverProvince" />
                </el-form-item>
                <el-form-item label="城市" prop="receiverCity">
                    <el-input class="w-[280px]" v-model="queryParams.receiverCity" />
                </el-form-item>
                <el-form-item label="区" prop="receiverRegion">
                    <el-input class="w-[280px]" v-model="queryParams.receiverRegion" />
                </el-form-item>
                <el-form-item label="详细地址" prop="receiverDetailAddress">
                    <el-input class="w-[280px]" v-model="queryParams.receiverDetailAddress" />
                </el-form-item>
                <el-form-item label="确认收货状态[0->未确认；1->已确认]" prop="confirmStatus">
                    <el-select
                        v-model="queryParams.confirmStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="删除状态【0->未删除；1->已删除】" prop="deleteStatus">
                    <el-select
                        v-model="queryParams.deleteStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="支付时间" prop="paymentTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="发货时间" prop="deliveryTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="确认收货时间" prop="receiveTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="评价时间" prop="commentTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="修改时间" prop="modifyTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['order:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="订单号" prop="orderSn" min-width="100" header-align="center" align="center"/>
                <el-table-column label="使用的优惠券" prop="couponId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="create_time" prop="createTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="用户名" prop="memberUsername" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订单总额" prop="totalAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="应付总额" prop="payAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="运费金额" prop="freightAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="促销优化金额（促销价、满减、阶梯价）" prop="promotionAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="积分抵扣金额" prop="integrationAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="优惠券抵扣金额" prop="couponAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="后台调整订单使用的折扣金额" prop="discountAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】" prop="payType" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订单来源[0->PC订单；1->app订单]" prop="sourceType" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】" prop="status" min-width="100" header-align="center" align="center"/>
                <el-table-column label="物流公司(配送方式)" prop="deliveryCompany" min-width="100" header-align="center" align="center"/>
                <el-table-column label="物流单号" prop="deliverySn" min-width="100" header-align="center" align="center"/>
                <el-table-column label="自动确认时间（天）" prop="autoConfirmDay" min-width="100" header-align="center" align="center"/>
                <el-table-column label="可以获得的积分" prop="integration" min-width="100" header-align="center" align="center"/>
                <el-table-column label="可以获得的成长值" prop="growth" min-width="100" header-align="center" align="center"/>
                <el-table-column label="发票类型[0->不开发票；1->电子发票；2->纸质发票]" prop="billType" min-width="100" header-align="center" align="center"/>
                <el-table-column label="发票抬头" prop="billHeader" min-width="100" header-align="center" align="center"/>
                <el-table-column label="发票内容" prop="billContent" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收票人电话" prop="billReceiverPhone" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收票人邮箱" prop="billReceiverEmail" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货人姓名" prop="receiverName" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货人电话" prop="receiverPhone" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货人邮编" prop="receiverPostCode" min-width="100" header-align="center" align="center"/>
                <el-table-column label="省份/直辖市" prop="receiverProvince" min-width="100" header-align="center" align="center"/>
                <el-table-column label="城市" prop="receiverCity" min-width="100" header-align="center" align="center"/>
                <el-table-column label="区" prop="receiverRegion" min-width="100" header-align="center" align="center"/>
                <el-table-column label="详细地址" prop="receiverDetailAddress" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订单备注" prop="note" min-width="100" header-align="center" align="center"/>
                <el-table-column label="确认收货状态[0->未确认；1->已确认]" prop="confirmStatus" min-width="100" header-align="center" align="center"/>
                <el-table-column label="删除状态【0->未删除；1->已删除】" prop="deleteStatus" min-width="100" header-align="center" align="center"/>
                <el-table-column label="下单时使用的积分" prop="useIntegration" min-width="100" header-align="center" align="center"/>
                <el-table-column label="支付时间" prop="paymentTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="发货时间" prop="deliveryTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="确认收货时间" prop="receiveTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="评价时间" prop="commentTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="修改时间" prop="modifyTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['order:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['order:del']"
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
<script lang="ts" setup name="order">
import { orderDeleteBatch, orderLists } from '@/api/order/order'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './order_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    memberId: '',
    orderSn: '',
    couponId: '',
    memberUsername: '',
    totalAmount: '',
    payAmount: '',
    freightAmount: '',
    promotionAmount: '',
    integrationAmount: '',
    couponAmount: '',
    discountAmount: '',
    payType: '',
    sourceType: '',
    status: '',
    deliveryCompany: '',
    deliverySn: '',
    autoConfirmDay: '',
    integration: '',
    growth: '',
    billType: '',
    billHeader: '',
    billContent: '',
    billReceiverPhone: '',
    billReceiverEmail: '',
    receiverName: '',
    receiverPhone: '',
    receiverPostCode: '',
    receiverProvince: '',
    receiverCity: '',
    receiverRegion: '',
    receiverDetailAddress: '',
    note: '',
    confirmStatus: '',
    deleteStatus: '',
    useIntegration: '',
    paymentTimeStart: '',
    paymentTimeEnd: '',
    deliveryTimeStart: '',
    deliveryTimeEnd: '',
    receiveTimeStart: '',
    receiveTimeEnd: '',
    commentTimeStart: '',
    commentTimeEnd: '',
    modifyTimeStart: '',
    modifyTimeEnd: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: orderLists,
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
    await orderDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
