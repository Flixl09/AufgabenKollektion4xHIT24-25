<script setup>
import Item from "@/components/Item.vue";
import AddItem from "@/components/AddItem.vue";
import { useInventoryStore } from "@/stores/ops.ts";
import {nextTick, onErrorCaptured, onMounted, ref, watch} from "vue";
import AddItemModal from "@/components/AddItemModal.vue";
import ErrorModal from "@/components/ErrorModal.vue";


let inventory = useInventoryStore();

onMounted(() => {
  inventory.loadItems();
});

</script>

<template>
  <div class="obendrueber mt-5">
    <ErrorModal />
    <h1 class="text-center my-4">ITEMS {{ inventory.items.length }}</h1>
    <AddItem :inventory="inventory" />
    <AddItemModal :inventory="inventory" />
    <div class="gridmaniac">
      <div v-for="item in inventory.items" :key="item.id">
        <Item :item="item" :inventory="inventory" class="itemmaniac" />
      </div>
    </div>
  </div>
</template>

<style>
.obendrueber {
  width: 100vw;
  display: grid;
  flex-direction: column;
  justify-items: center;
}

.gridmaniac {
  display: flex;
  width: 80vw;
  flex-wrap: wrap;
}

.itemmaniac {
  margin: 10px
}
</style>
