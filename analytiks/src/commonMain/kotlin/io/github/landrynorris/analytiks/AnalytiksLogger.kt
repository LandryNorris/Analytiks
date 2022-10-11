package io.github.landrynorris.analytiks

interface AnalytiksLogger {
    fun logEvent(name: String, params: Map<String, String>)
}