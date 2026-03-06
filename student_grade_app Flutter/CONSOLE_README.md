# Student Grade App - Console Version

A command-line tool to analyze student grades from Excel files.

## Features

- ✅ Parse Excel files (.xlsx) from command line
- 📊 Display grade statistics (total students, class average, etc.)
- 📈 Show grade distribution (A, B, C, D, E, F percentages)
- 👨‍🎓 List all students sorted by score
- ⚡ Fast and efficient processing

## Usage

### Run the console app with an Excel file path:

```bash
dart run bin/main.dart <path_to_excel_file.xlsx>
```

### Examples:

```bash
# Simple file in current directory
dart run bin/main.dart grades.xlsx

# File with full path
dart run bin/main.dart C:\Users\YourName\Desktop\student_grades.xlsx

# File with spaces in path (use quotes)
dart run bin/main.dart "C:\Users\YourName\My Documents\grades.xlsx"
```

## Excel File Format

Your Excel file should have the following format:

| Column A | Column B |
|----------|----------|
| Name     | Score    |
| John     | 85       |
| Jane     | 92       |
| Bob      | 78       |

- **Column A**: Student name
- **Column B**: Student score (numeric value)
- **Row 1**: Header (will be skipped)
- **Rows 2+**: Student data

## Output Example

```
📂 Processing: grades.xlsx
============================================================

✅ Successfully loaded 5 students
============================================================

📊 CLASS STATISTICS:
   Total Students: 5
   Class Average: 85.40
   Highest Score: 92.0
   Lowest Score: 78.0

📈 GRADE DISTRIBUTION:
   Grade A: 1 students (20.0%)
   Grade B: 2 students (40.0%)
   Grade C: 1 students (20.0%)
   Grade D: 0 students (0.0%)
   Grade E: 0 students (0.0%)
   Grade F: 1 students (20.0%)

👨‍🎓 STUDENT LIST (sorted by score):
────────────────────────────────────────────────────────
Rank | Student Name          | Score  | Grade
────────────────────────────────────────────────────────
1   | Jane                  | 92.0   | A
2   | John                  | 85.0   | B
3   | Bob                   | 78.0   | C
4   | Alice                 | 81.0   | B
5   | Charlie               | 75.0   | C
────────────────────────────────────────────────────────

✨ Done!
```

## Grading Scale

- **A**: Score >= 90
- **B**: Score >= 80
- **C**: Score >= 70
- **D**: Score >= 60
- **E**: Score >= 50
- **F**: Score < 50

## Installation

Make sure you have Dart SDK installed. Then install dependencies:

```bash
dart pub get
```

## Troubleshooting

- **"File not found"**: Check the file path and make sure it's correct
- **"Invalid .xlsx file"**: Ensure the file is a valid Excel format
- **"No valid student data found"**: Check your Excel format (Column A = Name, Column B = Score)

