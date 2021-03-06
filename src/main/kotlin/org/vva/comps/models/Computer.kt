package org.vva.comps.models

import java.util.Arrays

data class Computer(
    val name: String,
    val login: String,
    val ip: String
)

data class ComputerWithPing(
    val name: String,
    val login: String,
    val ip: String,
    val ping: Boolean
)

data class ComputerWithPorts(
    val name: String,
    val login: String,
    val ip: String,
    val ping: Boolean,
    val p139: Boolean,
    val p2000: Boolean,
    val p3389: Boolean,
    val p4899: Boolean
)



class Computers(var data: List<Computer>)

class CompsWithPing(var data: ArrayList<ComputerWithPing> = ArrayList())

class CompsWithPorts(var data: ArrayList<ComputerWithPorts> = ArrayList())