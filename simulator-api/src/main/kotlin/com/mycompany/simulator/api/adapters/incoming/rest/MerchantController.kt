package com.mycompany.simulator.api.adapters.incoming.rest

import com.mycompany.simulator.api.adapters.incoming.rest.requests.UploadMerchantRequest
import com.mycompany.simulator.api.adapters.incoming.rest.responses.ErrorDetail
import com.mycompany.simulator.api.adapters.incoming.rest.responses.UploadMerchantResponse
import com.mycompany.simulator.api.adapters.incoming.rest.validation.UploadMerchantRequestValidator
import com.mycompany.simulator.api.adapters.incoming.rest.validation.ValidationResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/merchant")
class MerchantController(
    private val uploadMerchantRequestValidator: UploadMerchantRequestValidator
) {
    @PostMapping("/start")
    fun start(): ResponseEntity<Void> {
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    fun upload(uploadMerchantRequest: UploadMerchantRequest): ResponseEntity<UploadMerchantResponse> {
        val validationResult: ValidationResult = uploadMerchantRequestValidator.validate(uploadMerchantRequest)
        if (!validationResult.isValidationPass()) {
            return ResponseEntity(
                UploadMerchantResponse(
                    messageId = uploadMerchantRequest.messageId,
                    errorDetails = validationResult.errors.map { ErrorDetail(it.key, it.value) },
                    warningDetails = validationResult.warnings.map { ErrorDetail(it.key, it.value) }
                ),
                HttpStatus.BAD_REQUEST
            )
        }
        return ResponseEntity(
            UploadMerchantResponse(messageId = uploadMerchantRequest.messageId),
            HttpStatus.OK
        )
    }
}
