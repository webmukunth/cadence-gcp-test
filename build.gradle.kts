val version: String by project
val springBootVersion: String by project
val springCloudVersion: String by project
val javaSpringJaegerVersion: String by project
val micrometerPrometheusVersion: String by project
val prometheusPushGatewayVersion: String by project
val uberCadenceClientVersion: String by project
val lombokVersion: String by project
val errorProneVersion: String by project

plugins {
    base apply true
    idea apply true
    id("com.gradle.build-scan") version "2.3"
    id("org.springframework.boot") version "2.1.7.RELEASE" apply false
    id("net.ltgt.errorprone") version "0.8.1" apply false
}

/* Common configuration for all sub projects */
subprojects {
    group = "com.anz.bs.magneto"
    version = "$version"

    apply(plugin = "java")
    apply(plugin = "maven")
    apply(plugin = "net.ltgt.errorprone")

    repositories {
        jcenter()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
        options.encoding = "UTF-8"
    }

    if ( this.path != ":commons-api") {
        dependencies {
            "implementation"(project(":commons-api"))
        }
    }

    dependencies {
        /* Workflow */
        "implementation"("com.uber.cadence:cadence-client:${uberCadenceClientVersion}")

        "compileOnly"("org.projectlombok:lombok:${lombokVersion}")
        "annotationProcessor"("org.projectlombok:lombok:${lombokVersion}")
        "errorprone"("com.google.errorprone:error_prone_core:${errorProneVersion}")
    }
}


/* Common configuration for all service projects */
configure(subprojects.filter { it.name.endsWith("service") }) {

    apply(plugin = "org.springframework.boot")

    dependencies {

        "implementation"("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
        /* Spring Boot */
        "implementation"("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
        "implementation"("org.springframework.boot:spring-boot-configuration-processor:${springBootVersion}")

        /* Observability stack */
        "implementation"("io.opentracing.contrib:opentracing-spring-jaeger-cloud-starter:${javaSpringJaegerVersion}")
        "implementation"("io.micrometer:micrometer-registry-prometheus:${micrometerPrometheusVersion}")
        "implementation"("io.prometheus:simpleclient_pushgateway:${prometheusPushGatewayVersion}")

        /* compile dependencies */
        "annotationProcessor"("org.springframework.boot:spring-boot-configuration-processor:${springBootVersion}")
        "testImplementation"("org.springframework.boot:spring-boot-starter-test:${springBootVersion}")
    }
}

/* Project specific dependencies */
project(":commons-api") {
    dependencies {
        "implementation"("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
        "implementation"("io.opentracing.contrib:opentracing-spring-jaeger-cloud-starter:${javaSpringJaegerVersion}")
        "implementation"("org.springframework.boot:spring-boot-configuration-processor:${springBootVersion}")
        "annotationProcessor"("org.springframework.boot:spring-boot-configuration-processor:${springBootVersion}")
    }
}

project(":workflow-service") {
    dependencies {
        "implementation"(project(":workflow-api"))
    }
}

project(":submitfile-service") {
    dependencies {
        "implementation"(project(":workflow-api"))
        "implementation"("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
    }
}
