/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller

import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Produces("application/json")
@Consumes("application/json")
@Path("/phone")
@Tag(name = "Phone Controller", description = "Phone related APIs")
class PhoneController
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons")
    fun allPhones(): Response
    {
        return Response
            .ok(listOf("1", "2", 3))
            .build()
    }
}