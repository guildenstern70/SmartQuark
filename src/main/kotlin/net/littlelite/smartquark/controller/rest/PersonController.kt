/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.rest

import net.littlelite.smartquark.dto.PersonDTO
import net.littlelite.smartquark.dto.ResultDTO
import net.littlelite.smartquark.model.Person
import net.littlelite.smartquark.service.PersonService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder


@Path("/person")
@Tag(name = "Person Controller", description = "Person related APIs")
class PersonController : BaseRestController()
{
    private val logger: Logger = LoggerFactory.getLogger(PersonService::class.java)

    @Inject
    lateinit var personService: PersonService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons")
    fun allPersons(): Response
    {
        return Response
            .ok(this.personService.getAllPersons())
            .build()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons")
    fun getOnePerson(@PathParam("id") id: Int): Response
    {
        val person = this.personService.getPerson(id) ?: return this.notFound("Person")
        logger.info("Person found")
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
        if (persons.isEmpty()) return this.notFound("Persons")
        return Response.ok(persons).build()
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a person")
    fun createPerson(personDTO: PersonDTO): Response
    {
        val createdPerson: Person = this.personService.addPerson(personDTO)
        return Response.created(
                UriBuilder
                        .fromResource(Person::class.java)
                        .path(createdPerson.id.toString())
                        .build()
                )
                .entity(createdPerson)
                .build()
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a person")
    fun deletePerson(@PathParam("id") id: Int): Response
    {
        val deletedOk = this.personService.deletePerson(id)
        val result = if (deletedOk) ResultDTO("OK", "Person deleted")
            else ResultDTO("KO", "Person was not deleted")
        return Response.ok(result).build()
    }
}