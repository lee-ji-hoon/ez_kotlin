package book.effective.chapter01.item09

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {

}

fun countCharactersInFileTryCatch(path: String): Int {
    val reader = BufferedReader(FileReader(path))
    try {
        return reader.lineSequence().sumOf { it.length }
    } finally {
        reader.close()
    }
}

fun countCharactersInFileUse(path: String): Int {
    BufferedReader(FileReader(path)).use { reader ->
        return reader.lineSequence().sumOf { it.length }
    }
}

fun countCharactersInFileUseLines(path: String): Int = File(path).useLines { lines ->
    lines.sumOf { it.length }
}