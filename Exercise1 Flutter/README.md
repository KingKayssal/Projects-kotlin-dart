# Exercise 1: Higher-Order Functions & Predicates

## 📌 Overview

This exercise demonstrates **higher-order functions** and **predicates** (filter functions) in both Dart and Kotlin. The program showcases how to use functional programming patterns to filter collections based on custom rules.

## 🎯 Learning Objectives

- Understand higher-order functions (functions that accept other functions as parameters)
- Apply predicate functions for filtering collections
- Compare functional programming syntax between Dart and Kotlin
- Practice lambda expressions and arrow functions

---

## 📂 Files

| File | Language | Purpose |
|------|----------|---------|
| `exercise1.dart` | Dart | Higher-order function implementation |
| `Exercise1.kt` | Kotlin | Higher-order function implementation |

---

## 🔍 What the Code Does

### The Problem
Filter a list of integers `[1, 2, 3, 4, 5, 6]` using different rules:
- Get even numbers
- Get numbers greater than 3
- Get odd numbers
- Get numbers less than 4

### The Solution
Both languages implement a `processList()` function that:
1. Takes a list of integers as input
2. Takes a **predicate** (a function that returns true/false)
3. Applies the predicate to each element
4. Returns only elements where the predicate returns `true`

```
[1, 2, 3, 4, 5, 6] 
         ↓
   predicate: n % 2 == 0
         ↓
    [2, 4, 6]
```

---

## 📊 Code Comparison

### Dart Implementation
```dart
List<int> processList(
  List<int> numbers,
  bool Function(int) predicate
) {
  List<int> result = [];
  for (int number in numbers) {
    if (predicate(number)) {
      result.add(number);
    }
  }
  return result;
}

// Usage
List<int> even = processList(nums, (n) => n % 2 == 0);
```

### Kotlin Implementation
```kotlin
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

// Usage
val even = processList(nums) { it % 2 == 0 }
```

---

## ▶️ How to Run

### Dart
```bash
cd Exercise1
dart exercise1.dart
```

**Output**:
```
Even numbers:   [2, 4, 6]
Greater than 3: [4, 5, 6]
Odd numbers:    [1, 3, 5]
Less than 4:    [1, 2, 3]
```

### Kotlin
```bash
cd Exercise1
kotlinc Exercise1.kt -include-runtime -d exercise1.jar
java -jar exercise1.jar
```

**Output**:
```
Even numbers:   [2, 4, 6]
Greater than 3: [4, 5, 6]
Odd numbers:    [1, 3, 5]
Less than 4:    [1, 2, 3]
```

---

## 🔑 Key Concepts

### 1. **Higher-Order Functions**
A function that takes another function as a parameter or returns a function.

**Example**: `processList()` is a higher-order function because it takes a predicate function as a parameter.

### 2. **Predicates**
A function that returns a boolean value (true/false) based on a condition.

**Example**: `(n) => n % 2 == 0` is a predicate that returns true if n is even.

### 3. **Lambda Expressions**
Anonymous functions written in shorthand notation.

- **Dart**: `(n) => n % 2 == 0`
- **Kotlin**: `{ it % 2 == 0 }` or `{ number -> number % 2 == 0 }`

### 4. **Functional vs Imperative**
Both implementations use the **imperative approach** (for loop + if statement). 
Modern alternatives:
- **Dart**: `.where((n) => n % 2 == 0).toList()`
- **Kotlin**: `.filter { it % 2 == 0 }`

---

## 💡 Why This Matters

- **Reusability**: One `processList()` function works with any predicate
- **Flexibility**: Predicates are customizable without modifying `processList()`
- **Clean Code**: Separates filtering logic from the filtering mechanism
- **Foundation**: Understanding higher-order functions is crucial for functional programming

---

## 📚 Related Topics

- Functional Programming Paradigms
- First-Class Functions
- Closures
- Function Composition
- Filter/Map/Reduce Operations
