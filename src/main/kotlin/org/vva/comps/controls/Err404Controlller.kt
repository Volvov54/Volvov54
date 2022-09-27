package org.vva.comps.controls

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@Controller
class MyErrorController : ErrorController {
    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest): String {
//        println("handleError()")
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
//        println("status = ${status}")
        if (status != null) {
            val statusCode = Integer.valueOf(status.toString())
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "/error-404.html"
            }
        }
        return "/error.html"
    }
}