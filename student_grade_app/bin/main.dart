import 'dart:io';
import 'dart:typed_data';
import 'package:excel/excel.dart';

/* =========================
   STUDENT MODEL
========================= */
class Student {
  final String name;
  final double score;
  final String grade;

  Student({required this.name, required this.score, required this.grade});

  @override
  String toString() {
    return '$name | Score: ${score.toStringAsFixed(1)} | Grade: $grade';
  }
}

/* =========================
   GRADE SERVICE
========================= */
class GradeService {
  static String calculateGrade(double score) {
    if (score >= 90) return "A";
    if (score >= 80) return "B";
    if (score >= 70) return "C";
    if (score >= 60) return "D";
    if (score >= 50) return "E";
    return "F";
  }
}

/* =========================
   MAIN PROGRAM
========================= */
void main(List<String> args) async {
  if (args.isEmpty) {
    print('❌ Error: No file path provided');
    print('Usage: dart run bin/main.dart <path_to_excel_file>');
    print('Example: dart run bin/main.dart grades.xlsx');
    exit(1);
  }

  final filePath = args[0];

  try {
    // Check if file exists
    final file = File(filePath);
    if (!await file.exists()) {
      print('❌ Error: File not found: $filePath');
      exit(1);
    }

    print('📂 Processing: $filePath');
    print('=' * 60);

    // Read the file
    final bytes = await file.readAsBytes();

    if (bytes.isEmpty) {
      print('❌ Error: Selected file is empty.');
      exit(1);
    }

    // Validate Excel file signature
    if (bytes.length < 4 ||
        bytes[0] != 0x50 ||
        bytes[1] != 0x4B ||
        bytes[2] != 0x03 ||
        bytes[3] != 0x04) {
      print('❌ Error: File does not appear to be a valid .xlsx file.');
      exit(1);
    }

    // Parse Excel
    late final excel;
    try {
      excel = Excel.decodeBytes(bytes);
    } catch (decodeError) {
      print('❌ Excel decoder error: $decodeError');
      exit(1);
    }

    if (excel == null || excel.tables == null) {
      print('❌ Failed to parse Excel file.');
      exit(1);
    }

    if (excel.tables.isEmpty) {
      print('❌ No sheets found in Excel file');
      exit(1);
    }

    List<Student> students = [];

    // Parse all sheets
    for (var tableName in excel.tables.keys) {
      try {
        final sheet = excel.tables[tableName];
        if (sheet == null) continue;
        if (sheet.rows == null || sheet.rows.isEmpty) continue;

        // Start from row 1 to skip the header row (row 0)
        for (int i = 1; i < sheet.rows.length; i++) {
          try {
            var row = sheet.rows[i];
            if (row == null || row.isEmpty) continue;

            // Only process rows that actually have data in columns A and B
            final cellA = row.length > 0 ? row[0] : null;
            final cellB = row.length > 1 ? row[1] : null;

            // Skip completely empty rows
            if (cellA == null && cellB == null) continue;

            final nameVal = cellA?.value;
            final scoreVal = cellB?.value;
            if (nameVal == null && scoreVal == null) continue;

            final name = nameVal?.toString().trim() ?? '';
            final scoreStr = scoreVal?.toString().trim() ?? '';

            // Skip rows where name or score is missing
            if (name.isEmpty || scoreStr.isEmpty) continue;

            final score = double.tryParse(scoreStr);

            // Skip rows where score is not a valid number
            if (score == null) continue;

            final grade = GradeService.calculateGrade(score);
            students.add(Student(name: name, score: score, grade: grade));
          } catch (rowError) {
            // Skip problematic rows
            continue;
          }
        }
      } catch (sheetError) {
        // Skip problematic sheets
        continue;
      }
    }

    if (students.isEmpty) {
      print('❌ No valid student data found. Check your Excel format.');
      print('   Expected columns: Column A (Name), Column B (Score)');
      exit(1);
    }

    // Sort by score descending
    students.sort((a, b) => b.score.compareTo(a.score));

    // Calculate statistics
    double total = students.fold(0, (sum, s) => sum + s.score);
    double average = total / students.length;

    // Display results
    print('\n✅ Successfully loaded ${students.length} students');
    print('=' * 60);
    print('\n📊 CLASS STATISTICS:');
    print('   Total Students: ${students.length}');
    print('   Class Average: ${average.toStringAsFixed(2)}');
    print('   Highest Score: ${students.first.score.toStringAsFixed(1)}');
    print('   Lowest Score: ${students.last.score.toStringAsFixed(1)}');

    // Grade distribution
    final gradeCount = <String, int>{};
    for (final student in students) {
      gradeCount[student.grade] = (gradeCount[student.grade] ?? 0) + 1;
    }

    print('\n📈 GRADE DISTRIBUTION:');
    for (final grade in ['A', 'B', 'C', 'D', 'E', 'F']) {
      final count = gradeCount[grade] ?? 0;
      final percentage = (count / students.length * 100).toStringAsFixed(1);
      print('   Grade $grade: $count students ($percentage%)');
    }

    // Student list
    print('\n👨‍🎓 STUDENT LIST (sorted by score):');
    print('─' * 60);
    print('Rank | Student Name          | Score  | Grade');
    print('─' * 60);

    for (int i = 0; i < students.length; i++) {
      final student = students[i];
      final rank = (i + 1).toString().padRight(4);
      final name = student.name.padRight(21);
      final score = student.score.toStringAsFixed(1).padRight(6);
      print('$rank| $name | $score | ${student.grade}');
    }

    print('─' * 60);

    // Create output Excel file with grades
    print('\n💾 Creating output file with grades...');
    final outputExcel = Excel.createExcel();
    final outputSheet = outputExcel['Students'];

    // Add headers
    outputSheet.appendRow(['Name', 'Score', 'Grade']);

    // Add student data
    for (final student in students) {
      outputSheet.appendRow([student.name, student.score, student.grade]);
    }

    // Generate output filename
    final inputFileName = filePath.split(RegExp(r'[\\/]')).last;
    final outputFileName = inputFileName.replaceAll(
      '.xlsx',
      '_with_grades.xlsx',
    );
    final outputFile = File(outputFileName);

    await outputFile.writeAsBytes(outputExcel.encode()!);
    print('✅ Output file created: $outputFileName');
    print('\n✨ Done!');
  } catch (e) {
    print('❌ Error: $e');
    exit(1);
  }
}
