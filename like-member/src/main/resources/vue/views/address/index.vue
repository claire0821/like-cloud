<template>
    <div class="index-lists">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="收货人姓名" prop="name">
                    <el-input class="w-[280px]" v-model="queryParams.name" />
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input class="w-[280px]" v-model="queryParams.phone" />
                </el-form-item>
                <el-form-item label="邮政编码" prop="postCode">
                    <el-input class="w-[280px]" v-model="queryParams.postCode" />
                </el-form-item>
                <el-form-item label="省份/直辖市" prop="province">
                    <el-input class="w-[280px]" v-model="queryParams.province" />
                </el-form-item>
                <el-form-item label="城市" prop="city">
                    <el-input class="w-[280px]" v-model="queryParams.city" />
                </el-form-item>
                <el-form-item label="区" prop="region">
                    <el-input class="w-[280px]" v-model="queryParams.region" />
                </el-form-item>
                <el-form-item label="详细地址(街道)" prop="detailAddress">
                    <el-input class="w-[280px]" v-model="queryParams.detailAddress" />
                </el-form-item>
                <el-form-item label="省市区代码" prop="areacode">
                    <el-input class="w-[280px]" v-model="queryParams.areacode" />
                </el-form-item>
                <el-form-item label="是否默认" prop="defaultStatus">
                    <el-select
                        v-model="queryParams.defaultStatus"
                        class="w-[280px]"
                        clearable
                    >
                        <el-option label="请选择字典生成" value="" />
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                    <el-button
                       :disabled="!selectData.length"
                       type="danger"
                       @click="handleDelete(selectData)"
                       >批量删除</el-button>
                    <el-button v-perms="['address:add']" type="primary" @click="handleAdd()">
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
                <el-table-column label="member_id" prop="memberId" min-width="100" header-align="center" align="center"/>
                <el-table-column label="收货人姓名" prop="name" min-width="100" header-align="center" align="center"/>
                <el-table-column label="电话" prop="phone" min-width="100" header-align="center" align="center"/>
                <el-table-column label="邮政编码" prop="postCode" min-width="100" header-align="center" align="center"/>
                <el-table-column label="省份/直辖市" prop="province" min-width="100" header-align="center" align="center"/>
                <el-table-column label="城市" prop="city" min-width="100" header-align="center" align="center"/>
                <el-table-column label="区" prop="region" min-width="100" header-align="center" align="center"/>
                <el-table-column label="详细地址(街道)" prop="detailAddress" min-width="100" header-align="center" align="center"/>
                <el-table-column label="省市区代码" prop="areacode" min-width="100" header-align="center" align="center"/>
                <el-table-column label="是否默认" prop="defaultStatus" min-width="100" header-align="center" align="center"/>
                <el-table-column label="操作" width="120" fixed="right" header-align="center" align="center">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['address:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['address:del']"
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
<script lang="ts" setup name="address">
import { addressDeleteBatch, addressLists } from '@/api/member/address'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './address_edit.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const showEdit = ref(false)
const queryParams = reactive({
    memberId: '',
    name: '',
    phone: '',
    postCode: '',
    province: '',
    city: '',
    region: '',
    detailAddress: '',
    areacode: '',
    defaultStatus: '',
})
const selectData = ref<any[]>([])
const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: addressLists,
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
    await addressDeleteBatch(ids)
    feedback.msgSuccess('删除成功')
    getLists()
}
getLists()
</script>
