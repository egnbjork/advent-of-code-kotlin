
//rock A X
//paper B Y
//scissors C Z
val winningPairs = mapOf(
    "X" to "C",
    "Y" to "A",
    "Z" to "B")

val drawPairs = mapOf(
    "X" to "A",
    "Y" to "B",
    "Z" to "C"
)

fun main() {
    val gameInput = readInput("Day02_test")

    println(gameInput.sumOf { getRoundScore(it) })
}

fun getRoundScore(round: String): Int {
    val roundPair = Pair(round.split(" ")[0], round.split(" ")[1])

    return when (roundPair.first) {
        drawPairs[roundPair.second] -> 3
        winningPairs[roundPair.second] -> 6
        else -> 0
    } + getItemScore(roundPair.second)
}

fun getItemScore(item: String): Int {
    return when(item) {
        "X" -> 1
        "Y" -> 2
        else -> 3
    }
}