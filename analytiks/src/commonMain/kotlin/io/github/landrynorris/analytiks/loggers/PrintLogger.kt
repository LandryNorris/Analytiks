package io.github.landrynorris.analytiks.loggers

class PrintLogger: TextLogger {
    override fun logText(text: String) {
        println(text)
    }
}