plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.hotmart"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenLocal()
	maven("https://hotmart-artifacts-315120000506.d.codeartifact.us-east-1.amazonaws.com/maven/java") {
		credentials {
			username = "aws"
			password = System.getenv("CODEARTIFACT_AUTH_TOKEN")
		}
	}
	maven("https://mvnrepository.vulcano.rocks/artifactory/libs-release")
	maven("https://mvnrepository.vulcano.rocks/artifactory/libs-snapshot")
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-undertow")
	implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2022.0.4"))
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("com.amazonaws:aws-java-sdk-sts:1.9.6")
	implementation("com.amazonaws:aws-java-sdk-s3:1.12.604")
	implementation("com.amazonaws:aws-java-sdk-core:1.12.604")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
