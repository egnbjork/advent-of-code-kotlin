package kotlin2023

import readInput

fun main() {
    val lines = readInput("kotlin2023/Day03_test")
    println(sumNotPartNumbers(lines))
}

fun sumNotPartNumbers(lines: List<String>): Int {
    return lines.mapIndexed { i, e ->
        extractNumbers(e).filter {
            isPartNumber(it, i, lines)
        }.sum()
    }.sum()
}

fun isPartNumber(number: Int, lineNumber: Int, lines: List<String>): Boolean {
    val lineWithNumber = lines[lineNumber]
    val numberLength = number.toString().length
    val firstPosition = lineWithNumber.indexOf(number.toString())
    val secondPosition = firstPosition + numberLength

    for (i in firstPosition..secondPosition) {
        //check at the top
        if (lineNumber != 0) {
            for (n in firstPosition - 1..secondPosition) {
                if (isSymbolAdjacent(lines[lineNumber - 1], n)) {
                    println("number $number is part at the top")
                    return true
                }
            }
        }
        //check left
        if (isSymbolAdjacent(lineWithNumber, firstPosition - 1)) {
            println("number $number is part on the left")
            return true
        }
        //check right
        if (isSymbolAdjacent(lineWithNumber, secondPosition)) {
            println("number $number is part on the right")
            return true
        }
        //check at the bottom
        if (lineNumber != lines.lastIndex) {
            for (n in firstPosition - 1..secondPosition) {
                if (isSymbolAdjacent(lines[lineNumber + 1], n)) {
                    println("number $number is part at the bottom")
                    return true
                }
            }
        }
    }
    return false
}

fun isSymbolAdjacent(line: String, position: Int): Boolean {
    if (position < 0 || position > line.length - 1) {
        return false
    }
    return !line[position].isDigit() &&
            line[position] != '.'
}

fun extractNumbers(line: String): List<Int> {
    return line
        .replace(Regex("[^0-9]"), " ")
        .trim()
        .split(" ")
        .filter {
            it.toIntOrNull() != null
        }.map {
            it.toInt()
        }
}
