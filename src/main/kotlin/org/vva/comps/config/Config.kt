package org.vva.comps.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.util.ResourceUtils
import org.vva.comps.models.Computers

object Config {
    private val mapper = jacksonObjectMapper()

    val computers = mapper.readValue<Computers>(
        ResourceUtils.getFile("classpath:computers.json"),
        Computers::class.java)
}
