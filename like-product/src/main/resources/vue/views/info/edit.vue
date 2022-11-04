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
                <el-form-item label="skuId" prop="skuId">
                    <el-input v-model="formData.skuId" placeholder="请输入skuId" />
                </el-form-item>
                <el-form-item label="spuId" prop="spuId">
                    <el-input v-model="formData.spuId" placeholder="请输入spuId" />
                </el-form-item>
                <el-form-item label="sku名称" prop="skuName">
                    <el-input v-model="formData.skuName" placeholder="请输入sku名称" />
                </el-form-item>
                <el-form-item label="sku介绍描述" prop="skuDesc">
                    <el-input
                        v-model="formData.skuDesc"
                        placeholder="请输入sku介绍描述"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="所属分类id" prop="catalogId">
                    <el-input v-model="formData.catalogId" placeholder="请输入所属分类id" />
                </el-form-item>
                <el-form-item label="品牌id" prop="brandId">
                    <el-input v-model="formData.brandId" placeholder="请输入品牌id" />
                </el-form-item>
                <el-form-item label="默认图片" prop="skuDefaultImg">
                    <el-input v-model="formData.skuDefaultImg" placeholder="请输入默认图片" />
                </el-form-item>
                <el-form-item label="标题" prop="skuTitle">
                    <el-input v-model="formData.skuTitle" placeholder="请输入标题" />
                </el-form-item>
                <el-form-item label="副标题" prop="skuSubtitle">
                    <el-input
                        v-model="formData.skuSubtitle"
                        placeholder="请输入副标题"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="价格" prop="price">
                    <el-input v-model="formData.price" placeholder="请输入价格" />
                </el-form-item>
                <el-form-item label="销量" prop="saleCount">
                    <el-input v-model="formData.saleCount" placeholder="请输入销量" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  infoEdit, infoAdd, infoDetail } from '@/api/info'
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
    return mode.value == 'edit' ? '编辑sku信息' : '新增sku信息'
})

const formData = reactive({
    skuId: '',
    spuId: '',
    skuName: '',
    skuDesc: '',
    catalogId: '',
    brandId: '',
    skuDefaultImg: '',
    skuTitle: '',
    skuSubtitle: '',
    price: '',
    saleCount: '',
})

const formRules = {
    skuId: [
        {
            required: true,
            message: '请输入skuId',
            trigger: ['blur']
        }
    ],
    spuId: [
        {
            required: true,
            message: '请输入spuId',
            trigger: ['blur']
        }
    ],
    skuName: [
        {
            required: true,
            message: '请输入sku名称',
            trigger: ['blur']
        }
    ],
    skuDesc: [
        {
            required: true,
            message: '请输入sku介绍描述',
            trigger: ['blur']
        }
    ],
    catalogId: [
        {
            required: true,
            message: '请输入所属分类id',
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
    skuDefaultImg: [
        {
            required: true,
            message: '请输入默认图片',
            trigger: ['blur']
        }
    ],
    skuTitle: [
        {
            required: true,
            message: '请输入标题',
            trigger: ['blur']
        }
    ],
    skuSubtitle: [
        {
            required: true,
            message: '请输入副标题',
            trigger: ['blur']
        }
    ],
    price: [
        {
            required: true,
            message: '请输入价格',
            trigger: ['blur']
        }
    ],
    saleCount: [
        {
            required: true,
            message: '请输入销量',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await infoEdit(data) : await infoAdd(data)
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
    const data = await infoDetail({
        skuId: row.skuId
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
