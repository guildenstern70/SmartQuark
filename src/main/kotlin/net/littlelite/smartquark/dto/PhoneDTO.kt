/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import io.quarkus.runtime.annotations.RegisterForReflection
import kotlinx.serialization.Serializable
import net.littlelite.smartquark.model.Phone
import jakarta.enterprise.context.SessionScoped

@RegisterForReflection
@SessionScoped
@Serializable
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