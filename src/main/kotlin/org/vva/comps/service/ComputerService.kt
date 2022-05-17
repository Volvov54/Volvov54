package org.vva.comps.service

import org.springframework.stereotype.Service
import org.vva.comps.config.Config.computers
import org.vva.comps.models.CompsWithPing
import org.vva.comps.models.ComputerWithPing
import java.net.InetAddress

@Service
class ComputerService {
    fun getCompsWithPing(): CompsWithPing {
        val compsWithPing = CompsWithPing()
        for (comp in computers.data) {
            println("comp name = ${comp.name}")
            val compWithPing = ComputerWithPing(
               comp.name,
               comp.login,
               comp.ip,
               getPing(comp.ip)
            )
            compsWithPing.data.add(compWithPing)
        }
        return compsWithPing
    }

    private fun getPing(ipAddress: String): Boolean {
        val geek = InetAddress.getByName(ipAddress)
        if (geek.isReachable(1000))
            return true
        else
            return false
    }
}