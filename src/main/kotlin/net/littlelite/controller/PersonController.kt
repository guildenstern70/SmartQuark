package net.littlelite.controller

import net.littlelite.dto.PersonDTO
import net.littlelite.model.Person
import net.littlelite.service.PersonService
import org.eclipse.microprofile.openapi.annotations.Operation
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Produces("application/json")
@Consumes("application/json")
@Path("/person")
class PersonController
{
    @Inject
    lateinit var personService: PersonService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons")
    fun allPersons(): List<Person>
    {
        return this.personService.getAllPersons()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons")
    fun getOnePerson(@PathParam("id") id: Int): Response
    {
        val person = this.personService.getPerson(id) ?:
            return Response.status(404).build()

        return Response.ok(person).build()
    }

    @GET
    @Path("ageMax/{ageMax}/ageMin/{ageMin}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get persons in age range")
    fun getPersonsByAge(@PathParam("ageMax") ageMax: Int,
                        @PathParam("ageMin") ageMin: Int): Response
    {
        val persons = this.personService.findByAge(ageMin, ageMax)
        if (persons.isEmpty())
            return Response.status(404).build()

        return Response.ok(persons).build()
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a person")
    fun createPerson(personDTO: PersonDTO): Person
    {
        return this.personService.addPerson(personDTO)
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a person")
    fun deletePerson(@PathParam("id") id: Int): Response
    {
        this.personService.deletePerson(id)
        return Response.ok().build()
    }
}