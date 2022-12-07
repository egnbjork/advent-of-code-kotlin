const val CD = "$ cd "

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
    println(dirSizes.toList().sortedBy { (_, v) -> v }.filter{ (_, v) -> v < 100_000}.sumOf { (_, v) -> v })
}