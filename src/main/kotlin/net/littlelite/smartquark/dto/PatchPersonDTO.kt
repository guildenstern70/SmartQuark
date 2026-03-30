/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-26
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import net.littlelite.smartquark.model.Person

/** DTO for PATCH partial updates — all fields nullable */
data class PatchPersonDTO(
    @field:Size(min = 1)
    val name: String? = null,
    @field:Size(min = 1)
    val surname: String? = null,
    @field:Min(0)
    val age: Int? = null,
    val phones: Set<@Valid PhoneDTO>? = null
)
{
    fun applyTo(person: Person)
    {
        this.name?.let { person.name = it }
        this.surname?.let { person.surname = it }
        this.age?.let { person.age = it }
        this.phones?.let { dTOS ->
            // replace phones completely
            // remove existing
            val existing = person.getPhones().toList()
            existing.forEach { person.removePhone(it) }
            // add new
            dTOS.forEach { person.addPhone(it.toPhone()) }
        }
    }
}

