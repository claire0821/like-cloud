<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="order_sn" prop="orderSn">
                    <el-input class="w-[280px]" v-model="queryParams.orderSn" />
                </el-form-item>
                <el-form-item label="spu_name" prop="spuName">
                    <el-input class="w-[280px]" v-model="queryParams.spuName" />
                </el-form-item>
                <el-form-item label="品牌" prop="spuBrand">
                    <el-input class="w-[280px]" v-model="queryParams.spuBrand" />
                </el-form-item>
                <el-form-item label="商品sku名字" prop="skuName">
                    <el-input class="w-[280px]" v-model="queryParams.skuName" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['item:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="order_sn" prop="orderSn" min-width="100" header-align="center" align="center"/>
                <el-table-column label="spu_id" prop="spuId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="spu_name" prop="spuName" min-width="100" header-align="center" align="center"/>
                <el-table-column label="spu_pic" prop="spuPic" min-width="100" header-align="center" align="center"/>
                <el-table-column label="品牌" prop="spuBrand" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品分类id" prop="categoryId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品sku编号" prop="skuId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品sku名字" prop="skuName" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品sku图片" prop="skuPic" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品sku价格" prop="skuPrice" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品购买的数量" prop="skuQuantity" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品销售属性组合（JSON）" prop="skuAttrsVals" min-width="100" header-align="center" align="center"/>
                <el-table-column label="商品促销分解金额" prop="promotionAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="优惠券优惠分解金额" prop="couponAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="积分优惠分解金额" prop="integrationAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="该商品经过优惠后的分解金额" prop="realAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="赠送积分" prop="giftIntegration" min-width="100" header-align="center" align="center"/>
                <el-table-column label="赠送成长值" prop="giftGrowth" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['item:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['item:del']"
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
<script lang="ts" setup name="item">
import { itemDeleteBatch, itemLists } from '@/api/order/item'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './item_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    orderId: '',
    orderSn: '',
    spuId: '',
    spuName: '',
    spuPic: '',
    spuBrand: '',
    categoryId: '',
    skuId: '',
    skuName: '',
    skuPic: '',
    skuPrice: '',
    skuQuantity: '',
    skuAttrsVals: '',
    promotionAmount: '',
    couponAmount: '',
    integrationAmount: '',
    realAmount: '',
    giftIntegration: '',
    giftGrowth: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: itemLists,
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
    await itemDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
