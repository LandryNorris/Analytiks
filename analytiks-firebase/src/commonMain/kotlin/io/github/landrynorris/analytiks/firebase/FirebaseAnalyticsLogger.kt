package io.github.landrynorris.analytiks.firebase

import io.github.landrynorris.analytiks.AnalytiksLogger

expect class FirebaseAnalyticsLogger: AnalytiksLogger {
    override fun logEvent(name: String, extras: Map<String, Any>)
}