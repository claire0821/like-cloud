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
                <el-form-item label="收货人姓名" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入收货人姓名" />
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="formData.phone" placeholder="请输入电话" />
                </el-form-item>
                <el-form-item label="邮政编码" prop="postCode">
                    <el-input v-model="formData.postCode" placeholder="请输入邮政编码" />
                </el-form-item>
                <el-form-item label="省份/直辖市" prop="province">
                    <el-input v-model="formData.province" placeholder="请输入省份/直辖市" />
                </el-form-item>
                <el-form-item label="城市" prop="city">
                    <el-input v-model="formData.city" placeholder="请输入城市" />
                </el-form-item>
                <el-form-item label="区" prop="region">
                    <el-input v-model="formData.region" placeholder="请输入区" />
                </el-form-item>
                <el-form-item label="详细地址(街道)" prop="detailAddress">
                    <el-input v-model="formData.detailAddress" placeholder="请输入详细地址(街道)" />
                </el-form-item>
                <el-form-item label="省市区代码" prop="areacode">
                    <el-input v-model="formData.areacode" placeholder="请输入省市区代码" />
                </el-form-item>
                <el-form-item label="是否默认" prop="defaultStatus">
                    <el-radio-group v-model="formData.defaultStatus" placeholder="请选择是否默认">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  addressEdit, addressAdd, addressDetail } from '@/api/address'
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
    return mode.value == 'edit' ? '编辑会员收货地址' : '新增会员收货地址'
})

const formData = reactive({
    id: '',
    memberId: '',
    name: '',
    phone: '',
    postCode: '',
    province: '',
    city: '',
    region: '',
    detailAddress: '',
    areacode: '',
    defaultStatus: '',
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
    name: [
        {
            required: true,
            message: '请输入收货人姓名',
            trigger: ['blur']
        }
    ],
    phone: [
        {
            required: true,
            message: '请输入电话',
            trigger: ['blur']
        }
    ],
    postCode: [
        {
            required: true,
            message: '请输入邮政编码',
            trigger: ['blur']
        }
    ],
    province: [
        {
            required: true,
            message: '请输入省份/直辖市',
            trigger: ['blur']
        }
    ],
    city: [
        {
            required: true,
            message: '请输入城市',
            trigger: ['blur']
        }
    ],
    region: [
        {
            required: true,
            message: '请输入区',
            trigger: ['blur']
        }
    ],
    detailAddress: [
        {
            required: true,
            message: '请输入详细地址(街道)',
            trigger: ['blur']
        }
    ],
    areacode: [
        {
            required: true,
            message: '请输入省市区代码',
            trigger: ['blur']
        }
    ],
    defaultStatus: [
        {
            required: true,
            message: '请选择是否默认',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await addressEdit(data) : await addressAdd(data)
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
    const data = await addressDetail({
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
