/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-22
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto.error

class InternalErrorDTO(message: String?, path: String?) : RestErrorDTO(message!!, path!!)
{
    init
    {
        status = "500"
        error = "Internal Server Error"
    }
}
