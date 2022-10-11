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
        homepage = "https://github.com/LandryNorris/Analytiks"
        summary = "Firebase Analytics logging for KMM on Android and iOS"
        license = "Apache 2.0"
        name = "SampleUI"

        ios.deploymentTarget = "11.0"

        pod("FirebaseAnalytics")

        framework {
            baseName = "SampleUI"
            isStatic = true
        }
    }
}

android {
    compileSdk = 31
    namespace = "io.github.landrynorris.analytiks.sample"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}

kotlin {
    android()
    jvm()

    iosX64()
    iosArm64()
    //iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":analytiks"))

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

            dependencies {
                implementation(project(":analytiks-firebase"))
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)

            dependencies {
                implementation(project(":analytiks-firebase"))
            }
        }

        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        //val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}