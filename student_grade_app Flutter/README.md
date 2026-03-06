# student_grade_app - Flutter Multi-Platform Application

## 📌 Overview

**student_grade_app** is a comprehensive Flutter application for managing, viewing, and analyzing student grades across multiple platforms. It combines a modern Flutter UI with backend processing capabilities and includes both visual and console-based interfaces for grade management.

## 🎯 Features

- 📱 **Cross-platform UI**: Works on Android, iOS, Windows, macOS, Web, and Linux
- 📊 **Interactive Dashboard**: View and analyze student grades visually
- 📈 **Statistics & Analytics**: Grade distribution, averages, and trends
- 📂 **File Management**: Import Excel files with student grades
- 🔍 **Advanced Filtering**: Sort and search students by scores
- 🎨 **Modern Material Design**: Beautiful, responsive interface
- 💾 **Data Persistence**: Cache and manage grade data locally
- 🖥️ **Console Version**: Command-line interface for batch processing
- 🌙 **Dark Mode Support**: Complete theme customization

---

## 🛠️ Technology Stack

| Component | Version |
|-----------|---------|
| **Language** | Dart |
| **Framework** | Flutter 3.11+ |
| **Package Manager** | Pub |

### Key Dependencies

| Package | Purpose |
|---------|---------|
| `flutter` | Flutter framework |
| `file_picker` | File selection for Excel imports |
| `excel` | Excel file parsing |
| `provider` | State management |
| `path` | File path handling |

---

## 📱 Platform Support

| Platform | Status | Min Version |
|----------|--------|------------|
| **Android** | ✅ Full | Android 5.0 (API 21) |
| **iOS** | ✅ Full | iOS 11.0+ |
| **Windows** | ✅ Full | Windows 10+ |
| **macOS** | ✅ Full | macOS 10.14+ |
| **Web** | ✅ Full | Modern browsers |
| **Linux** | ✅ Full | Ubuntu 18.04+ |

---

## 📁 Project Structure

```
student_grade_app/
├── lib/                           # Main Flutter app
│   ├── main.dart                  # App entry point
│   ├── screens/                   # UI screens
│   ├── widgets/                   # Reusable components
│   ├── models/                    # Data models
│   ├── services/                  # Business logic
│   └── utils/                     # Helper functions
├── bin/
│   ├── main.dart                  # Console app entry point
│   └── create_sample.dart         # Sample data generator
├── test/
│   ├── widget_test.dart           # Flutter widget tests
│   └── unit_test.dart             # Unit tests
├── android/                       # Android module
├── ios/                           # iOS module
├── windows/                       # Windows module
├── macos/                         # macOS module
├── linux/                         # Linux module
├── web/                           # Web module
├── pubspec.yaml                   # Project configuration
├── analysis_options.yaml          # Dart analyzer config
└── README.md                       # Project documentation
```

---

## 🚀 Getting Started

### Prerequisites

**For All Platforms**:
- Dart 3.0+
- Flutter 3.11+
- Text editor/IDE (VS Code, Android Studio, etc.)

**Platform-Specific**:
- **Android**: Android SDK 21+, emulator or device
- **iOS**: Xcode 12+, iPhone simulator or device
- **Windows**: Windows 10+, Visual Studio Build Tools
- **macOS**: Xcode 12+, macOS 10.14+
- **Web**: Any modern browser
- **Linux**: Development libraries (see below)

### Installation

1. **Install Flutter**:
   ```bash
   # Visit https://flutter.dev/docs/get-started/install
   # Follow platform-specific instructions
   ```

2. **Verify installation**:
   ```bash
   flutter --version
   flutter doctor  # Check setup
   ```

3. **Navigate to project**:
   ```bash
   cd student_grade_app
   ```

4. **Get dependencies**:
   ```bash
   flutter pub get
   ```

---

## 🔧 Development Setup

### VS Code Setup

1. **Install extensions**:
   - Flutter
   - Dart
   - Awesome Flutter Snippets

2. **Select device**:
   ```bash
   flutter devices  # List available devices
   ```

3. **Run app**:
   ```bash
   flutter run
   ```

### Android Studio Setup

1. **Install plugins**:
   - Flutter
   - Dart

2. **Configure emulator**: AVD Manager → Create device

3. **Run app**: Run button or `flutter run`

---

## ▶️ Running the App

### Flutter App (UI)

```bash
# Run on default device
flutter run

# Run on specific device
flutter run -d <device_id>

# Run with specific entrypoint
flutter run lib/main.dart

# Run in release mode
flutter run --release

# Run with debug logs
flutter run --verbose
```

### Console App

```bash
# Run console version
dart run bin/main.dart

# Process Excel file in console
dart run bin/main.dart path/to/grades.xlsx

# Generate sample data
dart run bin/create_sample.dart
```

---

## 📦 Building for Distribution

### Android

```bash
# Build APK (installable app)
flutter build apk

# Build App Bundle (for Play Store)
flutter build appbundle

# Build signed APK
flutter build apk --release
```

### iOS

```bash
# Build iOS app
flutter build ios

# Build for physical device
flutter build ios --release
```

### Windows

```bash
# Build Windows executable
flutter build windows --release

# Output location: build/windows/runner/Release/
```

### macOS

```bash
# Build macOS app
flutter build macos --release

# Output location: build/macos/Build/Products/Release/
```

### Web

```bash
# Build for web
flutter build web

# Deploy to hosting service
flutter build web --release
```

### Linux

```bash
# Build Linux app
flutter build linux --release

# Output location: build/linux/x64/release/bundle/
```

---

## 📊 Application Architecture

