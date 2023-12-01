package kotlin2022

import readInput

fun main() {
    val gameInput = readInput("Day04_test")
    val pairs = gameInput.map{it.split(",")}
    var count = 0
    for(pair in pairs) {
        if(rangeInRange(pair)) {
            count++
        }
    }
    println(count)
}

fun rangeInRange(pair: List<String>): Boolean {
    val range1String = pair[0].split("-")
    val range2String = pair[1].split('-')

    val range1 = range1String[0].toInt().rangeTo(range1String[1].toInt())
    val range2 = range2String[0].toInt().rangeTo(range2String[1].toInt())

    return inRange(range1, range2) || inRange(range2, range1)
}

fun inRange(range1: IntRange, range2: IntRange): Boolean{
    return range1.first - range2.first <= 0 &&
            range1.last - range2.first >= 0
}
