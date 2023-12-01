package kotlin2022

import readInput

fun main() {
    val gameInput = readInput("Day03_test")

    println("task1 ${gameInput.sumOf { getCharPriority(findCommonChar(it)) }}")
    println("task2 ${gameInput.chunked(3).sumOf{ getCharPriority(findCommonChar(it)) }}")
}

fun findCommonChar(items: List<String>): Char {
    return items[0].toSet().intersect(items[1].toSet()).intersect(items[2].toSet()).first()
}

fun findCommonChar(items: String): Char {
    val string1CharSet = items.take(items.length/2).toSet()
    val string2CharSet = items.substring(items.length/2, items.length).toSet()

    return string1CharSet.intersect(string2CharSet).first()
}

fun getCharPriority(char: Char): Int {
    val asciiCode = char.code
    return if(asciiCode > 91) asciiCode - 96
    else asciiCode - 65 + 27
}
