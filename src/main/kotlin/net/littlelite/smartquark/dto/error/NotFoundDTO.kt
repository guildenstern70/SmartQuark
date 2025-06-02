/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto.error

class NotFoundDTO(message: String, path: String) : RestErrorDTO(message, path)
{
    init
    {
        status = "404"
        error = "Not found"
    }
}