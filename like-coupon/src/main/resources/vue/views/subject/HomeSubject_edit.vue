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
                <el-form-item label="专题名字" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入专题名字" />
                </el-form-item>
                <el-form-item label="专题标题" prop="title">
                    <el-input v-model="formData.title" placeholder="请输入专题标题" />
                </el-form-item>
                <el-form-item label="专题副标题" prop="subTitle">
                    <el-input v-model="formData.subTitle" placeholder="请输入专题副标题" />
                </el-form-item>
                <el-form-item label="显示状态" prop="status">
                    <el-radio-group v-model="formData.status" placeholder="请选择显示状态">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="详情连接" prop="url">
                    <el-input
                        v-model="formData.url"
                        placeholder="请输入详情连接"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="排序" prop="sort">
                    <el-input-number v-model="formData.sort" :max="9999" />
                </el-form-item>
                <el-form-item label="专题图片地址" prop="img">
                    <el-input
                        v-model="formData.img"
                        placeholder="请输入专题图片地址"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  subjectEdit, subjectAdd, subjectDetail } from '@/api/coupon/subject'
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
    return mode.value == 'edit' ? '编辑首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】' : '新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】'
})

const formData = reactive({
    id: '',
    name: '',
    title: '',
    subTitle: '',
    status: '',
    url: '',
    sort: 0,
    img: '',
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
            message: '请输入专题名字',
            trigger: ['blur']
        }
    ],
    title: [
        {
            required: true,
            message: '请输入专题标题',
            trigger: ['blur']
        }
    ],
    subTitle: [
        {
            required: true,
            message: '请输入专题副标题',
            trigger: ['blur']
        }
    ],
    status: [
        {
            required: true,
            message: '请选择显示状态',
            trigger: ['blur']
        }
    ],
    url: [
        {
            required: true,
            message: '请输入详情连接',
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
    img: [
        {
            required: true,
            message: '请输入专题图片地址',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await subjectEdit(data) : await subjectAdd(data)
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
    const data = await subjectDetail({
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
