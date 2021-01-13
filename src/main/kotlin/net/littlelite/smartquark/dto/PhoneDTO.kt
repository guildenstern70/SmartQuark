/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import io.quarkus.runtime.annotations.RegisterForReflection
import net.littlelite.smartquark.model.Person
import net.littlelite.smartquark.model.Phone

@RegisterForReflection
data class PhoneDTO(
    val prefix: String,
    val number: String
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