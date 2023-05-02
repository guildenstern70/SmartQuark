/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*

@Suppress("JpaObjectClassSignatureInspection")
@Entity
@RegisterForReflection
data class Person(
        var name: String,
        var surname: String,
        var age: Int
)
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    private var phoneNumbers: MutableSet<Phone> = mutableSetOf()

    @JsonIgnore
    fun getPhones() = this.phoneNumbers

    fun setPhones(phones: Set<Phone>)
    {
        phones.forEach { this.addPhone(it) }
    }

    fun addPhone(phone: Phone)
    {
        phone.person = this
        this.phoneNumbers.add(phone)
    }

    fun removePhone(phone: Phone)
    {
        this.phoneNumbers.remove(phone)
        phone.person = null
    }
}
