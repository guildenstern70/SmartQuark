/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-22
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dto

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.enterprise.context.SessionScoped

@SessionScoped
@RegisterForReflection
data class ResultDTO(
    val operationResult: String,
    val message: String
)
