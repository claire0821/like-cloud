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
                <el-form-item label="名字" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入名字" />
                </el-form-item>
                <el-form-item label="图片地址" prop="pic">
                    <el-input
                        v-model="formData.pic"
                        placeholder="请输入图片地址"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="开始时间" prop="startTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.startTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择开始时间"
                    />
                </el-form-item>
                <el-form-item label="结束时间" prop="endTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.endTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择结束时间"
                    />
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-radio-group v-model="formData.status" placeholder="请选择状态">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="点击数" prop="clickCount">
                    <el-input-number v-model="formData.clickCount" :max="9999" />
                </el-form-item>
                <el-form-item label="广告详情连接地址" prop="url">
                    <el-input
                        v-model="formData.url"
                        placeholder="请输入广告详情连接地址"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-input
                        v-model="formData.note"
                        placeholder="请输入备注"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="排序" prop="sort">
                    <el-input-number v-model="formData.sort" :max="9999" />
                </el-form-item>
                <el-form-item label="发布者" prop="publisherId">
                    <el-input-number v-model="formData.publisherId" :max="9999" />
                </el-form-item>
                <el-form-item label="审核者" prop="authId">
                    <el-input-number v-model="formData.authId" :max="9999" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  advEdit, advAdd, advDetail } from '@/api/coupon/adv'
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
    return mode.value == 'edit' ? '编辑首页轮播广告' : '新增首页轮播广告'
})

const formData = reactive({
    id: '',
    name: '',
    pic: '',
    startTime: '',
    endTime: '',
    status: '',
    clickCount: 0,
    url: '',
    note: '',
    sort: 0,
    publisherId: 0,
    authId: 0,
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    name: [
        {
            required: true,
            message: '请输入名字',
            trigger: ['blur']
        }
    ],
    pic: [
        {
            required: true,
            message: '请输入图片地址',
            trigger: ['blur']
        }
    ],
    startTime: [
        {
            required: true,
            message: '请选择开始时间',
            trigger: ['blur']
        }
    ],
    endTime: [
        {
            required: true,
            message: '请选择结束时间',
            trigger: ['blur']
        }
    ],
    status: [
        {
            required: true,
            message: '请选择状态',
            trigger: ['blur']
        }
    ],
    clickCount: [
        {
            required: true,
            message: '请输入点击数',
            trigger: ['blur']
        }
    ],
    url: [
        {
            required: true,
            message: '请输入广告详情连接地址',
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
    sort: [
        {
            required: true,
            message: '请输入排序',
            trigger: ['blur']
        }
    ],
    publisherId: [
        {
            required: true,
            message: '请输入发布者',
            trigger: ['blur']
        }
    ],
    authId: [
        {
            required: true,
            message: '请输入审核者',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await advEdit(data) : await advAdd(data)
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
    const data = await advDetail({
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
