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
                <el-form-item label="商品id" prop="spuId">
                    <el-input v-model="formData.spuId" placeholder="请输入商品id" />
                </el-form-item>
                <el-form-item label="属性id" prop="attrId">
                    <el-input v-model="formData.attrId" placeholder="请输入属性id" />
                </el-form-item>
                <el-form-item label="属性名" prop="attrName">
                    <el-input v-model="formData.attrName" placeholder="请输入属性名" />
                </el-form-item>
                <el-form-item label="属性值" prop="attrValue">
                    <el-input v-model="formData.attrValue" placeholder="请输入属性值" />
                </el-form-item>
                <el-form-item label="顺序" prop="attrSort">
                    <el-input v-model="formData.attrSort" placeholder="请输入顺序" />
                </el-form-item>
                <el-form-item label="快速展示【是否展示在介绍上；0-否 1-是】" prop="quickShow">
                    <el-input v-model="formData.quickShow" placeholder="请输入快速展示【是否展示在介绍上；0-否 1-是】" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  valueEdit, valueAdd, valueDetail } from '@/api/value'
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
    return mode.value == 'edit' ? '编辑spu属性值' : '新增spu属性值'
})

const formData = reactive({
    id: '',
    spuId: '',
    attrId: '',
    attrName: '',
    attrValue: '',
    attrSort: '',
    quickShow: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    spuId: [
        {
            required: true,
            message: '请输入商品id',
            trigger: ['blur']
        }
    ],
    attrId: [
        {
            required: true,
            message: '请输入属性id',
            trigger: ['blur']
        }
    ],
    attrName: [
        {
            required: true,
            message: '请输入属性名',
            trigger: ['blur']
        }
    ],
    attrValue: [
        {
            required: true,
            message: '请输入属性值',
            trigger: ['blur']
        }
    ],
    attrSort: [
        {
            required: true,
            message: '请输入顺序',
            trigger: ['blur']
        }
    ],
    quickShow: [
        {
            required: true,
            message: '请输入快速展示【是否展示在介绍上；0-否 1-是】',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await valueEdit(data) : await valueAdd(data)
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
    const data = await valueDetail({
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
