plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.4.32"
    id("com.google.cloud.tools.jib") version "3.4.1"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("kapt") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

val queryDslVersion = "5.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

// Classes annotated with the below annotations will automatically have a no-argument constructor generated.
noArg {
    annotation("jakarta.persistence.Entity")
}

// Classes annotated with the below annotations are marked as 'open' to allow inheritance.
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0") // Use for swaggerUI.

    implementation("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta")
    kapt("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta") // Use for QueryDsl.

    runtimeOnly("com.h2database:h2")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.mockk:mockk:1.13.10") // Use for mocking instances in tests.
    testImplementation("com.appmattus.fixture:fixture:1.2.0") // Use for simplifying the creation of fixtures in tests.
}

jib {
    val profile: String = findProperty("profile")?.toString() ?: "local"
    val tag: String = findProperty("tag")?.toString() ?: "0.0.1"

    from {
        image = ("openjdk:17-alpine")
    }
    to {
        image = "nutrition-components"
        tags = setOf("latest-$profile", tag)
    }
    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
        ports = listOf("8080")
        jvmFlags =
            listOf(
                "-Dspring.profiles.active=$profile",
                "-XX:+UseContainerSupport",
                "-Dserver.port=8080",
                "-Dfile.encoding=UTF-8",
            )
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
