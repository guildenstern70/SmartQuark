/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto.error

class BadRequestDTO(message: String?, path: String?) : RestErrorDTO(message!!, path!!)
{
    init
    {
        status = "400"
        error = "Bad Request"
    }
}