plugins {
    kotlin("multiplatform") version "1.7.20"
    id("com.android.library")
}

group = "io.github.landrynorris.analytiks"
version = "0.0.1"

repositories {
    mavenCentral()
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
    iosArm32()
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