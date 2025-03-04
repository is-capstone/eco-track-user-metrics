plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependencyManagement)
}

group = "com.enzulode.metrics.crud"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // general
    implementation(platform(libs.dep.bom.springCloud))
    implementation(platform(libs.dep.bom.awssdk))
    // k8s
    implementation(libs.dep.spring.starter.k8sServiceDiscovery)

    // observability
    implementation(libs.dep.spring.starter.actuator)

    // specific
    // util
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // api
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    // persistence (database)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.flywaydb:flyway-database-postgresql")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("com.jayway.jsonpath:json-path")

    // distributed system
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    // persistence (blob)
    implementation("software.amazon.awssdk:s3")
    implementation("software.amazon.awssdk:s3-transfer-manager:2.30.30")

    // file processing (csv)
    implementation("com.opencsv:opencsv:5.9")
    implementation("com.google.guava:guava:33.4.0-jre")

    // analytics capabilities
    implementation("org.springframework.boot:spring-boot-starter-aop")
}

tasks.named("bootBuildImage", org.springframework.boot.gradle.tasks.bundling.BootBuildImage::class) {
    val repoOwnerAndName = System.getenv("GITHUB_ORG_REPO") as String
    val runId = System.getenv("GITHUB_SHA") as String

    imageName = "ghcr.io/${repoOwnerAndName}:${runId}"
    imagePlatform = "linux/amd64"
}