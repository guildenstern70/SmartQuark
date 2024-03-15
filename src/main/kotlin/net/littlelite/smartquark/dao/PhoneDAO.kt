/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-24
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.dao

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import net.littlelite.smartquark.model.Phone
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PhoneDAO : PanacheRepositoryBase<Phone, Int>
