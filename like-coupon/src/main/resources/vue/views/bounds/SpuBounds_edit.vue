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
                <el-form-item label="" prop="spuId">
                    <el-input-number v-model="formData.spuId" :max="9999" />
                </el-form-item>
                <el-form-item label="成长积分" prop="growBounds">
                    <el-input-number v-model="formData.growBounds" :max="9999" />
                </el-form-item>
                <el-form-item label="购物积分" prop="buyBounds">
                    <el-input-number v-model="formData.buyBounds" :max="9999" />
                </el-form-item>
                <el-form-item label="优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]" prop="work">
                   <el-checkbox v-model="formData.work" :true-label="1" :false-label="0" placeholder="请选择优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]"></el-checkbox>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  boundsEdit, boundsAdd, boundsDetail } from '@/api/coupon/bounds'
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
    return mode.value == 'edit' ? '编辑商品spu积分设置' : '新增商品spu积分设置'
})

const formData = reactive({
    id: '',
    spuId: 0,
    growBounds: 0,
    buyBounds: 0,
    work: [],
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
            message: '请输入',
            trigger: ['blur']
        }
    ],
    growBounds: [
        {
            required: true,
            message: '请输入成长积分',
            trigger: ['blur']
        }
    ],
    buyBounds: [
        {
            required: true,
            message: '请输入购物积分',
            trigger: ['blur']
        }
    ],
    work: [
        {
            required: true,
            message: '请选择优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.work = data.work.join(',')
    mode.value == 'edit' ? await boundsEdit(data) : await boundsAdd(data)
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
            formData.work = String(data.work).split(',')
        }
    }
}

const getDetail = async (row: Record<string, any>) => {
    const data = await boundsDetail({
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
