import java.util.*

fun main() {
    val foodList = readInput("Day01_test")
    val calorieSet = TreeSet<Int>()
    var elveCalories = 0

    for (food in foodList) {
        if (food.isNotEmpty()) {
            elveCalories += food.toInt()
        } else {
            calorieSet.add(elveCalories)
            elveCalories = 0
        }
    }

    println(calorieSet.descendingSet().take(3).sum())
}
