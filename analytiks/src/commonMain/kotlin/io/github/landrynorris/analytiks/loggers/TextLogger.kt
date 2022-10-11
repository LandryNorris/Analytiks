package io.github.landrynorris.analytiks.loggers

import io.github.landrynorris.analytiks.AnalytiksLogger

interface TextLogger: AnalytiksLogger {
    fun logText(text: String)

    override fun logEvent(name: String, extras: Map<String, Any>) {
        val paramsString = extras.map { "${it.key} to ${it.value}" }
            .joinToString(", ")
        logText("Analytiks: $name: $paramsString")
    }
}