### Model-View-ViewModel (MVVM)

```
Data Model (Excel/Local Storage)
        ↓
Business Logic (Grade Calculator)
        ↓
View Model (State Management)
        ↓
UI Screens (Dart Widgets)
```

### State Management

The app uses **Provider** pattern for state management:

```dart
// Accessing state
final grades = Provider.of<GradeProvider>(context);

// Updating state
grades.addGrade(student);
```

---

## 🎨 User Interface Features

### Main Screens

1. **Home Screen**
   - Overview of all students
   - Quick statistics
   - Recent grade updates

2. **Grades List**
   - Sortable student list
   - Filter by grade range
   - Search by name or ID
   - Quick edit/delete actions

3. **Statistics Dashboard**
   - Grade distribution chart
   - Average calculations
   - Pass/fail rates
   - Performance trends

4. **Import Screen**
   - File picker for Excel files
   - Preview before import
   - Error reporting

5. **Student Detail**
   - Complete student information
   - Grade breakdown
   - Calculation formula display
   - History tracking

### Design System

- **Material 3** components
- **Light/Dark** theme support
- **Responsive** layouts
- **Adaptive** navigation

---

## 📝 Features Workflow

### Import Grades from Excel

```
Select File (File Picker)
        ↓
Read Excel Data
        ↓
Validate Columns & Data
        ↓
Calculate Grades
        ↓
Store Locally
        ↓
Display in UI
```

### View Analytics

```
Load Stored Grades
        ↓
Calculate Statistics
        ↓
Generate Charts
        ↓
Display Dashboard
```

---

## 🧪 Testing

### Widget Tests

```bash
# Run widget tests
flutter test

# Run specific test file
flutter test test/widget_test.dart

# Run with coverage
flutter test --coverage
```

### Unit Tests

```bash
# Run unit tests
flutter test test/unit_test.dart
```

### Integration Tests

```bash
# Run integration tests
flutter drive --target=test_driver/app.dart
```

---

## 📊 Grading System

Same as student_calculator:

| Grade | Range | Status |
|-------|-------|--------|
| A | 80-100 | Excellent |
| B | 70-79 | Very Good |
| C | 60-69 | Good |
| D | 50-59 | Satisfactory |
| E | 40-49 | Pass |
| F | 0-39 | Fail |

---

## 💾 Data Management

### Local Storage

Data is persisted using:
- **SQLite** for relational data
- **Shared Preferences** for app settings
- **File System** for Excel imports

### Data Structure

```dart
class Student {
  String id;
  String name;
  double caMarks;      // Continuous Assessment
  double examMarks;    // Examination
  double finalMarks;   // Calculated: CA + (Exam × 0.7)
  String grade;        // Letter grade (A-F)
  DateTime timestamp;
}
```

---

## 🔍 Search & Filter

### Features

- **Search by name**: Partial name matching
- **Search by ID**: Exact ID lookup
- **Filter by grade**: Show specific grades (A, B, C, etc.)
- **Filter by score range**: E.g., 60-70
- **Sort options**:
  - By name (A-Z)
  - By score (highest first)
  - By grade
  - By date

---

## 🎨 Customization

### Theme Configuration

Edit in `lib/main.dart`:

```dart
theme: ThemeData(
  useMaterial3: true,
  colorScheme: ColorScheme.fromSeed(
    seedColor: Colors.blue,
  ),
),
```

### App Strings & Localization

Centralized in `lib/utils/strings.dart`:

```dart
class AppStrings {
  static const String appTitle = 'Student Grade App';
  static const String grades = 'Grades';
  ...
}
```

---

## ⚙️ Configuration

### pubspec.yaml

Modify dependencies:

```yaml
dependencies:
  flutter:
    sdk: flutter
  file_picker: ^5.0.0
  excel: ^2.0.0
  provider: ^6.0.0

dev_dependencies:
  flutter_test:
    sdk: flutter
```

---

## 🚨 Troubleshooting

### Common Issues

**Issue**: "Flutter command not found"
```
Solution: Add Flutter bin to PATH
export PATH="$PATH:`flutter/bin`"
```

**Issue**: "Device not found"
```
Solution: 
flutter devices        # List devices
flutter emulators     # List emulators
flutter emulators --launch <emulator_name>
```

**Issue**: "Build failed"
```
Solution:
flutter clean
flutter pub get
flutter pub upgrade
flutter run
```

**Issue**: "Hot reload not working"
```
Solution: 
1. Restart debug session
2. Use hot restart: press 'R' in terminal
3. Cold restart: flutter run
```

---

## 📚 Resources

- [Flutter Documentation](https://flutter.dev/docs)
- [Dart Documentation](https://dart.dev)
- [Flutter Gallery Examples](https://gallery.flutter.dev)
- [Provider Package](https://pub.dev/packages/provider)
- [Excel Package](https://pub.dev/packages/excel)

---

## 🔗 Related Projects

- `student_calculator` - Console-based grade calculator
- `GradeApp` - Android-only Jetpack Compose implementation
- `StudentGradeApp` - Enhanced Android application

---

## 🎓 Learning Outcomes

By studying this project, you'll learn:

✅ Flutter multi-platform development
✅ State management with Provider
✅ Working with Excel files in Dart
✅ Building responsive UIs
✅ File I/O and local storage
✅ Cross-platform app deployment
✅ Testing Flutter applications

---

## 🤝 Contributing

Guidelines:
1. Follow Dart style guide: `dart format`
2. Add tests for new features
3. Update documentation
4. Test on multiple platforms
5. Use meaningful commit messages

---

## 📄 License

See the main project README for license information.
