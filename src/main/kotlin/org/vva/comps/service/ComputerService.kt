package org.vva.comps.service

import com.lordcodes.turtle.ShellRunException
import com.lordcodes.turtle.shellRun
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import org.vva.comps.config.Config
import org.vva.comps.config.Config.computers
import org.vva.comps.models.*
import java.io.IOException
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
                    println("${comp.name}")
                    val p3389 = getPorts(comp.ip, 3389)
                    val active = if (p3389) {
                        getActiveCompList(getSessionList(comp.name))
                    } else {
                        ArrayList<Pair<String, String>>()
                    }
                    val compWithPorts = ComputerWithPorts(
                        comp.name,
                        comp.login,
                        comp.ip,
                        comp.commentary,
                        getPing(comp.ip),
                        getPorts(comp.ip, 139),
                        getPorts(comp.ip, 445),
                        getPorts(comp.ip, 2000),
                        p3389,
                        getPorts(comp.ip, 4899),
                        active
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

    private fun getActiveCompList(sessionList: String): ArrayList<Pair<String, String>> {
        val res = ArrayList<Pair<String, String>>()
        val list = sessionList.lines()
        if (list.size < 4) {
            return res
        }
        println(list[3])
        for (i in 5..list.size - 1) {
            val sessionName = list[i].substring(1, 18).trim()
            val userName = list[i].substring(19, 39).trim()
            val state = list[i].contains("Active")
            if (state) {
                res.add(Pair(userName, sessionName))
            }
        }
        return res
    }

    private fun getSessionList(compName: String): String {
        try {
            val output = shellRun("lines.bat", listOf(compName))
            return output
        } catch (e1: IOException) {
        } catch (e2: ShellRunException) {
        }
        return ""
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