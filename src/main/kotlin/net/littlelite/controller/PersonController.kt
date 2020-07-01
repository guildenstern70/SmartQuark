package net.littlelite.controller

import net.littlelite.dto.PersonDTO
import net.littlelite.service.PersonService
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Produces("application/json")
@Consumes("application/json")
@Path("/person")
class PersonController
{
    @Inject
    lateinit var personService: PersonService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun allPersons(): List<PersonDTO>
    {
        return this.personService.getAllPersons()
    }
}