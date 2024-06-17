package net.sharpdev.boilerplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BoilerplateKotlinApplication

fun main(args: Array<String>) {
    runApplication<BoilerplateKotlinApplication>(*args)
}
