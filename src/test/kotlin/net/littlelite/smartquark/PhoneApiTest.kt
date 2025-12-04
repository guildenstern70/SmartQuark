/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@QuarkusTest
@DisplayName("Phone API")
class PhoneApiTest {

    private fun createPersonAndGetId(name: String = "PhoneUser", surname: String = "Tester", age: Int = 30): Int {
        val requestBody = """
            {
              "age": $age,
              "name": "$name",
              "phones": [
                { "number": "1234567890", "prefix": "+1" }
              ],
              "surname": "$surname"
            }
        """.trimIndent()

        val response = given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .`when`()
            .post("/person")
            .then()
            .statusCode(201)
            .extract().response()

        return response.jsonPath().getInt("id")
    }

    @Test
    @DisplayName("should get phones for a person")
    fun testGetPhonesByPerson() {
        val id = createPersonAndGetId()

        val getResp = given()
            .`when`()
            .get("/phone/person/$id")
            .then()
            .statusCode(200)
            .extract().response()

        val phones = getResp.jsonPath().getList<Map<String, Any>>("")
        assertThat(phones).hasSize(1)
        assertThat(phones[0]["prefix"]).isEqualTo("+1")
        assertThat(phones[0]["number"]).isEqualTo("1234567890")
    }

    @Test
    @DisplayName("should replace phones for a person")
    fun testReplacePhones() {
        val id = createPersonAndGetId()

        val putBody = """
            [
              { "prefix": "+39", "number": "3331112222" },
              { "prefix": "+44", "number": "7700000000" }
            ]
        """.trimIndent()

        given()
            .header("Content-type", "application/json")
            .body(putBody)
            .`when`()
            .put("/phone/person/$id")
            .then()
            .statusCode(200)

        val getResp = given()
            .`when`()
            .get("/phone/person/$id")
            .then()
            .statusCode(200)
            .extract().response()

        val phones = getResp.jsonPath().getList<Map<String, Any>>("")
        assertThat(phones).hasSize(2)
        assertThat(phones[0]["prefix"]).isIn("+39", "+44")
        assertThat(phones[1]["prefix"]).isIn("+39", "+44")
    }

    @Test
    @DisplayName("should return 404 for non-existing person when getting/replacing phones")
    fun testPhonesNotFound() {
        given()
            .`when`()
            .get("/phone/person/999999")
            .then()
            .statusCode(404)

        val putBody = """ [ { "prefix": "+1", "number": "000" } ] """

        given()
            .header("Content-type", "application/json")
            .body(putBody)
            .`when`()
            .put("/phone/person/999999")
            .then()
            .statusCode(404)
    }
}
