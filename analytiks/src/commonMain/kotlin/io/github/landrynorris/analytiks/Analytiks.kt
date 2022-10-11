package io.github.landrynorris.analytiks

import io.github.landrynorris.analytiks.loggers.PrintLogger

object Analytiks {
    private val loggers = arrayListOf<AnalytiksLogger>(PrintLogger())

    fun setLoggers(vararg newLoggers: AnalytiksLogger) {
        loggers.clear()
        addLoggers(*newLoggers)
    }

    fun addLoggers(vararg newLoggers: AnalytiksLogger) {
        loggers.addAll(newLoggers)
    }

    fun logEvent(name: String, extras: Map<String, Any>) {
        loggers.forEach {
            it.logEvent(name, extras)
        }
    }
}