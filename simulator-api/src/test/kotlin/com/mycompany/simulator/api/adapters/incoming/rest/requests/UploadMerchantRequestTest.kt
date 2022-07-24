package com.mycompany.simulator.api.adapters.incoming.rest.requests

import com.mycompany.simulator.api.adapters.incoming.rest.validation.ErrorGroup
import com.mycompany.simulator.api.adapters.incoming.rest.validation.WarningGroup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import javax.validation.Validation
import javax.validation.Validator

internal class UploadMerchantRequestTest {
    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

//    @Test
//    fun `validate by default group`() {
//        val violations = validator.validate(
//            UploadMerchantRequest(
//                messageId = UUID.randomUUID().toString(),
//                setupRequests = listOf(
//                    SetupRequest(
//                        firstName = "",
//                        lastName = "",
//                        streetAddress = StreetAddress(
//                            addressLine1 = "address line 1",
//                            addressLine2 = ""
//                        )
//                    )
//                )
//            )
//        )
//        assertThat(violations.size).isEqualTo(3)
//        violations.forEach {
//            assertThat(it.message).isEqualTo("")
//        }
//    }

    @Test
    fun `validate empty error by ErrorGroup`() {
        val violations = validator.validate(
            UploadMerchantRequest(
                messageId = UUID.randomUUID().toString(),
                setupRequests = listOf(
                    SetupRequest(
                        firstName = "",
                        lastName = "",
                        streetAddress = StreetAddress(
                            addressLine1 = "address line 1",
                            addressLine2 = ""
                        )
                    )
                )
            ),
            ErrorGroup::class.java
        )
        assertThat(violations.size).isEqualTo(2)
        violations.forEach {
            assertThat(it.message).isEqualTo("must not be blank")
        }
    }

    @Test
    fun `validate length error by ErrorGroup`() {
        val violations = validator.validate(
            UploadMerchantRequest(
                messageId = UUID.randomUUID().toString(),
                setupRequests = listOf(
                    SetupRequest(
                        firstName = UUID.randomUUID().toString().substring(0, 11),
                        lastName = "last name",
                        streetAddress = StreetAddress(
                            addressLine1 = "address line 1",
                            addressLine2 = ""
                        )
                    )
                )
            ),
            ErrorGroup::class.java
        )
        assertThat(violations.size).isEqualTo(1)
    }

    @Test
    fun `validate empty error by WarningGroup`() {
        val violations = validator.validate(
            UploadMerchantRequest(
                messageId = UUID.randomUUID().toString(),
                setupRequests = listOf(
                    SetupRequest(
                        firstName = "first name",
                        lastName = "last name",
                        streetAddress = StreetAddress(
                            addressLine1 = "",
                            addressLine2 = ""
                        )
                    )
                )
            ),
            WarningGroup::class.java
        )
        assertThat(violations.size).isEqualTo(2)
    }

    @Test
    fun `validate length error by WarningGroup`() {
        val violations = validator.validate(
            UploadMerchantRequest(
                messageId = UUID.randomUUID().toString(),
                setupRequests = listOf(
                    SetupRequest(
                        firstName = "first name",
                        lastName = "last name",
                        streetAddress = StreetAddress(
                            addressLine1 = "a".repeat(41),
                            addressLine2 = "address line 2"
                        )
                    )
                )
            ),
            WarningGroup::class.java
        )
        assertThat(violations.size).isEqualTo(1)
    }
}