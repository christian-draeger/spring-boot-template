package com.tsi.dv.backend.config

import com.tsi.dv.starter.logger.logger
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service

@Service
class ApplicationBootstrap {
    @EventListener(ApplicationReadyEvent::class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun onApplicationReadyEvent() {
        logger.info { "start Bootstrapping Application..." }

        // TODO: Add your bootstrapping logic here

        logger.info { "end Bootstrapping Application!" }
    }
}
