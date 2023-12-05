package kotlin2023

import readInput
import java.lang.IllegalStateException

fun main() {
    val lines = readInput("kotlin2023/Day02_test")
    println(minimumSet(lines))
}

fun minimumSet(input: List<String>): Int {
    return input.sumOf {
        val parsedLine = it.substring(it.indexOf(": ") + 1)
        val gameSet = parsedLine.trim().split("; ")
        minimumCubes(gameSet)
    }
}

fun minimumCubes(set: List<String>): Int {
    var maxBlue = 0
    var maxRed = 0
    var maxGreen = 0

    for (gameCubes in set) {
        val parsedGameCubes =  gameCubes.split(", ")
        for (cubes in parsedGameCubes) {
            val cube = cubes.trim().split(" ")
            val color = cube[1]
            val count = cube[0].toInt()

            when (color) {
                "blue" -> if (count > maxBlue) maxBlue = count
                "red" -> if (count > maxRed) maxRed = count
                "green" -> if (count > maxGreen) maxGreen = count
                else -> throw IllegalStateException("$color is not a proper color")
            }
        }
    }
    return maxBlue * maxRed * maxGreen
}