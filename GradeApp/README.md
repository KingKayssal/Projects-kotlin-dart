# GradeApp - Android Application

## 📌 Overview

**GradeApp** is a modern Android application for managing student grades using Jetpack Compose and Material 3 design principles. Built with Kotlin and the latest Android development tools, it provides a sleek and responsive UI for grade management.

## 🎯 Features

- 📱 Modern Jetpack Compose UI with Material 3
- ⚡ Fast and responsive interface
- 🎨 Professional dark/light theme support
- 📊 Grade management and tracking
- 🔍 Efficient search and filtering capabilities

---

## 🛠️ Technology Stack

| Component | Version/Tool |
|-----------|-------------|
| **Language** | Kotlin |
| **UI Framework** | Jetpack Compose (Material 3) |
| **Build System** | Gradle KTS |
| **Min API Level** | 26 |
| **Target API Level** | 36 |
| **Java Compatibility** | 11 |

---

## 📁 Project Structure

```
GradeApp/
├── app/                           # Main application module
│   ├── src/
│   │   ├── main/                  # Main source code
│   │   ├── androidTest/           # Instrumented tests
│   │   └── test/                  # Unit tests
│   ├── build/                     # Build outputs
│   ├── build.gradle.kts           # App-level dependencies
│   └── proguard-rules.pro         # ProGuard/R8 obfuscation rules
├── gradle/
│   ├── libs.versions.toml         # Centralized dependency versions
│   └── wrapper/                   # Gradle wrapper files
├── gradle.properties               # Gradle configuration
├── settings.gradle.kts             # Project settings
└── build.gradle.kts                # Root-level build configuration
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** (Latest version)
- **JDK 11 or higher**
- **Android SDK** with API 26+ (Target API 36+)
- **Gradle 8.0+**

### Installation

1. **Clone or navigate to the project**:
   ```bash
   cd GradeApp
   ```

2. **Sync Gradle files**:
   ```bash
   ./gradlew sync
   ```
   Or in Android Studio: `File → Sync Now`

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run on device/emulator**:
   ```bash
   # Debug build (faster, larger APK)
   ./gradlew installDebug
   
   # Or use Android Studio: Run → Run 'app'
   ```

---

## 📋 Gradle Commands

### Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK (requires signing configuration)
./gradlew assembleRelease

# Build both debug and release
./gradlew assemble

# Clean build
./gradlew clean build
```

### Installation Commands

```bash
# Install debug build on connected device
./gradlew installDebug

# Uninstall app
./gradlew uninstallDebug

# Install and run (requires emulator/device)
./gradlew installDebug
adb shell am start -n com.example.gradeapp/.MainActivity
```

### Testing Commands

```bash
# Run all tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew test --tests {TestClass}
```

---

## 🔧 Configuration

### Gradle Properties
Edit `gradle.properties` to configure:
- Gradle memory settings
- SDK paths
- Build timeout values

### Dependencies
Central version management is configured in:
- `gradle/libs.versions.toml` - All dependencies with versions
- `app/build.gradle.kts` - App-specific dependencies

**Main Dependencies**:
- Jetpack Compose for UI
- Material 3 design components
- Kotlin coroutines for async operations
- AndroidX libraries

### ProGuard/R8 Configuration
- `app/proguard-rules.pro` - Obfuscation and optimization rules
- Enabled automatically in release builds
- Reduces APK size and protects code

---

## 📦 Build Variants

The app supports multiple build variants:

| Variant | Purpose |
|---------|---------|
| `debug` | Development builds with full logging |
| `release` | Optimized production builds |

---

## 🧪 Testing

### Unit Tests
Located in: `app/src/test/`
```bash
./gradlew test
```

### Instrumented Tests
Located in: `app/src/androidTest/`
```bash
./gradlew connectedAndroidTest
```

---

## 📝 Key Files

| File | Purpose |
|------|---------|
| `app/build.gradle.kts` | App dependencies and build configuration |
| `gradle/libs.versions.toml` | Centralized dependency version management |
| `app/proguard-rules.pro` | Code obfuscation and optimization rules |
| `gradle.properties` | Global Gradle configuration |
| `settings.gradle.kts` | Project structure and module configuration |

---

## 🎨 Material 3 Design

This app uses **Material 3**, the latest Material Design system:
- Dynamic color schemes
- Adaptive components
- Improved typography
- Enhanced accessibility

---

## 📊 API Level Support

| Level | Version | Support |
|-------|---------|---------|
| 26+ | Android 8.0+ | ✅ Minimum |
| 31+ | Android 12+ | Recommended |
| 36+ | Android 15+ | Target |

---

## 🔐 Security Notes

- **ProGuard enabled** in release builds to protect code
- **Minimum API 26** ensures modern security features
- Regular dependency updates recommended

---

## 📚 Resources

- [Android Developers Guide](https://developer.android.com)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Material 3 Design System](https://m3.material.io)
- [Kotlin Documentation](https://kotlinlang.org)

---

## 🤝 Contributing

Guidelines for maintaining and extending this project:
1. Follow Kotlin style guidelines
2. Use Compose best practices
3. Write comprehensive tests
4. Update documentation with changes
5. Test on multiple API levels

---

## 📄 License

See the main project README for license information.
