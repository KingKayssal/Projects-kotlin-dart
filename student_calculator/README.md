# student_calculator - Dart Console Application

## 📌 Overview

**student_calculator** is a command-line tool for calculating student grades from Excel files. It processes continuous assessment (CA) and examination scores, computes final grades, assigns letter grades, and provides comprehensive reporting capabilities.

## 🎯 Features

- 📊 Parse grades from Excel spreadsheets
- 🧮 Automatic grade calculation with configurable weights
- 📈 Grade distribution analysis
- 🎓 Based on standard grading scales
- 💾 Export results to output files
- 🔍 Flexible column header recognition
- ✅ Data validation and error handling

---

## 🛠️ Technology Stack

| Component | Version |
|-----------|---------|
| **Language** | Dart 3.0+ |
| **Package Manager** | Pub |
| **Type** | Console Application |

---

## 📦 Key Dependencies

| Package | Purpose |
|---------|---------|
| `excel` | Excel file parsing |
| `path` | File path manipulation |
| `args` | Command-line argument parsing |

---

## 📁 Project Structure

```
student_calculator/
├── bin/
│   ├── main.dart                  # Entry point
│   └── create_sample.dart         # Sample data generator
├── sample/                        # Sample Excel files
├── pubspec.yaml                   # Project configuration
└── README.md                       # Project documentation
```

---

## 📐 Grading Formula

| Component | Max Mark | Weight |
|-----------|----------|--------|
| CA (Continuous Assessment) | 30 | 30% |
| Exam | 70 | 70% |
| **Total Final Mark** | **100** | **100%** |

**Calculation**: Final Mark = CA + (Exam × 0.7)

### Grade Scale

| Grade | Range | Remark | Percentage |
|-------|-------|--------|-----------|
| **A** | 80-100 | Excellent | Top 20% |
| **B** | 70-79 | Very Good | Next 20% |
| **C** | 60-69 | Good | Middle 20% |
| **D** | 50-59 | Satisfactory | Next 20% |
| **E** | 40-49 | Pass | Bottom threshold |
| **F** | 0-39 | Fail | Below passing |

---

## 🗂️ Input Excel Format

Your input `.xlsx` file must have these column headers:

| Column | Required | Accepted Header Names |
|--------|----------|----------------------|
| Student ID | Optional | `ID`, `Student ID`, `No`, `Number` |
| Student Name | Optional | `Name`, `Student Name`, `Full Name` |
| CA Mark | ✅ Yes | `CA`, `CA Mark`, `Continuous` |
| Exam Mark | ✅ Yes | `Exam`, `Exam Mark`, `Examination` |

> ⚠️ **CA must be out of 30. Exam must be out of 70.**

### Example Excel Structure

```
| ID  | Student Name | CA | Exam |
|-----|--------------|----|----|
| 101 | Alice        | 28 | 65 |
| 102 | Bob          | 25 | 70 |
| 103 | Charlie      | 30 | 55 |
| 104 | Diana        | 20 | 45 |
| 105 | Eve          | 22 | 80 |
```

---

## 🚀 Setup & Run

### Prerequisites

