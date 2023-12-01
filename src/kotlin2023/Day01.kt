package kotlin2023

import readInput

val spelledNumbers = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
)
fun main() {
    val lines = readInput("kotlin2023/Day01_test")
    val result = lines.sumOf {
        it.getDigits()
    }
    println(result)
}

fun String.getDigits(): Int {
    val noSpellNumbers = this.parseSpelledNumbers()
    var digits = ""
    noSpellNumbers.forEach {
        if (it.isDigit()) {
            digits += it
        }
    }

    return when (digits.length) {
        2 -> digits.toInt()
        1 -> (digits + digits).toInt()
        else -> (digits.first() + "" +  digits.last()).toInt()
    }
}

fun String.parseSpelledNumbers(): String {
    var result = this
    spelledNumbers.forEach {
        if(result.contains(it.key)) {
            result = result.replace(it.key,
                    "${it.key.first()}${it.value}${it.key.last()}",
                    false)
        }
    }
    return result
}
