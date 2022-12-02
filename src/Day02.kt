
//rock A X
//paper B Y
//scissors C Z
val winningPairs = mapOf(
    "A" to "Y",
    "B" to "Z",
    "C" to "X"
)

val loosingPairs = mapOf(
    "A" to "Z",
    "B" to "X",
    "C" to "Y"
)

val drawPairs = mapOf(
    "A" to "X",
    "B" to "Y",
    "C" to "Z"
)

fun main() {
    val gameInput = readInput("Day02_test")

    println(gameInput.sumOf { getRoundScore(it) })
}

fun getRoundScore(round: String): Int {
    val roundPair = Pair(round.split(" ")[0], round.split(" ")[1])

    return when (roundPair.second) {
        "Y" -> drawPairs[roundPair.first]?.let { getItemScore(it) + 3 }
        "Z" -> winningPairs[roundPair.first]?.let{ getItemScore(it) + 6}
        else -> loosingPairs[roundPair.first]?.let{getItemScore(it)}
    } ?: 0
}

fun getItemScore(item: String): Int {
    return when(item) {
        "X" -> 1
        "Y" -> 2
        else -> 3
    }
}