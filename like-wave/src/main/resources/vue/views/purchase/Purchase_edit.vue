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
                <el-form-item label="" prop="id">
                    <el-input-number v-model="formData.id" :max="9999" />
                </el-form-item>
                <el-form-item label="" prop="assigneeId">
                    <el-input-number v-model="formData.assigneeId" :max="9999" />
                </el-form-item>
                <el-form-item label="" prop="assigneeName">
                    <el-input v-model="formData.assigneeName" placeholder="请输入" />
                </el-form-item>
                <el-form-item label="" prop="phone">
                    <el-input v-model="formData.phone" placeholder="请输入" />
                </el-form-item>
                <el-form-item label="" prop="priority">
                    <el-input-number v-model="formData.priority" :max="9999" />
                </el-form-item>
                <el-form-item label="" prop="status">
                    <el-radio-group v-model="formData.status" placeholder="请选择">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="" prop="wareId">
                    <el-input-number v-model="formData.wareId" :max="9999" />
                </el-form-item>
                <el-form-item label="" prop="amount">
                    <el-input-number v-model="formData.amount" :max="9999" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  purchaseEdit, purchaseAdd, purchaseDetail } from '@/api/wave/purchase'
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
    return mode.value == 'edit' ? '编辑采购信息' : '新增采购信息'
})

const formData = reactive({
    id: '',
    assigneeId: 0,
    assigneeName: '',
    phone: '',
    priority: 0,
    status: '',
    wareId: 0,
    amount: 0,
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    assigneeId: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    assigneeName: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    phone: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    priority: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    status: [
        {
            required: true,
            message: '请选择',
            trigger: ['blur']
        }
    ],
    wareId: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    amount: [
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
    mode.value == 'edit' ? await purchaseEdit(data) : await purchaseAdd(data)
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
    const data = await purchaseDetail({
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
