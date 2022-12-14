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
                <el-form-item label="订单id" prop="orderId">
                    <el-input-number v-model="formData.orderId" :max="9999" />
                </el-form-item>
                <el-form-item label="操作人[用户；系统；后台管理员]" prop="operateMan">
                    <el-input v-model="formData.operateMan" placeholder="请输入操作人[用户；系统；后台管理员]" />
                </el-form-item>
                <el-form-item label="订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】" prop="orderStatus">
                    <el-radio-group v-model="formData.orderStatus" placeholder="请选择订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-input
                        v-model="formData.note"
                        placeholder="请输入备注"
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
import {  historyEdit, historyAdd, historyDetail } from '@/api/order/history'
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
    return mode.value == 'edit' ? '编辑订单操作历史记录' : '新增订单操作历史记录'
})

const formData = reactive({
    id: '',
    orderId: 0,
    operateMan: '',
    orderStatus: '',
    note: '',
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
            message: '请输入订单id',
            trigger: ['blur']
        }
    ],
    operateMan: [
        {
            required: true,
            message: '请输入操作人[用户；系统；后台管理员]',
            trigger: ['blur']
        }
    ],
    orderStatus: [
        {
            required: true,
            message: '请选择订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】',
            trigger: ['blur']
        }
    ],
    note: [
        {
            required: true,
            message: '请输入备注',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await historyEdit(data) : await historyAdd(data)
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
    const data = await historyDetail({
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
