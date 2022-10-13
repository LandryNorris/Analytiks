package io.github.landrynorris.analytiks.firebase

import cocoapods.FirebaseAnalytics.FIRAnalytics
import io.github.landrynorris.analytiks.AnalytiksLogger

actual class FirebaseAnalyticsLogger actual constructor(): AnalytiksLogger {
    actual override fun logEvent(name: String, extras: Map<String, Any?>) {
         FIRAnalytics.logEventWithName(name, extras.mapKeys { it })
    }
}
