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
                <el-form-item label="order_id" prop="orderId">
                    <el-input-number v-model="formData.orderId" :max="9999" />
                </el-form-item>
                <el-form-item label="退货商品id" prop="skuId">
                    <el-input-number v-model="formData.skuId" :max="9999" />
                </el-form-item>
                <el-form-item label="订单编号" prop="orderSn">
                    <el-input v-model="formData.orderSn" placeholder="请输入订单编号" />
                </el-form-item>
                <el-form-item label="会员用户名" prop="memberUsername">
                    <el-input v-model="formData.memberUsername" placeholder="请输入会员用户名" />
                </el-form-item>
                <el-form-item label="退款金额" prop="returnAmount">
                    <el-input-number v-model="formData.returnAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="退货人姓名" prop="returnName">
                    <el-input v-model="formData.returnName" placeholder="请输入退货人姓名" />
                </el-form-item>
                <el-form-item label="退货人电话" prop="returnPhone">
                    <el-input v-model="formData.returnPhone" placeholder="请输入退货人电话" />
                </el-form-item>
                <el-form-item label="申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]" prop="status">
                    <el-radio-group v-model="formData.status" placeholder="请选择申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]">
                        <el-radio label="0">请选择字典生成</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="处理时间" prop="handleTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.handleTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择处理时间"
                    />
                </el-form-item>
                <el-form-item label="商品图片" prop="skuImg">
                    <el-input
                        v-model="formData.skuImg"
                        placeholder="请输入商品图片"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="商品名称" prop="skuName">
                    <el-input v-model="formData.skuName" placeholder="请输入商品名称" />
                </el-form-item>
                <el-form-item label="商品品牌" prop="skuBrand">
                    <el-input v-model="formData.skuBrand" placeholder="请输入商品品牌" />
                </el-form-item>
                <el-form-item label="商品销售属性(JSON)" prop="skuAttrsVals">
                    <el-input
                        v-model="formData.skuAttrsVals"
                        placeholder="请输入商品销售属性(JSON)"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="退货数量" prop="skuCount">
                    <el-input-number v-model="formData.skuCount" :max="9999" />
                </el-form-item>
                <el-form-item label="商品单价" prop="skuPrice">
                    <el-input-number v-model="formData.skuPrice" :max="9999" />
                </el-form-item>
                <el-form-item label="商品实际支付单价" prop="skuRealPrice">
                    <el-input-number v-model="formData.skuRealPrice" :max="9999" />
                </el-form-item>
                <el-form-item label="原因" prop="reason">
                    <el-input v-model="formData.reason" placeholder="请输入原因" />
                </el-form-item>
                <el-form-item label="描述" prop="description述">
                    <el-input
                        v-model="formData.description述"
                        placeholder="请输入描述"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="凭证图片，以逗号隔开" prop="descPics">
                    <el-input
                        v-model="formData.descPics"
                        placeholder="请输入凭证图片，以逗号隔开"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="处理备注" prop="handleNote">
                    <el-input
                        v-model="formData.handleNote"
                        placeholder="请输入处理备注"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="处理人员" prop="handleMan">
                    <el-input v-model="formData.handleMan" placeholder="请输入处理人员" />
                </el-form-item>
                <el-form-item label="收货人" prop="receiveMan">
                    <el-input v-model="formData.receiveMan" placeholder="请输入收货人" />
                </el-form-item>
                <el-form-item label="收货时间" prop="receiveTime">
                    <el-date-picker
                        class="flex-1 !flex"
                        v-model="formData.receiveTime"
                        type="datetime"
                        clearable
                        value-format="YYYY-MM-DD hh:mm:ss"
                        placeholder="请选择收货时间"
                    />
                </el-form-item>
                <el-form-item label="收货备注" prop="receiveNote">
                    <el-input
                        v-model="formData.receiveNote"
                        placeholder="请输入收货备注"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="收货电话" prop="receivePhone">
                    <el-input v-model="formData.receivePhone" placeholder="请输入收货电话" />
                </el-form-item>
                <el-form-item label="公司收货地址" prop="companyAddress">
                    <el-input
                        v-model="formData.companyAddress"
                        placeholder="请输入公司收货地址"
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
import {  applyEdit, applyAdd, applyDetail } from '@/api/order/apply'
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
    return mode.value == 'edit' ? '编辑订单退货申请' : '新增订单退货申请'
})

