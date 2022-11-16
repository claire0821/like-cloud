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
                <el-form-item label="优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]" prop="couponType">
                    <el-select class="flex-1" v-model="formData.couponType" placeholder="请选择优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="优惠券图片" prop="couponImg">
                    <el-input
                        v-model="formData.couponImg"
                        placeholder="请输入优惠券图片"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="优惠卷名字" prop="couponName">
                    <el-input v-model="formData.couponName" placeholder="请输入优惠卷名字" />
                </el-form-item>
                <el-form-item label="数量" prop="num">
                    <el-input-number v-model="formData.num" :max="9999" />
                </el-form-item>
                <el-form-item label="金额" prop="amount">
                    <el-input-number v-model="formData.amount" :max="9999" />
                </el-form-item>
                <el-form-item label="每人限领张数" prop="perLimit">
                    <el-input-number v-model="formData.perLimit" :max="9999" />
                </el-form-item>
                <el-form-item label="使用门槛" prop="minPoint">
                    <el-input-number v-model="formData.minPoint" :max="9999" />
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
                <el-form-item label="使用类型[0->全场通用；1->指定分类；2->指定商品]" prop="useType">
                    <el-select class="flex-1" v-model="formData.useType" placeholder="请选择使用类型[0->全场通用；1->指定分类；2->指定商品]">
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-input v-model="formData.note" placeholder="请输入备注" />
                </el-form-item>
                <el-form-item label="发行数量" prop="publishCount">
                    <el-input-number v-model="formData.publishCount" :max="9999" />
                </el-form-item>
                <el-form-item label="已使用数量" prop="useCount">
                    <el-input-number v-model="formData.useCount" :max="9999" />
                </el-form-item>
                <el-form-item label="领取数量" prop="receiveCount">
                    <el-input-number v-model="formData.receiveCount" :max="9999" />
                </el-form-item>
                <el-form-item label="可以领取的开始日期" prop="enableStartTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.enableStartTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择可以领取的开始日期"
                    />
                </el-form-item>
                <el-form-item label="可以领取的结束日期" prop="enableEndTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.enableEndTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择可以领取的结束日期"
                    />
                </el-form-item>
                <el-form-item label="优惠码" prop="code">
                    <el-input v-model="formData.code" placeholder="请输入优惠码" />
                </el-form-item>
                <el-form-item label="可以领取的会员等级[0->不限等级，其他-对应等级]" prop="memberLevel">
                   <el-checkbox v-model="formData.memberLevel" :true-label="1" :false-label="0" placeholder="请选择可以领取的会员等级[0->不限等级，其他-对应等级]"></el-checkbox>
                </el-form-item>
                <el-form-item label="发布状态[0-未发布，1-已发布]" prop="publish">
                   <el-checkbox v-model="formData.publish" :true-label="1" :false-label="0" placeholder="请选择发布状态[0-未发布，1-已发布]"></el-checkbox>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  couponEdit, couponAdd, couponDetail } from '@/api/coupon/coupon'
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
    return mode.value == 'edit' ? '编辑优惠券信息' : '新增优惠券信息'
})

const formData = reactive({
    id: '',
    couponType: '',
    couponImg: '',
    couponName: '',
    num: 0,
    amount: 0,
    perLimit: 0,
    minPoint: 0,
    startTime: '',
    endTime: '',
    useType: '',
    note: '',
    publishCount: 0,
    useCount: 0,
    receiveCount: 0,
    enableStartTime: '',
    enableEndTime: '',
    code: '',
    memberLevel: [],
    publish: [],
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    couponType: [
        {
            required: true,
            message: '请选择优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]',
            trigger: ['blur']
        }
    ],
    couponImg: [
        {
            required: true,
            message: '请输入优惠券图片',
            trigger: ['blur']
        }
    ],
    couponName: [
        {
            required: true,
            message: '请输入优惠卷名字',
            trigger: ['blur']
        }
    ],
    num: [
        {
            required: true,
            message: '请输入数量',
            trigger: ['blur']
        }
    ],
    amount: [
        {
            required: true,
            message: '请输入金额',
            trigger: ['blur']
        }
    ],
    perLimit: [
        {
            required: true,
            message: '请输入每人限领张数',
            trigger: ['blur']
        }
    ],
    minPoint: [
        {
            required: true,
            message: '请输入使用门槛',
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
    useType: [
        {
            required: true,
            message: '请选择使用类型[0->全场通用；1->指定分类；2->指定商品]',
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
    publishCount: [
        {
            required: true,
            message: '请输入发行数量',
            trigger: ['blur']
        }
    ],
    useCount: [
        {
            required: true,
            message: '请输入已使用数量',
            trigger: ['blur']
        }
    ],
    receiveCount: [
        {
            required: true,
            message: '请输入领取数量',
            trigger: ['blur']
        }
    ],
    enableStartTime: [
        {
            required: true,
            message: '请选择可以领取的开始日期',
            trigger: ['blur']
        }
    ],
    enableEndTime: [
        {
            required: true,
            message: '请选择可以领取的结束日期',
            trigger: ['blur']
        }
    ],
    code: [
        {
            required: true,
            message: '请输入优惠码',
            trigger: ['blur']
        }
    ],
    memberLevel: [
        {
            required: true,
            message: '请选择可以领取的会员等级[0->不限等级，其他-对应等级]',
            trigger: ['blur']
        }
    ],
    publish: [
        {
            required: true,
            message: '请选择发布状态[0-未发布，1-已发布]',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    data.memberLevel = data.memberLevel.join(',')
    data.publish = data.publish.join(',')
    mode.value == 'edit' ? await couponEdit(data) : await couponAdd(data)
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
            formData.memberLevel = String(data.memberLevel).split(',')
            //@ts-ignore
            formData.publish = String(data.publish).split(',')
        }
    }
}

const getDetail = async (row: Record<string, any>) => {
    const data = await couponDetail({
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
