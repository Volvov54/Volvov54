package org.vva.comps.controls

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun Hello(): String {
        return "Hello world"
    }
}