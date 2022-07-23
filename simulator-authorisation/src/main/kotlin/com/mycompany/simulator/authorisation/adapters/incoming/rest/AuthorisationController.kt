package com.mycompany.simulator.authorisation.adapters.incoming.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/authorisation")
class AuthorisationController {
    @PostMapping("/start")
    fun start(): ResponseEntity<Void> {
        return ResponseEntity.noContent().build()
    }
}
