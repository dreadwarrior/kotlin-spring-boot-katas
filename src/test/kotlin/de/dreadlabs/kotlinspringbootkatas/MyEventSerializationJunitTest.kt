package de.dreadlabs.kotlinspringbootkatas

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.test.context.TestConstructor
import java.time.LocalDateTime

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@JsonTest
class MyEventSerializationJunitTest(private val jacksonTester: JacksonTester<MyEvent>) {

    @Test
    fun `applies snake_case serialization`() {
        val myEvent = MyEvent(firstName = "Tommy", createdAt = LocalDateTime.now())

        val jsonContent = jacksonTester.write(myEvent)

        jsonContent.json shouldBe """{"first_name":"Tommy"}"""
    }
}