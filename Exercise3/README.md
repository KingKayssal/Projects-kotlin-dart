# Exercise 3: Filtering Objects & Computing Aggregates

## 📌 Overview

This exercise demonstrates **filtering custom objects** and **computing aggregate values** (like averages) from filtered collections. You'll work with classes, filtering, mapping, and reduction operations in both Dart and Kotlin.

## 🎯 Learning Objectives

- Work with custom classes and objects
- Filter collections based on object properties
- Extract values from filtered objects
- Compute aggregate statistics (averages, sums)
- Apply functional programming patterns to real-world scenarios

---

## 📂 Files

| File | Language | Purpose |
|------|----------|---------|
| `exercise3.dart` | Dart | Object filtering & aggregation implementation |
| `exercise3.kt` | Kotlin | Object filtering & aggregation implementation |

---

## 🔍 What the Code Does

### The Problem
Given a list of people with names and ages:
```
Alice (25), Bob (30), Charlie (35), Anna (22), Ben (28)
```

Tasks:
1. **STEP 1**: Filter people whose names start with 'A' or 'B'
2. **STEP 2**: Extract their ages from the filtered list
3. **STEP 3**: Calculate the average age
4. **STEP 4**: Round and display the result to 1 decimal place

### The Solution Flow

```
Original People List
       ↓
[STEP 1] Filter by name (starts with A or B)
       ↓
Filtered: Alice (25), Bob (30), Anna (22), Ben (28)
       ↓
[STEP 2] Extract ages
       ↓
Ages: [25, 30, 22, 28]
       ↓
[STEP 3] Calculate average: (25+30+22+28)/4 = 26.25
       ↓
[STEP 4] Display: "Average age: 26.3"
```

---

## 📊 Code Comparison

### Dart Implementation

```dart
class Person {
  final String name;
  final int age;
  Person(this.name, this.age);
  
  @override
  String toString() => "${name}(${age})";
}

void main() {
  List<Person> people = [
    Person("Alice", 25),
    Person("Bob", 30),
    Person("Charlie", 35),
    Person("Anna", 22),
    Person("Ben", 28),
  ];

  // STEP 1: Filter
  List<Person> filtered = people
      .where((p) => p.name.startsWith("A") || p.name.startsWith("B"))
      .toList();

  // STEP 2: Extract ages
  List<int> ages = filtered.map((p) => p.age).toList();

  // STEP 3: Calculate average
  double average = ages.reduce((a, b) => a + b) / ages.length;

  // STEP 4: Print rounded
  print("Average age: ${average.toStringAsFixed(1)}");
}
```

### Kotlin Implementation

```kotlin
data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(
        Person("Alice", 25),
        Person("Bob", 30),
        Person("Charlie", 35),
        Person("Anna", 22),
        Person("Ben", 28)
    )

    // STEP 1: Filter
    val filtered = people.filter { p ->
        p.name.startsWith("A") || p.name.startsWith("B")
    }

    // STEP 2: Extract ages
    val ages = filtered.map { it.age }

    // STEP 3: Calculate average
    val average = ages.average()

    // STEP 4: Print rounded
    println("Average age: ${"%.1f".format(average)}")
}
```

---

## ▶️ How to Run

### Dart
```bash
cd Exercise3
dart exercise3.dart
```

**Output**:
```
All people:
  Alice - age 25
  Bob - age 30
  Charlie - age 35
  Anna - age 22
  Ben - age 28

Filtered people (A or B):
  [Person(Alice(25)), Person(Bob(30)), Person(Anna(22)), Person(Ben(28))]

Their ages: [25, 30, 22, 28]

Average age: 26.3
```

### Kotlin
```bash
cd Exercise3
kotlinc exercise3.kt -include-runtime -d exercise3.jar
java -jar exercise3.jar
```

**Output**:
```
All people:
  Alice - age 25
  Bob - age 30
  Charlie - age 35
  Anna - age 22
  Ben - age 28

Filtered people (A or B):
  [Person(name=Alice, age=25), Person(name=Bob, age=30), Person(name=Anna, age=22), Person(name=Ben, age=28)]

Their ages: [25, 30, 22, 28]

Average age: 26.3
```

---

## 🔑 Key Concepts

### 1. **Custom Classes**

**Dart**:
```dart
class Person {
  final String name;
  final int age;
  Person(this.name, this.age);
}
```

**Kotlin** (more concise with `data class`):
```kotlin
data class Person(val name: String, val age: Int)
```

### 2. **Filtering Objects**
Filter based on object properties:
```dart
people.where((p) => p.name.startsWith("A") || p.name.startsWith("B"))
```

### 3. **Mapping/Extraction**
Convert objects to extracted values:
```dart
filtered.map((p) => p.age).toList()  // Extract ages from people
```

### 4. **Reduction Operations**
Combine all elements into a single value:

```dart
// Sum: reduce two elements at a time
ages.reduce((a, b) => a + b)

// Kotlin has built-in average()
ages.average()
```

### 5. **String Formatting**

**Dart**:
```dart
average.toStringAsFixed(1)  // "26.3"
```

**Kotlin**:
```kotlin
"%.1f".format(average)      // "26.3"
```

---

## 🔗 Multi-Step Operations

The beauty of this exercise is chaining operations:

```
List<Person> 
  → filter() → List<Person>
  → map() → List<Int>
  → reduce() → Int
  → divide → Double
  → format → String
```

Each step transforms the data:
| Step | Input Type | Output Type | Operation |
|------|-----------|-----------|-----------|
| 1 | `List<Person>` | `List<Person>` | Filter by name |
| 2 | `List<Person>` | `List<Int>` | Extract ages |
| 3 | `List<Int>` | `Int` | Sum all ages |
| 4 | `Int` | `Double` | Divide by count |
| 5 | `Double` | `String` | Format to string |

---

## 💡 Why This Matters

- **Real-world scenario**: Common pattern in data analysis and reporting
- **Object-oriented + Functional**: Combines two powerful paradigms
- **Performance**: Understanding these operations helps optimize queries
- **Readability**: Method chaining makes code expressive and clear
- **Data processing**: Essential skill for working with databases, APIs, and data analysis

---

## 🎓 Real-World Applications

- Calculate student grade averages (filter by class, compute average)
- Filter employees by department, calculate average salary
- Find specific transactions, compute total spending
- Analyze survey data, filter by age group, compute statistics
- Query databases, filter results, aggregate values

---

## 📚 Related Topics

- Object-Oriented Programming
- Functional Programming
- Chain Operations (Fluent API)
- Stream Processing
- Data Aggregation
- Statistical Computation
