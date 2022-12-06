const val DISTINCT_CHARS = 14

fun main() {
    val gameInput = readInput("Day06_test")

    val marker = mutableListOf<Char>()
    for((index, value) in gameInput[0].toCharArray().withIndex()) {
        marker.add(value)
        if(marker.size == DISTINCT_CHARS &&
            marker.distinct().size == DISTINCT_CHARS) {
            println(index + 1)
            return
        } else if(marker.size == DISTINCT_CHARS){
            marker.removeFirst()
        }
    }
}