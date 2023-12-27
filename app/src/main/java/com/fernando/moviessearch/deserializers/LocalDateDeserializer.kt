package com.fernando.moviessearch.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.time.LocalDate

class LocalDateDeserializer : StdDeserializer<LocalDate>(LocalDate::class.java) {
    override fun deserialize(jsonParser: JsonParser?, context: DeserializationContext?): LocalDate {
        return LocalDate.parse(jsonParser?.readValueAs(String::class.java))
    }
}