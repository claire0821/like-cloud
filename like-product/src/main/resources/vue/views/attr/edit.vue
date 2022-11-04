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
                <el-form-item label="属性id" prop="attrId">
                    <el-input v-model="formData.attrId" placeholder="请输入属性id" />
                </el-form-item>
                <el-form-item label="属性名" prop="attrName">
                    <el-input v-model="formData.attrName" placeholder="请输入属性名" />
                </el-form-item>
                <el-form-item label="是否需要检索[0-不需要，1-需要]" prop="searchType">
                    <el-select class="flex-1" v-model="formData.searchType" placeholder="请选择是否需要检索[0-不需要，1-需要]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="属性图标" prop="icon">
                    <el-input v-model="formData.icon" placeholder="请输入属性图标" />
                </el-form-item>
                <el-form-item label="可选值列表[用逗号分隔]" prop="valueSelect">
                    <el-input v-model="formData.valueSelect" placeholder="请输入可选值列表[用逗号分隔]" />
                </el-form-item>
                <el-form-item label="属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]" prop="attrType">
                    <el-select class="flex-1" v-model="formData.attrType" placeholder="请选择属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="启用状态[0 - 禁用，1 - 启用]" prop="enable">
                    <el-input v-model="formData.enable" placeholder="请输入启用状态[0 - 禁用，1 - 启用]" />
                </el-form-item>
                <el-form-item label="所属分类" prop="catelogId">
                    <el-input v-model="formData.catelogId" placeholder="请输入所属分类" />
                </el-form-item>
                <el-form-item label="快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整" prop="showDesc">
                    <el-input v-model="formData.showDesc" placeholder="请输入快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  attrEdit, attrAdd, attrDetail } from '@/api/attr'
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
    return mode.value == 'edit' ? '编辑商品属性' : '新增商品属性'
})

const formData = reactive({
    attrId: '',
    attrName: '',
    searchType: '',
    icon: '',
    valueSelect: '',
    attrType: '',
    enable: '',
    catelogId: '',
    showDesc: '',
})

const formRules = {
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
    searchType: [
        {
            required: true,
            message: '请选择是否需要检索[0-不需要，1-需要]',
            trigger: ['blur']
        }
    ],
    icon: [
        {
            required: true,
            message: '请输入属性图标',
            trigger: ['blur']
        }
    ],
    valueSelect: [
        {
            required: true,
            message: '请输入可选值列表[用逗号分隔]',
            trigger: ['blur']
        }
    ],
    attrType: [
        {
            required: true,
            message: '请选择属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]',
            trigger: ['blur']
        }
    ],
    enable: [
        {
            required: true,
            message: '请输入启用状态[0 - 禁用，1 - 启用]',
            trigger: ['blur']
        }
    ],
    catelogId: [
        {
            required: true,
            message: '请输入所属分类',
            trigger: ['blur']
        }
    ],
    showDesc: [
        {
            required: true,
            message: '请输入快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await attrEdit(data) : await attrAdd(data)
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
    const data = await attrDetail({
        attrId: row.attrId
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
