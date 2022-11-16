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
                <el-form-item label="会员id" prop="memberId">
                    <el-input-number v-model="formData.memberId" :max="9999" />
                </el-form-item>
                <el-form-item label="会员名字" prop="memberNickName">
                    <el-input v-model="formData.memberNickName" placeholder="请输入会员名字" />
                </el-form-item>
                <el-form-item label="获取方式[0->后台赠送；1->主动领取]" prop="getType">
                    <el-select class="flex-1" v-model="formData.getType" placeholder="请选择获取方式[0->后台赠送；1->主动领取]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="使用状态[0->未使用；1->已使用；2->已过期]" prop="useType">
                    <el-select class="flex-1" v-model="formData.useType" placeholder="请选择使用状态[0->未使用；1->已使用；2->已过期]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="使用时间" prop="useTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.useTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择使用时间"
                    />
                </el-form-item>
                <el-form-item label="订单id" prop="orderId">
                    <el-input-number v-model="formData.orderId" :max="9999" />
                </el-form-item>
                <el-form-item label="订单号" prop="orderSn">
                    <el-input-number v-model="formData.orderSn" :max="9999" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  historyEdit, historyAdd, historyDetail } from '@/api/coupon/history'
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
    return mode.value == 'edit' ? '编辑优惠券领取历史记录' : '新增优惠券领取历史记录'
})

const formData = reactive({
    id: '',
    couponId: 0,
    memberId: 0,
    memberNickName: '',
    getType: '',
    useType: '',
    useTime: '',
    orderId: 0,
    orderSn: 0,
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
    memberId: [
        {
            required: true,
            message: '请输入会员id',
            trigger: ['blur']
        }
    ],
    memberNickName: [
        {
            required: true,
            message: '请输入会员名字',
            trigger: ['blur']
        }
    ],
    getType: [
        {
            required: true,
            message: '请选择获取方式[0->后台赠送；1->主动领取]',
            trigger: ['blur']
        }
    ],
    useType: [
        {
            required: true,
            message: '请选择使用状态[0->未使用；1->已使用；2->已过期]',
            trigger: ['blur']
        }
    ],
    useTime: [
        {
            required: true,
            message: '请选择使用时间',
            trigger: ['blur']
        }
    ],
    orderId: [
        {
            required: true,
            message: '请输入订单id',
            trigger: ['blur']
        }
    ],
    orderSn: [
        {
            required: true,
            message: '请输入订单号',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await historyEdit(data) : await historyAdd(data)
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
    const data = await historyDetail({
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
