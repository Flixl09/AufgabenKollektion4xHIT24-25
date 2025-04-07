<script setup>
import {defineProps, ref} from 'vue'
import { item } from '@/types/item.ts'
import {useInventoryStore} from "@/stores/ops.ts";
const items = defineProps({ inventory: useInventoryStore, item: item })

const target = "#" + items.item.id

</script>

<template>
  <button type="button" class="btn btn-warning w-100" data-bs-toggle="modal" :data-bs-target="target">Edit</button>
</template>
