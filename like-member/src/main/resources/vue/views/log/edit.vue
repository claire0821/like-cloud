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
                <el-form-item label="ip" prop="ip">
                    <el-input v-model="formData.ip" placeholder="请输入ip" />
                </el-form-item>
                <el-form-item label="city" prop="city">
                    <el-input v-model="formData.city" placeholder="请输入city" />
                </el-form-item>
                <el-form-item label="登录类型[1-web，2-app]" prop="loginType">
                    <el-select class="flex-1" v-model="formData.loginType" placeholder="请选择登录类型[1-web，2-app]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  logEdit, logAdd, logDetail } from '@/api/log'
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
    return mode.value == 'edit' ? '编辑会员登录记录' : '新增会员登录记录'
})

const formData = reactive({
    id: '',
    memberId: '',
    ip: '',
    city: '',
    loginType: '',
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
    ip: [
        {
            required: true,
            message: '请输入ip',
            trigger: ['blur']
        }
    ],
    city: [
        {
            required: true,
            message: '请输入city',
            trigger: ['blur']
        }
    ],
    loginType: [
        {
            required: true,
            message: '请选择登录类型[1-web，2-app]',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await logEdit(data) : await logAdd(data)
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
    const data = await logDetail({
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
