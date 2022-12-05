fun main() {
    val gameInput = readInput("Day05_test")

    val crates = normalizeCrates(gameInput)
    val actions = normalizeActions(gameInput)

    crates.forEach { println(it) }

    val part1 = executePart1(crates, actions)

    printSolution(part1)
}

fun normalizeCrates(input: List<String>): List<MutableList<Char>> {
    var crateStackList = mutableListOf<MutableList<Char>>()

    for(line in input) {
        if(line.contains("[0-9]".toRegex())) {
            break
        }
        val normalizedLine = line.replace("    ", "[-] ")

        for ((index, crate) in normalizedLine.split(" ").withIndex()) {
            val normalizedCrate = crate.replace("[", "").replace("]", "")

            if(normalizedCrate == "-" || normalizedCrate.isEmpty()) {
                if (crateStackList.size <= index && normalizedCrate.isNotEmpty()) {
                    val crateStack = mutableListOf<Char>()
                    crateStackList.add(crateStack)
                }
                continue
            }

            if(crateStackList.size <= index || crateStackList.isEmpty()) {
                val crateStack = mutableListOf<Char>()
                crateStack.add(normalizedCrate.toCharArray()[0])
                crateStackList.add(crateStack)
            } else {
                crateStackList[index].add(normalizedCrate.toCharArray()[0])
            }
        }
    }

    return crateStackList
}

fun normalizeActions(input: List<String>): List<Triple<Int, Int, Int>> {
    val actions = mutableListOf<Triple<Int, Int, Int>>()

    for(line in input) {
        if (line.startsWith("move")) {
            val action = line.split(" ")
            actions.add(Triple(action[1].toInt(), action[3].toInt() - 1, action[5].toInt() - 1))
        }
    }
    return actions
}


fun executePart1(crates: List<MutableList<Char>>, actions: List<Triple<Int, Int, Int>>): List<MutableList<Char>> {
    for(action in actions) {
        repeat(action.first) {
            val crateToMove = crates[action.second].removeFirst()
            crates[action.third].add(0, crateToMove)
        }
    }

    return crates
}

fun printSolution(crates: List<MutableList<Char>>) {
    var solution = ""
    for (crate in crates) {
        solution += crate.first()
    }
    print(solution)
}