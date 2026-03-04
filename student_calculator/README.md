# 🎓 Student Grade Calculator

A console-based Dart application that reads CA and Exam marks from an
Excel file, computes the final mark and grade, then exports a formatted
results Excel sheet.

---

## 📐 Grading Formula

| Component | Max Mark | 
|-----------|----------|
| CA (Continuous Assessment) | 30 |
| Exam | 70 |
| **Final Mark** | **100** |

**Final Mark = CA Mark + Exam Mark**

### Grade Scale
| Grade | Range     | Remark       |
|-------|-----------|--------------|
| A     | 80 – 100  | Excellent    |
| B     | 70 – 79   | Very Good    |
| C     | 60 – 69   | Good         |
| D     | 50 – 59   | Satisfactory |
| E     | 40 – 49   | Pass         |
| F     | 0 – 39    | Fail         |

---

## 🗂️ Input Excel Format

Your input `.xlsx` file must have these column headers:

| Column       | Required | Accepted Header Names               |
|-------------|----------|--------------------------------------|
| Student ID  | Optional | `ID`, `Student ID`, `No`, `Number`   |
| Student Name| Optional | `Name`, `Student Name`, `Full Name`  |
| CA Mark     | ✅ Yes   | `CA`, `CA Mark`, `Continuous`        |
| Exam Mark   | ✅ Yes   | `Exam`, `Exam Mark`, `Examination`   |

> ⚠️ CA must be out of 30. Exam must be out of 70.

---

## 🚀 Setup & Run

### Requirements
- Dart SDK 3.0 or higher → https://dart.dev/get-dart

### 1. Install dependencies
```bash
dart pub get
```

### 2. Run interactively
```bash
dart run bin/main.dart
```
The app will prompt you for:
- Path to your input Excel file
- Path for the output Excel file (press Enter for default)

### 3. Run with arguments
```bash
dart run bin/main.dart -i "C:\path\to\marks.xlsx" -o "C:\path\to\results.xlsx"
```

### 4. Compile to a standalone .exe
```bash
dart compile exe bin/main.dart -o student_calculator.exe
```
Then run it anywhere with:
```bash
student_calculator.exe -i marks.xlsx -o results.xlsx
```

---

## 📊 Output Excel

The generated results file includes:

| Column | Description |
|--------|-------------|
| Student ID | From input file |
| Student Name | From input file |
| CA Mark (/30) | Raw CA score |
| Exam Mark (/70) | Raw Exam score |
| Final Mark (/100) | CA + Exam |
| Grade | A to F |
| Remark | Excellent → Fail |

### Colour Coding
| Colour | Meaning |
|--------|---------|
| 🟢 Green | A & B — Distinction |
| 🟡 Yellow | C, D, E — Pass |
| 🔴 Red | F — Fail |
| 🔵 Blue | Summary row |

---

## 📁 Project Structure
```
student_calculator/
├── bin/
│   └── main.dart          ← Main application code
├── sample/
│   └── marks.xlsx         ← Example input file
├── pubspec.yaml           ← Dart dependencies
└── README.md              ← This file
```

---

## 📦 Dependencies

| Package | Version | Purpose |
|---------|---------|---------|
| excel   | ^4.0.6  | Read/write .xlsx files |
| path    | ^1.9.0  | Cross-platform file paths |
| args    | ^2.4.2  | CLI argument parsing |

---

## 💡 Example Console Output
```
╔══════════════════════════════════════════════╗
║      STUDENT GRADE CALCULATOR  v1.0          ║
║   CA (30pts) + Exam (70pts) = Final (100)    ║
╚══════════════════════════════════════════════╝

  CLASS SUMMARY
  ─────────────────────────────────────
  Total Students : 15
  Class Average  : 57.5%
  Passed         : 12
  Failed         : 3
  Highest Mark   : 82.0 — Isaac Newton
  Lowest Mark    : 24.0 — Charlie Brown

  GRADE DISTRIBUTION:
  A : ██         (2)
  B : ██         (2)
  C : ████       (4)
  D : ██         (2)
  E : ██         (2)
  F : ███        (3)
```