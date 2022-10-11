package io.github.landrynorris.analytiks.firebase

import cocoapods.FirebaseAnalytics.FIRAnalytics
import io.github.landrynorris.analytiks.AnalytiksLogger

class FirebaseAnalyticsLogger: AnalytiksLogger {
    override fun logEvent(name: String, extras: Map<String, Any>) {
         FIRAnalytics.logEventWithName(name, extras.mapKeys { it })
    }
}
