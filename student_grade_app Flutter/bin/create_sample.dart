import 'dart:io';
import 'package:excel/excel.dart';

void main() async {
  final excel = Excel.createExcel();
  final sheet = excel['Sheet1'];

  // Add headers
  sheet.appendRow(['Name', 'Score']);

  // Add sample student data
  final students = [
    ['Alice Johnson', 95],
    ['Bob Smith', 87],
    ['Charlie Brown', 92],
    ['Diana Prince', 78],
    ['Ethan Hunt', 85],
    ['Fiona Green', 91],
    ['George Wilson', 76],
    ['Hannah Montana', 88],
    ['Isaac Newton', 99],
    ['Julia Roberts', 83],
    ['Kevin Hart', 79],
    ['Laura Palmer', 94],
    ['Michael Scott', 72],
    ['Nina Simone', 96],
    ['Oliver Queen', 81],
  ];

  for (final student in students) {
    sheet.appendRow(student);
  }

  // Save file
  final file = File('sample_grades.xlsx');
  await file.writeAsBytes(excel.encode()!);

  print('✅ Excel file created successfully: sample_grades.xlsx');
  print('Format:');
  print('  Column A: Name (text)');
  print('  Column B: Score (numbers)');
}
