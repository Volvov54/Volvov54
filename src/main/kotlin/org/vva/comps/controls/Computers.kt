package org.vva.comps.controls

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.vva.comps.config.Config.computers
import org.vva.comps.models.Computers

@RestController
@RequestMapping("comp")
class Computers {
    @GetMapping
    fun getComps(): Computers {
        return computers
    }
}