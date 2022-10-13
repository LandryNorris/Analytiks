import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.6.20"
    id("signing")
}

val projectVersion = "0.0.3"

group = "io.github.landrynorris.analytiks"
version = projectVersion

val properties by lazy {
    Properties().also { it.load(project.rootProject.file("credentials.properties").inputStream()) }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

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
    android {
        publishLibraryVariants("debug", "release")
    }

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

            dependencies {
                //implementation("com.google.firebase:firebase-bom:30.5.0")
                implementation("com.google.firebase:firebase-analytics-ktx:21.1.1")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
        }

        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}

publishing {
    publications {
        withType<MavenPublication> {
            artifact(javadocJar.get())
            pom {
                name.set("analytiks-firebase")
                description.set("Firebase Analytics integration for Analytiks library")
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
