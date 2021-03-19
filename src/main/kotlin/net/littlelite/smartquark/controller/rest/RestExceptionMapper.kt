/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.rest

import net.littlelite.smartquark.dto.error.InternalErrorDTO
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class RestExceptionMapper : ExceptionMapper<Throwable>
{
    override fun toResponse(exc: Throwable): Response
    {
        val message = exc.message
        val errorResponse = InternalErrorDTO(
                message,
                "")
        return Response
                .status(500, message)
                .entity(errorResponse.toJson())
                .build()
    }
}