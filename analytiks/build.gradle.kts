import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.6.20"
    id("signing")
}

group = "io.github.landrynorris.analytiks"
version = "0.0.2"

val properties by lazy {
    Properties().also { it.load(project.rootProject.file("credentials.properties").inputStream()) }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

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
    android {
        publishLibraryVariants("debug", "release")
    }
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

publishing {
    publications {
        withType<MavenPublication> {
            artifact(javadocJar.get())
            pom {
                name.set("analytiks")
                description.set("Analytics logger for Koltin Multiplatform")
                url.set("https://github.com/LandryNorris/JniUtils")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    connection.set("https://github.com/LandryNorris/JniUtils.git")
                    developerConnection.set("https://github.com/LandryNorris/JniUtils")
                    url.set("https://github.com/LandryNorris/JniUtils")
                }
                developers {
                    developer {
                        id.set("landrynorris")
                        name.set("Landry Norris")
                        email.set("landry.norris0@gmail.com")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

            credentials {
                username = getProperty("sonatype.username")
                password = getProperty("sonatype.password")
            }
        }
    }
}

project.signing {
    val secretKeyFile = getProperty("signing.secretKeyRingFile") ?: error("No key file found")
    val secretKey = File(secretKeyFile).readText()
    val signingPassword = getProperty("signing.password")
    useInMemoryPgpKeys(secretKey, signingPassword)
    sign(project.publishing.publications)
}

fun getProperty(name: String): String? {
    return System.getProperty(name) ?: properties.getProperty(name)
}
