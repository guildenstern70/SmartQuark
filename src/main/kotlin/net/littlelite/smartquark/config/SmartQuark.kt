/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-22
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.config

import io.smallrye.config.ConfigMapping

@ConfigMapping(prefix = "smartquark")
interface SmartQuark
{
    fun version(): String?
}
