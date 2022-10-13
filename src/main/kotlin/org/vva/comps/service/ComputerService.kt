package org.vva.comps.service

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import org.vva.comps.config.Config
import org.vva.comps.config.Config.computers
import org.vva.comps.models.*
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
        return geek.isReachable(1000)
    }

    fun getComp(name: String): Computer {
        return computers.data.first { it.name == name }
    }

    fun addComp(newComp: Computer): Computer {
        computers.data.add(newComp)
        Config.saveComps()
        return newComp
    }

    fun updateComp(name: String, updComp: Computer): Computer {
        val comp = computers.data.first { it.name == name }
        computers.data.remove(comp)
        computers.data.add(updComp)
        Config.saveComps()
        return updComp
    }
}