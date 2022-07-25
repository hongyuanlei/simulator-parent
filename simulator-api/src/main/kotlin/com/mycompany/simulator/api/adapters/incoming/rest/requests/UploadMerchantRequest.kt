package com.mycompany.simulator.api.adapters.incoming.rest.requests

import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UploadMerchantRequest(
    @get:NotBlank
    @get:Size(max = 60)
    val messageId: String? = null,

    @field:Valid
    val setupRequests: List<SetupRequest>? = null
)
