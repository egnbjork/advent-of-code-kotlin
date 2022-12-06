fun main() {
    val gameInput = readInput("Day06_test")

    val marker = mutableListOf<Char>()
    for((index, value) in gameInput[0].toCharArray().withIndex()) {
        marker.add(value)
        if(marker.size == 4 && marker.distinct().size == 4) {
            println(index + 1)
            return
        } else if(marker.size == 4){
            marker.removeFirst()
        }
    }
}