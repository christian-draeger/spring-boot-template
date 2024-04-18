package com.tsi.dv.backend.service

import com.tsi.dv.backend.persistence.greeting.GreetingModel
import com.tsi.dv.backend.persistence.greeting.GreetingRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class GreetingService(
    private val greetingRepository: GreetingRepository
) {
    fun create(name: String): GreetingModel {
        val greeting = "Hello, $name!"
        return greetingRepository
            .save(GreetingModel(name, greeting))
    }

    fun show(id: String): GreetingModel =
        greetingRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Greeting not found")

    fun showByName(name: String): List<GreetingModel> =
        greetingRepository.findByName(name) ?: throw IllegalArgumentException("Greeting not found")

    fun showAll(): List<GreetingModel> = greetingRepository.findAll()
}
