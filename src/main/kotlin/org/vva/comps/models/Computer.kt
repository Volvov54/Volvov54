package org.vva.comps.models

data class Computer(
    val name: String,
    val login: String,
    val ip: String
)

data class Computers(var data: List<Computer>)