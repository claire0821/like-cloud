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
                <el-form-item label="order_id" prop="orderId">
                    <el-input-number v-model="formData.orderId" :max="9999" />
                </el-form-item>
                <el-form-item label="order_sn" prop="orderSn">
                    <el-input v-model="formData.orderSn" placeholder="请输入order_sn" />
                </el-form-item>
                <el-form-item label="收货人" prop="consignee">
                    <el-input v-model="formData.consignee" placeholder="请输入收货人" />
                </el-form-item>
                <el-form-item label="收货人电话" prop="consigneeTel">
                    <el-input v-model="formData.consigneeTel" placeholder="请输入收货人电话" />
                </el-form-item>
                <el-form-item label="配送地址" prop="deliveryAddress">
                    <el-input
                        v-model="formData.deliveryAddress"
                        placeholder="请输入配送地址"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="订单备注" prop="orderComment">
                    <el-input v-model="formData.orderComment" placeholder="请输入订单备注" />
                </el-form-item>
                <el-form-item label="付款方式【 1:在线付款 2:货到付款】" prop="paymentWay">
                   <el-checkbox v-model="formData.paymentWay" :true-label="1" :false-label="0" placeholder="请选择付款方式【 1:在线付款 2:货到付款】"></el-checkbox>
                </el-form-item>
                <el-form-item label="任务状态" prop="taskStatus">
                    <el-radio-group v-model="formData.taskStatus" placeholder="请选择任务状态">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="订单描述" prop="orderBody">
                    <el-input v-model="formData.orderBody" placeholder="请输入订单描述" />
                </el-form-item>
                <el-form-item label="物流单号" prop="trackingNo">
                    <el-input v-model="formData.trackingNo" placeholder="请输入物流单号" />
                </el-form-item>
                <el-form-item label="仓库id" prop="wareId">
                    <el-input-number v-model="formData.wareId" :max="9999" />
                </el-form-item>
                <el-form-item label="工作单备注" prop="taskComment">
                    <el-input
                        v-model="formData.taskComment"
                        placeholder="请输入工作单备注"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  taskEdit, taskAdd, taskDetail } from '@/api/wave/task'
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
    return mode.value == 'edit' ? '编辑库存工作单' : '新增库存工作单'
})

const formData = reactive({
    id: '',
    orderId: 0,
    orderSn: '',
    consignee: '',
    consigneeTel: '',
    deliveryAddress: '',
    orderComment: '',
    paymentWay: [],
    taskStatus: '',
    orderBody: '',
    trackingNo: '',
    wareId: 0,
    taskComment: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    orderId: [
        {
            required: true,
            message: '请输入order_id',
            trigger: ['blur']
        }
    ],
    orderSn: [
        {
            required: true,
            message: '请输入order_sn',
            trigger: ['blur']
        }
    ],
    consignee: [
        {
            required: true,
            message: '请输入收货人',
            trigger: ['blur']
        }
    ],
    consigneeTel: [
        {
            required: true,
            message: '请输入收货人电话',
            trigger: ['blur']
        }
    ],
    deliveryAddress: [
        {
            required: true,
            message: '请输入配送地址',
            trigger: ['blur']
        }
    ],
    orderComment: [
        {
            required: true,
            message: '请输入订单备注',
            trigger: ['blur']
        }
    ],
    paymentWay: [
        {
            required: true,
            message: '请选择付款方式【 1:在线付款 2:货到付款】',
            trigger: ['blur']
        }
    ],
    taskStatus: [
        {
            required: true,
            message: '请选择任务状态',
            trigger: ['blur']
        }
    ],
    orderBody: [
        {
            required: true,
            message: '请输入订单描述',
            trigger: ['blur']
        }
    ],
    trackingNo: [
        {
            required: true,
            message: '请输入物流单号',
            trigger: ['blur']
        }
    ],
    wareId: [
        {
            required: true,
            message: '请输入仓库id',
            trigger: ['blur']
        }
    ],
    taskComment: [
        {
            required: true,
            message: '请输入工作单备注',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.paymentWay = data.paymentWay.join(',')
    mode.value == 'edit' ? await taskEdit(data) : await taskAdd(data)
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
            formData.paymentWay = String(data.paymentWay).split(',')
        }
    }
}

const getDetail = async (row: Record<string, any>) => {
    const data = await taskDetail({
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
