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
                <el-form-item label="会员id" prop="memberId">
                    <el-input-number v-model="formData.memberId" :max="9999" />
                </el-form-item>
                <el-form-item label="累计消费金额" prop="consumeAmount">
                    <el-input-number v-model="formData.consumeAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="累计优惠金额" prop="couponAmount">
                    <el-input-number v-model="formData.couponAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="订单数量" prop="orderCount">
                    <el-input-number v-model="formData.orderCount" :max="9999" />
                </el-form-item>
                <el-form-item label="优惠券数量" prop="couponCount">
                    <el-input-number v-model="formData.couponCount" :max="9999" />
                </el-form-item>
                <el-form-item label="评价数" prop="commentCount">
                    <el-input-number v-model="formData.commentCount" :max="9999" />
                </el-form-item>
                <el-form-item label="退货数量" prop="returnOrderCount">
                    <el-input-number v-model="formData.returnOrderCount" :max="9999" />
                </el-form-item>
                <el-form-item label="登录次数" prop="loginCount">
                    <el-input-number v-model="formData.loginCount" :max="9999" />
                </el-form-item>
                <el-form-item label="关注数量" prop="attendCount">
                    <el-input-number v-model="formData.attendCount" :max="9999" />
                </el-form-item>
                <el-form-item label="粉丝数量" prop="fansCount">
                    <el-input-number v-model="formData.fansCount" :max="9999" />
                </el-form-item>
                <el-form-item label="收藏的商品数量" prop="collectProductCount">
                    <el-input-number v-model="formData.collectProductCount" :max="9999" />
                </el-form-item>
                <el-form-item label="收藏的专题活动数量" prop="collectSubjectCount">
                    <el-input-number v-model="formData.collectSubjectCount" :max="9999" />
                </el-form-item>
                <el-form-item label="收藏的评论数量" prop="collectCommentCount">
                    <el-input-number v-model="formData.collectCommentCount" :max="9999" />
                </el-form-item>
                <el-form-item label="邀请的朋友数量" prop="inviteFriendCount">
                    <el-input-number v-model="formData.inviteFriendCount" :max="9999" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  infoEdit, infoAdd, infoDetail } from '@/api/member/info'
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
    return mode.value == 'edit' ? '编辑会员统计信息' : '新增会员统计信息'
})

const formData = reactive({
    id: '',
    memberId: 0,
    consumeAmount: 0,
    couponAmount: 0,
    orderCount: 0,
    couponCount: 0,
    commentCount: 0,
    returnOrderCount: 0,
    loginCount: 0,
    attendCount: 0,
    fansCount: 0,
    collectProductCount: 0,
    collectSubjectCount: 0,
    collectCommentCount: 0,
    inviteFriendCount: 0,
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
            message: '请输入会员id',
            trigger: ['blur']
        }
    ],
    consumeAmount: [
        {
            required: true,
            message: '请输入累计消费金额',
            trigger: ['blur']
        }
    ],
    couponAmount: [
        {
            required: true,
            message: '请输入累计优惠金额',
            trigger: ['blur']
        }
    ],
    orderCount: [
        {
            required: true,
            message: '请输入订单数量',
            trigger: ['blur']
        }
    ],
    couponCount: [
        {
            required: true,
            message: '请输入优惠券数量',
            trigger: ['blur']
        }
    ],
    commentCount: [
        {
            required: true,
            message: '请输入评价数',
            trigger: ['blur']
        }
    ],
    returnOrderCount: [
        {
            required: true,
            message: '请输入退货数量',
            trigger: ['blur']
        }
    ],
    loginCount: [
        {
            required: true,
            message: '请输入登录次数',
            trigger: ['blur']
        }
    ],
    attendCount: [
        {
            required: true,
            message: '请输入关注数量',
            trigger: ['blur']
        }
    ],
    fansCount: [
        {
            required: true,
            message: '请输入粉丝数量',
            trigger: ['blur']
        }
    ],
    collectProductCount: [
        {
            required: true,
            message: '请输入收藏的商品数量',
            trigger: ['blur']
        }
    ],
    collectSubjectCount: [
        {
            required: true,
            message: '请输入收藏的专题活动数量',
            trigger: ['blur']
        }
    ],
    collectCommentCount: [
        {
            required: true,
            message: '请输入收藏的评论数量',
            trigger: ['blur']
        }
    ],
    inviteFriendCount: [
        {
            required: true,
            message: '请输入邀请的朋友数量',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await infoEdit(data) : await infoAdd(data)
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
    const data = await infoDetail({
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
