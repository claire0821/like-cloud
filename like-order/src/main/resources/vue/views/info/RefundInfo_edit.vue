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
                <el-form-item label="退款的订单" prop="orderReturnId">
                    <el-input-number v-model="formData.orderReturnId" :max="9999" />
                </el-form-item>
                <el-form-item label="退款金额" prop="refund">
                    <el-input-number v-model="formData.refund" :max="9999" />
                </el-form-item>
                <el-form-item label="退款交易流水号" prop="refundSn">
                    <el-input v-model="formData.refundSn" placeholder="请输入退款交易流水号" />
                </el-form-item>
                <el-form-item label="退款状态" prop="refundStatus">
                    <el-radio-group v-model="formData.refundStatus" placeholder="请选择退款状态">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="退款渠道[1-支付宝，2-微信，3-银联，4-汇款]" prop="refundChannel">
                   <el-checkbox v-model="formData.refundChannel" :true-label="1" :false-label="0" placeholder="请选择退款渠道[1-支付宝，2-微信，3-银联，4-汇款]"></el-checkbox>
                </el-form-item>
                <el-form-item label="" prop="refundContent">
                    <editor v-model="formData.refundContent" :height="500" />
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
    return mode.value == 'edit' ? '编辑退款信息' : '新增退款信息'
})

const formData = reactive({
    id: '',
    orderReturnId: 0,
    refund: 0,
    refundSn: '',
    refundStatus: '',
    refundChannel: [],
    refundContent: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    orderReturnId: [
        {
            required: true,
            message: '请输入退款的订单',
            trigger: ['blur']
        }
    ],
    refund: [
        {
            required: true,
            message: '请输入退款金额',
            trigger: ['blur']
        }
    ],
    refundSn: [
        {
            required: true,
            message: '请输入退款交易流水号',
            trigger: ['blur']
        }
    ],
    refundStatus: [
        {
            required: true,
            message: '请选择退款状态',
            trigger: ['blur']
        }
    ],
    refundChannel: [
        {
            required: true,
            message: '请选择退款渠道[1-支付宝，2-微信，3-银联，4-汇款]',
            trigger: ['blur']
        }
    ],
    refundContent: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.refundChannel = data.refundChannel.join(',')
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
            //@ts-ignore
            formData.refundChannel = String(data.refundChannel).split(',')
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
