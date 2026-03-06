# Exercise 4: Complex Data Processing

## Description
This exercise demonstrates complex data processing with custom data classes, chaining multiple operations, and calculating aggregate statistics.

## Learning Objectives
- Work with data classes in Kotlin
- Chain multiple collection operations
- Calculate aggregate statistics (average, sum, min, max)
- Format output strings using string templates

## Key Concepts
- **Data Classes**: Classes that primarily hold data
- **Method Chaining**: Combining multiple operations in sequence
- **Aggregate Functions**: average(), sum(), minOf(), maxOf()
- **String Formatting**: Using format() for precise number display

## How to Run
```bash
cd Exercise-4-Complex-Data-Processing
kotlinc src/main/kotlin/Main.kt -include-runtime -d output.jar
java -jar output.jar
```

## What It Does
Processes a list of Person objects to:
1. Filter by name criteria
2. Calculate average age
3. Find oldest and youngest people
4. Display formatted statistics
