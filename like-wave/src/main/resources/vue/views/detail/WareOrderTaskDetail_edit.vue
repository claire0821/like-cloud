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
                <el-form-item label="sku_name" prop="skuName">
                    <el-input v-model="formData.skuName" placeholder="请输入sku_name" />
                </el-form-item>
                <el-form-item label="购买个数" prop="skuNum">
                    <el-input-number v-model="formData.skuNum" :max="9999" />
                </el-form-item>
                <el-form-item label="工作单id" prop="taskId">
                    <el-input-number v-model="formData.taskId" :max="9999" />
                </el-form-item>
                <el-form-item label="仓库id" prop="wareId">
                    <el-input-number v-model="formData.wareId" :max="9999" />
                </el-form-item>
                <el-form-item label="1-已锁定  2-已解锁  3-扣减" prop="lockStatus">
                    <el-radio-group v-model="formData.lockStatus" placeholder="请选择1-已锁定  2-已解锁  3-扣减">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  detailEdit, detailAdd, detailDetail } from '@/api/wave/detail'
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
    return mode.value == 'edit' ? '编辑库存工作单' : '新增库存工作单'
})

const formData = reactive({
    id: '',
    skuId: 0,
    skuName: '',
    skuNum: 0,
    taskId: 0,
    wareId: 0,
    lockStatus: '',
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
    skuName: [
        {
            required: true,
            message: '请输入sku_name',
            trigger: ['blur']
        }
    ],
    skuNum: [
        {
            required: true,
            message: '请输入购买个数',
            trigger: ['blur']
        }
    ],
    taskId: [
        {
            required: true,
            message: '请输入工作单id',
            trigger: ['blur']
        }
    ],
    wareId: [
        {
            required: true,
            message: '请输入仓库id',
            trigger: ['blur']
        }
    ],
    lockStatus: [
        {
            required: true,
            message: '请选择1-已锁定  2-已解锁  3-扣减',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await detailEdit(data) : await detailAdd(data)
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
    const data = await detailDetail({
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
