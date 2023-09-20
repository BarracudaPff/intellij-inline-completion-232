plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("org.jetbrains.intellij") version "1.13.3"
    id("dev.bmac.intellij.plugin-uploader") version "1.3.1"
    id("org.jetbrains.changelog") version "1.2.1"
    kotlin("plugin.lombok") version "1.8.21"
    id("io.freefair.lombok") version "5.3.0"
}

group = "com.example"
version = "1.0-SNAPSHOT"

val ktorVersion = "2.3.3"

repositories {
    mavenCentral()
    maven(url = "https://www.jetbrains.com/intellij-repository/snapshots")
    maven(url = "https://www.jetbrains.com/intellij-repository/releases")
}

dependencies {
    implementation("io.github.java-diff-utils:java-diff-utils:4.11")
    implementation("io.vavr:vavr:0.10.2")
    implementation("org.json:json:20220924")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("io.ktor:ktor-client-core:$ktorVersion") 
    implementation("io.ktor:ktor-client-cio:$ktorVersion") 
    implementation("io.ktor:ktor-client-gson:$ktorVersion") 
    implementation("io.ktor:ktor-client-json:$ktorVersion") 
    implementation("io.ktor:ktor-client-apache:$ktorVersion") 
    implementation("io.ktor:ktor-client-core:$ktorVersion") 
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion") 
    implementation("io.ktor:ktor-client-cio-jvm:$ktorVersion") 
    implementation("io.ktor:ktor-client-serialization:$ktorVersion") 

    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("com.auth0:java-jwt:4.4.0")

    implementation("com.google.code.gson:gson:2.3.1")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.google.guava:guava:31.1-jre")

    implementation("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    testImplementation("org.junit.platform:junit-platform-launcher:1.9.3")
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.2.2")
    type.set("IU")
    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("232.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
