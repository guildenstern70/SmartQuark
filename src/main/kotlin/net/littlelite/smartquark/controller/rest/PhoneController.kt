/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.rest

import net.littlelite.smartquark.service.PersonService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

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
}