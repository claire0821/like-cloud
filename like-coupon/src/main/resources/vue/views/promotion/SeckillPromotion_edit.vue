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
                <el-form-item label="活动标题" prop="title">
                    <el-input v-model="formData.title" placeholder="请输入活动标题" />
                </el-form-item>
                <el-form-item label="开始日期" prop="startTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.startTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择开始日期"
                    />
                </el-form-item>
                <el-form-item label="结束日期" prop="endTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.endTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择结束日期"
                    />
                </el-form-item>
                <el-form-item label="上下线状态" prop="status">
                    <el-radio-group v-model="formData.status" placeholder="请选择上下线状态">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="创建人" prop="userId">
                    <el-input-number v-model="formData.userId" :max="9999" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  promotionEdit, promotionAdd, promotionDetail } from '@/api/coupon/promotion'
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
    return mode.value == 'edit' ? '编辑秒杀活动' : '新增秒杀活动'
})

const formData = reactive({
    id: '',
    title: '',
    startTime: '',
    endTime: '',
    status: '',
    userId: 0,
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    title: [
        {
            required: true,
            message: '请输入活动标题',
            trigger: ['blur']
        }
    ],
    startTime: [
        {
            required: true,
            message: '请选择开始日期',
            trigger: ['blur']
        }
    ],
    endTime: [
        {
            required: true,
            message: '请选择结束日期',
            trigger: ['blur']
        }
    ],
    status: [
        {
            required: true,
            message: '请选择上下线状态',
            trigger: ['blur']
        }
    ],
    userId: [
        {
            required: true,
            message: '请输入创建人',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await promotionEdit(data) : await promotionAdd(data)
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
    const data = await promotionDetail({
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
