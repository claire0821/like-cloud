<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['info:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="会员id" prop="memberId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="累计消费金额" prop="consumeAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="累计优惠金额" prop="couponAmount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="订单数量" prop="orderCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="优惠券数量" prop="couponCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="评价数" prop="commentCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="退货数量" prop="returnOrderCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="登录次数" prop="loginCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="关注数量" prop="attendCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="粉丝数量" prop="fansCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收藏的商品数量" prop="collectProductCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收藏的专题活动数量" prop="collectSubjectCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收藏的评论数量" prop="collectCommentCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="邀请的朋友数量" prop="inviteFriendCount" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['info:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['info:del']"
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
<script lang="ts" setup name="info">
import { infoDeleteBatch, infoLists } from '@/api/member/info'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './info_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
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
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: infoLists,
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
    await infoDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
