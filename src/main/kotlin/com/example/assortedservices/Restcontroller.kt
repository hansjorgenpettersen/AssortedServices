package com.example.assortedservices

import org.springframework.web.bind.annotation.GetMapping

class Restcontroller {

    @GetMapping("/")
    fun defultReplay():String {
        return "Hello Webhuset"
    }

}