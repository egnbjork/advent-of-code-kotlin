package kotlin2022

import readInput

const val CD = "$ cd "
const val TOTAL_SPACE = 70000000
const val UPDATE_SPACE = 30000000

fun main() {
    val gameInput = readInput("Day07_test")

    val currentDirectoryPath = mutableListOf<String>("")
    val dirSizes = mutableMapOf<String, Int>()

    for (line in gameInput) {
        if (line.contains(CD) && line.contains("..")) {
            currentDirectoryPath.removeLast()
        } else if (line.contains(CD) && !line.contains("/")) {
            val directory = line.replace(CD, "")
            currentDirectoryPath.add("${currentDirectoryPath.last()}/$directory")
        } else if (line[0].isDigit()) {
            for(path in currentDirectoryPath) {
                val oldSize = (dirSizes[path] ?: 0)
                val newSize = oldSize + line.split(" ")[0].toInt()
                dirSizes[path] = newSize
            }
        }
    }
    println("part1")
    println(dirSizes.toList().sortedBy { (_, v) -> v }.filter{ (_, v) -> v < 100_000}.sumOf { (_, v) -> v })

    val usedSpace = dirSizes.toList().map{ (_, v) -> v }.maxByOrNull { it } ?: 0
    val neededSpace = usedSpace + UPDATE_SPACE - TOTAL_SPACE

    println("part2")
    if(neededSpace < UPDATE_SPACE) {
        println(
            dirSizes.toList()
                .map { it.second }
                .filter { it > neededSpace }.minOf { it }
        )
    }
}