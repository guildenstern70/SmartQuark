/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-26
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@QuarkusTest
@DisplayName("Validation API")
class ValidationApiTest {

    @Test
    @DisplayName("should return 400 with validation messages for invalid person payload")
    fun testInvalidPersonPayload() {
        val requestBody = """
            {
              "age": -5,
              "name": "",
              "phones": [
                { "number": "12", "prefix": "" }
              ],
              "surname": ""
            }
        """.trimIndent()

        given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .`when`()
            .post("/person")
            .then()
            .statusCode(400)
            .body(containsString("must not be blank"))
            .body(containsString("must be greater than or equal to"))
    }
}
