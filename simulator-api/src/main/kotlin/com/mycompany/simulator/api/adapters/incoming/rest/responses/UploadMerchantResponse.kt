package com.mycompany.simulator.api.adapters.incoming.rest.responses

data class UploadMerchantResponse(
    val messageId: String? = null,
    val errorDetails: List<ErrorDetail>? = null,
    val warningDetails: List<ErrorDetail>? = null
)