- **Dart SDK 3.0+** - [Install Dart](https://dart.dev/get-dart)
- **Command-line terminal**
- **Excel file** with student grades

### Installation

1. **Navigate to the project**:
   ```bash
   cd student_calculator
   ```

2. **Get dependencies**:
   ```bash
   dart pub get
   ```

3. **Verify installation**:
   ```bash
   dart run bin/main.dart --help
   ```

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

### 3. Run with file argument
```bash
# Process a local Excel file
dart run bin/main.dart grades.xlsx

# Full path on Windows
dart run bin/main.dart C:\Users\Student\Desktop\grades.xlsx

# Full path on macOS/Linux
dart run bin/main.dart /Users/student/Desktop/grades.xlsx
```

### 4. Compile to Standalone Executable
```bash
# Compile to standalone .exe (Windows)
dart compile exe bin/main.dart -o student_calculator.exe

# Compile to app (macOS)
dart compile exe bin/main.dart -o student_calculator

# Then run anywhere without Dart SDK
./student_calculator marks.xlsx
```

---

## 📈 Usage Examples

### Basic Usage
```bash
dart run bin/main.dart grades.xlsx
```

### Output Format

The program generates:
1. **Console Report**: Displays grades and statistics
2. **Summary Statistics**: Average, highest, lowest grades
3. **Grade Distribution**: Count of each grade
4. **Pass/Fail Rate**: Percentage of passing students

### Example Console Output

```
═══════════════════════════════════════════════════════════
              GRADE CALCULATION SUMMARY
═══════════════════════════════════════════════════════════

Student Grade Distribution:
─────────────────────────────────────────────────────────
Total Students Processed:    105
Grade A (80-100):            18 students
Grade B (70-79):             25 students
Grade C (60-69):             30 students
Grade D (50-59):             22 students
Grade E (40-49):             7 students
Grade F (0-39):              3 students

Class Statistics:
─────────────────────────────────────────────────────────
Average Grade:               65.3
Highest Grade:               98 (Alice Johnson)
Lowest Grade:                22 (Bob Smith)
Pass Rate:                   97.1%
Fail Rate:                   2.9%

Grade Distribution Chart:
─────────────────────────────────────────────────────────
A ███████░░░░░░░░░░░  (17%)
B █████████░░░░░░░░░░ (24%)
C ████████████░░░░░░░ (29%)
D ██████████░░░░░░░░░░ (21%)
E ██░░░░░░░░░░░░░░░░░  (7%)
F █░░░░░░░░░░░░░░░░░░  (3%)
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
| Final Mark (/100) | CA + (Exam × 0.7) |
| Grade | A to F |
| Remark | Excellent → Fail |

### Colour Coding (in Excel Output)

| Colour | Meaning |
|--------|---------|
| 🟢 Green | A & B — Distinction |
| 🟡 Yellow | C, D, E — Pass |
| 🔴 Red | F — Fail |
| 🔵 Blue | Summary row |

---

## ⚙️ Configuration Options

### Command-line Arguments

```bash
# Display help
dart run bin/main.dart --help

# Specify output file name
dart run bin/main.dart input.xlsx --output results.xlsx

# Verbose mode
dart run bin/main.dart input.xlsx --verbose
```

---

## 🧪 Testing

### Generate Test Data

```bash
# Generate sample Excel file
dart run bin/create_sample.dart
```

Creates a sample file with:
- 20-30 students
- Various CA and Exam score combinations
- Mix of pass and fail grades

### Test Processing

```bash
dart run bin/main.dart sample.xlsx
```

---

## 🔍 Column Header Flexibility

The program is flexible with column headers (case-insensitive):

✅ **Valid Headers**:
- `CA`, `ca`, `Ca`, `cA`
- `Exam Mark`, `exam mark`, `EXAM MARK`
- `Student Name`, `studentname`, `Student_Name`

The program uses fuzzy matching to identify columns automatically.

---

## 📁 Project Structure

```
student_calculator/
├── bin/
│   ├── main.dart                  # Main calculator logic
│   └── create_sample.dart         # Sample data generator
├── sample/                        # Sample Excel files
├── pubspec.yaml                   # Project configuration
└── README.md                       # This documentation
```

---

## 📦 Dependencies

| Package | Version | Purpose |
|---------|---------|---------|
| `excel` | ^4.0.0+ | Read/write .xlsx files |
| `path` | ^1.9.0+ | Cross-platform file paths |
| `args` | ^2.4.2+ | CLI argument parsing |

---

## 🔄 Processing Pipeline

```
Excel File (.xlsx)
      ↓
Read and Parse Sheets
      ↓
Identify Column Headers
      ↓
Validate CA & Exam Marks
      ↓
Calculate Final Marks
      ↓
Assign Letter Grades
      ↓
Compute Statistics
      ↓
Generate Report
      ↓
Display Results
```

---

## ✅ Error Handling

### Missing Columns
```
Error: Could not find Student ID column
Searched for: ID, Student ID, No, Number
Found columns: Name, CA, Exam
```

### Invalid Marks
```
Warning: Row 5 - Invalid CA mark: 35 (maximum is 30)
Warning: Row 7 - Invalid Exam mark: 75 (maximum is 70)
```

### Corrupted File
```
Error: Could not read Excel file
Details: File not found or corrupted
```

---

## 💾 File Handling

### Supported Formats
- ✅ `.xlsx` (Excel 2007+)
- ✅ Relative paths: `grades.xlsx`
- ✅ Absolute paths: `/User/Desktop/grades.xlsx`

### File Requirements
- Must be valid Excel format
- First sheet is processed
- Requires proper column headers
- Minimum 2 rows (header + 1 student)

---

## 🎓 Grade Calculation Examples

### Example 1: High Achiever
```
Student: Alice Johnson
CA Mark: 28/30
Exam Mark: 65/70

Final = 28 + (65 × 0.7) = 28 + 45.5 = 73.5
Grade: B (Very Good)
```

### Example 2: Average Student
```
Student: Bob Smith
CA Mark: 20/30
Exam Mark: 50/70

Final = 20 + (50 × 0.7) = 20 + 35 = 55
Grade: D (Satisfactory)
```

### Example 3: Excellent Performance
```
Student: Charlie Davis
CA Mark: 30/30
Exam Mark: 70/70

Final = 30 + (70 × 0.7) = 30 + 49 = 79
Grade: B (Very Good)
```

---

## 🚨 Troubleshooting

### Common Issues

**Issue**: "Pub get failed"
```
Solution: 
dart pub cache clean
dart pub get
```

**Issue**: "Can't find Excel package"
```
Solution:
dart pub add excel
dart pub get
```

**Issue**: "File not found"
```
Solution: 
1. Verify file: ls grades.xlsx (macOS/Linux) or dir grades.xlsx (Windows)
2. Use full absolute path
3. Check extension is .xlsx
```

**Issue**: "Invalid column headers"
```
Solution:
1. Open Excel file
2. Check column names match expected names
3. Ensure CA and Exam columns exist
4. Re-save file as .xlsx format
```

---

## 📚 Resources

- [Dart Documentation](https://dart.dev)
- [Excel Package](https://pub.dev/packages/excel)
- [Pub Documentation](https://pub.dev)

---

## 🤝 Contributing

Guidelines:
1. Follow Dart style guide: `dart format`
2. Add tests for new features
3. Document significant changes
4. Test with various Excel formats

---

## 📄 License

See the main project README for license information.