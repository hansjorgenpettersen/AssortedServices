package com.example.assortedservices

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class Restcontroller {

    @GetMapping("/")
    fun defultReplay():String {
        return "Hello Webhuset"
    }

    @GetMapping ("random")
    fun getRandomNumber():String {
        var randomNumber = Random.nextInt(0,1000)
        return randomNumber.toString()
    }

}