package org.vva.comps.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.vva.comps.models.Computers
import java.io.File

object Config {
    private val mapper = jacksonObjectMapper()

    val computers = mapper.readValue<Computers>(
        File("computers.json"),
        Computers::class.java)
}
