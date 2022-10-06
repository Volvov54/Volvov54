package org.vva.comps.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.vva.comps.models.Computers
import java.io.File

object Config {
    private val mapper = jacksonObjectMapper()

    var computers = readComputers()

    private fun readComputers(): Computers {
        val comps = mapper.readValue<Computers>(
            File("computers.json"),
            Computers::class.java
        )
        if (comps == null) return Computers(ArrayList())
        else return comps
    }

    fun saveComps() {
        val dataSorted = computers.data.sortedBy { it.name }
        val compsSorted = Computers(ArrayList())
        dataSorted.forEach { compsSorted.data.add(it) }
        mapper.writerWithDefaultPrettyPrinter().writeValue(
            File("computers.json"),
            compsSorted
        )
        computers = readComputers()
    }
}
