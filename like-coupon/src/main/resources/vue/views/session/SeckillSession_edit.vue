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
                <el-form-item label="场次名称" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入场次名称" />
                </el-form-item>
                <el-form-item label="每日开始时间" prop="startTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.startTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择每日开始时间"
                    />
                </el-form-item>
                <el-form-item label="每日结束时间" prop="endTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.endTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择每日结束时间"
                    />
                </el-form-item>
                <el-form-item label="启用状态" prop="status">
                    <el-radio-group v-model="formData.status" placeholder="请选择启用状态">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  sessionEdit, sessionAdd, sessionDetail } from '@/api/coupon/session'
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
    return mode.value == 'edit' ? '编辑秒杀活动场次' : '新增秒杀活动场次'
})

const formData = reactive({
    id: '',
    name: '',
    startTime: '',
    endTime: '',
    status: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    name: [
        {
            required: true,
            message: '请输入场次名称',
            trigger: ['blur']
        }
    ],
    startTime: [
        {
            required: true,
            message: '请选择每日开始时间',
            trigger: ['blur']
        }
    ],
    endTime: [
        {
            required: true,
            message: '请选择每日结束时间',
            trigger: ['blur']
        }
    ],
    status: [
        {
            required: true,
            message: '请选择启用状态',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await sessionEdit(data) : await sessionAdd(data)
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
    const data = await sessionDetail({
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
