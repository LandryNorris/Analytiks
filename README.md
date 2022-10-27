Analytiks
=========

Kotlin Multiplatform Analytics Logging

This library allows users to log 
analytics events on any platform. It 
provides several pluggable analytics 
loggers and allows you to define your 
own.

Using this library
------------------

Add the following to your build.gradle

```kotlin
val commonMain by getting {
    dependencies {
        implementation("io.github.landrynorris.analytiks:analytics:$analytiksVersion")
        implementation("io.github.landrynorris.analytiks:analytics-firebase:$analytiksVersion") //for firebase integration
    }
}

val commonTest by getting {
    dependencies {
        implementation("io.github.landrynorris.analytiks:analytics-test:$analytiksVersion")
    }
}
```

Log Analytics
-------------

Call Analytiks.logEvent(name, params) to log events. Pass in a name and a map of properties to log.
Every applied logger will receive this information.

Add Loggers
-----------

Call Analytiks.addLoggers(...) to add new Loggers. By default, only the PrintLogger is
applied. Other loggers must be added with the addLoggers method.

Testing
-------

Add a TestLogger and keep a reference to check if an event was received. You can search
by event name, parameter key, or parameter value.
