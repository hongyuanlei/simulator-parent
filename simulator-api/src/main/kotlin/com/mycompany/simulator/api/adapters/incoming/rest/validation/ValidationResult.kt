package com.mycompany.simulator.api.adapters.incoming.rest.validation

data class ValidationResult(
    val errors: Map<String, String>,
    val warnings: Map<String, String>
) {
    fun isValidationPass(): Boolean = errors.isEmpty() && warnings.isEmpty()
}
