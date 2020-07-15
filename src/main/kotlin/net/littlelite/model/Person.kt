package net.littlelite.model

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@RegisterForReflection
data class Person(
        val name: String,
        val surname: String,
        val age: Int
)
{
    constructor() : this("?", "?", 0)

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
}
