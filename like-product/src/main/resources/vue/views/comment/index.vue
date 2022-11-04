<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="sku_id" prop="skuId">
                    <el-input class="w-[280px]" v-model="queryParams.skuId" />
                </el-form-item>
                <el-form-item label="spu_id" prop="spuId">
                    <el-input class="w-[280px]" v-model="queryParams.spuId" />
                </el-form-item>
                <el-form-item label="商品名字" prop="spuName">
                    <el-input class="w-[280px]" v-model="queryParams.spuName" />
                </el-form-item>
                <el-form-item label="会员昵称" prop="memberNickName">
                    <el-input class="w-[280px]" v-model="queryParams.memberNickName" />
                </el-form-item>
                <el-form-item label="星级" prop="star">
                    <el-input class="w-[280px]" v-model="queryParams.star" />
                </el-form-item>
                <el-form-item label="会员ip" prop="memberIp">
                    <el-input class="w-[280px]" v-model="queryParams.memberIp" />
                </el-form-item>
                <el-form-item label="显示状态[0-不显示，1-显示]" prop="showStatus">
                    <el-select
                        v-model="queryParams.showStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="购买时属性组合" prop="spuAttributes">
                    <el-input class="w-[280px]" v-model="queryParams.spuAttributes" />
                </el-form-item>
                <el-form-item label="点赞数" prop="likesCount">
                    <el-input class="w-[280px]" v-model="queryParams.likesCount" />
                </el-form-item>
                <el-form-item label="回复数" prop="replyCount">
                    <el-input class="w-[280px]" v-model="queryParams.replyCount" />
                </el-form-item>
                <el-form-item label="用户头像" prop="memberIcon">
                    <el-input class="w-[280px]" v-model="queryParams.memberIcon" />
                </el-form-item>
                <el-form-item label="评论类型[0 - 对商品的直接评论，1 - 对评论的回复]" prop="commentType">
                    <el-select
                        v-model="queryParams.commentType"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['comment:add']" type="primary" @click="handleAdd()">
                    <template #icon>
                        <icon name="el-icon-Plus" />
                    </template>
                    新增
                </el-button>
            </div>
            <el-table
                class="mt-4"
                size="large"
                v-loading="pager.loading"
                :data="pager.lists"
            >
                <el-table-column label="sku_id" prop="skuId" min-width="100" />
                <el-table-column label="spu_id" prop="spuId" min-width="100" />
                <el-table-column label="商品名字" prop="spuName" min-width="100" />
                <el-table-column label="会员昵称" prop="memberNickName" min-width="100" />
                <el-table-column label="星级" prop="star" min-width="100" />
                <el-table-column label="会员ip" prop="memberIp" min-width="100" />
                <el-table-column label="创建时间" prop="createTime" min-width="100" />
                <el-table-column label="显示状态[0-不显示，1-显示]" prop="showStatus" min-width="100" />
                <el-table-column label="购买时属性组合" prop="spuAttributes" min-width="100" />
                <el-table-column label="点赞数" prop="likesCount" min-width="100" />
                <el-table-column label="回复数" prop="replyCount" min-width="100" />
                <el-table-column label="评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]" prop="resources" min-width="100" />
                <el-table-column label="用户头像" prop="memberIcon" min-width="100" />
                <el-table-column label="评论类型[0 - 对商品的直接评论，1 - 对评论的回复]" prop="commentType" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['comment:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['comment:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.id)"
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
<script lang="ts" setup name="comment">
import { commentDelete, commentLists } from '@/api/comment'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
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

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: commentLists,
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

const handleDelete = async (id: number) => {
    await feedback.confirm('确定要删除？')
    await commentDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
