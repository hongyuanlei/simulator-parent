package com.mycompany.simulator.api.adapters.incoming.rest.validation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import javax.validation.Validator

@Configuration
class ValidationConfiguration {

    @Bean
    fun validator(): Validator {
        return LocalValidatorFactoryBean()
    }
}
