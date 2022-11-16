<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]" prop="couponType">
                    <el-select
                        v-model="queryParams.couponType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="优惠卷名字" prop="couponName">
                    <el-input class="w-[280px]" v-model="queryParams.couponName" />
                </el-form-item>
                <el-form-item label="开始时间" prop="startTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="结束时间" prop="endTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="使用类型[0->全场通用；1->指定分类；2->指定商品]" prop="useType">
                    <el-select
                        v-model="queryParams.useType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-input class="w-[280px]" v-model="queryParams.note" />
                </el-form-item>
                <el-form-item label="可以领取的开始日期" prop="enableStartTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="可以领取的结束日期" prop="enableEndTime">
                    <daterange-picker
                        v-model:startTime="queryParams.createTimeStart"
                        v-model:endTime="queryParams.createTimeEnd"
                    />
                </el-form-item>
                <el-form-item label="优惠码" prop="code">
                    <el-input class="w-[280px]" v-model="queryParams.code" />
                </el-form-item>
                <el-form-item label="可以领取的会员等级[0->不限等级，其他-对应等级]" prop="memberLevel">
                    <template #default="scope">
                        <el-icon v-if="scope.row.memberLevel == 1"><SuccessFilled /></el-icon>
                        <el-icon v-else><CircleCloseFilled /></el-icon>
                    </template>
                </el-form-item>
                <el-form-item label="发布状态[0-未发布，1-已发布]" prop="publish">
                    <template #default="scope">
                        <el-icon v-if="scope.row.publish == 1"><SuccessFilled /></el-icon>
                        <el-icon v-else><CircleCloseFilled /></el-icon>
                    </template>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['coupon:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]" prop="couponType" min-width="100" header-align="center" align="center"/>
                <el-table-column label="优惠券图片" prop="couponImg" min-width="100" header-align="center" align="center"/>
                <el-table-column label="优惠卷名字" prop="couponName" min-width="100" header-align="center" align="center"/>
                <el-table-column label="数量" prop="num" min-width="100" header-align="center" align="center"/>
                <el-table-column label="金额" prop="amount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="每人限领张数" prop="perLimit" min-width="100" header-align="center" align="center"/>
                <el-table-column label="使用门槛" prop="minPoint" min-width="100" header-align="center" align="center"/>
                <el-table-column label="开始时间" prop="startTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="结束时间" prop="endTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="使用类型[0->全场通用；1->指定分类；2->指定商品]" prop="useType" min-width="100" header-align="center" align="center"/>
                <el-table-column label="备注" prop="note" min-width="100" header-align="center" align="center"/>
                <el-table-column label="发行数量" prop="publishCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="已使用数量" prop="useCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="领取数量" prop="receiveCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="可以领取的开始日期" prop="enableStartTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="可以领取的结束日期" prop="enableEndTime" min-width="100" header-align="center" align="center"/>
                <el-table-column label="优惠码" prop="code" min-width="100" header-align="center" align="center"/>
                <el-table-column label="可以领取的会员等级[0->不限等级，其他-对应等级]" prop="memberLevel" min-width="100" header-align="center" align="center"/>
                <el-table-column label="发布状态[0-未发布，1-已发布]" prop="publish" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['coupon:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['coupon:del']"
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
<script lang="ts" setup name="coupon">
import { couponDeleteBatch, couponLists } from '@/api/coupon/coupon'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './coupon_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    couponType: '',
    couponImg: '',
    couponName: '',
    num: '',
    amount: '',
    perLimit: '',
    minPoint: '',
    startTimeStart: '',
    startTimeEnd: '',
    endTimeStart: '',
    endTimeEnd: '',
    useType: '',
    note: '',
    publishCount: '',
    useCount: '',
    receiveCount: '',
    enableStartTimeStart: '',
    enableStartTimeEnd: '',
    enableEndTimeStart: '',
    enableEndTimeEnd: '',
    code: '',
    memberLevel: '',
    publish: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: couponLists,
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
    await couponDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
