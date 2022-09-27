package org.vva.comps.service

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import org.vva.comps.config.Config.computers
import org.vva.comps.models.CompsWithPing
import org.vva.comps.models.CompsWithPorts
import org.vva.comps.models.ComputerWithPing
import org.vva.comps.models.ComputerWithPorts
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

@Service
class ComputerService {
    suspend fun getCompsWithPing(): CompsWithPing {
        val compsWithPing = CompsWithPing()
        coroutineScope {
            for (comp in computers.data) {
                launch {
                    println("comp name = ${comp.name}")
                    val compWithPing = ComputerWithPing(
                        comp.name,
                        comp.login,
                        comp.ip,
                        comp.commentary,
                        getPing(comp.ip)
                    )
                    compsWithPing.data.add(compWithPing)
                }
            }
        }
        return compsWithPing
    }

    suspend fun getCompsWithPorts(): CompsWithPorts {
        val compsWithPorts = CompsWithPorts()
        coroutineScope {
            for (comp in computers.data) {
                launch {
                    println("comp name = ${comp.name}")
                    val compWithPorts = ComputerWithPorts(
                        comp.name,
                        comp.login,
                        comp.ip,
                        comp.commentary,
                        getPing(comp.ip),
                        getPorts(comp.ip, 139),
                        getPorts(comp.ip, 2000),
                        getPorts(comp.ip, 3389),
                        getPorts(comp.ip, 4899),
                    )
                    compsWithPorts.data.add(compWithPorts)
                }
            }
        }
        return compsWithPorts
    }

    private fun getPorts(ip: String, port: Int): Boolean {
        try {
            val socket = Socket()
            socket.connect(InetSocketAddress(ip, port), 5)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun getPing(ipAddress: String): Boolean {
        val geek = InetAddress.getByName(ipAddress)
        if (geek.isReachable(1000))
            return true
        else
            return false
    }
}