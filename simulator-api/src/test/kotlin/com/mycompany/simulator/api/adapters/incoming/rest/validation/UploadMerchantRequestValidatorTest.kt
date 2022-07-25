package com.mycompany.simulator.api.adapters.incoming.rest.validation

import com.mycompany.simulator.api.APIConfiguration
import com.mycompany.simulator.api.adapters.incoming.rest.requests.SetupRequest
import com.mycompany.simulator.api.adapters.incoming.rest.requests.StreetAddress
import com.mycompany.simulator.api.adapters.incoming.rest.requests.UploadMerchantRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [APIConfiguration::class])
internal class UploadMerchantRequestValidatorTest {

    @Autowired
    private lateinit var validator: UploadMerchantRequestValidator

    @Test
    fun `should validate UploadMerchantRequest`() {
        val uploadMerchantRequest = UploadMerchantRequest(
            messageId = UUID.randomUUID().toString(),
            setupRequests = listOf(
                SetupRequest(
                    firstName = "",
                    lastName = "",
                    streetAddress = StreetAddress(
                        addressLine1 = "",
                        addressLine2 = ""
                    )
                )
            )
        )
        val validationResult: ValidationResult = validator.validate(uploadMerchantRequest)
        assertThat(validationResult.isValidationPass()).isFalse
        assertThat(validationResult.errors).isEqualTo(
            mapOf(
                "FN" to "Invalid firstName",
                "LN" to "Invalid lastName"
            )
        )
        assertThat(validationResult.warnings).isEqualTo(
            mapOf(
                "addressLine1" to "must not be blank",
                "addressLine2" to "must not be blank"
            )
        )
    }
}
