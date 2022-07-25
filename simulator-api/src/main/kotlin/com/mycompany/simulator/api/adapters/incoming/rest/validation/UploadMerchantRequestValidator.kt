package com.mycompany.simulator.api.adapters.incoming.rest.validation

import com.mycompany.simulator.api.adapters.incoming.rest.requests.UploadMerchantRequest
import org.springframework.stereotype.Component
import javax.validation.Validator

@Component
class UploadMerchantRequestValidator(private val validator: Validator) {
    fun validate(uploadMerchantRequest: UploadMerchantRequest): ValidationResult {
        return ValidationResult(
            errors = validateByGroup(uploadMerchantRequest, ErrorGroup::class.java),
            warnings = validateByGroup(uploadMerchantRequest, WarningGroup::class.java)
        )
    }

    private fun <T> validateByGroup(uploadMerchantRequest: UploadMerchantRequest, java: Class<T>): Map<String, String> {
        val violations = validator.validate(uploadMerchantRequest, java)
        return violations.associate {
            codeOfField(it.propertyPath.toList().last().toString()) to it.message
        }
    }

    private companion object {
        private val field2CodeMapping: Map<String, String> = mapOf(
            "firstName" to "FN",
            "lastName" to "LN"
        )

        private fun codeOfField(fieldName: String): String {
            field2CodeMapping[fieldName]?.let {
                return it
            }
            return fieldName
        }
    }
}
