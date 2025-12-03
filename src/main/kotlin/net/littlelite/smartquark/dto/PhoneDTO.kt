/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import net.littlelite.smartquark.model.Phone

data class PhoneDTO(
    val prefix: String = "",
    val number: String = ""
    )
{
    fun toPhone(): Phone = Phone(this.prefix, this.number)

    companion object
    {
        fun fromPhone(phone: Phone): PhoneDTO
        {
            return PhoneDTO(phone.prefix, phone.number)
        }
    }
}