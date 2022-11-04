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
                    <el-input v-model="formData.id" placeholder="请输入id" />
                </el-form-item>
                <el-form-item label="subject_id" prop="subjectId">
                    <el-input v-model="formData.subjectId" placeholder="请输入subject_id" />
                </el-form-item>
                <el-form-item label="subject_name" prop="subjectName">
                    <el-input v-model="formData.subjectName" placeholder="请输入subject_name" />
                </el-form-item>
                <el-form-item label="subject_img" prop="subjectImg">
                    <el-input
                        v-model="formData.subjectImg"
                        placeholder="请输入subject_img"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="活动url" prop="subjectUrll">
                    <el-input
                        v-model="formData.subjectUrll"
                        placeholder="请输入活动url"
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
import {  subjectEdit, subjectAdd, subjectDetail } from '@/api/subject'
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
    return mode.value == 'edit' ? '编辑会员收藏的专题活动' : '新增会员收藏的专题活动'
})

const formData = reactive({
    id: '',
    subjectId: '',
    subjectName: '',
    subjectImg: '',
    subjectUrll: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    subjectId: [
        {
            required: true,
            message: '请输入subject_id',
            trigger: ['blur']
        }
    ],
    subjectName: [
        {
            required: true,
            message: '请输入subject_name',
            trigger: ['blur']
        }
    ],
    subjectImg: [
        {
            required: true,
            message: '请输入subject_img',
            trigger: ['blur']
        }
    ],
    subjectUrll: [
        {
            required: true,
            message: '请输入活动url',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await subjectEdit(data) : await subjectAdd(data)
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
    const data = await subjectDetail({
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
