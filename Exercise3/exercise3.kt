data class Person(val name: String, val age: Int)

fun main() {

    // Sample data from the lecture
    val people = listOf(
        Person("Alice",   25),
        Person("Bob",     30),
        Person("Charlie", 35),
        Person("Anna",    22),
        Person("Ben",     28)
    )

    println("All people:")
    people.forEach { p -> println("  ${p.name} - age ${p.age}") }
    println()

    // STEP 1: Filter people whose name starts with A or B
    val filtered = people.filter { p ->
        p.name.startsWith("A") ||
        p.name.startsWith("B")
    }

    println("Filtered people (A or B):")
    println("  $filtered")
    println()

    // STEP 2: Extract ages
    val ages = filtered.map { it.age }
    println("Their ages: $ages")
    println()

    // STEP 3: Calculate average
    val average = ages.average()

    // STEP 4: Print rounded to 1 decimal place
    println("Average age: ${"%.1f".format(average)}")
}