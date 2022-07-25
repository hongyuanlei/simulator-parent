package com.mycompany.simulator.api.adapters.incoming.rest.requests

import com.mycompany.simulator.api.adapters.incoming.rest.validation.ErrorGroup
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SetupRequest(
    @get:NotBlank(groups = [ErrorGroup::class], message = "Invalid firstName")
    @get:Size(max = 10, groups = [ErrorGroup::class], message = "Invalid firstName")
    val firstName: String,
    @get:NotBlank(groups = [ErrorGroup::class], message = "Invalid lastName")
    @get:Size(max = 10, groups = [ErrorGroup::class], message = "Invalid lastName")
    val lastName: String,
    val birthDay: LocalDate? = null,
    @field:Valid
    val streetAddress: StreetAddress? = null
)
