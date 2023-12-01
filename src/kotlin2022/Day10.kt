package kotlin2022

import readInput

fun main() {
    val gameInput = readInput("Day10_test")
    println(gameInput)

    val programMap = linkedMapOf<Int, Int>()
    programMap[0] = 1

    for (line in gameInput) {
        var lastCycle = programMap.entries.last()
        programMap[lastCycle.key + 1] = lastCycle.value
        if (line != "noop") {
            lastCycle = programMap.entries.last()
            programMap[lastCycle.key + 1] = lastCycle.value + line.replace("addx ", "").toInt()
        }
        println(line)
    }
    println(programMap.entries)

    val spritePos = programMap.map { it.value - 1..it.value + 1 }
    val cursorPos = List(6) { List(40) { it } }
    val screen = cursorPos.flatten().mapIndexed { cycle, pos ->
        if (pos in spritePos[cycle]) {
            "#"
        } else {
            " "
        }
    }.chunked(40).joinToString("\n") { it.joinToString("") }
    println(screen)
}
