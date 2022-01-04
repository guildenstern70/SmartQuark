/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-22
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.rest

import net.littlelite.smartquark.dto.error.InternalErrorDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class RestExceptionMapper : ExceptionMapper<Throwable>
{
    private val logger: Logger = LoggerFactory.getLogger(RestExceptionMapper::class.java)

    override fun toResponse(exc: Throwable): Response
    {
        logger.error(exc.localizedMessage)
        exc.printStackTrace()
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