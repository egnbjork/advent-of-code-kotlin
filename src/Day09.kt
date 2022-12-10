import kotlin.math.abs

fun main() {
    val gameInput = readInput("Day09_test")

    val parsedInput = parseSteps(gameInput)
    println(parsedInput)

    val rope = Rope()
    parsedInput.forEach { move(rope, it) }
    println(rope.positionVisited.size)
}

fun move(rope: Rope, direction: Pair<Char, Int>) {
    when (direction.first) {
        'R' -> rope.right(direction.second)
        'U' -> rope.up(direction.second)
        'L' -> rope.left(direction.second)
        'D' -> rope.down(direction.second)
    }
}

fun parseSteps(input: List<String>): List<Pair<Char, Int>> {
    return input.map {
        Pair(
            it.split(" ")[0].toCharArray()[0],
            it.split(" ")[1].toInt()
        )
    }
}

class Rope {
    var head = Pair(0, 0)
    var tail = MutableList(9) { 0 to 0 }
    var positionVisited = mutableSetOf <Pair<Int, Int>>()

    fun right(steps: Int) {
        repeat(steps) {
            head = head.copy(head.first, head.second + 1)
            loopTail()
            positionVisited.add(tail.last())
        }
    }

    fun up(steps: Int) {
        repeat(steps) {
            head = head.copy(head.first + 1, head.second)
            loopTail()
            positionVisited.add(tail.last())
        }
    }

    fun left(steps: Int) {
        repeat(steps) {
            head = head.copy(head.first, head.second - 1)
            loopTail()
            positionVisited.add(tail.last())
        }
    }

    fun down(steps: Int) {
        repeat(steps) {
            head = head.copy(head.first - 1, head.second)
            loopTail()
            positionVisited.add(tail.last())
        }
    }

    private fun loopTail() {
        tail.forEachIndexed { i, v ->
            if(i == 0) {
                tail[0] = getTailPosition(head, tail[0])
            } else {
                tail[i] = getTailPosition(tail[i - 1], tail[i])
            }
        }
    }
    private fun getTailPosition(start: Pair<Int, Int>, end: Pair<Int, Int>): Pair<Int, Int> {
        val xDistance = start.first - end.first
        val yDistance = start.second - end.second

        if (xDistance == 0 && abs(yDistance) == 2) {
            return Pair(end.first, (end.second + yDistance / 2))
        }
        if (yDistance == 0 && abs(xDistance) == 2) {
            return Pair(end.first + (xDistance / 2), end.second)
        }
        if (abs(xDistance) <= 1 && abs(yDistance) <= 1) {
            return Pair(end.first, end.second)
        }

        return Pair(end.first + (xDistance / abs(xDistance)),
            end.second + (yDistance / abs(yDistance)))
    }
}