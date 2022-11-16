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
                <el-form-item label="sku_id" prop="skuId">
                    <el-input-number v-model="formData.skuId" :max="9999" />
                </el-form-item>
                <el-form-item label="活动场次id" prop="sessionId">
                    <el-input-number v-model="formData.sessionId" :max="9999" />
                </el-form-item>
                <el-form-item label="订阅时间" prop="subcribeTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.subcribeTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择订阅时间"
                    />
                </el-form-item>
                <el-form-item label="发送时间" prop="sendTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.sendTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择发送时间"
                    />
                </el-form-item>
                <el-form-item label="通知方式[0-短信，1-邮件]" prop="noticeType">
                    <el-select class="flex-1" v-model="formData.noticeType" placeholder="请选择通知方式[0-短信，1-邮件]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  noticeEdit, noticeAdd, noticeDetail } from '@/api/coupon/notice'
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
    return mode.value == 'edit' ? '编辑秒杀商品通知订阅' : '新增秒杀商品通知订阅'
})

const formData = reactive({
    id: '',
    memberId: 0,
    skuId: 0,
    sessionId: 0,
    subcribeTime: '',
    sendTime: '',
    noticeType: '',
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
    skuId: [
        {
            required: true,
            message: '请输入sku_id',
            trigger: ['blur']
        }
    ],
    sessionId: [
        {
            required: true,
            message: '请输入活动场次id',
            trigger: ['blur']
        }
    ],
    subcribeTime: [
        {
            required: true,
            message: '请选择订阅时间',
            trigger: ['blur']
        }
    ],
    sendTime: [
        {
            required: true,
            message: '请选择发送时间',
            trigger: ['blur']
        }
    ],
    noticeType: [
        {
            required: true,
            message: '请选择通知方式[0-短信，1-邮件]',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await noticeEdit(data) : await noticeAdd(data)
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
    const data = await noticeDetail({
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
