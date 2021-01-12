/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.config

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Contact
import org.eclipse.microprofile.openapi.annotations.info.Info
import org.eclipse.microprofile.openapi.annotations.info.License
import javax.ws.rs.core.Application

@OpenAPIDefinition(
    info = Info(
        title="SmartQuarkus API",
        version = "1.0.1",
        contact = Contact(
            name = "Support",
            url = "https://quarkus.io/",
            email = "techsupport@example.com"),
        license = License(
            name = "Licensed under MIT License",
            url = "https://opensource.org/licenses/MIT"))
)
class OpenApiConfig : Application()

