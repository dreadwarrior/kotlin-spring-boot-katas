package de.dreadlabs.kotlinspringbootkatas

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MyEvent(
    val firstName: String,
    @field:JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        timezone = "UTC"
    ) val createdAt: LocalDateTime
)
