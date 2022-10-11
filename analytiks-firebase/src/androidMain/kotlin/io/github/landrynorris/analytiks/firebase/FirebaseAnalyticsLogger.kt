package io.github.landrynorris.analytiks.firebase

import android.os.Bundle
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import io.github.landrynorris.analytiks.AnalytiksLogger

actual class FirebaseAnalyticsLogger: AnalytiksLogger {
    private val firebase by lazy {
        Firebase.analytics
    }

    actual override fun logEvent(name: String, extras: Map<String, Any>) {
        firebase.logEvent(name) {
            extras.entries.forEach {
                when(it.value) {
                    is Double -> param(it.key, it.value as Double)
                    is Long -> param(it.key, it.value as Long)
                    is Bundle -> param(it.key, it.value as Bundle)
                    else -> param(it.key, it.value.toString())
                }
            }
        }
    }
}