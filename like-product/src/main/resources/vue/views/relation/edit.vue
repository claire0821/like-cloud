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
                    <el-input v-model="formData.id" placeholder="请输入" />
                </el-form-item>
                <el-form-item label="品牌id" prop="brandId">
                    <el-input v-model="formData.brandId" placeholder="请输入品牌id" />
                </el-form-item>
                <el-form-item label="分类id" prop="catelogId">
                    <el-input v-model="formData.catelogId" placeholder="请输入分类id" />
                </el-form-item>
                <el-form-item label="" prop="brandName">
                    <el-input v-model="formData.brandName" placeholder="请输入" />
                </el-form-item>
                <el-form-item label="" prop="catelogName">
                    <el-input v-model="formData.catelogName" placeholder="请输入" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  relationEdit, relationAdd, relationDetail } from '@/api/relation'
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
    return mode.value == 'edit' ? '编辑品牌分类关联' : '新增品牌分类关联'
})

const formData = reactive({
    id: '',
    brandId: '',
    catelogId: '',
    brandName: '',
    catelogName: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    brandId: [
        {
            required: true,
            message: '请输入品牌id',
            trigger: ['blur']
        }
    ],
    catelogId: [
        {
            required: true,
            message: '请输入分类id',
            trigger: ['blur']
        }
    ],
    brandName: [
        {
            required: true,
            message: '请输入',
            trigger: ['blur']
        }
    ],
    catelogName: [
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
    mode.value == 'edit' ? await relationEdit(data) : await relationAdd(data)
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
    const data = await relationDetail({
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
