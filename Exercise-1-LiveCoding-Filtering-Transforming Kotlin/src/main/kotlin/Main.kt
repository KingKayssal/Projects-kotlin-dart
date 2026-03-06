package exercise1.livecoding

/**
 * Live Coding Exercise: Filtering and Transforming with Lambdas
 * 
 * This exercise demonstrates how to use lambdas for filtering and transforming collections.
 * 
 * Learning Objectives:
 * - Use filter() to select elements based on a condition
 * - Use map() to transform elements
 * - Use forEach() to iterate and perform actions
 */

fun liveCodingExample() {
    println("=".repeat(60))
    println("Live Coding: Filtering and Transforming with Lambdas")
    println("=".repeat(60))
    
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    
    println("\nOriginal numbers: $numbers")
    
    // 1. Filter out numbers less than or equal to 5.
    // 2. Square each remaining number.
    // 3. Print each squared number.
    println("\nFiltered (> 5) and squared:")
    numbers.filter { it > 5 }
        .map { it * it }
        .forEach { println("  $it") }
    
    println("\n" + "=".repeat(60))
}

fun main() {
    liveCodingExample()
}
