
<template>
  <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="errorModalLabel">
            <i class="bi bi-exclamation-triangle-fill text-danger"></i> Error
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p id="errorMessage"></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import * as bootstrap from 'bootstrap'

function triggerErrorModal(message: string) {
  document.getElementById('errorMessage')!.textContent = message
  const errorModal = new bootstrap.Modal(document.getElementById('errorModal')!)
  errorModal.show()
}

export default {
  methods: {
    triggerErrorModal
  }
}

</script>

<style>
.modal-header .bi {
  margin-right: 10px;
}
</style>