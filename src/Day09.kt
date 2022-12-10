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
    var tail = Pair(0, 0)
    var positionVisited = mutableSetOf <Pair<Int, Int>>()

    fun right(steps: Int) {
        repeat(steps) {
            head = head.copy(head.first, head.second + 1)
            tail = getTailPosition()
            positionVisited.add(tail)
        }
    }

    fun up(steps: Int) {
        repeat(steps) {
            head = head.copy(head.first + 1, head.second)
            tail = getTailPosition()
            positionVisited.add(tail)
        }
    }

    fun left(steps: Int) {
        repeat(steps) {
            head = head.copy(head.first, head.second - 1)
            tail = getTailPosition()
            positionVisited.add(tail)
        }
    }

    fun down(steps: Int) {
        repeat(steps) {
            head = head.copy(head.first - 1, head.second)
            tail = getTailPosition()
            positionVisited.add(tail)
        }
    }

    private fun getTailPosition(): Pair<Int, Int> {
        val xDistance = head.first - tail.first
        val yDistance = head.second - tail.second

        if (xDistance == 0 && abs(yDistance) == 2) {
            return Pair(tail.first, (tail.second + yDistance / 2))
        }
        if (yDistance == 0 && abs(xDistance) == 2) {
            return Pair(tail.first + (xDistance / 2), tail.second)
        }
        if (abs(xDistance) <= 1 && abs(yDistance) <= 1) {
            return Pair(tail.first, tail.second)
        }

        return Pair(tail.first + (xDistance / abs(xDistance)),
            tail.second + (yDistance / abs(yDistance)))
    }
}