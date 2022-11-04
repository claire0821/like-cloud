<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="等级名称" prop="name">
                    <el-input class="w-[280px]" v-model="queryParams.name" />
                </el-form-item>
                <el-form-item label="等级需要的成长值" prop="growthPoint">
                    <el-input class="w-[280px]" v-model="queryParams.growthPoint" />
                </el-form-item>
                <el-form-item label="是否为默认等级[0->不是；1->是]" prop="defaultStatus">
                    <el-select
                        v-model="queryParams.defaultStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item label="免运费标准" prop="freeFreightPoint">
                    <el-input class="w-[280px]" v-model="queryParams.freeFreightPoint" />
                </el-form-item>
                <el-form-item label="每次评价获取的成长值" prop="commentGrowthPoint">
                    <el-input class="w-[280px]" v-model="queryParams.commentGrowthPoint" />
                </el-form-item>
                <el-form-item label="是否有免邮特权" prop="priviledgeFreeFreight">
                    <el-input class="w-[280px]" v-model="queryParams.priviledgeFreeFreight" />
                </el-form-item>
                <el-form-item label="是否有会员价格特权" prop="priviledgeMemberPrice">
                    <el-input class="w-[280px]" v-model="queryParams.priviledgeMemberPrice" />
                </el-form-item>
                <el-form-item label="是否有生日特权" prop="priviledgeBirthday">
                    <el-input class="w-[280px]" v-model="queryParams.priviledgeBirthday" />
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-input class="w-[280px]" v-model="queryParams.note" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button v-perms="['level:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="等级名称" prop="name" min-width="100" />
                <el-table-column label="等级需要的成长值" prop="growthPoint" min-width="100" />
                <el-table-column label="是否为默认等级[0->不是；1->是]" prop="defaultStatus" min-width="100" />
                <el-table-column label="免运费标准" prop="freeFreightPoint" min-width="100" />
                <el-table-column label="每次评价获取的成长值" prop="commentGrowthPoint" min-width="100" />
                <el-table-column label="是否有免邮特权" prop="priviledgeFreeFreight" min-width="100" />
                <el-table-column label="是否有会员价格特权" prop="priviledgeMemberPrice" min-width="100" />
                <el-table-column label="是否有生日特权" prop="priviledgeBirthday" min-width="100" />
                <el-table-column label="备注" prop="note" min-width="100" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['level:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['level:del']"
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
<script lang="ts" setup name="level">
import { levelDelete, levelLists } from '@/api/level'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    name: '',
    growthPoint: '',
    defaultStatus: '',
    freeFreightPoint: '',
    commentGrowthPoint: '',
    priviledgeFreeFreight: '',
    priviledgeMemberPrice: '',
    priviledgeBirthday: '',
    note: '',
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: levelLists,
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
    await levelDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
