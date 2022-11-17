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
                <el-form-item label="" prop="branchId">
                    <el-input-number v-model="formData.branchId" :max="9999" />
                </el-form-item>
                <el-form-item label="" prop="xid">
                    <el-input v-model="formData.xid" placeholder="请输入" />
                </el-form-item>
                <el-form-item label="" prop="context">
                    <el-input v-model="formData.context" placeholder="请输入" />
                </el-form-item>
                <el-form-item label="" prop="logStatus">
                    <el-radio-group v-model="formData.logStatus" placeholder="请选择">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="" prop="logCreated">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.logCreated"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择"
                    />
                </el-form-item>
                <el-form-item label="" prop="logModified">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.logModified"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择"
                    />
                </el-form-item>
                <el-form-item label="" prop="ext">
                    <el-input v-model="formData.ext" placeholder="请输入" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  logEdit, logAdd, logDetail } from '@/api/wave/log'
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
    return mode.value == 'edit' ? '编辑【请填写功能名称】' : '新增【请填写功能名称】'
})

const formData = reactive({
    id: '',
    branchId: 0,
    xid: '',
    context: '',
    rollbackInfo: '',
    logStatus: '',
    logCreated: '',
    logModified: '',
    ext: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    branchId: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    xid: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    context: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    rollbackInfo: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    logStatus: [
        {
            required: true,
            message: '请选择',
            trigger: ['blur']
        }
    ],
    logCreated: [
        {
            required: true,
            message: '请选择',
            trigger: ['blur']
        }
    ],
    logModified: [
        {
            required: true,
            message: '请选择',
            trigger: ['blur']
        }
    ],
    ext: [
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
    mode.value == 'edit' ? await logEdit(data) : await logAdd(data)
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
    const data = await logDetail({
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
