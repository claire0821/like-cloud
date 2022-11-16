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
                <el-form-item label="活动id" prop="promotionId">
                    <el-input-number v-model="formData.promotionId" :max="9999" />
                </el-form-item>
                <el-form-item label="活动场次id" prop="promotionSessionId">
                    <el-input-number v-model="formData.promotionSessionId" :max="9999" />
                </el-form-item>
                <el-form-item label="商品id" prop="skuId">
                    <el-input-number v-model="formData.skuId" :max="9999" />
                </el-form-item>
                <el-form-item label="秒杀价格" prop="seckillPrice">
                    <el-input-number v-model="formData.seckillPrice" :max="9999" />
                </el-form-item>
                <el-form-item label="秒杀总量" prop="seckillCount">
                    <el-input-number v-model="formData.seckillCount" :max="9999" />
                </el-form-item>
                <el-form-item label="每人限购数量" prop="seckillLimit">
                    <el-input-number v-model="formData.seckillLimit" :max="9999" />
                </el-form-item>
                <el-form-item label="排序" prop="seckillSort">
                    <el-input-number v-model="formData.seckillSort" :max="9999" />
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
    return mode.value == 'edit' ? '编辑秒杀活动商品关联' : '新增秒杀活动商品关联'
})

const formData = reactive({
    id: '',
    promotionId: 0,
    promotionSessionId: 0,
    skuId: 0,
    seckillPrice: 0,
    seckillCount: 0,
    seckillLimit: 0,
    seckillSort: 0,
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    promotionId: [
        {
            required: true,
            message: '请输入活动id',
            trigger: ['blur']
        }
    ],
    promotionSessionId: [
        {
            required: true,
            message: '请输入活动场次id',
            trigger: ['blur']
        }
    ],
    skuId: [
        {
            required: true,
            message: '请输入商品id',
            trigger: ['blur']
        }
    ],
    seckillPrice: [
        {
            required: true,
            message: '请输入秒杀价格',
            trigger: ['blur']
        }
    ],
    seckillCount: [
        {
            required: true,
            message: '请输入秒杀总量',
            trigger: ['blur']
        }
    ],
    seckillLimit: [
        {
            required: true,
            message: '请输入每人限购数量',
            trigger: ['blur']
        }
    ],
    seckillSort: [
        {
            required: true,
            message: '请输入排序',
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
