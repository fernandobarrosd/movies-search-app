package com.fernando.moviessearch.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TMDBAPIResponse<T>(
    @JsonProperty("results" )val results: T
)