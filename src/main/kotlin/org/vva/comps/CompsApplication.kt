package org.vva.comps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CompsApplication

fun main(args: Array<String>) {
    runApplication<CompsApplication>(*args)
}
