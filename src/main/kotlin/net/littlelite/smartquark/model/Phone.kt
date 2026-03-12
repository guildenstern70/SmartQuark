/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-26
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*

@Entity
@RegisterForReflection
class Phone(
    var prefix: String,
    var number: String
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @ManyToOne
    var person: Person? = null
}
