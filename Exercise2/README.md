# Exercise 2: Transforming Between Collection Types

## 📌 Overview

This exercise demonstrates **collection transformations** in both Dart and Kotlin. You'll learn how to convert between different collection types (lists, maps) and filter/process data efficiently.

## 🎯 Learning Objectives

- Transform lists into maps using loops
- Use `forEach()` to iterate and filter data
- Apply modern collection transformation methods
- Compare declarative vs imperative approaches
- Work with key-value pairs (maps/dictionaries)

---

## 📂 Files

| File | Language | Purpose |
|------|----------|---------|
| `exercise2.dart` | Dart | Collection transformation implementation |
| `Exercise2.kt` | Kotlin | Collection transformation implementation |

---

## 🔍 What the Code Does

### The Problem
Given a list of words: `["apple", "cat", "banana", "dog", "elephant"]`
1. Create a map with words as keys and their lengths as values
2. Filter the map to show only words with length > 4
3. Implement the same logic using modern collection methods

### The Solution
The code demonstrates three approaches:

#### STEP 1: Manual Transformation (Loop-based)
```
List of words → For each word → Create key-value pair → Store in Map
["apple", "cat", "banana", "dog", "elephant"]
              ↓
{"apple": 5, "cat": 3, "banana": 6, "dog": 3, "elephant": 8}
```

#### STEP 2: Filter & Display
Show only entries where length > 4:
```
{"apple": 5, "banana": 6, "elephant": 8}
           ↓
apple has length 5
banana has length 6
elephant has length 8
```

#### STEP 3: Modern Collection Transformation
Use built-in methods instead of manual loops:
- **Dart**: `words.asMap().map()` 
- **Kotlin**: `words.associateWith()`

---

## 📊 Code Comparison

### Dart Implementation

**Step 1: Manual Transformation**
```dart
Map<String, int> wordLengths = {};
for (String word in words) {
  wordLengths[word] = word.length;
}
```

**Step 3: Using asMap() & map()**
```dart
Map<String, int> wordLengths2 = words.asMap().map(
  (index, word) => MapEntry(word, word.length)
);
```

### Kotlin Implementation

**Step 1: Manual Transformation**
```kotlin
val wordLengths = mutableMapOf<String, Int>()
for (word in words) {
  wordLengths[word] = word.length
}
```

**Step 3: Using associateWith()**
```kotlin
val wordLengths2 = words.associateWith { it.length }
```

---

## ▶️ How to Run

### Dart
```bash
cd Exercise2
dart exercise2.dart
```

**Output**:
```
All words and lengths:
{apple: 5, cat: 3, banana: 6, dog: 3, elephant: 8}

Words with length greater than 4:
apple has length 5
banana has length 6
elephant has length 8

Using associateWith:
apple has length 5
banana has length 6
elephant has length 8
```

### Kotlin
```bash
cd Exercise2
kotlinc Exercise2.kt -include-runtime -d exercise2.jar
java -jar exercise2.jar
```

**Output**:
```
All words and lengths:
{apple=5, cat=3, banana=6, dog=3, elephant=8}

Words with length greater than 4:
apple has length 5
banana has length 6
elephant has length 8

Using associateWith:
apple has length 5
banana has length 6
elephant has length 8
```

---

## 🔑 Key Concepts

### 1. **Maps (Dictionaries)**
Data structure that stores key-value pairs.

```dart
Map<String, int> wordLengths = {"apple": 5, "cat": 3};
```

### 2. **Collection Transformation**
Converting one collection type to another.

- **List → Map**: `words.asMap()` (Dart), `associateWith()` (Kotlin)
- **Map → List**: `wordLengths.keys.toList()`

### 3. **Functional Collection Methods**

| Operation | Dart | Kotlin | Purpose |
|-----------|------|--------|---------|
| Transform | `.map()` | `.map()` | Convert each element |
| Filter | `.where()` | `.filter()` | Keep matching elements |
| Store pairs | `MapEntry()` | `Pair()` | Create key-value pairs |
| Iterate | `.forEach()` | `.forEach {}` | Execute for each item |

### 4. **Declarative vs Imperative**

**Imperative** (Step-by-step instructions):
```dart
Map<String, int> result = {};
for (String word in words) {
  result[word] = word.length;
}
```

**Declarative** (Describe what you want):
```dart
Map<String, int> result = words.asMap().map(
  (index, word) => MapEntry(word, word.length)
);
```

### 5. **Entries vs KeysValues**

```dart
// Filter entries (key-value pairs)
wordLengths.entries.where((entry) => entry.value > 4)

// Access key and value
entry.key    // "apple"
entry.value  // 5
```

---

## 💡 Why This Matters

- **Data Organization**: Maps are essential for organizing related data
- **Flexibility**: Understanding transformations allows you to restructure data easily
- **Performance**: Choosing the right collection type improves efficiency
- **Readability**: Declarative methods make code clearer and less error-prone
- **Real-world**: APIs often return and require different collection structures

---

## 📚 Related Topics

- Collection Transformations
- Functional Data Processing
- Stream Processing
- Data Mapping
- Key-Value Data Structures
