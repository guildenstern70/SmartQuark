/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.web

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import net.littlelite.smartquark.Main
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class WebController
{
    @Inject
    @field: Default
    lateinit var index: Template

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun homePage(): TemplateInstance
    {
        return this.index.data("version", Main.VERSION);
    }

}