package kotlin2023

import readInput

val bag = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
)

fun main() {
    var count = 0
    val lines = readInput("kotlin2023/Day02_test")
    lines.forEach {
        val parsedLine = it.substring(it.indexOf(": ") + 1)
        val gameSet = parsedLine.split(";")
        if (valid(gameSet)) {
            count += it.substring(5, it.indexOf(": ")).toInt()
        }
    }
    println(count)
}

fun valid(gameSet: List<String>): Boolean {
    println(gameSet)
    for (game in gameSet) {
        val set = game.split(", ")
        for(cubes in set) {
            val cube = cubes.trim().split(" ")
            val color = cube[1]
            val count = cube[0].toInt()
            if((bag[color] ?: 0) < count) {
                return false
            }
        }
    }
    return true
}