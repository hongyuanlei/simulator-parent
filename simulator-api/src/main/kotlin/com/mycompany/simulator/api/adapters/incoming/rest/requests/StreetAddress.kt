package com.mycompany.simulator.api.adapters.incoming.rest.requests

import com.mycompany.simulator.api.adapters.incoming.rest.validation.WarningGroup
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class StreetAddress(
    @get:NotBlank(groups = [WarningGroup::class])
    @get:Size(max = 40, groups = [WarningGroup::class])
    val addressLine1: String? = null,
    @get:NotBlank(groups = [WarningGroup::class])
    @get:Size(max = 40, groups = [WarningGroup::class])
    val addressLine2: String? = null
)
