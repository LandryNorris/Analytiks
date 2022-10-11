plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

group = "io.github.landrynorris.analytiks"
version = "0.0.1"

repositories {
    mavenCentral()
}

android {
    compileSdk = 31
    namespace = "io.github.landrynorris.analytiks"
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
    macosX64()
    macosArm64()
    linuxX64()
    linuxArm64()
    linuxArm32Hfp()
    mingwX64()
    mingwX86()

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
    }
}