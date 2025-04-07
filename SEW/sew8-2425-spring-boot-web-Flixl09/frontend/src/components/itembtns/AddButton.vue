<script setup>
import { defineProps } from 'vue'
import { item } from '@/types/item.ts'
import {useInventoryStore} from "@/stores/ops.ts";
const items = defineProps({ inventory: useInventoryStore, item: item })

const add = () => {
  items.item.amount += 1
  items.inventory.updateItem(items.item)
}
</script>

<template>
  <button @click="add()" class="btn btn-success w-100">Add</button>
</template>