package com.jvmartinez.r_mortyhub.core.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Character(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("status") val status: String,
    @JsonProperty("species") val species: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("gender") val gender: String,
    @JsonProperty("origin") val origin: LocationReference,
    @JsonProperty("location") val location: LocationReference,
    @JsonProperty("image") val image: String,
    @JsonProperty("episode") val episode: List<String>,
    @JsonProperty("url") val url: String,
    @JsonProperty("created") val created: String
)

data class LocationReference(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: String
)