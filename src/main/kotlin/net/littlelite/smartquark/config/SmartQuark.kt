/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.config

import io.smallrye.config.ConfigMapping
import io.smallrye.config.WithDefault

@ConfigMapping(prefix = "smartquark")
interface SmartQuark
{
    @WithDefault("SmartQuark")
    fun name(): String?

    @WithDefault("0.9.0")
    fun version(): String?
}
