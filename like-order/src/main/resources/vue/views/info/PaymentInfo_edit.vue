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
                <el-form-item label="订单号（对外业务号）" prop="orderSn">
                    <el-input v-model="formData.orderSn" placeholder="请输入订单号（对外业务号）" />
                </el-form-item>
                <el-form-item label="订单id" prop="orderId">
                    <el-input-number v-model="formData.orderId" :max="9999" />
                </el-form-item>
                <el-form-item label="支付宝交易流水号" prop="alipayTradeNo">
                    <el-input v-model="formData.alipayTradeNo" placeholder="请输入支付宝交易流水号" />
                </el-form-item>
                <el-form-item label="支付总金额" prop="totalAmount">
                    <el-input-number v-model="formData.totalAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="交易内容" prop="subject">
                    <el-input v-model="formData.subject" placeholder="请输入交易内容" />
                </el-form-item>
                <el-form-item label="支付状态" prop="paymentStatus">
                    <el-radio-group v-model="formData.paymentStatus" placeholder="请选择支付状态">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="确认时间" prop="confirmTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.confirmTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择确认时间"
                    />
                </el-form-item>
                <el-form-item label="回调内容" prop="callbackContent">
                    <editor v-model="formData.callbackContent" :height="500" />
                </el-form-item>
                <el-form-item label="回调时间" prop="callbackTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.callbackTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择回调时间"
                    />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  infoEdit, infoAdd, infoDetail } from '@/api/order/info'
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
    return mode.value == 'edit' ? '编辑支付信息' : '新增支付信息'
})

const formData = reactive({
    id: '',
    orderSn: '',
    orderId: 0,
    alipayTradeNo: '',
    totalAmount: 0,
    subject: '',
    paymentStatus: '',
    confirmTime: '',
    callbackContent: '',
    callbackTime: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    orderSn: [
        {
            required: true,
            message: '请输入订单号（对外业务号）',
            trigger: ['blur']
        }
    ],
    orderId: [
        {
            required: true,
            message: '请输入订单id',
            trigger: ['blur']
        }
    ],
    alipayTradeNo: [
        {
            required: true,
            message: '请输入支付宝交易流水号',
            trigger: ['blur']
        }
    ],
    totalAmount: [
        {
            required: true,
            message: '请输入支付总金额',
            trigger: ['blur']
        }
    ],
    subject: [
        {
            required: true,
            message: '请输入交易内容',
            trigger: ['blur']
        }
    ],
    paymentStatus: [
        {
            required: true,
            message: '请选择支付状态',
            trigger: ['blur']
        }
    ],
    confirmTime: [
        {
            required: true,
            message: '请选择确认时间',
            trigger: ['blur']
        }
    ],
    callbackContent: [
        {
            required: true,
            message: '请输入回调内容',
            trigger: ['blur']
        }
    ],
    callbackTime: [
        {
            required: true,
            message: '请选择回调时间',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await infoEdit(data) : await infoAdd(data)
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
    const data = await infoDetail({
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
