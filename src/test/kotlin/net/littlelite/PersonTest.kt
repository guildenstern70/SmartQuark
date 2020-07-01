package net.littlelite

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.startsWith
import org.junit.jupiter.api.Test

@QuarkusTest
class PersonTest
{

    @Test
    fun testPersonEndpoint()
    {
        given()
                .`when`().get("/person")
                .then()
                .statusCode(200)
                .body(`startsWith`("[{\"name\":\"Alessio\",\"surname\":\"Saltarin\",\"age\""))
    }

}