package net.littlelite.controller

import io.quarkus.qute.Template
import io.quarkus.qute.TemplateInstance
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
        return this.index.data("version", "0.0.1");
    }

}