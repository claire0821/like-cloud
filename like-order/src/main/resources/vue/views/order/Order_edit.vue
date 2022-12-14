<template>
    <div class="edit-popup">
        <popup
            ref="popupRef"
            :title="popupTitle"
            :async="true"
            width="550px"
            :clickModalClose="true"
            @confirm="handleSubmit"
            @close="handleClose"
        >
            <el-form ref="formRef" :model="formData" label-width="84px" :rules="formRules">
                <el-form-item label="id" prop="id">
                    <el-input-number v-model="formData.id" :max="9999" />
                </el-form-item>
                <el-form-item label="member_id" prop="memberId">
                    <el-input-number v-model="formData.memberId" :max="9999" />
                </el-form-item>
                <el-form-item label="订单号" prop="orderSn">
                    <el-input v-model="formData.orderSn" placeholder="请输入订单号" />
                </el-form-item>
                <el-form-item label="使用的优惠券" prop="couponId">
                    <el-input-number v-model="formData.couponId" :max="9999" />
                </el-form-item>
                <el-form-item label="用户名" prop="memberUsername">
                    <el-input v-model="formData.memberUsername" placeholder="请输入用户名" />
                </el-form-item>
                <el-form-item label="订单总额" prop="totalAmount">
                    <el-input-number v-model="formData.totalAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="应付总额" prop="payAmount">
                    <el-input-number v-model="formData.payAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="运费金额" prop="freightAmount">
                    <el-input-number v-model="formData.freightAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="促销优化金额（促销价、满减、阶梯价）" prop="promotionAmount">
                    <el-input-number v-model="formData.promotionAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="积分抵扣金额" prop="integrationAmount">
                    <el-input-number v-model="formData.integrationAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="优惠券抵扣金额" prop="couponAmount">
                    <el-input-number v-model="formData.couponAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="后台调整订单使用的折扣金额" prop="discountAmount">
                    <el-input-number v-model="formData.discountAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】" prop="payType">
                    <el-select class="flex-1" v-model="formData.payType" placeholder="请选择支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="订单来源[0->PC订单；1->app订单]" prop="sourceType">
                    <el-select class="flex-1" v-model="formData.sourceType" placeholder="请选择订单来源[0->PC订单；1->app订单]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】" prop="status">
                    <el-radio-group v-model="formData.status" placeholder="请选择订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="物流公司(配送方式)" prop="deliveryCompany">
                    <el-input v-model="formData.deliveryCompany" placeholder="请输入物流公司(配送方式)" />
                </el-form-item>
                <el-form-item label="物流单号" prop="deliverySn">
                    <el-input v-model="formData.deliverySn" placeholder="请输入物流单号" />
                </el-form-item>
                <el-form-item label="自动确认时间（天）" prop="autoConfirmDay">
                    <el-input-number v-model="formData.autoConfirmDay" :max="9999" />
                </el-form-item>
                <el-form-item label="可以获得的积分" prop="integration">
                    <el-input-number v-model="formData.integration" :max="9999" />
                </el-form-item>
                <el-form-item label="可以获得的成长值" prop="growth">
                    <el-input-number v-model="formData.growth" :max="9999" />
                </el-form-item>
                <el-form-item label="发票类型[0->不开发票；1->电子发票；2->纸质发票]" prop="billType">
                    <el-select class="flex-1" v-model="formData.billType" placeholder="请选择发票类型[0->不开发票；1->电子发票；2->纸质发票]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="发票抬头" prop="billHeader">
                    <el-input v-model="formData.billHeader" placeholder="请输入发票抬头" />
                </el-form-item>
                <el-form-item label="发票内容" prop="billContent">
                    <editor v-model="formData.billContent" :height="500" />
                </el-form-item>
                <el-form-item label="收票人电话" prop="billReceiverPhone">
                    <el-input v-model="formData.billReceiverPhone" placeholder="请输入收票人电话" />
                </el-form-item>
                <el-form-item label="收票人邮箱" prop="billReceiverEmail">
                    <el-input v-model="formData.billReceiverEmail" placeholder="请输入收票人邮箱" />
                </el-form-item>
                <el-form-item label="收货人姓名" prop="receiverName">
                    <el-input v-model="formData.receiverName" placeholder="请输入收货人姓名" />
                </el-form-item>
                <el-form-item label="收货人电话" prop="receiverPhone">
                    <el-input v-model="formData.receiverPhone" placeholder="请输入收货人电话" />
                </el-form-item>
                <el-form-item label="收货人邮编" prop="receiverPostCode">
                    <el-input v-model="formData.receiverPostCode" placeholder="请输入收货人邮编" />
                </el-form-item>
                <el-form-item label="省份/直辖市" prop="receiverProvince">
                    <el-input v-model="formData.receiverProvince" placeholder="请输入省份/直辖市" />
                </el-form-item>
                <el-form-item label="城市" prop="receiverCity">
                    <el-input v-model="formData.receiverCity" placeholder="请输入城市" />
                </el-form-item>
                <el-form-item label="区" prop="receiverRegion">
                    <el-input v-model="formData.receiverRegion" placeholder="请输入区" />
                </el-form-item>
                <el-form-item label="详细地址" prop="receiverDetailAddress">
                    <el-input v-model="formData.receiverDetailAddress" placeholder="请输入详细地址" />
                </el-form-item>
                <el-form-item label="订单备注" prop="note">
                    <el-input
                        v-model="formData.note"
                        placeholder="请输入订单备注"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="确认收货状态[0->未确认；1->已确认]" prop="confirmStatus">
                    <el-radio-group v-model="formData.confirmStatus" placeholder="请选择确认收货状态[0->未确认；1->已确认]">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="删除状态【0->未删除；1->已删除】" prop="deleteStatus">
                    <el-radio-group v-model="formData.deleteStatus" placeholder="请选择删除状态【0->未删除；1->已删除】">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="下单时使用的积分" prop="useIntegration">
                    <el-input-number v-model="formData.useIntegration" :max="9999" />
                </el-form-item>
                <el-form-item label="支付时间" prop="paymentTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.paymentTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择支付时间"
                    />
                </el-form-item>
                <el-form-item label="发货时间" prop="deliveryTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.deliveryTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择发货时间"
                    />
                </el-form-item>
                <el-form-item label="确认收货时间" prop="receiveTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.receiveTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择确认收货时间"
                    />
                </el-form-item>
                <el-form-item label="评价时间" prop="commentTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.commentTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择评价时间"
                    />
                </el-form-item>
                <el-form-item label="修改时间" prop="modifyTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.modifyTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择修改时间"
                    />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  orderEdit, orderAdd, orderDetail } from '@/api/order/order'
