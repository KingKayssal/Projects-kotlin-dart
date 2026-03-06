package exercise4.dataprocessing

/**
 * Exercise 4: Complex Data Processing
 * 
 * This exercise demonstrates complex data processing with custom data classes,
 * chaining multiple operations, and calculating aggregate statistics.
 * 
 * Learning Objectives:
 * - Work with data classes
 * - Chain multiple collection operations
 * - Calculate aggregate statistics (average, sum, etc.)
 * - Format output strings
 */

/**
 * Represents a person with a name and age.
 */
data class Person(val name: String, val age: Int)

fun runExercise() {
    println("=".repeat(60))
    println("Exercise 4: Complex Data Processing")
    println("=".repeat(60))
    
    val people = listOf(
        Person("Alice", 25),
        Person("Bob", 30),
        Person("Charlie", 20),
        Person("Anna", 22),
        Person("David", 40)
    )

    println("\nAll people:")
    people.forEach { println("  ${it.name}, age ${it.age}") }

    // 1. Filter people whose name starts with 'A' or 'B'.
    // 2. Extract ages.
    // 3. Calculate average.
    val filteredPeople = people.filter { 
        it.name.startsWith("A") || it.name.startsWith("B") 
    }
    
    println("\nPeople whose name starts with 'A' or 'B':")
    filteredPeople.forEach { println("  ${it.name}, age ${it.age}") }
    
    val averageAge = filteredPeople
        .map { it.age }
        .average()

    // 4. Format and print.
    if (!averageAge.isNaN()) {
        println("\nAverage age: ${"%.1f".format(averageAge)}")
    } else {
        println("\nNo people found starting with A or B.")
    }
    
    // Additional statistics
    println("\nAdditional Statistics:")
    println("  Total people: ${people.size}")
    println("  Average age (all): ${"%.1f".format(people.map { it.age }.average())}")
    println("  Oldest: ${people.maxByOrNull { it.age }?.name} (${people.maxOf { it.age }})")
    println("  Youngest: ${people.minByOrNull { it.age }?.name} (${people.minOf { it.age }})")
    
    println("\n" + "=".repeat(60))
}

fun main() {
    runExercise()
}

├── Exercise-2-Build-Higher-Order-Function/
│   ├── README.md
│   └── src/main/kotlin/Main.kt
│
├── Exercise-3-Transforming-Collection-Types/
│   ├── README.md
│   └── src/main/kotlin/Main.kt
│
├── Exercise-4-Complex-Data-Processing/
│   ├── README.md
│   └── src/main/kotlin/Main.kt
│
└── app/ (Main Student Grades Application)
```

---

## 💡 Learning Path

It's recommended to complete the exercises in order:

1. **Exercise 1** - Get comfortable with lambdas and basic collection operations
2. **Exercise 2** - Build your own higher-order functions
3. **Exercise 3** - Master collection transformations
4. **Exercise 4** - Apply everything to complex data processing

---

## 🎯 Key Kotlin Concepts Covered

- Lambda expressions
- Higher-order functions
- Collection operations (filter, map, forEach)
- Data classes
- Generic functions
- Map operations (associateWith, groupBy)
- Aggregate functions (average, sum, min, max)
- String formatting and templates

---

## 📖 Additional Resources

- [Kotlin Collections Overview](https://kotlinlang.org/docs/collections-overview.html)
- [Higher-Order Functions](https://kotlinlang.org/docs/lambdas.html)
- [Data Classes](https://kotlinlang.org/docs/data-classes.html)

---

**Happy Coding! 🎉**
