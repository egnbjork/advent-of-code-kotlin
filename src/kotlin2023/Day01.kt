package kotlin2023

import readInput

fun main() {
    val lines = readInput("kotlin2023/Day01_test")
    val result = lines.map{
        it.getDigits()
    }.sum()
    println(result)
}

fun String.getDigits(): Int {
    var digits = ""
    this.forEach {
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
