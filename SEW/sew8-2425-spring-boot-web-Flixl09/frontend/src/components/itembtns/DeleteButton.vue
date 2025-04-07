<script setup>
import {defineProps, ref} from 'vue'
import { item } from '@/types/item.ts'
import {useInventoryStore} from "@/stores/ops.ts";
const items = defineProps({ inventory: useInventoryStore, item: item })

const del = () => {
  items.inventory.removeItem(items.item)
}
</script>

<template>
  <button @click="del()" class="btn btn-danger w-100">Delete</button>
</template>