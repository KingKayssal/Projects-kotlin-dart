# StudentGradeApp - Android Application

## 📌 Overview

**StudentGradeApp** is an enhanced Android application for managing student grades with advanced features and modern Kotlin development practices. It builds upon the foundation of GradeApp with additional functionality and refined configuration for contemporary Android development.

## 🎯 Features

- 📱 Modern Jetpack Compose UI with Material 3
- ⚡ Type-safe view binding with ViewBinding
- 🎨 Professional dark/light theme support
- 📊 Advanced grade management and analytics
- 🔍 Smart filtering and search capabilities
- 🔄 Seamless state management with coroutines
- 📡 Dependency conflict resolution optimization

---

## 🛠️ Technology Stack

| Component | Version/Tool |
|-----------|-------------|
| **Language** | Kotlin |
| **UI Framework** | Jetpack Compose + ViewBinding |
| **Build System** | Gradle KTS |
| **Min API Level** | 31 (Android 12+) |
| **Target API Level** | 36 (Android 15+) |
| **Java Compatibility** | 11 |

---

## 📈 Key Improvements

### Over Standard GradeApp

| Feature | GradeApp | StudentGradeApp |
|---------|----------|-----------------|
| Min API | 26 | **31** ✨ |
| ViewBinding | ❌ | ✅ Type-safe |
| Packaging | Basic | **Advanced** 🔧 |
| Dependency Management | Standard | **Optimized** ⚙️ |

### Benefits of Higher Minimum API (31)

- 🚀 Faster performance on modern devices
- 🔐 Better security features
- 📱 Enhanced Material 3 support
- 🎯 Access to Android 12+ APIs
- 💾 Smaller device compatibility baseline

---

## 📁 Project Structure

```
StudentGradeApp/
├── app/                           # Main application module
│   ├── src/
│   │   ├── main/                  # Main source code
│   │   │   ├── java/kotlin/       # App logic
│   │   │   ├── res/               # Resources
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/           # Instrumented tests
│   │   └── test/                  # Unit tests
│   ├── build/                     # Build outputs
│   ├── build.gradle.kts           # App-level dependencies
│   └── proguard-rules.pro         # ProGuard/R8 obfuscation rules
├── gradle/
│   ├── libs.versions.toml         # Centralized dependency versions
│   ├── gradle-daemon-jvm.properties
│   └── wrapper/                   # Gradle wrapper files
├── gradle.properties               # Gradle configuration
├── local.properties                # Local development settings
├── settings.gradle.kts             # Project settings
└── build.gradle.kts                # Root-level build configuration
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** (Latest version - Hedgehog or newer)
- **JDK 11 or higher**
- **Android SDK** with API 31+ (Recommended: API 36+)
- **Gradle 8.0+**
- **Android 12+ device/emulator** for running

### Installation

1. **Navigate to the project**:
   ```bash
   cd StudentGradeApp
   ```

2. **Configure Local Environment**:
   Create `local.properties` if needed:
   ```properties
   sdk.dir=/path/to/android/sdk
   ```

3. **Sync Gradle files**:
   ```bash
   ./gradlew sync
   ```

4. **Build the project**:
   ```bash
   ./gradlew build
   ```

5. **Install on device**:
   ```bash
   # Device must be API 31+
   ./gradlew installDebug
   ```

---

## 📋 Gradle Commands

### Build Commands

```bash
# Development build
./gradlew assembleDebug

# Production build
./gradlew assembleRelease

# Complete build
./gradlew assemble

# Clean and rebuild
./gradlew clean build
```

### Installation Commands

```bash
# Install debug APK
./gradlew installDebug

# Install release APK (requires signing)
./gradlew installRelease

