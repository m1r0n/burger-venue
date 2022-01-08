buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    java
    application
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

application {
    mainClass.set("ee.qminder.BurgerVenueApplication")
}

allprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")


    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:2.5.4")
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}