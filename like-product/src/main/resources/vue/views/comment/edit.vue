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
                <el-form-item label="sku_id" prop="skuId">
                    <el-input v-model="formData.skuId" placeholder="请输入sku_id" />
                </el-form-item>
                <el-form-item label="spu_id" prop="spuId">
                    <el-input v-model="formData.spuId" placeholder="请输入spu_id" />
                </el-form-item>
                <el-form-item label="商品名字" prop="spuName">
                    <el-input v-model="formData.spuName" placeholder="请输入商品名字" />
                </el-form-item>
                <el-form-item label="会员昵称" prop="memberNickName">
                    <el-input v-model="formData.memberNickName" placeholder="请输入会员昵称" />
                </el-form-item>
                <el-form-item label="星级" prop="star">
                    <el-input v-model="formData.star" placeholder="请输入星级" />
                </el-form-item>
                <el-form-item label="会员ip" prop="memberIp">
                    <el-input v-model="formData.memberIp" placeholder="请输入会员ip" />
                </el-form-item>
                <el-form-item label="显示状态[0-不显示，1-显示]" prop="showStatus">
                    <el-radio-group v-model="formData.showStatus" placeholder="请选择显示状态[0-不显示，1-显示]">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="购买时属性组合" prop="spuAttributes">
                    <el-input v-model="formData.spuAttributes" placeholder="请输入购买时属性组合" />
                </el-form-item>
                <el-form-item label="点赞数" prop="likesCount">
                    <el-input v-model="formData.likesCount" placeholder="请输入点赞数" />
                </el-form-item>
                <el-form-item label="回复数" prop="replyCount">
                    <el-input v-model="formData.replyCount" placeholder="请输入回复数" />
                </el-form-item>
                <el-form-item label="评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]" prop="resources">
                    <el-input
                        v-model="formData.resources"
                        placeholder="请输入评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="内容" prop="content">
                    <editor v-model="formData.content" :height="500" />
                </el-form-item>
                <el-form-item label="用户头像" prop="memberIcon">
                    <el-input v-model="formData.memberIcon" placeholder="请输入用户头像" />
                </el-form-item>
                <el-form-item label="评论类型[0 - 对商品的直接评论，1 - 对评论的回复]" prop="commentType">
                    <el-select class="flex-1" v-model="formData.commentType" placeholder="请选择评论类型[0 - 对商品的直接评论，1 - 对评论的回复]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  commentEdit, commentAdd, commentDetail } from '@/api/comment'
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
    return mode.value == 'edit' ? '编辑商品评价' : '新增商品评价'
})

const formData = reactive({
    id: '',
    skuId: '',
    spuId: '',
    spuName: '',
    memberNickName: '',
    star: '',
    memberIp: '',
    showStatus: '',
    spuAttributes: '',
    likesCount: '',
    replyCount: '',
    resources: '',
    content: '',
    memberIcon: '',
    commentType: '',
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
    spuId: [
        {
            required: true,
            message: '请输入spu_id',
            trigger: ['blur']
        }
    ],
    spuName: [
        {
            required: true,
            message: '请输入商品名字',
            trigger: ['blur']
        }
    ],
    memberNickName: [
        {
            required: true,
            message: '请输入会员昵称',
            trigger: ['blur']
        }
    ],
    star: [
        {
            required: true,
            message: '请输入星级',
            trigger: ['blur']
        }
    ],
    memberIp: [
        {
            required: true,
            message: '请输入会员ip',
            trigger: ['blur']
        }
    ],
    showStatus: [
        {
            required: true,
            message: '请选择显示状态[0-不显示，1-显示]',
            trigger: ['blur']
        }
    ],
    spuAttributes: [
        {
            required: true,
            message: '请输入购买时属性组合',
            trigger: ['blur']
        }
    ],
    likesCount: [
        {
            required: true,
            message: '请输入点赞数',
            trigger: ['blur']
        }
    ],
    replyCount: [
        {
            required: true,
            message: '请输入回复数',
            trigger: ['blur']
        }
    ],
    resources: [
        {
            required: true,
            message: '请输入评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]',
            trigger: ['blur']
        }
    ],
    content: [
        {
            required: true,
            message: '请输入内容',
            trigger: ['blur']
        }
    ],
    memberIcon: [
        {
            required: true,
            message: '请输入用户头像',
            trigger: ['blur']
        }
    ],
    commentType: [
        {
            required: true,
            message: '请选择评论类型[0 - 对商品的直接评论，1 - 对评论的回复]',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await commentEdit(data) : await commentAdd(data)
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
    const data = await commentDetail({
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
