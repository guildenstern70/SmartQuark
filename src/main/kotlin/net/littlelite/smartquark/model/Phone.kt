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
class Phone(
    var prefix: String,
    var number: String
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @ManyToOne
    var person: Person? = null
}
