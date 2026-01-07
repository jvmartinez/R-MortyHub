package com.jvmartinez.r_mortyhub.core.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Info(
    @JsonProperty("count") val count: Int,
    @JsonProperty("pages") val pages: Int,
    @JsonProperty("next") val next: String? = null,
    @JsonProperty("prev") val prev: String? = null
)
