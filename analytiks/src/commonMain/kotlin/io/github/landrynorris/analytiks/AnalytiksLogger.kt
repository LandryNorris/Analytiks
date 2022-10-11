package io.github.landrynorris.analytiks

interface AnalytiksLogger {
    /**
     * Log an analytics event
     */
    fun logEvent(name: String, extras: Map<String, Any>)
}