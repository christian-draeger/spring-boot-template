package com.tsi.dv.starter.logger

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties


private const val DV_LOGGER_PROPERTY_PREFIX = "dv.logger"

@AutoConfiguration
@EnableConfigurationProperties(DvLoggerConfigurationProperties::class)
// @Order(Ordered.LOWEST_PRECEDENCE)
@ConditionalOnProperty(prefix = DV_LOGGER_PROPERTY_PREFIX, name = ["enabled"], havingValue = "true")
open class DvLoggerConfig {
    init {
        logger.info { "----> LoggerConfig loaded" }
    }

}

@ConfigurationProperties(prefix = DV_LOGGER_PROPERTY_PREFIX)
data class DvLoggerConfigurationProperties(
    var enabled: Boolean = true,
)
