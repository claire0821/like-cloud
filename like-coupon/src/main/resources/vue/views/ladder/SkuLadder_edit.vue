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
                <el-form-item label="满几件" prop="fullCount">
                    <el-input-number v-model="formData.fullCount" :max="9999" />
                </el-form-item>
                <el-form-item label="打几折" prop="discount">
                    <el-input-number v-model="formData.discount" :max="9999" />
                </el-form-item>
                <el-form-item label="折后价" prop="price">
                    <el-input-number v-model="formData.price" :max="9999" />
                </el-form-item>
                <el-form-item label="是否叠加其他优惠[0-不可叠加，1-可叠加]" prop="addOther">
                   <el-checkbox v-model="formData.addOther" :true-label="1" :false-label="0" placeholder="请选择是否叠加其他优惠[0-不可叠加，1-可叠加]"></el-checkbox>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  ladderEdit, ladderAdd, ladderDetail } from '@/api/coupon/ladder'
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
    return mode.value == 'edit' ? '编辑商品阶梯价格' : '新增商品阶梯价格'
})

const formData = reactive({
    id: '',
    skuId: 0,
    fullCount: 0,
    discount: 0,
    price: 0,
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
    fullCount: [
        {
            required: true,
            message: '请输入满几件',
            trigger: ['blur']
        }
    ],
    discount: [
        {
            required: true,
            message: '请输入打几折',
            trigger: ['blur']
        }
    ],
    price: [
        {
            required: true,
            message: '请输入折后价',
            trigger: ['blur']
        }
    ],
    addOther: [
        {
            required: true,
            message: '请选择是否叠加其他优惠[0-不可叠加，1-可叠加]',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.addOther = data.addOther.join(',')
    mode.value == 'edit' ? await ladderEdit(data) : await ladderAdd(data)
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
    const data = await ladderDetail({
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
