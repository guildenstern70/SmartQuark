package net.littlelite.model

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@RegisterForReflection
data class Person(
        var name: String,
        var surname: String,
        var age: Int
)
{
    constructor() : this("?", "?", 0)

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
}
