import com.bmuschko.gradle.docker.tasks.image.Dockerfile

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.docker.spring.boot.application)
    alias(libs.plugins.gitProperties)
}

allOpen {
    annotations("jakarta.persistence.Entity", "jakarta.persistence.Embeddable", "jakarta.persistence.MappedSuperclass")
}

docker {
    springBootApplication {
        baseImage.set("bellsoft/liberica-openjre-alpine:21")
        maintainer.set(rootProject.name)
        images.set(setOf("${rootProject.name}:latest"))
        ports.set(setOf(8080))
        jvmArgs.set(listOf("-Xmx2048m"))
    }
}

springBoot {
    buildInfo()
}

dependencies {
    api(projects.starter.apidoc)
    api(projects.starter.logger)

    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.spring)
    // implementation(libs.bundles.databaseUtils)
    runtimeOnly(libs.postgres)
    developmentOnly(libs.bundles.springDevTools)
    testImplementation(libs.bundles.springTesting)
}

tasks {

    withType<Dockerfile> {
        dependsOn(bootJar)
        instruction("RUN adduser -u 4711 --disabled-password -h /home/java -s /bin/ash java")
        instruction("USER 4711")
    }

    build {
        finalizedBy(dockerCreateDockerfile)
    }
}
