buildscript {

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.android.tools.build:gradle:7.2.2")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}
