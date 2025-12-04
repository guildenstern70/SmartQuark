/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.rest

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import net.littlelite.smartquark.service.PersonService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import net.littlelite.smartquark.dto.PhoneDTO

@Produces("application/json")
@Consumes("application/json")
@Path("/phone")
@Tag(name = "Phone Controller", description = "Phone related APIs")
class PhoneController : BaseRestController()
{
    @Inject
    lateinit var personService: PersonService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all phones")
    fun allPhones(): Response
    {
        return Response
            .ok(this.personService.getAllPhones())
            .build()
    }

    @GET
    @Path("/person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all phone numbers for a person")
    fun getPhonesByPerson(@PathParam("id") id: Int): Response
    {
        val phones = this.personService.getPhonesByPersonId(id) ?: return this.notFound("Person")
        return Response.ok(phones).build()
    }

    @PUT
    @Path("/person/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Replace a person's phone numbers with the provided list")
    fun replacePhones(@PathParam("id") id: Int, phones: List<PhoneDTO>): Response
    {
        val updated = this.personService.replacePhones(id, phones) ?: return this.notFound("Person")
        return Response.ok(updated).build()
    }
}