const formData = reactive({
    id: '',
    orderId: 0,
    skuId: 0,
    orderSn: '',
    memberUsername: '',
    returnAmount: 0,
    returnName: '',
    returnPhone: '',
    status: '',
    handleTime: '',
    skuImg: '',
    skuName: '',
    skuBrand: '',
    skuAttrsVals: '',
    skuCount: 0,
    skuPrice: 0,
    skuRealPrice: 0,
    reason: '',
    description述: '',
    descPics: '',
    handleNote: '',
    handleMan: '',
    receiveMan: '',
    receiveTime: '',
    receiveNote: '',
    receivePhone: '',
    companyAddress: '',
})

const formRules = {
    id: [
        {
            required: true,
            message: '请输入id',
            trigger: ['blur']
        }
    ],
    orderId: [
        {
            required: true,
            message: '请输入order_id',
            trigger: ['blur']
        }
    ],
    skuId: [
        {
            required: true,
            message: '请输入退货商品id',
            trigger: ['blur']
        }
    ],
    orderSn: [
        {
            required: true,
            message: '请输入订单编号',
            trigger: ['blur']
        }
    ],
    memberUsername: [
        {
            required: true,
            message: '请输入会员用户名',
            trigger: ['blur']
        }
    ],
    returnAmount: [
        {
            required: true,
            message: '请输入退款金额',
            trigger: ['blur']
        }
    ],
    returnName: [
        {
            required: true,
            message: '请输入退货人姓名',
            trigger: ['blur']
        }
    ],
    returnPhone: [
        {
            required: true,
            message: '请输入退货人电话',
            trigger: ['blur']
        }
    ],
    status: [
        {
            required: true,
            message: '请选择申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]',
            trigger: ['blur']
        }
    ],
    handleTime: [
        {
            required: true,
            message: '请选择处理时间',
            trigger: ['blur']
        }
    ],
    skuImg: [
        {
            required: true,
            message: '请输入商品图片',
            trigger: ['blur']
        }
    ],
    skuName: [
        {
            required: true,
            message: '请输入商品名称',
            trigger: ['blur']
        }
    ],
    skuBrand: [
        {
            required: true,
            message: '请输入商品品牌',
            trigger: ['blur']
        }
    ],
    skuAttrsVals: [
        {
            required: true,
            message: '请输入商品销售属性(JSON)',
            trigger: ['blur']
        }
    ],
    skuCount: [
        {
            required: true,
            message: '请输入退货数量',
            trigger: ['blur']
        }
    ],
    skuPrice: [
        {
            required: true,
            message: '请输入商品单价',
            trigger: ['blur']
        }
    ],
    skuRealPrice: [
        {
            required: true,
            message: '请输入商品实际支付单价',
            trigger: ['blur']
        }
    ],
    reason: [
        {
            required: true,
            message: '请输入原因',
            trigger: ['blur']
        }
    ],
    description述: [
        {
            required: true,
            message: '请输入描述',
            trigger: ['blur']
        }
    ],
    descPics: [
        {
            required: true,
            message: '请输入凭证图片，以逗号隔开',
            trigger: ['blur']
        }
    ],
    handleNote: [
        {
            required: true,
            message: '请输入处理备注',
            trigger: ['blur']
        }
    ],
    handleMan: [
        {
            required: true,
            message: '请输入处理人员',
            trigger: ['blur']
        }
    ],
    receiveMan: [
        {
            required: true,
            message: '请输入收货人',
            trigger: ['blur']
        }
    ],
    receiveTime: [
        {
            required: true,
            message: '请选择收货时间',
            trigger: ['blur']
        }
    ],
    receiveNote: [
        {
            required: true,
            message: '请输入收货备注',
            trigger: ['blur']
        }
    ],
    receivePhone: [
        {
            required: true,
            message: '请输入收货电话',
            trigger: ['blur']
        }
    ],
    companyAddress: [
        {
            required: true,
            message: '请输入公司收货地址',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await applyEdit(data) : await applyAdd(data)
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
    const data = await applyDetail({
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
