# Exercise 2: Build Your Own Higher-Order Function

## Description
This exercise focuses on creating custom higher-order functions that accept functions as parameters.

## Learning Objectives
- Understand higher-order functions
- Create generic functions with type parameters
- Use predicate functions for filtering

## Key Concepts
A **higher-order function** is a function that:
- Takes one or more functions as parameters, OR
- Returns a function as its result

## How to Run
```bash
cd Exercise-2-Build-Higher-Order-Function
kotlinc src/main/kotlin/Main.kt -include-runtime -d output.jar
java -jar output.jar
```

## What It Does
The `processList` function is a custom implementation that filters a list based on a predicate function, demonstrating how to build your own higher-order functions.
