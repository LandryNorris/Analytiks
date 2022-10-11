plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
}

val projectVersion = "0.0.1"

group = "io.github.landrynorris.analytiks"
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
        name = "AnalytiksFirebase"

        ios.deploymentTarget = "11.0"

        pod("FirebaseAnalytics")

        framework {
            baseName = "AnalytiksFirebase"
            isStatic = true
        }
    }
}

android {
    compileSdk = 31
    namespace = "io.github.landrynorris.analytiks.firebase"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}

kotlin {
    android()
    jvm()
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }

    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":analytiks"))
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

        //val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        //val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}