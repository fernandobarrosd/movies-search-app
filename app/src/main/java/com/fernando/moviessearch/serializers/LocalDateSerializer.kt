package com.fernando.moviessearch.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateSerializer : StdSerializer<LocalDate>(LocalDate::class.java) {
    override fun serialize(localDate: LocalDate?, generator: JsonGenerator?, provider: SerializerProvider?) {
        generator?.writeString(localDate?.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }
}