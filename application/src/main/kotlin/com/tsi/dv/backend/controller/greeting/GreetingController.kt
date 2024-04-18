package com.tsi.dv.backend.controller.greeting

import com.tsi.dv.backend.persistence.greeting.GreetingModel
import com.tsi.dv.backend.service.GreetingService
import com.tsi.dv.starter.apidoc.SWAGGER_SECURITY_SCHEME_NAME
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/greeting/v1")
@SecurityRequirement(name = SWAGGER_SECURITY_SCHEME_NAME)
@CrossOrigin(
    originPatterns = [
        "http://localhost:[3000,3001,3002,3003,3004]",
    ],
)
class GreetingController(
    private val greetingService: GreetingService
) {

    @GetMapping("/hello/name/{name}")
    fun greetingsByName(@PathVariable name: String) = greetingService.showByName(name).toResponse()

    @GetMapping("/hello/id/{id}")
    fun greeting(@PathVariable id: String) = greetingService.show(id).toResponse()

    @GetMapping("/hello/all")
    fun allGreetings() = greetingService.showAll().toResponse()

    @PostMapping("/hello/{name}")
    fun sayHello(
        @PathVariable name: String
    ) = greetingService.create(name).let {
        ResponseEntity.created(URI.create("/api/greeting/v1/hello/id/${it.id}")).body(it.toResponse())
    }
}

data class GreetingResponse(
    val message: String,
    val created: String,
    val updated: String?,
)

fun GreetingModel.toResponse() = GreetingResponse(
    message = message,
    created = created.toString(),
    updated = updated?.toString(),
)

fun List<GreetingModel>.toResponse() = map(GreetingModel::toResponse)