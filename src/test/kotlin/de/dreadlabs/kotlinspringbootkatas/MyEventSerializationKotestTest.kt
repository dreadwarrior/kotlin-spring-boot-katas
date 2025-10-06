package de.dreadlabs.kotlinspringbootkatas

import io.kotest.assertions.json.shouldContainJsonKey
import io.kotest.assertions.json.shouldContainJsonKeyValue
import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

@ApplyExtension(SpringExtension::class)
@JsonTest
class MyEventSerializationKotestTest(
    @param:Autowired private val jacksonTester: JacksonTester<MyEvent>
) : FreeSpec({

    "properties are snake_case-serialized" {
        val localDateTime = LocalDateTime.of(2025, 10, 6, 23, 47, 10, 666)
        val zonedDateTime = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC)
        val myEvent = MyEvent(firstName = "Tommy", createdAt = zonedDateTime.toLocalDateTime())

        val jsonContent = jacksonTester.write(myEvent)

        jsonContent.json should {
            it.shouldContainJsonKeyValue("$.first_name", "Tommy")
            it.shouldContainJsonKeyValue("$.created_at", "2025-10-06T21:47:10.000Z") // format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
        }
    }

    "deserialization" {
        val s = """
            {
                "first_name": "Tommy",
                "created_at": "2025-10-06T21:47:10.000Z"
            }
        """.trimIndent()

        val objectContent = jacksonTester.parse(s)

        objectContent.`object` should {
            it.firstName shouldBe "Tommy"
            it.createdAt shouldBe LocalDateTime.of(2025, 10, 6, 23, 47, 10, 0)
        }
    }
})