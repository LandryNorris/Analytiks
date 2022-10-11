package io.github.landrynorris.analytiks

import io.github.landrynorris.analytiks.test.logger.TestLogger
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LoggerTests {

    @Test
    fun testLoggerName() {
        val logger = TestLogger()
        sendTestEvents(logger)

        assertTrue(logger.hasEvent("testEvent"))
        assertTrue(logger.hasEvent("testEvent2"))
        assertTrue(logger.hasEvent("An event"))
        assertFalse(logger.hasEvent("An event not in the logger"))
        assertFalse(logger.hasEvent("Event"))
        assertFalse(logger.hasEvent(""))
    }

    @Test
    fun testLoggerKeys() {
        val logger = TestLogger()
        sendTestEvents(logger)

        assertTrue(logger.hasKey("four"))
        assertTrue(logger.hasKey("a key"))
        assertTrue(logger.hasKey("one"))
        assertFalse(logger.hasKey("2"))
        assertFalse(logger.hasKey("a value"))
        assertFalse(logger.hasKey(""))
    }

    @Test
    fun testLoggerValues() {
        val logger = TestLogger()
        sendTestEvents(logger)

        assertTrue(logger.hasValue(4))
        assertTrue(logger.hasValue("a value"))
        assertTrue(logger.hasValue(true))
        assertTrue(logger.hasValue(false))
    }

    private fun sendTestEvents(logger: TestLogger) {
        logger.logEvent("testEvent", mapOf("test" to "2", "a key" to "a value"))
        logger.logEvent("testEvent2", mapOf("four" to 4))
        logger.logEvent("Another Test Event",
            mapOf("pause" to "true", "play" to "false"))
        logger.logEvent("An event", mapOf("one" to 1, "true" to true, "false" to false))
    }
}