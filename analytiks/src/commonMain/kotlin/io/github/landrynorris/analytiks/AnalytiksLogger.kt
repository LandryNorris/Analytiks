package io.github.landrynorris.analytiks

interface AnalytiksLogger {
    fun logEvent(name: String, extras: Map<String, Any>)
}