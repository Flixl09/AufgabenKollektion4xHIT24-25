<script setup>
import {ref} from "vue";
import {defineProps} from "vue";
import BuyButton from "@/components/itembtns/BuyButton.vue";
import {item} from "@/types/item.js";
import {useInventoryStore} from "@/stores/ops.js";
import AddButton from "@/components/itembtns/AddButton.vue";
import DeleteButton from "@/components/itembtns/DeleteButton.vue";
import ToggleCollected from "@/components/itembtns/ToggleCollected.vue";
import EditButton from "@/components/itembtns/EditButton.vue";
import EditModal from "@/components/EditModal.vue";
const itemref = defineProps({ item: item, inventory: useInventoryStore })
</script>

<template>
  <div class="card">
    <div class="card-body">
      <div class="card-header">
        <h5 class="">{{ itemref.item.name }}</h5>
      </div>
      <h6 class="card-subtitle mb-2 mt-2 text-body-secondary">ID: {{ itemref.item.id }}</h6>
      <p class="card-text">Amount: {{ itemref.item.amount }}</p>
      <div class="d-flex justify-content-between align-items-center" id="btndiv">
        <BuyButton :item="itemref.item" :inventory="itemref.inventory" class="mb-2"/>
        <AddButton :item="itemref.item" :inventory="itemref.inventory" class="mb-2"/>
      </div>
      <div class="d-flex justify-content-between align-items-center" id="btndiv">
        <DeleteButton :item="itemref.item" :inventory="itemref.inventory" class="mb-4" />
        <EditButton :item="itemref.item" :inventory="itemref.inventory" class="mb-4"/>
      </div>
      <blockquote class="blockquote mb-0">
        <footer class="blockquote-footer">Collected: {{ itemref.item.collected }}</footer>
        <ToggleCollected :item="itemref.item" :inventory="itemref.inventory" class="btn-sm" style="width: 1rem"/>
      </blockquote>
      <EditModal :item="itemref.item" :inventory="itemref.inventory" />
    </div>
  </div>
</template>

<style>
.card {
  width: 17.9rem;
}

#btndiv {
  width: auto;
}
</style>
