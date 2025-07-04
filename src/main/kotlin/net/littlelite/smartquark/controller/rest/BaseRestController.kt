/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.rest

import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriInfo
import net.littlelite.smartquark.dto.error.BadRequestDTO
import net.littlelite.smartquark.dto.error.NotFoundDTO

@Produces(MediaType.APPLICATION_JSON)
abstract class BaseRestController
{
    @Context
    private val uriInfo: UriInfo? = null

    protected fun notFound(resourceName: String): Response
    {
        val path = uriInfo!!.path
        val message = "$resourceName not found"
        val errorResponse = NotFoundDTO(
                message,
                path)
        return Response
                .status(404, message)
                .entity(errorResponse.toJson())
                .build()
    }

    protected fun badRequest(resourceName: String): Response
    {
        val path = uriInfo!!.path
        val message = "$resourceName bad request"
        val errorResponse = BadRequestDTO(
                message,
                path)
        return Response
                .status(400, message)
                .entity(errorResponse.toJson())
                .build()
    }
}