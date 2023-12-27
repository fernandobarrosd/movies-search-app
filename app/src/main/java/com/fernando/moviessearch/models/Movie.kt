package com.fernando.moviessearch.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Movie(
    @JsonProperty("id") val id: Long,
    @JsonProperty("title") val title: String,
    @JsonProperty("release_date") val releaseDate: String,
    @JsonProperty("poster_path") val posterImageUrl: String
)
