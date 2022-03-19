package com.dementiev_a.homecontrollerbackend.dto

import javax.validation.constraints.NotEmpty

data class NotifyRequest(
    @field:NotEmpty var key: String? = null,
    @field:NotEmpty var sensor: String? = null
)