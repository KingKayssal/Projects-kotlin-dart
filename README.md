# 🎓 Student Grade Management Projects

A comprehensive workspace containing multiple implementations of student grade management systems across different platforms and technologies. This collection demonstrates building the same domain logic using Kotlin/Android, Dart, and Flutter.

---

## 📁 Project Structure

```
Projects kotlin & dart/
├── Exercise1/                     # Higher-Order Functions & Predicates
│   ├── exercise1.dart
│   ├── Exercise1.kt
│   └── README.md
├── Exercise2/                     # Collection Transformations
│   ├── exercise2.dart
│   ├── Exercise2.kt
│   └── README.md
├── Exercise3/                     # Object Filtering & Aggregates
│   ├── exercise3.dart
│   ├── exercise3.kt
│   └── README.md
├── GradeApp/                      # Android App (Kotlin/Compose)
├── StudentGradeApp/               # Android App (Kotlin/Compose)
├── student_calculator/            # Dart Console Application
├── student_grade_app/             # Flutter Multi-Platform App
└── README.md                       # This file
```

---

## 🎯 Exercises Overview

### **Exercise 1: Higher-Order Functions & Predicates**

**Path**: `./Exercise1/`

Demonstrates functional programming patterns using predicates and higher-order functions to filter lists dynamically.

**Topics**: Higher-order functions, predicates, lambda expressions, functional filtering

**Files**:
- `exercise1.dart` - Dart implementation
- `Exercise1.kt` - Kotlin implementation

[📖 Full Documentation](./Exercise1/README.md)

---

### **Exercise 2: Transforming Between Collection Types**

**Path**: `./Exercise2/`

Explores converting between collection types (lists to maps) and filtering/processing data using both imperative and functional approaches.

**Topics**: Collections, transformations, maps/dictionaries, forEach, filter, map operations

**Files**:
- `exercise2.dart` - Dart implementation
- `Exercise2.kt` - Kotlin implementation

[📖 Full Documentation](./Exercise2/README.md)

---

### **Exercise 3: Filtering Objects & Computing Aggregates**

**Path**: `./Exercise3/`

Combines object-oriented programming with functional operations to filter custom objects and compute statistical aggregates.

**Topics**: Custom classes, object filtering, data extraction, reduction, aggregate calculations

**Files**:
- `exercise3.dart` - Dart implementation
- `exercise3.kt` - Kotlin implementation

[📖 Full Documentation](./Exercise3/README.md)

---

## 🚀 Projects Overview

### 1. **GradeApp** - Android Application (Kotlin + Jetpack Compose)

**Path**: `./GradeApp/`

**Description**: Modern Android application for managing student grades using Jetpack Compose.

**Technology Stack**:
- Language: Kotlin
- UI Framework: Jetpack Compose (Material 3)
- Build System: Gradle KTS
- Min SDK: API 26
- Target SDK: API 36
- Java Compatibility: 11

**Setup & Build**:
```bash
cd GradeApp
./gradlew build          # Build the project
./gradlew installDebug   # Install on connected device/emulator
```

**Key Files**:
- `app/build.gradle.kts` - App-level configuration
- `gradle/libs.versions.toml` - Dependency versions catalog
- `app/proguard-rules.pro` - ProGuard obfuscation rules

---

### 2. **StudentGradeApp** - Android Application (Kotlin + Jetpack Compose)

**Path**: `./StudentGradeApp/`

**Description**: Enhanced Android application with additional features and refined configuration.

**Technology Stack**:
- Language: Kotlin
- UI Framework: Jetpack Compose + ViewBinding
- Build System: Gradle KTS
- Min SDK: API 31
- Target SDK: API 36
- Java Compatibility: 11

**Setup & Build**:
```bash
cd StudentGradeApp
./gradlew build          # Build the project
./gradlew installDebug   # Install on connected device/emulator
```

**Key Features**:
- Higher minimum API level (31) for modern device targeting
- Advanced packaging configuration
- ViewBinding support for type-safe layout references
- Dependency exclusion rules for conflict resolution

**Key Files**:
- `app/build.gradle.kts` - App-level configuration
- `gradle/libs.versions.toml` - Dependency versions catalog

---

### 3. **student_calculator** - Dart Console Application

**Path**: `./student_calculator/`

**Description**: Command-line tool for calculating and exporting student grades from Excel files.

**Technology Stack**:
- Language: Dart 3.0+
- Dependencies: excel, path, args
- Type: Console Application

**Grading Formula**:
| Component | Max Mark |
|-----------|----------|
| CA (Continuous Assessment) | 30 |
| Exam | 70 |
| **Final Mark** | **100** |

**Grade Scale**:
| Grade | Range | Remark |
|-------|-------|--------|
| A | 80-100 | Excellent |
| B | 70-79 | Very Good |
| C | 60-69 | Good |
| D | 50-59 | Satisfactory |
| E | 40-49 | Pass |
| F | 0-39 | Fail |

**Setup & Run**:
```bash
cd student_calculator

# Get dependencies
dart pub get

# Run the calculator
dart run bin/main.dart <path_to_input.xlsx>

# Example with sample file
dart run bin/main.dart sample.xlsx
```

**Input Excel Format**:
- **Column Headers** (flexible):
  - Student ID: `ID`, `Student ID`, `No`, `Number`
  - Student Name: `Name`, `Student Name`, `Full Name`
  - CA Mark: `CA`, `CA Mark`, `Continuous` (out of 30)
  - Exam Mark: `Exam`, `Exam Mark`, `Examination` (out of 70)

