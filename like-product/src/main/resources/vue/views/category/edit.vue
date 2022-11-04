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
                <el-form-item label="分类id" prop="catId">
                    <el-input v-model="formData.catId" placeholder="请输入分类id" />
                </el-form-item>
                <el-form-item label="分类名称" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入分类名称" />
                </el-form-item>
                <el-form-item label="父分类id" prop="parentCid">
                    <el-input v-model="formData.parentCid" placeholder="请输入父分类id" />
                </el-form-item>
                <el-form-item label="层级" prop="catLevel">
                    <el-input v-model="formData.catLevel" placeholder="请输入层级" />
                </el-form-item>
                <el-form-item label="是否显示[0-不显示，1显示]" prop="showStatus">
                    <el-radio-group v-model="formData.showStatus" placeholder="请选择是否显示[0-不显示，1显示]">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="排序" prop="sort">
                    <el-input v-model="formData.sort" placeholder="请输入排序" />
                </el-form-item>
                <el-form-item label="图标地址" prop="icon">
                    <el-input v-model="formData.icon" placeholder="请输入图标地址" />
                </el-form-item>
                <el-form-item label="计量单位" prop="productUnit">
                    <el-input v-model="formData.productUnit" placeholder="请输入计量单位" />
                </el-form-item>
                <el-form-item label="商品数量" prop="productCount">
                    <el-input v-model="formData.productCount" placeholder="请输入商品数量" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  categoryEdit, categoryAdd, categoryDetail } from '@/api/category'
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
    return mode.value == 'edit' ? '编辑商品三级分类' : '新增商品三级分类'
})

const formData = reactive({
    catId: '',
    name: '',
    parentCid: '',
    catLevel: '',
    showStatus: '',
    sort: '',
    icon: '',
    productUnit: '',
    productCount: '',
})

const formRules = {
    catId: [
        {
            required: true,
            message: '请输入分类id',
            trigger: ['blur']
        }
    ],
    name: [
        {
            required: true,
            message: '请输入分类名称',
            trigger: ['blur']
        }
    ],
    parentCid: [
        {
            required: true,
            message: '请输入父分类id',
            trigger: ['blur']
        }
    ],
    catLevel: [
        {
            required: true,
            message: '请输入层级',
            trigger: ['blur']
        }
    ],
    showStatus: [
        {
            required: true,
            message: '请选择是否显示[0-不显示，1显示]',
            trigger: ['blur']
        }
    ],
    sort: [
        {
            required: true,
            message: '请输入排序',
            trigger: ['blur']
        }
    ],
    icon: [
        {
            required: true,
            message: '请输入图标地址',
            trigger: ['blur']
        }
    ],
    productUnit: [
        {
            required: true,
            message: '请输入计量单位',
            trigger: ['blur']
        }
    ],
    productCount: [
        {
            required: true,
            message: '请输入商品数量',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await categoryEdit(data) : await categoryAdd(data)
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
    const data = await categoryDetail({
        catId: row.catId
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
