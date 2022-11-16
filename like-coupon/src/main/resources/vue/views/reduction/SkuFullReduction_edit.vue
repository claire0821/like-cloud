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
                <el-form-item label="spu_id" prop="skuId">
                    <el-input-number v-model="formData.skuId" :max="9999" />
                </el-form-item>
                <el-form-item label="满多少" prop="fullPrice">
                    <el-input-number v-model="formData.fullPrice" :max="9999" />
                </el-form-item>
                <el-form-item label="减多少" prop="reducePrice">
                    <el-input-number v-model="formData.reducePrice" :max="9999" />
                </el-form-item>
                <el-form-item label="是否参与其他优惠" prop="addOther">
                   <el-checkbox v-model="formData.addOther" :true-label="1" :false-label="0" placeholder="请选择是否参与其他优惠"></el-checkbox>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  reductionEdit, reductionAdd, reductionDetail } from '@/api/coupon/reduction'
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
    return mode.value == 'edit' ? '编辑商品满减信息' : '新增商品满减信息'
})

const formData = reactive({
    id: '',
    skuId: 0,
    fullPrice: 0,
    reducePrice: 0,
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
            message: '请输入spu_id',
            trigger: ['blur']
        }
    ],
    fullPrice: [
        {
            required: true,
            message: '请输入满多少',
            trigger: ['blur']
        }
    ],
    reducePrice: [
        {
            required: true,
            message: '请输入减多少',
            trigger: ['blur']
        }
    ],
    addOther: [
        {
            required: true,
            message: '请选择是否参与其他优惠',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.addOther = data.addOther.join(',')
    mode.value == 'edit' ? await reductionEdit(data) : await reductionAdd(data)
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
    const data = await reductionDetail({
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
