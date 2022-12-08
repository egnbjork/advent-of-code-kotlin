fun main() {
    val gameInput = readInput("Day08_test")
    val mapOfTrees = parseInput(gameInput)
    var count = 0

    for ((i, row) in mapOfTrees.withIndex()) {
        for ((n, tree) in row.withIndex()) {
            if(notABorderTree(i, n, mapOfTrees.size, row.size))  {
                if (visibleFromUp(tree, i, n, mapOfTrees)) {
                    count++
                } else if (visibleFromRight(tree, i, n, mapOfTrees, row.size)) {
                    count++
                } else if (visibleFromBottom(tree, i, n, mapOfTrees)) {
                    count++
                } else if (visibleFromLeft(tree, i, n, mapOfTrees)) {
                    count++
                }
            }
        }
    }

    println((mapOfTrees.size + mapOfTrees[0].size) * 2 - 4 + count)
}

fun visibleFromUp(tree: Int, i: Int, n: Int, mapOfTrees: List<List<Int>>): Boolean {
    repeat(i) {index ->
        if(tree <= mapOfTrees[index][n]) {
            return false
        }
    }
    return true
}

fun visibleFromRight(tree: Int, i: Int, n: Int, mapOfTrees: List<List<Int>>, rowSize: Int): Boolean {
    repeat(rowSize - n - 1) { index ->
        if (tree <= mapOfTrees[i][rowSize - index - 1]) {
            return false
        }
    }
    return true
}

fun visibleFromBottom(tree: Int, i: Int, n: Int, mapOfTrees: List<List<Int>>): Boolean {
    repeat(mapOfTrees.size - i - 1) { index ->
        if (tree <= mapOfTrees[mapOfTrees.size - index - 1][n]) {
            return false
        }
    }
    return true
}

fun visibleFromLeft(tree: Int, i: Int, n: Int, mapOfTrees: List<List<Int>>): Boolean {
    repeat(n) { index ->
        if (tree <= mapOfTrees[i][index]) {
            return false
        }
    }
    return true
}

fun notABorderTree(i: Int, n: Int, mapSize: Int, rowSize: Int): Boolean {
    return i != 0 &&
            n != 0 &&
            i != mapSize - 1
            && n != rowSize -1}

fun parseInput(gameInput: List<String>): List<List<Int>> {
    val arr = mutableListOf<MutableList<Int>>()
    for(line in gameInput) {
        val row = mutableListOf<Int>()
        for(char in line.toCharArray()) {
            row.add(char.digitToInt())
        }
        arr.add(row)
    }

    return arr
}