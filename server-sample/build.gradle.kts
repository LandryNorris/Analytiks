plugins {
    application
    kotlin("multiplatform")
}

val ktorVersion = "2.1.2"

application {
    mainClass.set("io.ktor.server.cio.EngineMain")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":analytiks"))
                implementation("com.squareup.okio:okio:3.2.0")
                implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
                implementation("io.ktor:ktor-server-cio-jvm:$ktorVersion")
            }
        }
    }
}
