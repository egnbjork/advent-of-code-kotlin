import java.util.*

fun main() {
    val gameInput = readInput("Day08_test")
    val mapOfTrees = parseInput(gameInput)
    var score = 0
    val scoreSet = TreeSet<Int>()

    for ((i, row) in mapOfTrees.withIndex()) {
        for ((n, tree) in row.withIndex()) {
            if (notABorderTree(i, n, mapOfTrees.size, row.size)) {
                score += scoreLeft(tree, i, n, mapOfTrees) *
                        scoreUp(tree, i, n, mapOfTrees) *
                        scoreRight(tree, i, n, mapOfTrees, row.size) *
                        scoreDown(tree, i, n, mapOfTrees)
            }
            scoreSet.add(score)
            score = 0
        }
        }
    println(scoreSet.last())
    }

fun scoreUp(tree: Int, i: Int, n: Int, mapOfTrees: List<List<Int>>): Int {
    var score = 0
    repeat(i) {index ->
        if(tree > mapOfTrees[i - index - 1][n]) {
            score++
        } else return ++score
    }
    return score
}

fun scoreRight(tree: Int, i: Int, n: Int, mapOfTrees: List<List<Int>>, rowSize: Int): Int {
    var score = 0
    repeat(rowSize - n - 1) { index ->
        if (tree > mapOfTrees[i][index + n + 1]) {
            score++
        } else return ++score
    }
    return score
}

fun scoreDown(tree: Int, i: Int, n: Int, mapOfTrees: List<List<Int>>): Int {
    var score = 0
    repeat(mapOfTrees.size - i - 1) { index ->
        if (tree > mapOfTrees[i + index + 1][n]) {
            score++
        } else return ++score
    }
    return score
}

fun scoreLeft(tree: Int, i: Int, n: Int, mapOfTrees: List<List<Int>>): Int {
    var score = 0
    repeat(n) { index ->
        if (tree > mapOfTrees[i][n - index - 1]) {
            score++
        } else return ++score
    }
    return score
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

//30373
//25512
//65332
//33549
//35390