**Key Files**:
- `bin/main.dart` (360 lines) - Main calculator logic
- `pubspec.yaml` - Project configuration
- `README.md` - Detailed documentation

---

### 4. **student_grade_app** - Flutter Multi-Platform Application

**Path**: `./student_grade_app/`

**Description**: Cross-platform application for viewing and analyzing student grades. Includes both Flutter UI and console variants.

**Technology Stack**:
- Language: Dart
- Framework: Flutter 3.11+
- Supported Platforms: Android, iOS, Windows, macOS, Web, Linux
- Dependencies: flutter, file_picker, excel

**Features**:
- 📱 Interactive Flutter UI for grade management
- 📊 Grade statistics and distribution analysis
- 📈 Student sorting by score
- 📂 Excel file parsing with file picker
- 🖥️ Console version available (`bin/main.dart`)

**Setup & Run**:

**Flutter App**:
```bash
cd student_grade_app

# Get dependencies
flutter pub get

# Run on available device/emulator
flutter run

# Build for specific platform
flutter build android
flutter build windows
flutter build web
```

**Console Version**:
```bash
dart run bin/main.dart <path_to_excel_file.xlsx>
```

**Console Usage Examples**:
```bash
# Simple file in current directory
dart run bin/main.dart grades.xlsx

# File with full path
dart run bin/main.dart C:\Users\YourName\Desktop\student_grades.xlsx

# File with spaces in path (use quotes)
dart run bin/main.dart "C:\Users\YourName\My Documents\grades.xlsx"
```

**Output**:
- Total student count
- Class average score
- Grade distribution (A, B, C, D, E, F percentages)
- Sorted student list with individual scores

**Key Files**:
- `lib/main.dart` (447 lines) - Flutter UI implementation
- `bin/main.dart` - Console version
- `CONSOLE_README.md` - Console application documentation
- `pubspec.yaml` - Project configuration
- `android/` - Android-specific configuration
- `ios/`, `windows/`, `web/`, `macos/`, `linux/` - Platform configurations

---

## 📊 Technology Comparison

| Feature | GradeApp | StudentGradeApp | student_calculator | student_grade_app |
|---------|----------|-----------------|-------------------|-------------------|
| **Language** | Kotlin | Kotlin | Dart | Dart/Flutter |
| **Type** | Android App | Android App | Console App | Multi-Platform App |
| **Min API/SDK** | 26 | 31 | 3.0+ | 3.11+ |
| **UI Framework** | Compose | Compose | CLI | Flutter |
| **Platforms** | Android Only | Android Only | Windows/Mac/Linux | All Major Platforms |
| **Status** | Template Project | Production Ready | Functional | Fully Functional |

---

## 🔧 Development Environment Setup

### Prerequisites

**For Android Projects (GradeApp, StudentGradeApp)**:
- Android Studio (latest version)
- Android SDK API 36
- Kotlin 1.9+
- JDK 11+
- Gradle 8.0+

**For Dart/Flutter Projects**:
- Dart SDK 3.0+
- Flutter SDK 3.11+
- Visual Studio Code or Android Studio with plugins

### Installation

**Android Studio**:
```bash
# Download and install from https://developer.android.com/studio
```

**Dart SDK**:
```bash
# https://dart.dev/get-dart
# Or via package manager:
choco install dart-sdk  # Windows (Chocolatey)
brew install dart       # macOS (Homebrew)
```

**Flutter**:
```bash
# https://flutter.dev/docs/get-started/install
```

---

## 📝 Excel File Format Examples

### Student Calculator Input
```
ID    | Name      | CA   | Exam
------|-----------|------|-----
001   | John Doe  | 25   | 65
002   | Jane Smith| 28   | 72
003   | Bob Brown | 20   | 55
```

### Console App Input
```
Name      | Score
----------|-------
John      | 85
Jane      | 92
Bob       | 78
```

---

## 🔄 Git Status

These projects are version-controlled with Git. Key commands:

```bash
# Check repository status
git status

# View recent commits
git log --oneline

# Create a new branch for features
git checkout -b feature/your-feature

# Commit changes
git add .
git commit -m "Your commit message"

# Push to remote
git push origin main
```

---

## 📚 Key Concepts & Features Across Projects

### Grading Logic
- **CA + Exam = Final Mark** (30 + 70 = 100)
- Grade assignment based on final mark thresholds
- Remarks associated with each grade

### Data Processing
- Excel file parsing and validation
- Student record management
- Grade calculation and statistics
- Report generation

### UI/UX
- Modern material design (both Android and Flutter)
- Responsive layouts
- File picker integration
- Data visualization

---

## 🤝 Contributing

When working on these projects:

1. Create a feature branch
2. Make your changes
3. Test thoroughly
4. Commit with descriptive messages
5. Push to remote repository

---

## 📖 Additional Resources

- **Dart Documentation**: https://dart.dev/guides
- **Flutter Documentation**: https://flutter.dev/docs
- **Android Development**: https://developer.android.com/docs
- **Jetpack Compose**: https://developer.android.com/jetpack/compose
- **Excel Package**: https://pub.dev/packages/excel
- **Apache POI (Java)**: https://poi.apache.org/

---

## ⚖️ License

These projects are part of a learning/experimental workspace. Check individual project configurations for license information.

---

**Last Updated**: March 4, 2026

For questions or contributions, refer to individual project documentation.
