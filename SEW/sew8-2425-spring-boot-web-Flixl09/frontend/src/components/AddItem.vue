<script setup lang="ts">
import {defineProps, reactive, ref} from 'vue'
import {useInventoryStore} from "@/stores/ops.js";
import {addValidator} from "@/validators/addValidator.ts";
import type {Validation} from "@vuelidate/core";

const items = defineProps({ inventory: useInventoryStore})
const itemv = reactive({id: null, name: '', amount: undefined, collected: false})
const v$: Validation = addValidator(itemv).value

function toggleCollected() {
  itemv.collected = !itemv.collected
}

const handleSubmit = async () => {
  const valid = await v$.$validate();
  if (valid) {
    await items.inventory!.addItem(itemv)
  }
}


</script>

<template>
  <form @submit.prevent="handleSubmit">
    <div class="input-group mb-3">
      <div>
        <input type="text" class="form-control" placeholder="Item name" v-model="itemv.name">
        <span v-if="v$.name.$error">{{ v$.name.$errors[0].$message }}</span>
      </div>
      <div>
        <input type="number" class="form-control" placeholder="Amount" v-model="itemv.amount">
        <span v-if="v$.amount.$error">{{ v$.amount.$errors[0].$message }}</span>
      </div>
      <div>
        <button type="button" class="btn" :class="{'btn-danger': !itemv.collected, 'btn-success': itemv.collected}" @click="toggleCollected">Collected</button>
      </div>
      <div>
        <button type="submit" class="btn btn-primary">Add</button>
      </div>
    </div>
  </form>

</template>