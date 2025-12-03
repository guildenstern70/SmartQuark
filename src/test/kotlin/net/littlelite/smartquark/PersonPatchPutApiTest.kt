package net.littlelite.smartquark

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@QuarkusTest
@DisplayName("Person PATCH and PUT API")
class PersonPatchPutApiTest {

    private fun createPersonAndGetId(name: String = "Init", surname: String = "Tester", age: Int = 20): Int {
        val requestBody = """
            {
              "age": $age,
              "name": "$name",
              "phones": [
                {
                  "number": "33383208328",
                  "prefix": "+39"
                }
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
    @DisplayName("PATCH should update a single field and keep others")
    fun testPatchPersonName() {
        val id = createPersonAndGetId(name = "Alice", surname = "Smith", age = 30)

        val patchBody = """
            {
              "name": "Alicia"
            }
        """.trimIndent()

        given()
            .header("Content-type", "application/json")
            .body(patchBody)
            .`when`()
            .patch("/person/$id")
            .then()
            .statusCode(200)
            .body("name", equalTo("Alicia"))

        // verify other fields unchanged
        val getResp = given()
            .`when`()
            .get("/person/$id")
            .then()
            .statusCode(200)
            .extract().response()

        assertThat(getResp.jsonPath().getString("surname")).isEqualTo("Smith")
        assertThat(getResp.jsonPath().getInt("age")).isEqualTo(30)
    }

    @Test
    @DisplayName("PATCH on non-existent id should return 404")
    fun testPatchNotFound() {
        val patchBody = "{" + "\"name\": \"Nobody\"" + "}"

        given()
            .header("Content-type", "application/json")
            .body(patchBody)
            .`when`()
            .patch("/person/999999")
            .then()
            .statusCode(404)
    }

    @Test
    @DisplayName("PUT should fully replace the resource")
    fun testPutPersonReplace() {
        val id = createPersonAndGetId(name = "Original", surname = "One", age = 25)

        val putBody = """
            {
              "age": 45,
              "name": "Robert",
              "phones": [
                { "prefix": "+1", "number": "5551112222" },
                { "prefix": "+44", "number": "7700123456" }
              ],
              "surname": "Jones"
            }
        """.trimIndent()

        given()
            .header("Content-type", "application/json")
            .body(putBody)
            .`when`()
            .put("/person/$id")
            .then()
            .statusCode(200)
            .body("name", equalTo("Robert"))
            .body("surname", equalTo("Jones"))
            .body("age", equalTo(45))

        val getResp = given()
            .`when`()
            .get("/person/$id")
            .then()
            .statusCode(200)
            .extract().response()

        val phones = getResp.jsonPath().getList<Map<String, Any>>("phones")
        assertThat(phones).hasSize(2)
    }

    @Test
    @DisplayName("PUT with invalid payload should return 400")
    fun testPutInvalid() {
        val id = createPersonAndGetId(name = "Bad", surname = "Input", age = 50)

        val putBody = """
            {
              "age": -5,
              "name": "Bad",
              "phones": [],
              "surname": "Input"
            }
        """.trimIndent()

        given()
            .header("Content-type", "application/json")
            .body(putBody)
            .`when`()
            .put("/person/$id")
            .then()
            .statusCode(400)
    }

}

