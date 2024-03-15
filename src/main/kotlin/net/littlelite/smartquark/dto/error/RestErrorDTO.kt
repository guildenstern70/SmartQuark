/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-24
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto.error

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

open class RestErrorDTO() : Serializable
{
    var timestamp: String
    var status: String
    var error: String
    var message: String
    var path: String

    init
    {
        timestamp = toNowDateString()
        message = ""
        path = ""
        error = ""
        status = ""
    }

    protected constructor(
            message: String,
            path: String
    ): this() {
        this.message = message
        this.path = path
    }

    fun toJson(): String
    {
        return ObjectMapper().writeValueAsString(this)
    }

    private fun toNowDateString(): String {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        return df.format(Date())
    }

}