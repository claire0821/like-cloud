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
                <el-form-item label="分组id" prop="attrGroupId">
                    <el-input v-model="formData.attrGroupId" placeholder="请输入分组id" />
                </el-form-item>
                <el-form-item label="组名" prop="attrGroupName">
                    <el-input v-model="formData.attrGroupName" placeholder="请输入组名" />
                </el-form-item>
                <el-form-item label="排序" prop="sort">
                    <el-input v-model="formData.sort" placeholder="请输入排序" />
                </el-form-item>
                <el-form-item label="描述" prop="descript">
                    <el-input v-model="formData.descript" placeholder="请输入描述" />
                </el-form-item>
                <el-form-item label="组图标" prop="icon">
                    <el-input v-model="formData.icon" placeholder="请输入组图标" />
                </el-form-item>
                <el-form-item label="所属分类id" prop="catelogId">
                    <el-input v-model="formData.catelogId" placeholder="请输入所属分类id" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  groupEdit, groupAdd, groupDetail } from '@/api/group'
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
    return mode.value == 'edit' ? '编辑属性分组' : '新增属性分组'
})

const formData = reactive({
    attrGroupId: '',
    attrGroupName: '',
    sort: '',
    descript: '',
    icon: '',
    catelogId: '',
})

const formRules = {
    attrGroupId: [
        {
            required: true,
            message: '请输入分组id',
            trigger: ['blur']
        }
    ],
    attrGroupName: [
        {
            required: true,
            message: '请输入组名',
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
    descript: [
        {
            required: true,
            message: '请输入描述',
            trigger: ['blur']
        }
    ],
    icon: [
        {
            required: true,
            message: '请输入组图标',
            trigger: ['blur']
        }
    ],
    catelogId: [
        {
            required: true,
            message: '请输入所属分类id',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await groupEdit(data) : await groupAdd(data)
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
    const data = await groupDetail({
        attrGroupId: row.attrGroupId
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
