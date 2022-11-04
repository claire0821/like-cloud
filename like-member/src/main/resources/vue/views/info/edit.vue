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
                <el-form-item label="会员id" prop="memberId">
                    <el-input v-model="formData.memberId" placeholder="请输入会员id" />
                </el-form-item>
                <el-form-item label="累计消费金额" prop="consumeAmount">
                    <el-input v-model="formData.consumeAmount" placeholder="请输入累计消费金额" />
                </el-form-item>
                <el-form-item label="累计优惠金额" prop="couponAmount">
                    <el-input v-model="formData.couponAmount" placeholder="请输入累计优惠金额" />
                </el-form-item>
                <el-form-item label="订单数量" prop="orderCount">
                    <el-input v-model="formData.orderCount" placeholder="请输入订单数量" />
                </el-form-item>
                <el-form-item label="优惠券数量" prop="couponCount">
                    <el-input v-model="formData.couponCount" placeholder="请输入优惠券数量" />
                </el-form-item>
                <el-form-item label="评价数" prop="commentCount">
                    <el-input v-model="formData.commentCount" placeholder="请输入评价数" />
                </el-form-item>
                <el-form-item label="退货数量" prop="returnOrderCount">
                    <el-input v-model="formData.returnOrderCount" placeholder="请输入退货数量" />
                </el-form-item>
                <el-form-item label="登录次数" prop="loginCount">
                    <el-input v-model="formData.loginCount" placeholder="请输入登录次数" />
                </el-form-item>
                <el-form-item label="关注数量" prop="attendCount">
                    <el-input v-model="formData.attendCount" placeholder="请输入关注数量" />
                </el-form-item>
                <el-form-item label="粉丝数量" prop="fansCount">
                    <el-input v-model="formData.fansCount" placeholder="请输入粉丝数量" />
                </el-form-item>
                <el-form-item label="收藏的商品数量" prop="collectProductCount">
                    <el-input v-model="formData.collectProductCount" placeholder="请输入收藏的商品数量" />
                </el-form-item>
                <el-form-item label="收藏的专题活动数量" prop="collectSubjectCount">
                    <el-input v-model="formData.collectSubjectCount" placeholder="请输入收藏的专题活动数量" />
                </el-form-item>
                <el-form-item label="收藏的评论数量" prop="collectCommentCount">
                    <el-input v-model="formData.collectCommentCount" placeholder="请输入收藏的评论数量" />
                </el-form-item>
                <el-form-item label="邀请的朋友数量" prop="inviteFriendCount">
                    <el-input v-model="formData.inviteFriendCount" placeholder="请输入邀请的朋友数量" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  infoEdit, infoAdd, infoDetail } from '@/api/info'
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
    memberId: '',
    consumeAmount: '',
    couponAmount: '',
    orderCount: '',
    couponCount: '',
    commentCount: '',
    returnOrderCount: '',
    loginCount: '',
    attendCount: '',
    fansCount: '',
    collectProductCount: '',
    collectSubjectCount: '',
    collectCommentCount: '',
    inviteFriendCount: '',
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
