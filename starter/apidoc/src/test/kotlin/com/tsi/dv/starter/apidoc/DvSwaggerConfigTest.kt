package com.tsi.dv.starter.apidoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import strikt.api.expectThat
import strikt.assertions.isTrue

class DvSwaggerConfigTest {
    val contextRunner = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(DvSwaggerConfig::class.java))
        .withPropertyValues("dv.swagger.enabled=true")

    @Test
    fun `should contain DvSwaggerConfig beans`() {
        contextRunner.run { context ->
            expectThat(context.containsBean("dvOpenAPI")).isTrue()
            expectThat(context.containsBean("dvSwaggerApiGroups")).isTrue()
        }
    }

    @Test
    fun `should contain default config values`() {
        contextRunner.run { context ->
            context.getBean(DvSwaggerConfigurationProperties::class.java).apply {
                assertThat(enabled).isTrue()
                assertThat(apiTitle).isEqualTo("API Doc")
                assertThat(apiVersion).isEqualTo("v1")
                assertThat(apiGroups).isNull()
            }
        }
    }

    @Test
    fun `should set config values`() {
        contextRunner.withPropertyValues(
            "dv.swagger.enabled=false",
            "dv.swagger.api-title=Some Title",
            "dv.swagger.api-version=4.7.11",
        )
            .run { context ->
            context.getBean(DvSwaggerConfigurationProperties::class.java).apply {
                assertThat(enabled).isFalse()
                assertThat(apiTitle).isEqualTo("Some Title")
                assertThat(apiVersion).isEqualTo("4.7.11")
                assertThat(apiGroups).isNull()
            }
        }
    }
}