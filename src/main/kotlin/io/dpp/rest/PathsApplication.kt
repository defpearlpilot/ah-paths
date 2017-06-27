package io.dpp.rest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class PathsApplication {
  companion object {
    @JvmStatic fun main(args: Array<String>) {
      SpringApplication.run(PathsApplication::class.java, *args)
    }
  }
}