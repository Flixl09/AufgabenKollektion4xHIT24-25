import {getCurrentInstance, ref} from 'vue'
import {defineStore} from 'pinia'
import {item} from "@/types/item.ts";
import router from "@/router";
import app from "@/App.vue";
import ErrorModal from "@/components/ErrorModal.vue";

export const useInventoryStore = defineStore('inventory', () => {
    const items = ref<item[]>([])

    async function error(response: Response) {
        let aa = await response.json()
        console.log(aa)
        ErrorModal.methods!.triggerErrorModal(aa.message || aa.error)
    }

    async function loadItems() {
        const response = await fetch('http://localhost:8080/api/grocery')
        const data = await response.json()
        items.value = sortItems(data)
    }

    async function addItem(item: item) {
        const response = await fetch('http://localhost:8080/api/grocery', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        })

        if (!response.ok) return error(response)
        const data = await response.json()
        items.value.push(data)
        items.value = sortItems(items.value)
    }

    async function removeItem(item: item) {
        const response = await fetch(`http://localhost:8080/api/grocery/${item.id}`, {
            method: 'DELETE',
            body: JSON.stringify(item)
        })
        if (!response.ok) return error(response)
        items.value.slice(items.value.indexOf(item), 1)
        await loadItems()
    }

    async function updateItem(item: item) {
        const response = await fetch(`http://localhost:8080/api/grocery`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        })
        if (!response.ok) return error(response)
        const index = items.value.findIndex(i => i.id === item.id)
        items.value[index] = item
    }

    async function toggleCollected(item: item) {
        const response = await fetch(`http://localhost:8080/api/grocery/${item.id}?collected=` + !item.collected, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        if (!response.ok) return error(response)
        const index = items.value.findIndex(i => i.id === item.id)
        items.value[index].collected = !item.collected
        items.value = sortItems(items.value)
    }

    function sortItems(items: any[]): any[] {
        return items.sort((a, b) => {
            if (a.collected !== b.collected) {
                return a.collected ? 1 : -1;
            }
            return a.name.localeCompare(b.name);
        });
    }


    return {items, addItem, removeItem, loadItems, updateItem, toggleCollected}
})
