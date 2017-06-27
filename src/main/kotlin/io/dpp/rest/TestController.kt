package io.dpp.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController
{
  @RequestMapping("/test")
  fun greeting(): String
  {
    return "SUCCESS"
  }
}