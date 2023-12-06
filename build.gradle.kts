plugins {
	java
	id("org.springframework.boot") version "3.1.6"
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
			password = System.getenv("eyJ2ZXIiOjEsImlzdSI6MTcwMTg4NTM1OCwiZW5jIjoiQTEyOEdDTSIsInRhZyI6IkRIYU5KMjNlSXBkS3pESjlGYU8yRHciLCJleHAiOjE3MDE5Mjg1NTgsImFsZyI6IkExMjhHQ01LVyIsIml2Ijoibnlfbkd5YXNzbGhGc3k5dyJ9.OgU129FA1fZeJ7jFgJeuvw.68Dc3CbG84iyvS-w.pA7W234lf9CUxiDPDMVY_mwabIX_cUFtnGRrk_7rf2D0emLck403LOyIODE_Sao1Se2t4OxFCZfsejI9COdtK_wl2hL3FHOD6-ZfU5oBcttHsSbJgGeE29RkfBuUnT_Iv32-1X36zaTEunHYhyjPs4FhM9XRfwgsDaAWtcUiNjAtk1UbLomxvXiU6gkR1LKjB07agvKn69F7idwhOtryCUw3ACgaTaz1WWsLqmfp6Bupc8w4zNfarx91_k_UQhRf9j_M8Beupblyujcac0Imudxeufbxh7gh_DqVCJBz8h8wWlGls4G-_qzP6mhSvVY1DAlgBIzChmXK5QWO5jJBKPeUetdDSb6LEt2w1A72Mjnl8BQuiWACLAwp4GN-3BunVMGobP1BxHShx6WMwXdrD17qefJ5NYBfEuNGJTqwMuAa0CRUwU8b9sDEwNWRONAiEs-sNJAiv_N2px1qBMo4wHtBU92qQOn1U6mTdl-Aheh3VRqniohDeG4swsjEbNwG_5XwOmOqgz-W2A8B1_GimkeodMTCSczU915Pij1661OePBBO9bfnX2mMLP9aZphaX0lNoxn1NmeH6ITbNKD8j64lk0u2e5CGjRSjF-wd9-DMc2ovFB-0Tfwxy96ISGT2GglEttvPnVjP0zS37FNgsWWUgefbQQkKdu7pLJLaHefIqZqCL2Gf4gYQneVyrMNnmtRgIAKA9ySbzyF0JcYXLfnjQWowCMOmxFIt1vSBm44Nz-yBbBlZmUpomLB9_UbRXpcZPDUCkgbrrs3MFbmcisphvByahXctMjlURpWy_h3MWRioAenkqg_uZAqt4JhXD-HD4-Vvjs4EXqrHkamGodm1oeoGI5uJ2GuA1bxaVyBU5JTusknX0eOMd1w8_k_29o_CJ9RDX6j1gL4ugLZN1h7Qxhg29L4J32I-yFqDUXSPq2ivFlcpQhdTWfA1qSJEeajdbSMcyzjO9eWJ6Zx5nWEszJqXOFaxrF7Tmfbfpo5x23bq6lmDSVQFeqOEgzR_wuoIqkHWfYlX_qKD8BcF6dm0bHkkXCqv_r4SraZYZpDzNkx8vYTRPEw0Oy2zvgj7X9ZYZSAI6scEr9TG7j2J_WrvAdeSBNr8TU2TDuXeQpbvEEc2Xtur4-DLwsQtwkfGqgPwBqitDvdFrsnGtOiUUu5_dhL5IKQSyOmlQlqlrO0NLZUSwroF15L9jLUMDRFy6tr4eGcUjWN5FylMLEUL3Uj5tvo61dL3px8clKnDKRC7IC02Gd1I2SQCnIGVUik56KanAGnIJ_qiMeCSgrwNOcjTeZlMNRY.xx6zC8plSCw9Fp-wZVQLfQ")
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
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.4")
	implementation("io.github.openfeign:feign-httpclient:13.1")
	implementation("com.amazonaws:aws-java-sdk-sts:1.9.6")
	implementation("com.amazonaws:aws-java-sdk-s3:1.12.604")
	implementation("com.amazonaws:aws-java-sdk-core:1.12.604")
	implementation("com.hotmart.datahub:event-agent:3.3.5")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
