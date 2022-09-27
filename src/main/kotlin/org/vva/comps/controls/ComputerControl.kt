package org.vva.comps.controls

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.vva.comps.config.Config.computers
import org.vva.comps.models.CompsWithPing
import org.vva.comps.models.CompsWithPorts
import org.vva.comps.models.Computers
import org.vva.comps.service.ComputerService

@RestController
@RequestMapping("api/comps")
@CrossOrigin
class ComputerControl {
    @Autowired
    lateinit var computerService: ComputerService

    @GetMapping
    fun getComps(): Computers {
        return computers
    }

    @GetMapping("/ping")
    suspend fun getCompsWithPing(): CompsWithPing {
        return computerService.getCompsWithPing()
    }

    @GetMapping("/ports")
    suspend fun getCompsWithPorts(): CompsWithPorts {
        return computerService.getCompsWithPorts()
    }
}