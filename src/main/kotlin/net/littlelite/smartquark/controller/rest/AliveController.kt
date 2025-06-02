/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.rest

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Path("/alive")
@Tag(name = "Alive Controller", description = "Health related APIs")
class AliveController
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun alive() = "{ \"alive\": true }"
}
