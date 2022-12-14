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
                <el-form-item label="秒杀订单超时关闭时间(分)" prop="flashOrderOvertime">
                    <el-input-number v-model="formData.flashOrderOvertime" :max="9999" />
                </el-form-item>
                <el-form-item label="正常订单超时时间(分)" prop="normalOrderOvertime">
                    <el-input-number v-model="formData.normalOrderOvertime" :max="9999" />
                </el-form-item>
                <el-form-item label="发货后自动确认收货时间（天）" prop="confirmOvertime">
                    <el-input-number v-model="formData.confirmOvertime" :max="9999" />
                </el-form-item>
                <el-form-item label="自动完成交易时间，不能申请退货（天）" prop="finishOvertime">
                    <el-input-number v-model="formData.finishOvertime" :max="9999" />
                </el-form-item>
                <el-form-item label="订单完成后自动好评时间（天）" prop="commentOvertime">
                    <el-input-number v-model="formData.commentOvertime" :max="9999" />
                </el-form-item>
                <el-form-item label="会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】" prop="memberLevel">
                   <el-checkbox v-model="formData.memberLevel" :true-label="1" :false-label="0" placeholder="请选择会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】"></el-checkbox>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  settingEdit, settingAdd, settingDetail } from '@/api/order/setting'
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
    return mode.value == 'edit' ? '编辑订单配置信息' : '新增订单配置信息'
})

const formData = reactive({
    id: '',
    flashOrderOvertime: 0,
    normalOrderOvertime: 0,
    confirmOvertime: 0,
    finishOvertime: 0,
    commentOvertime: 0,
    memberLevel: [],
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    flashOrderOvertime: [
        {
            required: true,
            message: '请输入秒杀订单超时关闭时间(分)',
            trigger: ['blur']
        }
    ],
    normalOrderOvertime: [
        {
            required: true,
            message: '请输入正常订单超时时间(分)',
            trigger: ['blur']
        }
    ],
    confirmOvertime: [
        {
            required: true,
            message: '请输入发货后自动确认收货时间（天）',
            trigger: ['blur']
        }
    ],
    finishOvertime: [
        {
            required: true,
            message: '请输入自动完成交易时间，不能申请退货（天）',
            trigger: ['blur']
        }
    ],
    commentOvertime: [
        {
            required: true,
            message: '请输入订单完成后自动好评时间（天）',
            trigger: ['blur']
        }
    ],
    memberLevel: [
        {
            required: true,
            message: '请选择会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.memberLevel = data.memberLevel.join(',')
    mode.value == 'edit' ? await settingEdit(data) : await settingAdd(data)
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
            formData.memberLevel = String(data.memberLevel).split(',')
        }
    }
}

const getDetail = async (row: Record<string, any>) => {
    const data = await settingDetail({
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
