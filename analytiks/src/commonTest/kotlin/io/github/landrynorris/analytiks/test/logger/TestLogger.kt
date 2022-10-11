package io.github.landrynorris.analytiks.test.logger

import io.github.landrynorris.analytiks.AnalytiksLogger

class TestLogger: AnalytiksLogger {
    private val logs = arrayListOf<LogEvent>()

    override fun logEvent(name: String, extras: Map<String, Any>) {
        logs.add(LogEvent(name, extras))
    }

    fun hasEvent(name: String): Boolean = logs.any { it.name == name }
    fun hasKey(key: String): Boolean = logs.any { it.extras.containsKey(key) }
    fun hasValue(value: Any): Boolean = logs.any { it.extras.containsValue(value) }
}
