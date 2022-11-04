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
                <el-form-item label="sku_id" prop="skuId">
                    <el-input v-model="formData.skuId" placeholder="请输入sku_id" />
                </el-form-item>
                <el-form-item label="图片地址" prop="imgUrl">
                    <el-input v-model="formData.imgUrl" placeholder="请输入图片地址" />
                </el-form-item>
                <el-form-item label="排序" prop="imgSort">
                    <el-input v-model="formData.imgSort" placeholder="请输入排序" />
                </el-form-item>
                <el-form-item label="默认图[0 - 不是默认图，1 - 是默认图]" prop="defaultImg">
                    <el-input v-model="formData.defaultImg" placeholder="请输入默认图[0 - 不是默认图，1 - 是默认图]" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  imagesEdit, imagesAdd, imagesDetail } from '@/api/images'
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
    return mode.value == 'edit' ? '编辑sku图片' : '新增sku图片'
})

const formData = reactive({
    id: '',
    skuId: '',
    imgUrl: '',
    imgSort: '',
    defaultImg: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
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
    imgUrl: [
        {
            required: true,
            message: '请输入图片地址',
            trigger: ['blur']
        }
    ],
    imgSort: [
        {
            required: true,
            message: '请输入排序',
            trigger: ['blur']
        }
    ],
    defaultImg: [
        {
            required: true,
            message: '请输入默认图[0 - 不是默认图，1 - 是默认图]',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await imagesEdit(data) : await imagesAdd(data)
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
    const data = await imagesDetail({
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
