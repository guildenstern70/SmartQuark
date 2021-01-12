/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.model

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.*

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
    var phoneNumbers: MutableSet<Phone> = mutableSetOf()
}