import Popup from '@/components/popup/index.vue'
import feedback from '@/utils/feedback'
import type { PropType } from 'vue'
defineProps({
    dictData: {
        type: Object as PropType<Record<string, any[]>>,
        default: () => ({})
    }
})
const emit = defineEmits(['success', 'close'])
const formRef = shallowRef<FormInstance>()
const popupRef = shallowRef<InstanceType<typeof Popup>>()
const mode = ref('add')
const popupTitle = computed(() => {
    return mode.value == 'edit' ? '编辑订单' : '新增订单'
})

const formData = reactive({
    id: '',
    memberId: 0,
    orderSn: '',
    couponId: 0,
    memberUsername: '',
    totalAmount: 0,
    payAmount: 0,
    freightAmount: 0,
    promotionAmount: 0,
    integrationAmount: 0,
    couponAmount: 0,
    discountAmount: 0,
    payType: '',
    sourceType: '',
    status: '',
    deliveryCompany: '',
    deliverySn: '',
    autoConfirmDay: 0,
    integration: 0,
    growth: 0,
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
    useIntegration: 0,
    paymentTime: '',
    deliveryTime: '',
    receiveTime: '',
    commentTime: '',
    modifyTime: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    memberId: [
        {
            required: true,
            message: '请输入member_id',
            trigger: ['blur']
        }
    ],
    orderSn: [
        {
            required: true,
            message: '请输入订单号',
            trigger: ['blur']
        }
    ],
    couponId: [
        {
            required: true,
            message: '请输入使用的优惠券',
            trigger: ['blur']
        }
    ],
    memberUsername: [
        {
            required: true,
            message: '请输入用户名',
            trigger: ['blur']
        }
    ],
    totalAmount: [
        {
            required: true,
            message: '请输入订单总额',
            trigger: ['blur']
        }
    ],
    payAmount: [
        {
            required: true,
            message: '请输入应付总额',
            trigger: ['blur']
        }
    ],
    freightAmount: [
        {
            required: true,
            message: '请输入运费金额',
            trigger: ['blur']
        }
    ],
    promotionAmount: [
        {
            required: true,
            message: '请输入促销优化金额（促销价、满减、阶梯价）',
            trigger: ['blur']
        }
    ],
    integrationAmount: [
        {
            required: true,
            message: '请输入积分抵扣金额',
            trigger: ['blur']
        }
    ],
    couponAmount: [
        {
            required: true,
            message: '请输入优惠券抵扣金额',
            trigger: ['blur']
        }
    ],
    discountAmount: [
        {
            required: true,
            message: '请输入后台调整订单使用的折扣金额',
            trigger: ['blur']
        }
    ],
    payType: [
        {
            required: true,
            message: '请选择支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】',
            trigger: ['blur']
        }
    ],
    sourceType: [
        {
            required: true,
            message: '请选择订单来源[0->PC订单；1->app订单]',
            trigger: ['blur']
        }
    ],
    status: [
        {
            required: true,
            message: '请选择订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】',
            trigger: ['blur']
        }
    ],
    deliveryCompany: [
        {
            required: true,
            message: '请输入物流公司(配送方式)',
            trigger: ['blur']
        }
    ],
    deliverySn: [
        {
            required: true,
            message: '请输入物流单号',
            trigger: ['blur']
        }
    ],
    autoConfirmDay: [
        {
            required: true,
            message: '请输入自动确认时间（天）',
            trigger: ['blur']
        }
    ],
    integration: [
        {
            required: true,
            message: '请输入可以获得的积分',
            trigger: ['blur']
        }
    ],
    growth: [
        {
            required: true,
            message: '请输入可以获得的成长值',
            trigger: ['blur']
        }
    ],
    billType: [
        {
            required: true,
            message: '请选择发票类型[0->不开发票；1->电子发票；2->纸质发票]',
            trigger: ['blur']
        }
    ],
    billHeader: [
        {
            required: true,
            message: '请输入发票抬头',
            trigger: ['blur']
        }
    ],
    billContent: [
        {
            required: true,
            message: '请输入发票内容',
            trigger: ['blur']
        }
    ],
    billReceiverPhone: [
        {
            required: true,
            message: '请输入收票人电话',
            trigger: ['blur']
        }
    ],
    billReceiverEmail: [
        {
            required: true,
            message: '请输入收票人邮箱',
            trigger: ['blur']
        }
    ],
    receiverName: [
        {
            required: true,
            message: '请输入收货人姓名',
            trigger: ['blur']
        }
    ],
    receiverPhone: [
        {
            required: true,
            message: '请输入收货人电话',
            trigger: ['blur']
        }
    ],
    receiverPostCode: [
        {
            required: true,
            message: '请输入收货人邮编',
            trigger: ['blur']
        }
    ],
    receiverProvince: [
        {
            required: true,
            message: '请输入省份/直辖市',
            trigger: ['blur']
        }
    ],
    receiverCity: [
        {
            required: true,
            message: '请输入城市',
            trigger: ['blur']
        }
    ],
    receiverRegion: [
        {
            required: true,
            message: '请输入区',
            trigger: ['blur']
        }
    ],
    receiverDetailAddress: [
        {
            required: true,
            message: '请输入详细地址',
            trigger: ['blur']
        }
    ],
    note: [
        {
            required: true,
            message: '请输入订单备注',
            trigger: ['blur']
        }
    ],
    confirmStatus: [
        {
            required: true,
            message: '请选择确认收货状态[0->未确认；1->已确认]',
            trigger: ['blur']
        }
    ],
    deleteStatus: [
        {
            required: true,
            message: '请选择删除状态【0->未删除；1->已删除】',
            trigger: ['blur']
        }
    ],
    useIntegration: [
        {
            required: true,
            message: '请输入下单时使用的积分',
            trigger: ['blur']
        }
    ],
    paymentTime: [
        {
            required: true,
            message: '请选择支付时间',
            trigger: ['blur']
        }
    ],
    deliveryTime: [
        {
            required: true,
            message: '请选择发货时间',
            trigger: ['blur']
        }
    ],
    receiveTime: [
        {
            required: true,
            message: '请选择确认收货时间',
            trigger: ['blur']
        }
    ],
    commentTime: [
        {
            required: true,
            message: '请选择评价时间',
            trigger: ['blur']
        }
    ],
    modifyTime: [
        {
            required: true,
            message: '请选择修改时间',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await orderEdit(data) : await orderAdd(data)
    popupRef.value?.close()
    feedback.msgSuccess('操作成功')
    emit('success')
}

const open = (type = 'add') => {
    mode.value = type
    popupRef.value?.open()
}

const setFormData = async (data: Record<string, any>) => {
    for (const key in formData) {
        if (data[key] != null && data[key] != undefined) {
            //@ts-ignore
            formData[key] = data[key]
        }
    }
}

const getDetail = async (row: Record<string, any>) => {
    const data = await orderDetail({
        id: row.id
    })
    setFormData(data)
}

const handleClose = () => {
    emit('close')
}

defineExpose({
    open,
    setFormData,
    getDetail
})
</script>