# Run app (if only one device connected)
./gradlew installDebug
adb shell am start -n com.example.studentgradeapp/.MainActivity
```

### Development Commands

```bash
# Run all tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Run with verbose output
./gradlew build --stacktrace --info
```

---

## 🔧 Configuration Files

### `build.gradle.kts` (App Level)

Contains:
- Kotlin version and plugins
- Android configuration
- Build types (debug/release)
- Dependency declarations
- Signing configurations

Example structure:
```kotlin
plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.studentgradeapp"
    compileSdk = 36
    
    defaultConfig {
        minSdk = 31      // Android 12+
        targetSdk = 36   // Android 15+
    }
}
```

### `gradle/libs.versions.toml`

Central version management:
- **Versions section**: All library versions
- **Libraries section**: Dependency definitions
- **Plugins section**: Gradle plugin versions

Benefits:
- ✅ Single source of truth
- ✅ Easy dependency updates
- ✅ Prevents version conflicts

### `gradle.properties`

Global settings:
```properties
org.gradle.jvmargs=-Xmx2048m
android.useNewApkStructure=true
kotlin.code.style=official
```

### `local.properties`

Development machine settings (not version controlled):
```properties
sdk.dir=/Users/username/Library/Android/sdk
```

---

## 📦 Build Configuration

### API Levels

```
Min: 31 (Android 12)     ←─── Minimum supported
↓
Target: 36 (Android 15)  ←─── Recommended for latest features
↑
Compile: 36              ←─── Used for compilation
```

### ViewBinding Integration

Type-safe view binding replaces findViewById:

```kotlin
// Traditional way (❌ error-prone)
val button: Button = findViewById(R.id.button)

// ViewBinding way (✅ safe)
val binding = ActivityMainBinding.inflate(layoutInflater)
val button = binding.button
```

### Dependency Conflict Resolution

The project includes exclusion rules to prevent conflicts:

```gradle
configurations.all {
    resolutionStrategy {
        force 'some.library:some-library:1.0'
    }
}
```

---

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```
Location: `app/src/test/`

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```
Location: `app/src/androidTest/`

### Test Coverage
```bash
./gradlew testDebugUnitTest
```

---

## 🎨 Material 3 Design

Enhanced Material 3 support:
- Dynamic color system
- Adaptive typography
- Modern components
- Improved accessibility
- Dark mode optimization

---

## 🔐 Security Features

- **Minimum API 31**: Access to latest security APIs
- **ProGuard enabled**: Code obfuscation in release builds
- **ViewBinding**: Type-safe, null-safe view access
- **Gradle dependency scanning**: Automated vulnerability checks

---

## ⚙️ Advanced Configuration

### Gradle Daemon
Configure JVM settings for faster builds:
```properties
# gradle/gradle-daemon-jvm.properties
-XX:MaxMetaspaceSize=1g
```

### Build Caching
```bash
./gradlew build --build-cache
```

### Parallel Builds
```properties
org.gradle.parallel=true
org.gradle.workers.max=8
```

---

## 📊 Performance Optimization

### APK Size Reduction
- **R8/ProGuard**: Code minification and obfuscation
- **Gradle build cache**: Faster subsequent builds
- **Dependency optimization**: Remove unused libraries

### Build Time Optimization
- Parallel execution enabled
- Build daemon configured
- Incremental compilation support

---

## 📚 Resources

- [Android Studio Documentation](https://developer.android.com/studio)
- [Gradle Documentation](https://docs.gradle.org)
- [Jetpack Compose Guide](https://developer.android.com/jetpack/compose)
- [ViewBinding Guide](https://developer.android.com/topic/libraries/view-binding)
- [Material 3 Design](https://m3.material.io)

---

## 🚨 Troubleshooting

### Common Issues

**Issue**: "Minimum API level mismatch"
```
Solution: Ensure connected device is API 31+
Run: adb shell getprop ro.build.version.sdk
```

**Issue**: "Gradle sync failed"
```
Solution: 
1. ./gradlew clean
2. File → Invalidate Caches and Restart
3. Re-sync Gradle
```

**Issue**: "Build timeout"
```
Solution: Increase Gradle JVM memory in gradle.properties
org.gradle.jvmargs=-Xmx4096m
```

---

## 🤝 Contributing

Guidelines:
1. Follow Kotlin coding standards
2. Use ViewBinding for all UI components
3. Implement comprehensive tests
4. Update documentation
5. Test on API 31+ devices

---

## 📄 License

See the main project README for license information.
