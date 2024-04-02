package com.jagaldol.myfitness.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/users")
    fun getUsers(): String {

        val myFunc: () -> String = { "hello World" }

        return "hello World"
    }
}