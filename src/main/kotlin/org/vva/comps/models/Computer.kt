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



class Computers(var data: List<Computer>)

class CompsWithPing(var data: ArrayList<ComputerWithPing> = ArrayList<ComputerWithPing>())