package com.tsi.dv.starter.logger

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertySource
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.util.Assert
import java.io.IOException

class DvLoggerEnvironmentPostProcessor : EnvironmentPostProcessor {

    init {
        logger.info { "----> DvLoggerEnvironmentPostProcessor loaded" }
    }

    private val loader = YamlPropertySourceLoader()

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        val path: Resource = ClassPathResource("dv-config-logger.yml")
        val propertySource = loadYaml(this::class.simpleName!!, path)

        environment.propertySources.addFirst(propertySource).also {
            logger.info { "----> set DvSwagger config defaults" }
        }
    }

    private fun loadYaml(name: String, path: Resource): PropertySource<*> {
        Assert.isTrue(path.exists()) { "Resource $path does not exist" }
        return try {
            loader.load(name, path)[0]
        } catch (ex: IOException) {
            throw IllegalStateException("Failed to load yaml configuration from $path", ex)
        }
    }
}
