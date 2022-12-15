package org.vva.comps.models

data class Computer(
    val name: String,
    val login: String,
    val ip: String,
    val commentary: String?
)

data class ComputerWithPing(
    val name: String,
    val login: String,
    val ip: String,
    val commentary: String?,
    val ping: Boolean
)

data class ComputerWithPorts(
    val name: String,
    val login: String,
    val ip: String,
    val commentary: String?,
    val ping: Boolean,
    val p139: Boolean,
    val p445: Boolean,
    val p2000: Boolean,
    val p3389: Boolean,
    val p4899: Boolean,
    val active: ArrayList<Pair<String,String>>
)

class Computers(var data: MutableList<Computer>)

class CompsWithPing(var data: ArrayList<ComputerWithPing> = ArrayList())

class CompsWithPorts(var data: ArrayList<ComputerWithPorts> = ArrayList())