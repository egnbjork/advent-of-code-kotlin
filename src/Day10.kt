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
    }

    val indexes = 20..220 step 40

    var sum = 0
    indexes.forEach {
        sum += (programMap[it - 1] ?: 0) * it
    }
    println(sum)
}
