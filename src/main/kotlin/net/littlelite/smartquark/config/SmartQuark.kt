package net.littlelite.smartquark.config

import io.smallrye.config.ConfigMapping

@ConfigMapping(prefix = "smartquark")
interface SmartQuark
{
    fun version(): String?
}
