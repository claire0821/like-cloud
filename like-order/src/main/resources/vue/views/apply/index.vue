<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="订单编号" prop="orderSn">
                    <el-input class="w-[280px]" v-model="queryParams.orderSn" />
                </el-form-item>
                <el-form-item label="会员用户名" prop="memberUsername">
                    <el-input class="w-[280px]" v-model="queryParams.memberUsername" />
                </el-form-item>
                <el-form-item label="退货人姓名" prop="returnName">
                    <el-input class="w-[280px]" v-model="queryParams.returnName" />
                </el-form-item>
                <el-form-item label="退货人电话" prop="returnPhone">
                    <el-input class="w-[280px]" v-model="queryParams.returnPhone" />
                </el-form-item>
                <el-form-item label="申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]" prop="status">
                    <el-select
                        v-model="queryParams.status"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="处理时间" prop="handleTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="商品名称" prop="skuName">
                    <el-input class="w-[280px]" v-model="queryParams.skuName" />
                </el-form-item>
                <el-form-item label="商品品牌" prop="skuBrand">
                    <el-input class="w-[280px]" v-model="queryParams.skuBrand" />
                </el-form-item>
                <el-form-item label="原因" prop="reason">
                    <el-input class="w-[280px]" v-model="queryParams.reason" />
                </el-form-item>
                <el-form-item label="处理人员" prop="handleMan">
                    <el-input class="w-[280px]" v-model="queryParams.handleMan" />
                </el-form-item>
                <el-form-item label="收货人" prop="receiveMan">
                    <el-input class="w-[280px]" v-model="queryParams.receiveMan" />
                </el-form-item>
                <el-form-item label="收货时间" prop="receiveTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="收货电话" prop="receivePhone">
                    <el-input class="w-[280px]" v-model="queryParams.receivePhone" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['apply:add']" type="primary" @click="handleAdd()">
                        <template #icon>
                            <icon name="el-icon-Plus" />
                        </template>
                    新增
                </el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <el-table
                class="mt-4"
                size="large"
                v-loading="pager.loading"
                :data="pager.lists"
                @selection-change="handleSelectionChange"
            >
            <el-table-column type="selection" width="55" header-align="center" align="center"/>
                <el-table-column label="order_id" prop="orderId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退货商品id" prop="skuId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订单编号" prop="orderSn" min-width="100" header-align="center" align="center"/>
                <el-table-column label="申请时间" prop="createTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="会员用户名" prop="memberUsername" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退款金额" prop="returnAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退货人姓名" prop="returnName" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退货人电话" prop="returnPhone" min-width="100" header-align="center" align="center"/>
                <el-table-column label="申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]" prop="status" min-width="100" header-align="center" align="center"/>
                <el-table-column label="处理时间" prop="handleTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品图片" prop="skuImg" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品名称" prop="skuName" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品品牌" prop="skuBrand" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品销售属性(JSON)" prop="skuAttrsVals" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退货数量" prop="skuCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品单价" prop="skuPrice" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品实际支付单价" prop="skuRealPrice" min-width="100" header-align="center" align="center"/>
                <el-table-column label="原因" prop="reason" min-width="100" header-align="center" align="center"/>
                <el-table-column label="描述" prop="description述" min-width="100" header-align="center" align="center"/>
                <el-table-column label="凭证图片，以逗号隔开" prop="descPics" min-width="100" header-align="center" align="center"/>
                <el-table-column label="处理备注" prop="handleNote" min-width="100" header-align="center" align="center"/>
                <el-table-column label="处理人员" prop="handleMan" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货人" prop="receiveMan" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货时间" prop="receiveTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货备注" prop="receiveNote" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货电话" prop="receivePhone" min-width="100" header-align="center" align="center"/>
                <el-table-column label="公司收货地址" prop="companyAddress" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['apply:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['apply:del']"
                            type="danger"
                            link
                            @click="handleDelete([row.id])"
                        >
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="flex justify-end mt-4">
                <pagination v-model="pager" @change="getLists" />
            </div>
        </el-card>
        <edit-popup
            v-if="showEdit"
            ref="editRef"
            @success="getLists"
            @close="showEdit = false"
        />
    </div>
</template>
<script lang="ts" setup name="apply">
import { applyDeleteBatch, applyLists } from '@/api/order/apply'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './apply_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    orderId: '',
    skuId: '',
    orderSn: '',
    memberUsername: '',
    returnAmount: '',
    returnName: '',
    returnPhone: '',
    status: '',
    handleTimeStart: '',
    handleTimeEnd: '',
    skuImg: '',
    skuName: '',
    skuBrand: '',
    skuAttrsVals: '',
    skuCount: '',
    skuPrice: '',
    skuRealPrice: '',
    reason: '',
    description述: '',
    descPics: '',
    handleNote: '',
    handleMan: '',
    receiveMan: '',
    receiveTimeStart: '',
    receiveTimeEnd: '',
    receiveNote: '',
    receivePhone: '',
    companyAddress: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: applyLists,
    params: queryParams
})


const handleAdd = async () => {
    showEdit.value = true
    await nextTick()
    editRef.value?.open('add')
}

const handleEdit = async (data: any) => {
    showEdit.value = true
    await nextTick()
    editRef.value?.open('edit')
    editRef.value?.getDetail(data)
}
const handleSelectionChange = (val: any[]) => {
    selectData.value = val.map(({ id }) => id)
}
//批量删除
const handleDelete = async (ids: any[] | number) => {
    await feedback.confirm('确定要删除？')
    await applyDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
