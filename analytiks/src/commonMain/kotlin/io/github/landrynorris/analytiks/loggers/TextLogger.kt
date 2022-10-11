package io.github.landrynorris.analytiks.loggers

import io.github.landrynorris.analytiks.AnalytiksLogger

interface TextLogger: AnalytiksLogger {
    fun logText(text: String)

    override fun logEvent(name: String, params: Map<String, String>) {
        val paramsString = params.map { "${it.key} to ${it.value}" }
            .joinToString(", ")
        logText("Analytiks: $name: $paramsString")
    }
}