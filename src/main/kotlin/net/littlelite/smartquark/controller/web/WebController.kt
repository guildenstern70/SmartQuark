/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-26
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.controller.web

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
import io.quarkus.runtime.configuration.ConfigUtils
import jakarta.inject.Inject
import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import net.littlelite.smartquark.config.SmartQuark

@Path("/")
@RequestScoped
class WebController @Inject constructor(private val index: Template,
    private val smartQuark: SmartQuark)
{
    @GET
    @Produces(MediaType.TEXT_HTML)
    fun homePage(): TemplateInstance
    {
        val profiles = ConfigUtils.getProfiles()
        var profile = "?"
        if (profiles.isNotEmpty()) {
            profile = profiles[0].toString()
        }
        return this.index
            .data("version", smartQuark.version())
            .data("profile", profile)
    }

}