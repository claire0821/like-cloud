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
                <el-form-item label="会员等级id" prop="levelId">
                    <el-input-number v-model="formData.levelId" :max="9999" />
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="formData.username" placeholder="请输入用户名" />
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="formData.password" placeholder="请输入密码" />
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="formData.nickname" placeholder="请输入昵称" />
                </el-form-item>
                <el-form-item label="手机号码" prop="mobile">
                    <el-input v-model="formData.mobile" placeholder="请输入手机号码" />
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="formData.email" placeholder="请输入邮箱" />
                </el-form-item>
                <el-form-item label="头像" prop="header">
                    <el-input
                        v-model="formData.header"
                        placeholder="请输入头像"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                   <el-checkbox v-model="formData.gender" :true-label="1" :false-label="0" placeholder="请选择性别"></el-checkbox>
                </el-form-item>
                <el-form-item label="生日" prop="birth">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.birth"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择生日"
                    />
                </el-form-item>
                <el-form-item label="所在城市" prop="city">
                    <el-input
                        v-model="formData.city"
                        placeholder="请输入所在城市"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="职业" prop="job">
                    <el-input v-model="formData.job" placeholder="请输入职业" />
                </el-form-item>
                <el-form-item label="个性签名" prop="sign">
                    <el-input v-model="formData.sign" placeholder="请输入个性签名" />
                </el-form-item>
                <el-form-item label="用户来源" prop="sourceType">
                    <el-select class="flex-1" v-model="formData.sourceType" placeholder="请选择用户来源">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="积分" prop="integration">
                    <el-input-number v-model="formData.integration" :max="9999" />
                </el-form-item>
                <el-form-item label="成长值" prop="growth">
                    <el-input-number v-model="formData.growth" :max="9999" />
                </el-form-item>
                <el-form-item label="启用状态" prop="status">
                    <el-radio-group v-model="formData.status" placeholder="请选择启用状态">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  memberEdit, memberAdd, memberDetail } from '@/api/member/member'
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
    return mode.value == 'edit' ? '编辑会员' : '新增会员'
})

const formData = reactive({
    id: '',
    levelId: 0,
    username: '',
    password: '',
    nickname: '',
    mobile: '',
    email: '',
    header: '',
    gender: [],
    birth: '',
    city: '',
    job: '',
    sign: '',
    sourceType: '',
    integration: 0,
    growth: 0,
    status: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    levelId: [
        {
            required: true,
            message: '请输入会员等级id',
            trigger: ['blur']
        }
    ],
    username: [
        {
            required: true,
            message: '请输入用户名',
            trigger: ['blur']
        }
    ],
    password: [
        {
            required: true,
            message: '请输入密码',
            trigger: ['blur']
        }
    ],
    nickname: [
        {
            required: true,
            message: '请输入昵称',
            trigger: ['blur']
        }
    ],
    mobile: [
        {
            required: true,
            message: '请输入手机号码',
            trigger: ['blur']
        }
    ],
    email: [
        {
            required: true,
            message: '请输入邮箱',
            trigger: ['blur']
        }
    ],
    header: [
        {
            required: true,
            message: '请输入头像',
            trigger: ['blur']
        }
    ],
    gender: [
        {
            required: true,
            message: '请选择性别',
            trigger: ['blur']
        }
    ],
    birth: [
        {
            required: true,
            message: '请选择生日',
            trigger: ['blur']
        }
    ],
    city: [
        {
            required: true,
            message: '请输入所在城市',
            trigger: ['blur']
        }
    ],
    job: [
        {
            required: true,
            message: '请输入职业',
            trigger: ['blur']
        }
    ],
    sign: [
        {
            required: true,
            message: '请输入个性签名',
            trigger: ['blur']
        }
    ],
    sourceType: [
        {
            required: true,
            message: '请选择用户来源',
            trigger: ['blur']
        }
    ],
    integration: [
        {
            required: true,
            message: '请输入积分',
            trigger: ['blur']
        }
    ],
    growth: [
        {
            required: true,
            message: '请输入成长值',
            trigger: ['blur']
        }
    ],
    status: [
        {
            required: true,
            message: '请选择启用状态',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.gender = data.gender.join(',')
    mode.value == 'edit' ? await memberEdit(data) : await memberAdd(data)
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
            formData.gender = String(data.gender).split(',')
        }
    }
}

const getDetail = async (row: Record<string, any>) => {
    const data = await memberDetail({
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
