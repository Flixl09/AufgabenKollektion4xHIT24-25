<script setup>
import {defineProps, ref, watch} from 'vue'
import { item } from '@/types/item.ts'
import {useInventoryStore} from "@/stores/ops.ts";
const items = defineProps({ inventory: useInventoryStore, item: item })
const itemref = { id: items.item.id, name: items.item.name, amount: items.item.amount, collected: items.item.collected }

const reloadItem = () => {
  items.inventory.loadItems()
  itemref.id = items.item.id
  itemref.name = items.item.name
  itemref.amount = items.item.amount
  itemref.collected = items.item.collected
}

</script>

<template>
  <div class="modal fade" :id="items.item.id" tabindex="-1" :aria-labelledby="items.item.name" aria-hidden="true" @focus="reloadItem()">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" :id="items.item.name">Edit ID: {{ items.item.id }} Name: {{ items.item.name }}</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <form @submit.prevent="items.inventory.updateItem(itemref)" @keydown.esc="dismiss">
          <div class="modal-body">
            <div class="mb-3">
              <label for="name" class="form-label">Name</label>
              <input type="text" class="form-control" id="name" v-model="itemref.name" @keydown.enter="submit">
            </div>
            <div class="mb-3">
              <label for="amount" class="form-label">Amount</label>
              <input type="number" class="form-control" id="amount" v-model="itemref.amount" @keydown.enter="submit">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Save changes</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>