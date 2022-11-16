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
                <el-form-item label="优惠券id" prop="couponId">
                    <el-input-number v-model="formData.couponId" :max="9999" />
                </el-form-item>
                <el-form-item label="产品分类id" prop="categoryId">
                    <el-input-number v-model="formData.categoryId" :max="9999" />
                </el-form-item>
                <el-form-item label="产品分类名称" prop="categoryName">
                    <el-input v-model="formData.categoryName" placeholder="请输入产品分类名称" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  relationEdit, relationAdd, relationDetail } from '@/api/coupon/relation'
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
    return mode.value == 'edit' ? '编辑优惠券分类关联' : '新增优惠券分类关联'
})

const formData = reactive({
    id: '',
    couponId: 0,
    categoryId: 0,
    categoryName: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    couponId: [
        {
            required: true,
            message: '请输入优惠券id',
            trigger: ['blur']
        }
    ],
    categoryId: [
        {
            required: true,
            message: '请输入产品分类id',
            trigger: ['blur']
        }
    ],
    categoryName: [
        {
            required: true,
            message: '请输入产品分类名称',
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
