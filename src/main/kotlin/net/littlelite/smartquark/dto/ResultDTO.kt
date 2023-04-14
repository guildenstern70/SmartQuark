/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-23
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import io.quarkus.runtime.annotations.RegisterForReflection
import kotlinx.serialization.Serializable
import javax.enterprise.context.SessionScoped

@SessionScoped
@RegisterForReflection
@Serializable
data class ResultDTO(
    val operationResult: String,
    val message: String
)
