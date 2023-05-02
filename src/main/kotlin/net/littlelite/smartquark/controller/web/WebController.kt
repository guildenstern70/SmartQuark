/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.web

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import net.littlelite.smartquark.config.SmartQuark
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/")
class WebController
{
    @Inject
    @field: Default
    lateinit var index: Template

    @Inject
    lateinit var smartquark: SmartQuark

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun homePage(): TemplateInstance
    {
        return this.index.data("version", smartquark.version());
    }

}