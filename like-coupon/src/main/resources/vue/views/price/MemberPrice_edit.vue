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
                <el-form-item label="sku_id" prop="skuId">
                    <el-input-number v-model="formData.skuId" :max="9999" />
                </el-form-item>
                <el-form-item label="会员等级id" prop="memberLevelId">
                    <el-input-number v-model="formData.memberLevelId" :max="9999" />
                </el-form-item>
                <el-form-item label="会员等级名" prop="memberLevelName">
                    <el-input v-model="formData.memberLevelName" placeholder="请输入会员等级名" />
                </el-form-item>
                <el-form-item label="会员对应价格" prop="memberPrice">
                    <el-input-number v-model="formData.memberPrice" :max="9999" />
                </el-form-item>
                <el-form-item label="可否叠加其他优惠[0-不可叠加优惠，1-可叠加]" prop="addOther">
                   <el-checkbox v-model="formData.addOther" :true-label="1" :false-label="0" placeholder="请选择可否叠加其他优惠[0-不可叠加优惠，1-可叠加]"></el-checkbox>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  priceEdit, priceAdd, priceDetail } from '@/api/coupon/price'
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
    return mode.value == 'edit' ? '编辑商品会员价格' : '新增商品会员价格'
})

const formData = reactive({
    id: '',
    skuId: 0,
    memberLevelId: 0,
    memberLevelName: '',
    memberPrice: 0,
    addOther: [],
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
    memberLevelId: [
        {
            required: true,
            message: '请输入会员等级id',
            trigger: ['blur']
        }
    ],
    memberLevelName: [
        {
            required: true,
            message: '请输入会员等级名',
            trigger: ['blur']
        }
    ],
    memberPrice: [
        {
            required: true,
            message: '请输入会员对应价格',
            trigger: ['blur']
        }
    ],
    addOther: [
        {
            required: true,
            message: '请选择可否叠加其他优惠[0-不可叠加优惠，1-可叠加]',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.addOther = data.addOther.join(',')
    mode.value == 'edit' ? await priceEdit(data) : await priceAdd(data)
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
            //@ts-ignore
            formData.addOther = String(data.addOther).split(',')
        }
    }
}

const getDetail = async (row: Record<string, any>) => {
    const data = await priceDetail({
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
