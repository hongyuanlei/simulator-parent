package com.mycompany.simulator.app

import com.mycompany.simulator.api.APIConfiguration
import com.mycompany.simulator.authorisation.AuthorisationConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    APIConfiguration::class,
    AuthorisationConfiguration::class
)
class SimulatorApplication

fun main(args: Array<String>) {
    runApplication<SimulatorApplication>(*args)
}
