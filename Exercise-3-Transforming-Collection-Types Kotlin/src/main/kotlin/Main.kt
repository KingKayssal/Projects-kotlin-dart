package exercise3.collections

/**
 * Exercise 3: Transforming Between Collection Types
 * 
 * This exercise demonstrates how to transform data between different collection types
 * such as Lists and Maps.
 * 
 * Learning Objectives:
 * - Use associateWith() to create maps from lists
 * - Filter maps based on values
 * - Work with key-value pairs
 */

fun runExercise() {
    println("=".repeat(60))
    println("Exercise 3: Transforming Between Collection Types")
    println("=".repeat(60))
    
    val strings = listOf("apple", "banana", "kiwi", "elephant", "cat")
    println("\nOriginal strings: $strings")
    
    // Transform list to map: string -> length
    val stringLengths = strings.associateWith { it.length }
    println("\nString lengths map:")
    stringLengths.forEach { (text, length) ->
        println("  $text -> $length")
    }
    
    // Filter map for strings with length > 4
    println("\nStrings with length > 4:")
    stringLengths.filter { it.value > 4 }
        .forEach { (text, length) ->
            println("  $text has length $length")
        }
    
    // Additional transformations
    println("\nGrouping by length:")
    val groupedByLength = strings.groupBy { it.length }
    groupedByLength.forEach { (length, words) ->
        println("  Length $length: $words")
    }
    
    println("\n" + "=".repeat(60))
}

fun main() {
    runExercise()
}
