fun main() {
    val foodList = readInput("Day01_test")
    val calorieList = mutableListOf<Int>()
    var elveCalories = 0

    for (food in foodList) {
        if (food.isNotEmpty()) {
            elveCalories += food.toInt()
        } else {
            calorieList.add(elveCalories)
            elveCalories = 0
        }
    }

    println(calorieList.maxOrNull())
}
