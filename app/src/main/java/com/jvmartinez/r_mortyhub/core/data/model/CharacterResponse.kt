package com.jvmartinez.r_mortyhub.core.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CharacterResponse(
    @JsonProperty("info") val info: Info,
    @JsonProperty("results")  val results: List<Character>
)