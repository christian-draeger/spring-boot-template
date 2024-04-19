package com.tsi.dv.starter.apidoc

import com.tsi.dv.starter.logger.logger
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

const val SWAGGER_SECURITY_SCHEME_NAME = "Bearer Authentication"
private const val DV_APIDOC_PROPERTY_PREFIX = "dv.swagger"

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = DV_APIDOC_PROPERTY_PREFIX, name = ["enabled"], havingValue = "true")
@AutoConfiguration
@EnableConfigurationProperties(DvSwaggerConfigurationProperties::class)
@SecurityScheme(
    name = SWAGGER_SECURITY_SCHEME_NAME,
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
open class DvSwaggerConfig(
    private val properties: DvSwaggerConfigurationProperties
) {

    init {
        logger.info { "----> DvSwaggerConfig loaded" }
    }

    @Bean("dvOpenAPI")
    open fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(Info().title(properties.apiTitle).version(properties.apiVersion))
    }

    @Bean("dvSwaggerApiGroups")
    open fun apis(): List<GroupedOpenApi> {
        logger.info { "###########" + properties.apiGroups?.entries }
        return properties.apiGroups?.entries?.map {
            GroupedOpenApi.builder()
                .group(it.key)
                .pathsToMatch(it.value)
                .build()
        } ?: listOf(
            GroupedOpenApi.builder()
                .group("default")
                .pathsToMatch("/**")
                .build()
        )
    }
}

@ConfigurationProperties(prefix = DV_APIDOC_PROPERTY_PREFIX)
data class DvSwaggerConfigurationProperties(
    var enabled: Boolean = true,
    var apiTitle: String = "API Doc",
    var apiVersion: String = "v1",
    var apiGroups: Map<String, String>? = null
)
