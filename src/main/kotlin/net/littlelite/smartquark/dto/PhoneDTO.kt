/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-26
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import net.littlelite.smartquark.model.Phone

data class PhoneDTO(
    @field:NotBlank
    val prefix: String = "",
    @field:NotBlank
    @field:Pattern(regexp = "^\\d{6,15}$")
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