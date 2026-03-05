fun main() {

    val nums = listOf(1, 2, 3, 4, 5, 6)

    // Test 1: Get even numbers
    val even = processList(nums) { it % 2 == 0 }
    println("Even numbers:   $even")

    // Test 2: Get numbers greater than 3
    val greaterThan3 = processList(nums) { it > 3 }
    println("Greater than 3: $greaterThan3")

    // Test 3: Get odd numbers
    val odd = processList(nums) { it % 2 != 0 }
    println("Odd numbers:    $odd")

    // Test 4: Get numbers less than 4
    val lessThan4 = processList(nums) { it < 4 }
    println("Less than 4:    $lessThan4")
}

fun processList(
    numbers: List<Int>,
    predicate: (Int) -> Boolean
): List<Int> {

    val result = mutableListOf<Int>()

    for (number in numbers) {
        if (predicate(number)) {
            result.add(number)
        }
    }

    return result
}