<script setup>
import { defineProps } from 'vue'
import { item } from '@/types/item.ts'
import {useInventoryStore} from "@/stores/ops.ts";
const items = defineProps({ inventory: useInventoryStore, item: item })

const buy = () => {
  if (items.item.amount <= 0) {
    return
  }
  items.item.amount -= 1
  items.inventory.updateItem(items.item)
}
</script>

<template>
  <button @click="buy()" class="btn btn-primary w-100">Buy</button>
</template>