package com.tsi.dv.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.resource.NoResourceFoundException

@ControllerAdvice
class ErrorControllerHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleEntryIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<*> =
        ResponseEntity(
            ErrorMessageDTO(
                ex.message,
            ),
            HttpStatus.BAD_REQUEST,
        )

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleEntryIllegalArgumentException(ex: NoResourceFoundException): ResponseEntity<*> =
        ResponseEntity(
            ErrorMessageDTO(
                ex.message,
            ),
            HttpStatus.NOT_FOUND,
        )

    data class ErrorMessageDTO(
        val message: String?,
    )
}