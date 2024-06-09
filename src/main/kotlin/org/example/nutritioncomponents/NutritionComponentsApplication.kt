package org.example.nutritioncomponents

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class NutritionComponentsApplication

fun main(args: Array<String>) {
    runApplication<NutritionComponentsApplication>(*args)
}
