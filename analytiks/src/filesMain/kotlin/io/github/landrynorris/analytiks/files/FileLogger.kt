package io.github.landrynorris.analytiks.files

import io.github.landrynorris.analytiks.loggers.TextLogger
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer

class FileLogger(path: String, fileSystem: FileSystem): TextLogger {
    private val file = fileSystem.appendingSink(path.toPath()).buffer()

    override fun logText(text: String) {
        file.writeUtf8(text)
    }

    fun close() = file.close()
}