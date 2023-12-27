package com.fernando.moviessearch.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fernando.moviessearch.deserializers.LocalDateDeserializer
import com.fernando.moviessearch.serializers.LocalDateSerializer
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class Movie(
    @JsonProperty("id") val id: Long,
    @JsonProperty("title") val title: String,

    @JsonProperty("release_date")
    @JsonDeserialize(using = LocalDateDeserializer::class)
    @JsonSerialize(using = LocalDateSerializer::class)
    val releaseDate: LocalDate,
    @JsonProperty("poster_path") val posterImageUrl: String,

    @JsonProperty("overview")
    val sinopse: String
)
