plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
    id("org.jetbrains.compose") version "1.2.0-rc01"
}

val projectVersion = "0.0.1"

group = "io.github.landrynorris.analytiks.sample"
version = projectVersion

repositories {
    mavenCentral()
}

kotlin {
    cocoapods {
        version = projectVersion
        name = "AnalytiksFirebase"

        pod("FirebaseAnalytics")

        framework {
            baseName = "AnalytiksFirebase"
            isStatic = true
        }
    }
}

android {
    compileSdk = 32
    namespace = "io.github.landrynorris.analytiks.sample"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

kotlin {
    android()
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":analytiks"))
                implementation(project(":analytiks-firebase"))

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
        }

        val iosMain by creating {
            dependsOn(commonMain)
        }

        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}