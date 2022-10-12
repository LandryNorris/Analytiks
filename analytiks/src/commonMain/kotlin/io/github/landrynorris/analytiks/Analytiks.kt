package io.github.landrynorris.analytiks

import io.github.landrynorris.analytiks.loggers.PrintLogger

object Analytiks {
    private val loggers = arrayListOf<AnalytiksLogger>(PrintLogger())

    /**
     * Replace the loggers list with a new list. This removes all existing loggers.
     */
    fun setLoggers(vararg newLoggers: AnalytiksLogger) {
        loggers.clear()
        addLoggers(*newLoggers)
    }

    /**
     * Add analytics loggers
     */
    fun addLoggers(vararg newLoggers: AnalytiksLogger) {
        loggers.addAll(newLoggers)
    }

    /**
     * Log an event using each analytics logger.
     */
    fun logEvent(name: String, extras: Map<String, Any> = mapOf()) {
        loggers.forEach {
            it.logEvent(name, extras)
        }
    }
}
