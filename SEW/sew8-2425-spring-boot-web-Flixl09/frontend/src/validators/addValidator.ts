import {computed} from "vue";
import {useVuelidate} from "@vuelidate/core";
import {required, integer, maxLength, minLength, helpers} from "@vuelidate/validators";

export function addValidator(inputData: any) {
    const rules = computed(() => ({
        name: {required: helpers.withMessage("Der Name darf nicht leer sein!", required), minLength: minLength(1), maxLength: maxLength(255)},
        amount: {required: helpers.withMessage("Du hast die Anzahl (Amount) vergessen, du dulli!", required), integer: integer},
    }))

    return useVuelidate(rules, inputData)
}
