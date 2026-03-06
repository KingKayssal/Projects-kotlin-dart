package exercise2.higherorder

/**
 * Exercise 2: Build Your Own Higher-Order Function
 * 
 * This exercise focuses on creating custom higher-order functions that accept
 * functions as parameters.
 * 
 * Learning Objectives:
 * - Understand higher-order functions
 * - Create generic functions with type parameters
 * - Use predicate functions for filtering
 */

/**
 * A custom higher-order function that processes a list based on a predicate.
 * 
 * @param T The type of elements in the list
 * @param list The list to process
 * @param predicate A function that returns true for elements to keep
 * @return A new list containing only elements that match the predicate
 */
fun <T> processList(list: List<T>, predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (item in list) {
        if (predicate(item)) {
            result.add(item)
        }
    }
    return result
}

fun runExercise() {
    println("=".repeat(60))
    println("Exercise 2: Build Your Own Higher-Order Function")
    println("=".repeat(60))
    
    val list = listOf(3, 4, 5, 6)
    println("\nOriginal list: $list")
    
    val filteredList = processList(list) { it > 5 }
    println("Filtered list (items > 5): $filteredList")
    
    // Additional examples
    val stringList = listOf("apple", "banana", "kiwi", "grape")
    println("\nOriginal strings: $stringList")
    
    val longStrings = processList(stringList) { it.length > 5 }
    println("Strings with length > 5: $longStrings")
    
    println("\n" + "=".repeat(60))
}

fun main() {
    runExercise()
}
