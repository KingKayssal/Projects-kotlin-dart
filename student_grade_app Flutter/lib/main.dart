import 'dart:io';
import 'dart:typed_data';
import 'package:flutter/material.dart';
import 'package:file_picker/file_picker.dart';
import 'package:excel/excel.dart' hide Border;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: ExcelGradeApp(),
    );
  }
}

/* =========================
   STUDENT MODEL
========================= */
class Student {
  final String name;
  final double score;
  final String grade;

  Student({required this.name, required this.score, required this.grade});
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

  static Color getGradeColor(String grade) {
    switch (grade) {
      case "A":
        return Colors.green;
      case "B":
        return Colors.blue;
      case "C":
        return Colors.orange;
      case "D":
        return Colors.deepOrange;
      case "E":
      case "F":
        return Colors.red;
      default:
        return Colors.grey;
    }
  }
}

/* =========================
   EXCEL APP
========================= */
class ExcelGradeApp extends StatefulWidget {
  const ExcelGradeApp({super.key});

  @override
  State<ExcelGradeApp> createState() => _ExcelGradeAppState();
}

class _ExcelGradeAppState extends State<ExcelGradeApp> {
  List<Student> students = [];
  bool isLoading = false;
  String? errorMessage;
  double classAverage = 0;

  /* =========================
     PARSE EXCEL FILE
  ========================= */
  Future<void> pickExcelFile() async {
    setState(() {
      isLoading = true;
      errorMessage = null;
    });

    try {
      FilePickerResult? result = await FilePicker.platform.pickFiles(
        type: FileType.custom,
        allowedExtensions: ['xlsx'],
      );

      if (result == null) {
        setState(() => isLoading = false);
        return;
      }

      late final Uint8List bytes;
      final pickedBytes = result.files.single.bytes;
      if (pickedBytes != null) {
        bytes = pickedBytes;
      } else {
        final path = result.files.single.path;
        if (path == null) throw Exception("Could not access file");
        bytes = await File(path).readAsBytes();
      }

      if (bytes.isEmpty) throw Exception('Selected file is empty.');

      if (bytes.length < 4 ||
          bytes[0] != 0x50 ||
          bytes[1] != 0x4B ||
          bytes[2] != 0x03 ||
          bytes[3] != 0x04) {
        throw Exception('File does not appear to be a valid .xlsx file.');
      }

      var excel;
      try {
        excel = Excel.decodeBytes(bytes);
      } catch (decodeError) {
        throw Exception('Excel decoder error: $decodeError');
      }

      if (excel == null || excel.tables == null) {
        throw Exception('Failed to parse Excel file.');
      }

      List<Student> parsedStudents = [];

      if (excel.tables.isEmpty)
        throw Exception('No sheets found in Excel file');

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
              parsedStudents.add(
                Student(name: name, score: score, grade: grade),
              );
            } catch (rowError) {
              debugPrint('Skipping row $i due to: $rowError');
              continue;
            }
          }
        } catch (sheetError) {
          debugPrint('Skipping sheet $tableName due to: $sheetError');
          continue;
        }
      }

      if (parsedStudents.isEmpty) {
        throw Exception(
          'No valid student data found. Check your Excel format.',
        );
      }

      parsedStudents.sort((a, b) => b.score.compareTo(a.score));

      double total = parsedStudents.fold(0, (sum, s) => sum + s.score);
      double average = total / parsedStudents.length;

      setState(() {
        students = parsedStudents;
        classAverage = average;
        isLoading = false;
      });
    } catch (e, st) {
      debugPrint('Excel parse error: $e\n$st');
      setState(() {
        errorMessage = e.toString();
        isLoading = false;
      });
    }
  }

  void clearData() {
    setState(() {
      students.clear();
      classAverage = 0;
      errorMessage = null;
    });
  }

  /* =========================
     UI
  ========================= */
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Student Grade App",
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        elevation: 0,
        backgroundColor: Colors.blueAccent,
      ),
      body: Column(
        children: [
          // Action Buttons
          Container(
            padding: const EdgeInsets.all(16),
            color: Colors.blueAccent.withOpacity(0.1),
            child: Row(
              children: [
                Expanded(
                  child: ElevatedButton.icon(
                    onPressed: pickExcelFile,
                    icon: const Icon(Icons.upload_file),
                    label: const Text("Upload Excel"),
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.blueAccent,
                      padding: const EdgeInsets.symmetric(vertical: 12),
                    ),
                  ),
                ),
                const SizedBox(width: 12),
                Expanded(
                  child: ElevatedButton.icon(
                    onPressed: clearData,
                    icon: const Icon(Icons.delete_outline),
                    label: const Text("Clear"),
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.redAccent,
                      padding: const EdgeInsets.symmetric(vertical: 12),
                    ),
                  ),
                ),
              ],
            ),
          ),

          // Status & Stats
          if (isLoading)
            const Padding(
              padding: EdgeInsets.all(20),
              child: CircularProgressIndicator(),
            )
          else if (errorMessage != null)
            Padding(
              padding: const EdgeInsets.all(16),
              child: Container(
                padding: const EdgeInsets.all(12),
                decoration: BoxDecoration(
                  color: Colors.red.shade50,
                  border: Border.all(color: Colors.red),
                  borderRadius: BorderRadius.circular(8),
                ),
                child: Row(
                  children: [
                    const Icon(Icons.error, color: Colors.red),
                    const SizedBox(width: 10),
                    Expanded(
                      child: Text(
                        errorMessage!,
                        style: const TextStyle(color: Colors.red),
                      ),
                    ),
                  ],
                ),
              ),
            )
          else if (students.isNotEmpty)
            Container(
              margin: const EdgeInsets.all(16),
              padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
              decoration: BoxDecoration(
                color: Colors.blue.shade50,
                borderRadius: BorderRadius.circular(12),
                border: Border.all(color: Colors.blueAccent, width: 2),
              ),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Column(
                    children: [
                      const Text(
                        "Total Students",
                        style: TextStyle(
                          fontSize: 12,
                          color: Colors.grey,
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        "${students.length}",
                        style: const TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                          color: Colors.blueAccent,
                        ),
                      ),
                    ],
                  ),
                  Container(height: 40, width: 1, color: Colors.blueAccent),
                  Column(
                    children: [
                      const Text(
                        "Class Average",
                        style: TextStyle(
                          fontSize: 12,
                          color: Colors.grey,
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        classAverage.toStringAsFixed(2),
                        style: TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                          color: Colors.amber.shade700,
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),

          // Student List
          Expanded(
            child: students.isEmpty
                ? Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Icon(
                          Icons.upload_file,
                          size: 64,
                          color: Colors.grey.shade300,
                        ),
                        const SizedBox(height: 16),
                        Text(
                          "Upload an Excel file to get started",
                          style: TextStyle(
                            fontSize: 16,
                            color: Colors.grey.shade600,
                          ),
                        ),
                      ],
                    ),
                  )
                : ListView.builder(
                    padding: const EdgeInsets.all(12),
                    itemCount: students.length,
                    itemBuilder: (context, index) {
                      final student = students[index];
                      final gradeColor = GradeService.getGradeColor(
                        student.grade,
                      );

                      return Card(
                        elevation: 2,
                        margin: const EdgeInsets.symmetric(
                          vertical: 8,
                          horizontal: 4,
                        ),
                        child: ListTile(
                          contentPadding: const EdgeInsets.symmetric(
                            horizontal: 16,
                            vertical: 12,
                          ),
                          leading: CircleAvatar(
                            backgroundColor: gradeColor,
                            child: Text(
                              (index + 1).toString(),
                              style: const TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                          title: Text(
                            student.name,
                            style: const TextStyle(fontWeight: FontWeight.w600),
                          ),
                          subtitle: Text(
                            "Score: ${student.score.toStringAsFixed(1)}",
                            style: TextStyle(color: Colors.grey.shade600),
                          ),
                          trailing: Container(
                            decoration: BoxDecoration(
                              color: gradeColor,
                              shape: BoxShape.circle,
                            ),
                            width: 50,
                            height: 50,
                            child: Center(
                              child: Text(
                                student.grade,
                                style: const TextStyle(
                                  fontSize: 18,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.white,
                                ),
                              ),
                            ),
                          ),
                        ),
                      );
                    },
                  ),
          ),
        ],
      ),
    );
  }
}
