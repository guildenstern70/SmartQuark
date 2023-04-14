/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.rest

import net.littlelite.smartquark.dto.error.BadRequestDTO
import net.littlelite.smartquark.dto.error.NotFoundDTO
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

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