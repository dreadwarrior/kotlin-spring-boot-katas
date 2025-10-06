package de.dreadlabs.kotlinspringbootkatas

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<KotlinSpringBootKatasApplication>().with(TestcontainersConfiguration::class).run(*args)
}
