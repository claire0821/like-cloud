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
                    <el-input v-model="formData.id" placeholder="请输入id" />
                </el-form-item>
                <el-form-item label="member_id" prop="memberId">
                    <el-input v-model="formData.memberId" placeholder="请输入member_id" />
                </el-form-item>
                <el-form-item label="变化的值" prop="changeCount">
                    <el-input v-model="formData.changeCount" placeholder="请输入变化的值" />
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-input v-model="formData.note" placeholder="请输入备注" />
                </el-form-item>
                <el-form-item label="来源[0->购物；1->管理员修改;2->活动]" prop="sourceTyoe">
                    <el-input v-model="formData.sourceTyoe" placeholder="请输入来源[0->购物；1->管理员修改;2->活动]" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  historyEdit, historyAdd, historyDetail } from '@/api/history'
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
    return mode.value == 'edit' ? '编辑积分变化历史记录' : '新增积分变化历史记录'
})

const formData = reactive({
    id: '',
    memberId: '',
    changeCount: '',
    note: '',
    sourceTyoe: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    memberId: [
        {
            required: true,
            message: '请输入member_id',
            trigger: ['blur']
        }
    ],
    changeCount: [
        {
            required: true,
            message: '请输入变化的值',
            trigger: ['blur']
        }
    ],
    note: [
        {
            required: true,
            message: '请输入备注',
            trigger: ['blur']
        }
    ],
    sourceTyoe: [
        {
            required: true,
            message: '请输入来源[0->购物；1->管理员修改;2->活动]',
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
