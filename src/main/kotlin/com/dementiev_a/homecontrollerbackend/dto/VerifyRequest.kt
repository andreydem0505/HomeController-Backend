package com.dementiev_a.homecontrollerbackend.dto

import javax.validation.constraints.NotEmpty

data class VerifyRequest(@field:NotEmpty var key: String? = null)