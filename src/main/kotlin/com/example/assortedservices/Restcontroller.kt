package com.example.assortedservices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.net.URISyntaxException
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

    //dataRevicer
    var outorTemperature = "0.0"


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
    fun setLivePowerUsage(@RequestParam liveUsage:String){
        powerUse = liveUsage
        println("Live Power Usage: " + liveUsage)
    }
    @GetMapping ("api/power/live")
    fun apiPowerLive():String {
        return powerUse
    }
    @GetMapping ("api/getOutdoorTemperature")
    fun getOutdoorTemperature () : String {
        return outorTemperature
    }
    @GetMapping ("api/wether/setOutdoorTemperature")
    fun setOutdoorTemperature(@RequestParam outdoorTemp:String){
        outorTemperature = outdoorTemp
        println("Outdoor Temp Is: " + outdoorTemp)
    }

}