/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-25
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import net.littlelite.smartquark.model.Person

/** DTO for PATCH partial updates — all fields nullable */
data class PatchPersonDTO(
    val name: String? = null,
    val surname: String? = null,
    val age: Int? = null,
    val phones: Set<PhoneDTO>? = null
)
{
    fun applyTo(person: Person)
    {
        this.name?.let { person.name = it }
        this.surname?.let { person.surname = it }
        this.age?.let { person.age = it }
        this.phones?.let {
            // replace phones completely
            // remove existing
            val existing = person.getPhones().toList()
            existing.forEach { person.removePhone(it) }
            // add new
            it.forEach { person.addPhone(it.toPhone()) }
        }
    }
}

