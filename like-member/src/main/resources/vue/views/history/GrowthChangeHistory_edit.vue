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
                <el-form-item label="member_id" prop="memberId">
                    <el-input-number v-model="formData.memberId" :max="9999" />
                </el-form-item>
                <el-form-item label="改变的值（正负计数）" prop="changeCount">
                    <el-input-number v-model="formData.changeCount" :max="9999" />
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-input v-model="formData.note" placeholder="请输入备注" />
                </el-form-item>
                <el-form-item label="积分来源[0-购物，1-管理员修改]" prop="sourceType">
                    <el-select class="flex-1" v-model="formData.sourceType" placeholder="请选择积分来源[0-购物，1-管理员修改]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  historyEdit, historyAdd, historyDetail } from '@/api/member/history'
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
    return mode.value == 'edit' ? '编辑成长值变化历史记录' : '新增成长值变化历史记录'
})

const formData = reactive({
    id: '',
    memberId: 0,
    changeCount: 0,
    note: '',
    sourceType: '',
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
            message: '请输入改变的值（正负计数）',
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
    sourceType: [
        {
            required: true,
            message: '请选择积分来源[0-购物，1-管理员修改]',
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
