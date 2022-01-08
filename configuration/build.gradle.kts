plugins {
    java
    id("org.springframework.boot")
}

dependencies {

    // inner application dependies
    implementation(project(":common"))
    implementation(project(":application"))
    implementation(project(":adapters:foursquare"))
    implementation(project(":adapters:qminder-rest"))
    implementation(project(":adapters:web"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
repositories {
    mavenCentral()
}
