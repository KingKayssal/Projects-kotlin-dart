# Student Grade Management Projects

A comprehensive workspace containing multiple implementations of student grade management systems across different platforms and technologies. This collection demonstrates building the same domain logic using Kotlin for Android and Dart for console and Flutter applications.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Project Structure](#project-structure)
- [Kotlin Exercises](#kotlin-exercises)
- [Application Projects](#application-projects)
- [Technology Comparison](#technology-comparison)
- [Development Environment Setup](#development-environment-setup)
- [Excel File Format](#excel-file-format)
- [Key Concepts](#key-concepts)
- [Getting Started](#getting-started)
- [Troubleshooting](#troubleshooting)
- [References](#references)

---

## Project Overview

This repository contains two primary components:

### 1. Kotlin Functional Programming Exercises

Four structured exercises designed to introduce functional programming concepts in Kotlin, including:
- Lambda expressions and higher-order functions
- Collection transformations and filtering
- Data classes and complex data processing
- Predicate functions and type parameters

### 2. Student Grade Management Applications

Multiple implementations of student grade calculation and management systems across different platforms:
- Android applications using Kotlin and Jetpack Compose
- Console applications using Dart
- Cross-platform Flutter applications
- Web, desktop, and mobile support

---

## Project Structure

```
Projects-kotlin-dart/
├── Exercise-1-LiveCoding-Filtering-Transforming Kotlin/
│   ├── README.md
│   └── src/main/kotlin/
├── Exercise-2-Build-Higher-Order-Function Kotlin/
│   ├── README.md
│   └── src/main/kotlin/
├── Exercise-3-Transforming-Collection-Types Kotlin/
│   ├── README.md
│   └── src/main/kotlin/
├── Exercise-4-Complex-Data-Processing Kotlin/
│   ├── README.md
│   └── src/main/kotlin/
├── Exercise1/
│   ├── exercise1.dart
│   ├── Exercise1.kt
│   └── README.md
├── Exercise2/
│   ├── exercise2.dart
│   ├── Exercise2.kt
│   └── README.md
├── Exercise3/
│   ├── exercise3.dart
│   ├── exercise3.kt
│   └── README.md
├── Student Calculator Kotlin/
│   ├── app/
│   ├── console-app/
│   ├── build.gradle
│   ├── settings.gradle
│   └── QUICK-START.md
├── student_grade_app/
│   ├── lib/
│   ├── bin/
│   ├── android/
│   ├── ios/
│   ├── windows/
│   ├── web/
│   ├── pubspec.yaml
│   └── README.md
└── README.md
```

---

## Kotlin Exercises

### Exercise 1: Live Coding - Filtering and Transforming

**Location:** `Exercise-1-LiveCoding-Filtering-Transforming Kotlin/`

**Objective:** Introduction to lambda expressions and basic collection operations in Kotlin.

**Topics Covered:**
- The `filter()` function for conditional selection
- The `map()` function for element transformation
- The `forEach()` function for iteration and action execution

**Implementation Example:**
```kotlin
numbers.filter { it > 5 }
    .map { it * it }
    .forEach { println(it) }
```

**Documentation:** See [Exercise 1 README](Exercise-1-LiveCoding-Filtering-Transforming%20Kotlin/README.md)

---

### Exercise 2: Build Your Own Higher-Order Function

**Location:** `Exercise-2-Build-Higher-Order-Function Kotlin/`

**Objective:** Understanding and implementing custom higher-order functions with generic type parameters.

**Topics Covered:**
- Higher-order function concepts
- Generic functions with type parameters
- Predicate functions for filtering logic
- Function types as parameters

**Key Concept:**
```kotlin
fun <T> processList(list: List<T>, predicate: (T) -> Boolean): List<T>
```

**Documentation:** See [Exercise 2 README](Exercise-2-Build-Higher-Order-Function%20Kotlin/README.md)

---

### Exercise 3: Transforming Between Collection Types

**Location:** `Exercise-3-Transforming-Collection-Types Kotlin/`

**Objective:** Converting between different collection types and working with key-value pairs.

**Topics Covered:**
- The `associateWith()` function for list-to-map conversion
- Map filtering based on values
- Key-value pair operations
- The `groupBy()` function for data categorization

**Implementation Example:**
```kotlin
val stringLengths = strings.associateWith { it.length }
val filtered = stringLengths.filter { it.value > 5 }
```

**Documentation:** See [Exercise 3 README](Exercise-3-Transforming-Collection-Types%20Kotlin/README.md)

---

### Exercise 4: Complex Data Processing

**Location:** `Exercise-4-Complex-Data-Processing Kotlin/`

**Objective:** Applying functional programming to custom data classes and computing statistical aggregates.

**Topics Covered:**
- Kotlin data classes
- Chaining multiple collection operations
- Aggregate calculations (average, sum, minimum, maximum)
- String templates for formatted output

**Implementation Example:**
```kotlin
data class Person(val name: String, val age: Int)

val averageAge = people
    .filter { it.name.startsWith("A") || it.name.startsWith("B") }
    .map { it.age }
    .average()
```

**Documentation:** See [Exercise 4 README](Exercise-4-Complex-Data-Processing%20Kotlin/README.md)

---

## Application Projects

### 1. Student Calculator (Kotlin/Android)

**Location:** `Student Calculator Kotlin/`

**Description:** A comprehensive Android application for managing student grades, built with Kotlin and Jetpack Compose.

**Technology Stack:**
- Language: Kotlin
- UI Framework: Jetpack Compose with Material Design 3
- Build System: Gradle with Kotlin DSL
- Minimum SDK: API 26 (Android 8.0)
- Target SDK: API 34 (Android 14)
- Java Compatibility: JDK 11

**Features:**
- Excel file import for student data (.xlsx format)
- Automatic grade calculation based on CA and Exam marks
- Customizable grading formula (CA weight + Exam weight)
- Student record management and viewing
- Statistical analysis (class average, pass rate, grade distribution)
- Excel export functionality
- Persistent settings using SharedPreferences

**Project Components:**
- `app/` - Main Android application module
- `console-app/` - Console version for command-line usage
- `gradle/` - Gradle wrapper and configuration

**Build Instructions:**
```bash
cd "Student Calculator Kotlin"
gradlew.bat build          # Build the project
gradlew.bat installDebug   # Install on connected device/emulator
```

**Documentation:** See [Student Calculator QUICK-START.md](Student%20Calculator%20Kotlin/QUICK-START.md)

---

### 2. Student Grade App (Flutter/Dart)

**Location:** `student_grade_app/`

**Description:** A cross-platform application for viewing and analyzing student grades, supporting multiple platforms including Android, iOS, Windows, macOS, Web, and Linux.

**Technology Stack:**
- Language: Dart 3.0+
- Framework: Flutter 3.11+
- Dependencies: flutter, file_picker, excel, path
- Build System: Flutter build system

**Features:**
- Interactive Flutter UI for grade management
- Excel file parsing with visual file picker
- Grade statistics and distribution analysis
- Student sorting by score
- Console application variant available
- Multi-platform support (mobile, desktop, web)

**Grading System:**

**Formula:**
| Component | Maximum Mark |
|-----------|--------------|
| CA (Continuous Assessment) | 30 |
| Exam | 70 |
| Final Mark | 100 |

**Grade Scale:**
| Grade | Range | Remark |
|-------|-------|--------|
| A | 80-100 | Excellent |
| B | 70-79 | Very Good |
| C | 60-69 | Good |
| D | 50-59 | Satisfactory |
| E | 40-49 | Pass |
| F | 0-39 | Fail |

**Running the Application:**

**Flutter GUI Version:**
```bash
cd student_grade_app
flutter pub get
flutter run
```

**Console Version:**
```bash
cd student_grade_app
dart pub get
dart run bin/main.dart <path_to_excel_file.xlsx>
```

**Console Usage Examples:**
```bash
# Simple file in current directory
dart run bin/main.dart grades.xlsx

# File with full path
dart run bin/main.dart C:\Users\YourName\Desktop\student_grades.xlsx

# File with spaces in path (use quotes)
dart run bin/main.dart "C:\Users\YourName\My Documents\grades.xlsx"
```

**Project Structure:**
- `lib/main.dart` - Flutter UI implementation (447 lines)
- `bin/main.dart` - Console application
- `android/`, `ios/`, `windows/`, `web/`, `macos/`, `linux/` - Platform-specific configurations
- `CONSOLE_README.md` - Console application documentation

---

### 3. Additional Exercises (Dart and Kotlin Implementations)

**Exercise1/, Exercise2/, Exercise3/**

These directories contain parallel implementations of functional programming concepts in both Dart and Kotlin, demonstrating language-specific approaches to:
- Higher-order functions and predicates
- Collection transformations
- Object filtering and aggregate computation

Each directory includes:
- Dart implementation (`.dart` file)
- Kotlin implementation (`.kt` file)
- Documentation (README.md)

---

## Technology Comparison

| Feature | Student Calculator | student_grade_app (Flutter) | student_grade_app (Console) |
|---------|-------------------|----------------------------|----------------------------|
| **Language** | Kotlin | Dart | Dart |
| **Platform Type** | Android Only | Multi-Platform | Command-Line |
| **UI Framework** | Jetpack Compose | Flutter | Terminal |
| **Minimum SDK/Version** | API 26 | Flutter 3.11+ | Dart 3.0+ |
| **Excel Support** | Apache POI | excel package | excel package |
| **Supported Platforms** | Android | Android, iOS, Web, Windows, macOS, Linux | Windows, macOS, Linux |
| **Data Persistence** | SharedPreferences | Flutter storage | None |
| **Build System** | Gradle | Flutter | Dart |

---

## Development Environment Setup

### Prerequisites

**For Kotlin/Android Projects:**
- Android Studio (latest stable version)
- Android SDK (API 26-34)
- Kotlin 1.9 or later
- Java Development Kit (JDK) 11 or later
- Gradle 7.0 or later

**For Dart/Flutter Projects:**
- Dart SDK 3.0 or later
- Flutter SDK 3.11 or later (for Flutter projects)
- Visual Studio Code with Dart/Flutter extensions, or Android Studio with Flutter plugin

### Installation Instructions

**Android Studio:**
1. Download from https://developer.android.com/studio
2. Install Android SDK components
3. Configure JDK 11+

**Dart SDK:**
```bash
# Windows (using Chocolatey)
choco install dart-sdk

# macOS (using Homebrew)
brew install dart

# Or download from: https://dart.dev/get-dart
```

**Flutter SDK:**
```bash
# Download and extract from: https://flutter.dev/docs/get-started/install
# Add Flutter to system PATH
# Run: flutter doctor
```

**Kotlin Compiler (for console exercises):**
```bash
# Windows (using Chocolatey)
choco install kotlin

# macOS (using Homebrew)
brew install kotlin

# Linux (using SDKMAN!)
sdk install kotlin
```

### Verifying Installation

```bash
# Check Kotlin version
kotlinc -version

# Check Dart version
dart --version

# Check Flutter version
flutter --version

# Check Android SDK
sdkmanager --list
```

---

## Excel File Format

### Required Column Headers

The Excel files should contain the following columns (case-insensitive, flexible naming):

**Student Identification:**
- `ID`, `Student ID`, `No`, or `Number`

**Student Name:**
- `Name`, `Student Name`, or `Full Name`

**Continuous Assessment Mark:**
- `CA`, `CA Mark`, or `Continuous` (out of 30)

**Examination Mark:**
- `Exam`, `Exam Mark`, or `Examination` (out of 70)

### Example Format

```
ID    | Name           | CA   | Exam
------|----------------|------|------
001   | Nedjou Destin       | 25   | 65
002   | Cheikha Samuel     | 28   | 72
003   | Robert Brown   | 20   | 55
004   | Alicia Alex  | 30   | 68
```

### Sample Data Generation

The `student_grade_app` project includes Python scripts for generating sample data:

```bash
cd student_grade_app
python create_sample.py
```

---

## Key Concepts

### Functional Programming in Kotlin
- Lambda expressions and closures
- Higher-order functions (functions that accept or return functions)
- Immutability and pure functions
- Collection operations: `filter`, `map`, `reduce`, `fold`, `groupBy`
- Function composition and chaining

### Functional Programming in Dart
- First-class functions
- Anonymous functions and arrow syntax
- Collection methods: `where`, `map`, `reduce`, `fold`
- Functional operators on iterables

### Android Development
- Activity lifecycle management
- RecyclerView and adapter pattern
- Material Design principles
- File I/O operations
- SharedPreferences for data persistence
- Permission handling for external storage

### Flutter Development
- Widget tree and composition
- State management
- Cross-platform rendering
- Platform channels
- File picker integration
- Responsive UI design

### Grade Calculation Logic
- Weighted average computation
- Threshold-based grade assignment
- Statistical analysis (mean, distribution)
- Data validation and error handling

---

## Getting Started

### Running Kotlin Console Exercises

**Method 1: Using IntelliJ IDEA**
1. Open IntelliJ IDEA
2. Select File -> Open
3. Navigate to the exercise folder
4. Right-click on `Main.kt`
5. Select "Run 'MainKt'"

**Method 2: Using Kotlin Compiler**
```powershell
# Navigate to exercise source folder
cd "Exercise-1-LiveCoding-Filtering-Transforming Kotlin\src\main\kotlin"

# Compile and run
kotlinc Main.kt -include-runtime -d output.jar
java -jar output.jar
```

**Method 3: Direct Execution**
```powershell
cd "Exercise-1-LiveCoding-Filtering-Transforming Kotlin\src\main\kotlin"
kotlin Main.kt
```

### Running Android Application

1. Open Android Studio
2. Select File -> Open
3. Navigate to `Student Calculator Kotlin` folder
4. Wait for Gradle sync to complete
5. Connect an Android device or start an emulator
6. Click Run (or press Shift + F10)

### Running Flutter Application

**Desktop/Mobile:**
```bash
cd student_grade_app
flutter pub get
flutter devices  # List available devices
flutter run -d <device_id>
```

**Web:**
```bash
flutter run -d chrome
```

**Build for Production:**
```bash
flutter build apk        # Android APK
flutter build appbundle  # Android App Bundle
flutter build web        # Web deployment
flutter build windows    # Windows executable
```

### Running Dart Console Application

```bash
cd student_grade_app
dart pub get
dart run bin/main.dart sample.xlsx
```

---

## Troubleshooting

### Kotlin Exercises

**Problem:** "kotlinc: command not found"
**Solution:** Install Kotlin compiler using package manager or download from kotlinlang.org

**Problem:** "Error: Could not find or load main class"
**Solution:** Ensure compilation includes `-include-runtime` flag and classpath is correct

**Problem:** OutOfMemoryError during compilation
**Solution:** Increase JVM heap size: `kotlinc -J-Xmx1024m Main.kt`

### Android Application

**Problem:** "Gradle sync failed"
**Solution:** 
1. Check internet connectivity
2. Update Gradle version in `gradle/wrapper/gradle-wrapper.properties`
3. Clean project: Build -> Clean Project
4. Invalidate caches: File -> Invalidate Caches / Restart

**Problem:** "Cannot resolve symbol 'apache.poi'"
**Solution:** 
1. Verify dependencies in `app/build.gradle`
2. Sync Gradle files
3. Check Maven repository accessibility

**Problem:** Application crashes on Excel import
**Solution:** 
1. Verify Excel file format matches expected structure
2. Check file permissions
3. Ensure storage permissions are granted
4. Review logcat for specific error messages

### Flutter Application

**Problem:** "Unable to locate Android SDK"
**Solution:** 
1. Run `flutter doctor`
2. Set ANDROID_HOME environment variable
3. Configure Android SDK location in Flutter settings

**Problem:** "Pub get failed"
**Solution:** 
1. Check internet connection
2. Clear pub cache: `flutter pub cache repair`
3. Update Flutter: `flutter upgrade`

**Problem:** File picker not working
**Solution:** 
1. Check platform-specific permissions
2. Verify file_picker package version compatibility
3. Ensure proper initialization in platform-specific code

---

## Learning Path

### Stage 1: Master Kotlin Fundamentals (2-3 hours)
1. Complete Exercise 1 - Understand basic lambda expressions
2. Complete Exercise 2 - Build custom higher-order functions
3. Complete Exercise 3 - Master collection transformations
4. Complete Exercise 4 - Apply concepts to complex data

### Stage 2: Explore Dart Equivalents (1-2 hours)
1. Review Dart implementations in Exercise1, Exercise2, Exercise3
2. Compare Kotlin and Dart syntax
3. Understand language-specific idioms

### Stage 3: Android Application Development (3-4 hours)
1. Run Student Calculator application
2. Analyze codebase architecture
3. Understand Android-specific components
4. Test with sample Excel files

### Stage 4: Cross-Platform Development (2-3 hours)
1. Run Flutter application
2. Explore multi-platform compatibility
3. Compare Android-specific vs. cross-platform approaches
4. Test console application variant

### Stage 5: Extension and Customization (Ongoing)
1. Modify grading formulas
2. Add new statistical analyses
3. Implement additional features
4. Optimize performance

---

## References

### Official Documentation
- Kotlin Language Reference: https://kotlinlang.org/docs/reference/
- Kotlin Collections: https://kotlinlang.org/docs/collections-overview.html
- Android Developer Guide: https://developer.android.com/
- Dart Language Tour: https://dart.dev/guides/language/language-tour
- Flutter Documentation: https://flutter.dev/docs
- Apache POI: https://poi.apache.org/

### Academic Resources
- Functional Programming in Kotlin (JetBrains Academy)
- Android Development with Kotlin (Google Codelabs)
- Flutter & Dart Complete Guide (Official Documentation)

### Package Documentation
- excel (Dart): https://pub.dev/packages/excel
- file_picker (Flutter): https://pub.dev/packages/file_picker
- Apache POI (Java): https://poi.apache.org/apidocs/

---

## Version Control

This project uses Git for version control. Key information:

**Current Branch:** add-gradle-configuration
**Remote Repository:** https://github.com/KingKayssal/Projects-kotlin-dart.git

### Common Git Commands

```bash
# View current status
git status

# View commit history
git log --oneline

# Create feature branch
git checkout -b feature/branch-name

# Stage changes
git add .

# Commit with message
git commit -m "Description of changes"

# Push to remote
git push origin branch-name

# Pull latest changes
git pull origin main
```

---

## License

This project is developed for educational purposes as part of university coursework. All code is provided for academic learning and assessment.

---

## Acknowledgments

This project demonstrates practical applications of functional programming concepts, mobile application development, and cross-platform software engineering principles using modern development frameworks and languages.

**Frameworks and Libraries Used:**
- Kotlin Standard Library
- Jetpack Compose
- Apache POI
- Dart SDK
- Flutter Framework
- Material Design Components

---

**Last Updated:** March 6, 2026

For detailed information about specific exercises or applications, refer to the README files in their respective directories.
