package com.example.assortedservices

import org.apache.tomcat.util.http.parser.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Component
import org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import kotlin.random.Random

@Component
@RestController
class Restcontroller {

    //GET IP

    private var request: HttpServletRequest? = null

    @Autowired
    fun setRequest(request: HttpServletRequest?) {
        this.request = request
    }
    //GET IP

    var homeIp = "Not Set"
    var powerUse = "0"

    @GetMapping("/")
    fun defultReplay():String {
        return "Hello Webhuset"
    }

    @GetMapping ("random")
    fun getRandomNumber():String {
        var randomNumber = Random.nextInt(0,1000)

        println("Random number : " + randomNumber)

        return randomNumber.toString()
    }

    @GetMapping("/remoteIpAddress")
    fun getClientIp(): String? {
        var remoteAddr: String? = ""
        if (request != null) {
            remoteAddr = request!!.getHeader("X-FORWARDED-FOR")
            if (remoteAddr == null || "" == remoteAddr) {
                remoteAddr = request!!.remoteAddr
            }
        }

        println("Remote IP-Address: " + remoteAddr)

        return remoteAddr
    }
    @GetMapping("/setHomeAddress")
    fun setHomeAddress(): String? {
        var remoteAddr: String? = ""
        if (request != null) {
            remoteAddr = request!!.getHeader("X-FORWARDED-FOR")
            if (remoteAddr == null || "" == remoteAddr) {
                remoteAddr = request!!.remoteAddr
            }
        }

        homeIp = remoteAddr.toString()

        println("Home Address Is: " + remoteAddr)

        return remoteAddr
    }
    @GetMapping("/getHomeIP")
    fun getHomeIP():String {
        return homeIp
    }

    @GetMapping ("api/power/setLivePowerUsage")
    fun createNewStudent(@RequestParam liveUsage:String){
        powerUse = liveUsage
        println("Live Power Usage: " + liveUsage)
    }

    @GetMapping ("api/power/live")
    fun apiPowerLive():String {
        return powerUse
    }
}