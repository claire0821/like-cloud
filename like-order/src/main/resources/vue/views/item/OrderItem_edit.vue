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
                <el-form-item label="order_sn" prop="orderSn">
                    <el-input v-model="formData.orderSn" placeholder="请输入order_sn" />
                </el-form-item>
                <el-form-item label="spu_id" prop="spuId">
                    <el-input-number v-model="formData.spuId" :max="9999" />
                </el-form-item>
                <el-form-item label="spu_name" prop="spuName">
                    <el-input v-model="formData.spuName" placeholder="请输入spu_name" />
                </el-form-item>
                <el-form-item label="spu_pic" prop="spuPic">
                    <el-input
                        v-model="formData.spuPic"
                        placeholder="请输入spu_pic"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="品牌" prop="spuBrand">
                    <el-input v-model="formData.spuBrand" placeholder="请输入品牌" />
                </el-form-item>
                <el-form-item label="商品分类id" prop="categoryId">
                    <el-input-number v-model="formData.categoryId" :max="9999" />
                </el-form-item>
                <el-form-item label="商品sku编号" prop="skuId">
                    <el-input-number v-model="formData.skuId" :max="9999" />
                </el-form-item>
                <el-form-item label="商品sku名字" prop="skuName">
                    <el-input v-model="formData.skuName" placeholder="请输入商品sku名字" />
                </el-form-item>
                <el-form-item label="商品sku图片" prop="skuPic">
                    <el-input
                        v-model="formData.skuPic"
                        placeholder="请输入商品sku图片"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="商品sku价格" prop="skuPrice">
                    <el-input-number v-model="formData.skuPrice" :max="9999" />
                </el-form-item>
                <el-form-item label="商品购买的数量" prop="skuQuantity">
                    <el-input-number v-model="formData.skuQuantity" :max="9999" />
                </el-form-item>
                <el-form-item label="商品销售属性组合（JSON）" prop="skuAttrsVals">
                    <el-input
                        v-model="formData.skuAttrsVals"
                        placeholder="请输入商品销售属性组合（JSON）"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    />
                </el-form-item>
                <el-form-item label="商品促销分解金额" prop="promotionAmount">
                    <el-input-number v-model="formData.promotionAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="优惠券优惠分解金额" prop="couponAmount">
                    <el-input-number v-model="formData.couponAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="积分优惠分解金额" prop="integrationAmount">
                    <el-input-number v-model="formData.integrationAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="该商品经过优惠后的分解金额" prop="realAmount">
                    <el-input-number v-model="formData.realAmount" :max="9999" />
                </el-form-item>
                <el-form-item label="赠送积分" prop="giftIntegration">
                    <el-input-number v-model="formData.giftIntegration" :max="9999" />
                </el-form-item>
                <el-form-item label="赠送成长值" prop="giftGrowth">
                    <el-input-number v-model="formData.giftGrowth" :max="9999" />
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import {  itemEdit, itemAdd, itemDetail } from '@/api/order/item'
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
    return mode.value == 'edit' ? '编辑订单项信息' : '新增订单项信息'
})

const formData = reactive({
    id: '',
    orderId: 0,
    orderSn: '',
    spuId: 0,
    spuName: '',
    spuPic: '',
    spuBrand: '',
    categoryId: 0,
    skuId: 0,
    skuName: '',
    skuPic: '',
    skuPrice: 0,
    skuQuantity: 0,
    skuAttrsVals: '',
    promotionAmount: 0,
    couponAmount: 0,
    integrationAmount: 0,
    realAmount: 0,
    giftIntegration: 0,
    giftGrowth: 0,
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
    orderSn: [
        {
            required: true,
            message: '请输入order_sn',
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
            message: '请输入spu_name',
            trigger: ['blur']
        }
    ],
    spuPic: [
        {
            required: true,
            message: '请输入spu_pic',
            trigger: ['blur']
        }
    ],
    spuBrand: [
        {
            required: true,
            message: '请输入品牌',
            trigger: ['blur']
        }
    ],
    categoryId: [
        {
            required: true,
            message: '请输入商品分类id',
            trigger: ['blur']
        }
    ],
    skuId: [
        {
            required: true,
            message: '请输入商品sku编号',
            trigger: ['blur']
        }
    ],
    skuName: [
        {
            required: true,
            message: '请输入商品sku名字',
            trigger: ['blur']
        }
    ],
    skuPic: [
        {
            required: true,
            message: '请输入商品sku图片',
            trigger: ['blur']
        }
    ],
    skuPrice: [
        {
            required: true,
            message: '请输入商品sku价格',
            trigger: ['blur']
        }
    ],
    skuQuantity: [
        {
            required: true,
            message: '请输入商品购买的数量',
            trigger: ['blur']
        }
    ],
    skuAttrsVals: [
        {
            required: true,
            message: '请输入商品销售属性组合（JSON）',
            trigger: ['blur']
        }
    ],
    promotionAmount: [
        {
            required: true,
            message: '请输入商品促销分解金额',
            trigger: ['blur']
        }
    ],
    couponAmount: [
        {
            required: true,
            message: '请输入优惠券优惠分解金额',
            trigger: ['blur']
        }
    ],
    integrationAmount: [
        {
            required: true,
            message: '请输入积分优惠分解金额',
            trigger: ['blur']
        }
    ],
    realAmount: [
        {
            required: true,
            message: '请输入该商品经过优惠后的分解金额',
            trigger: ['blur']
        }
    ],
    giftIntegration: [
        {
            required: true,
            message: '请输入赠送积分',
            trigger: ['blur']
        }
    ],
    giftGrowth: [
        {
            required: true,
            message: '请输入赠送成长值',
            trigger: ['blur']
        }
    ],
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    const data: any = { ...formData }
    mode.value == 'edit' ? await itemEdit(data) : await itemAdd(data)
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
    const data = await itemDetail({
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
