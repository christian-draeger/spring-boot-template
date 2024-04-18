plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    api(libs.bundles.springdoc)
    api(projects.starter.logger)
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation(libs.bundles.springTesting)
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}
