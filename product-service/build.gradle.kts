plugins {
	java
    application
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "io.github.imecuadorian"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"
val jetbrainsAnnotations = "24.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("io.github.resilience4j:resilience4j-spring-boot3")
	implementation("io.github.resilience4j:resilience4j-reactor")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
    implementation("io.github.resilience4j:resilience4j-feign")
    implementation("org.jetbrains:annotations:${jetbrainsAnnotations}")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

application {
    mainClass.set("io.github.imecuadorian.product.ProductServiceApplication")
}
