package com.mycompany.simulator.app

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT
import org.springframework.boot.test.web.server.LocalManagementPort
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration

@SpringBootTest(webEnvironment = DEFINED_PORT)
internal class SimulatorApplicationTest {
    @LocalServerPort
    private var serverPort: Int = -1

    @LocalManagementPort
    private var managementPort: Int = -1

    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp() {
        webTestClient = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:$managementPort")
            .responseTimeout(Duration.ofSeconds(5))
            .build()
    }

    @Test
    fun `should listen on port 8080`() {
        assertThat(serverPort).isEqualTo(8080)
        assertThat(managementPort).isEqualTo(8080)
    }

    @Test
    fun `should expose liveness endpoint`() {
        webTestClient
            .get()
            .uri("actuator/health/liveness")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.status").isEqualTo("UP")
    }

    @Test
    fun `should expose readiness endpoint`() {
        webTestClient
            .get()
            .uri("actuator/health/readiness")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.status").isEqualTo("UP")
    }
}
