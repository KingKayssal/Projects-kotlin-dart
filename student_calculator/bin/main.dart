import 'dart:io';
import 'package:excel/excel.dart';
import 'package:path/path.dart' as p;
import 'package:args/args.dart';

// ─────────────────────────────────────────────
//  GRADING CONFIGURATION
//  CA = 30pts | Exam = 70pts | Final = 100pts
// ─────────────────────────────────────────────
const double caMaxScore   = 30.0;
const double examMaxScore = 70.0;

class Student {
  final String id;
  final String name;
  final double caRaw;
  final double examRaw;

  Student({
    required this.id,
    required this.name,
    required this.caRaw,
    required this.examRaw,
  });

  double get finalMark => caRaw + examRaw;

  String get grade {
    final m = finalMark;
    if (m >= 80) return 'A';
    if (m >= 70) return 'B';
    if (m >= 60) return 'C';
    if (m >= 50) return 'D';
    if (m >= 40) return 'E';
    return 'F';
  }

  String get remark {
    switch (grade) {
      case 'A': return 'Excellent';
      case 'B': return 'Very Good';
      case 'C': return 'Good';
      case 'D': return 'Satisfactory';
      case 'E': return 'Pass';
      default:  return 'Fail';
    }
  }
}

// ─────────────────────────────────────────────
//  HELPERS
// ─────────────────────────────────────────────
double? _toDouble(dynamic v) {
  if (v == null) return null;
  if (v is num) return v.toDouble();
  return double.tryParse(v.toString().trim());
}

String _str(dynamic v) => v?.toString().trim() ?? '';

// ─────────────────────────────────────────────
//  READ INPUT EXCEL
// ─────────────────────────────────────────────
List<Student> readStudents(String filePath) {
  final bytes = File(filePath).readAsBytesSync();
  final excel = Excel.decodeBytes(bytes);

  final sheetName = excel.tables.keys.first;
  final sheet = excel.tables[sheetName]!;
  final rows = sheet.rows;

  if (rows.isEmpty) {
    print('  [ERROR] The Excel file is empty.');
    exit(1);
  }

  final header = rows[0].map((c) => _str(c?.value).toLowerCase()).toList();

  int colIdx(List<String> candidates) {
    for (final c in candidates) {
      final idx = header.indexWhere((h) => h.contains(c));
      if (idx != -1) return idx;
    }
    return -1;
  }

  final idCol   = colIdx(['id', 'student id', 'no', 'number']);
  final nameCol = colIdx(['name', 'student name', 'full name']);
  final caCol   = colIdx(['ca', 'continuous', 'coursework']);
  final exCol   = colIdx(['exam', 'examination']);

  if (caCol == -1 || exCol == -1) {
    print('  [ERROR] Could not find CA or Exam columns.');
    print('  Headers found: ${header.join(", ")}');
    exit(1);
  }

  final students = <Student>[];

  for (int i = 1; i < rows.length; i++) {
    final row = rows[i];
    if (row.every((c) => c == null || _str(c.value).isEmpty)) continue;

    final id   = idCol   != -1 ? _str(row[idCol]?.value)   : '$i';
    final name = nameCol != -1 ? _str(row[nameCol]?.value) : 'Student $i';
    final ca   = _toDouble(row[caCol]?.value);
    final ex   = _toDouble(row[exCol]?.value);

    if (ca == null || ex == null) {
      print('  [WARN] Row ${i + 1} skipped — missing mark (id=$id, name=$name)');
      continue;
    }

    students.add(Student(id: id, name: name, caRaw: ca, examRaw: ex));
  }

  return students;
}

// ─────────────────────────────────────────────
//  WRITE OUTPUT EXCEL
// ─────────────────────────────────────────────
void writeResults(List<Student> students, String outputPath) {
  final excel = Excel.createExcel();
  final sheet = excel['Results'];
  excel.setDefaultSheet('Results');

  // Header row
  final headers = [
    'Student ID',
    'Student Name',
    'CA Mark (/30)',
    'Exam Mark (/70)',
    'Final Mark (/100)',
    'Grade',
    'Remark',
  ];

  for (int c = 0; c < headers.length; c++) {
    final cell = sheet.cell(CellIndex.indexByColumnRow(columnIndex: c, rowIndex: 0));
    cell.value = TextCellValue(headers[c]);
    cell.cellStyle = CellStyle(
      bold: true,
      backgroundColorHex: ExcelColor.fromHexString('#1F4E79'),
      fontColorHex: ExcelColor.fromHexString('#FFFFFF'),
      horizontalAlign: HorizontalAlign.Center,
    );
  }

  // Data rows
  for (int r = 0; r < students.length; r++) {
    final s = students[r];
    final rowIdx = r + 1;
    final bgColor = r % 2 == 0
        ? ExcelColor.fromHexString('#D9E1F2')
        : ExcelColor.fromHexString('#FFFFFF');

    final gradeColor = (s.grade == 'A' || s.grade == 'B')
        ? ExcelColor.fromHexString('#375623')
        : (s.grade == 'F')
            ? ExcelColor.fromHexString('#9C0006')
            : ExcelColor.fromHexString('#7F6000');

    final gradeBg = (s.grade == 'A' || s.grade == 'B')
        ? ExcelColor.fromHexString('#C6EFCE')
        : (s.grade == 'F')
            ? ExcelColor.fromHexString('#FFC7CE')
            : ExcelColor.fromHexString('#FFEB9C');

    final values = [s.id, s.name, s.caRaw, s.examRaw, s.finalMark, s.grade, s.remark];

    for (int c = 0; c < values.length; c++) {
      final cell = sheet.cell(CellIndex.indexByColumnRow(columnIndex: c, rowIndex: rowIdx));
      final v = values[c];

      if (v is String) {
        cell.value = TextCellValue(v);
      } else if (v is double) {
        cell.value = DoubleCellValue(double.parse(v.toStringAsFixed(2)));
      }

      if (c == 5 || c == 6) {
        cell.cellStyle = CellStyle(
          backgroundColorHex: gradeBg,
          fontColorHex: gradeColor,
          bold: true,
          horizontalAlign: HorizontalAlign.Center,
        );
      } else {
        cell.cellStyle = CellStyle(
          backgroundColorHex: bgColor,
          horizontalAlign: c < 2 ? HorizontalAlign.Left : HorizontalAlign.Center,
        );
      }
    }
  }

  // Summary row
  if (students.isNotEmpty) {
    final summaryRow = students.length + 2;
    final avgCA    = students.map((s) => s.caRaw).reduce((a, b) => a + b) / students.length;
    final avgExam  = students.map((s) => s.examRaw).reduce((a, b) => a + b) / students.length;
    final avgFinal = students.map((s) => s.finalMark).reduce((a, b) => a + b) / students.length;
    final passCount = students.where((s) => s.grade != 'F').length;

    final summaryData = [
      'CLASS AVERAGE',
      '${students.length} students',
      avgCA.toStringAsFixed(1),
      avgExam.toStringAsFixed(1),
      avgFinal.toStringAsFixed(1),
      '',
      'Pass: $passCount / ${students.length}',
    ];

    for (int c = 0; c < summaryData.length; c++) {
      final cell = sheet.cell(CellIndex.indexByColumnRow(columnIndex: c, rowIndex: summaryRow));
      cell.value = TextCellValue(summaryData[c]);
      cell.cellStyle = CellStyle(
        bold: true,
        backgroundColorHex: ExcelColor.fromHexString('#2E75B6'),
        fontColorHex: ExcelColor.fromHexString('#FFFFFF'),
        horizontalAlign: HorizontalAlign.Center,
      );
    }
  }

  // Column widths
  sheet.setColumnWidth(0, 14);
  sheet.setColumnWidth(1, 24);
  sheet.setColumnWidth(2, 16);
  sheet.setColumnWidth(3, 16);
  sheet.setColumnWidth(4, 18);
  sheet.setColumnWidth(5, 10);
  sheet.setColumnWidth(6, 16);

  excel.delete('Sheet1');

  File(outputPath).writeAsBytesSync(excel.encode()!);
}

// ─────────────────────────────────────────────
//  CONSOLE DISPLAY
// ─────────────────────────────────────────────
void printBanner() {
  print('');
  print('╔══════════════════════════════════════════════╗');
  print('║      STUDENT GRADE CALCULATOR  v1.0         ║');
  print('║   CA (30pts) + Exam (70pts) = Final (100)   ║');
  print('╚══════════════════════════════════════════════╝');
  print('');
}

void printReport(List<Student> students) {
  print('┌──────────────┬────────────────────────┬──────┬──────┬───────┬─────┬──────────────┐');
  print('│ ID           │ Name                   │  CA  │ Exam │ Final │ Grd │ Remark       │');
  print('├──────────────┼────────────────────────┼──────┼──────┼───────┼─────┼──────────────┤');

  for (final s in students) {
    final id   = s.id.padRight(12);
    final name = s.name.padRight(22);
    final ca   = s.caRaw.toStringAsFixed(1).padLeft(4);
    final ex   = s.examRaw.toStringAsFixed(1).padLeft(4);
    final fin  = s.finalMark.toStringAsFixed(1).padLeft(5);
    final grd  = s.grade.padRight(3);
    final rem  = s.remark.padRight(12);
    print('│ $id │ $name │ $ca │ $ex │ $fin │ $grd │ $rem │');
  }

  print('└──────────────┴────────────────────────┴──────┴──────┴───────┴─────┴──────────────┘');

  if (students.isEmpty) return;

  final avgFinal   = students.map((s) => s.finalMark).reduce((a, b) => a + b) / students.length;
  final passCount  = students.where((s) => s.grade != 'F').length;
  final failCount  = students.length - passCount;
  final highest    = students.reduce((a, b) => a.finalMark > b.finalMark ? a : b);
  final lowest     = students.reduce((a, b) => a.finalMark < b.finalMark ? a : b);

  print('');
  print('  CLASS SUMMARY');
  print('  ─────────────────────────────────────');
  print('  Total Students : ${students.length}');
  print('  Class Average  : ${avgFinal.toStringAsFixed(1)}%');
  print('  Passed         : $passCount');
  print('  Failed         : $failCount');
  print('  Highest Mark   : ${highest.finalMark.toStringAsFixed(1)} — ${highest.name}');
  print('  Lowest Mark    : ${lowest.finalMark.toStringAsFixed(1)} — ${lowest.name}');
  print('');
  print('  GRADE DISTRIBUTION:');
  for (final g in ['A', 'B', 'C', 'D', 'E', 'F']) {
    final count = students.where((s) => s.grade == g).length;
    final bar   = ('█' * count).padRight(10);
    print('  $g : $bar ($count)');
  }
  print('');
}

// ─────────────────────────────────────────────
//  MAIN
// ─────────────────────────────────────────────
void main(List<String> args) {
  printBanner();

  final parser = ArgParser()
    ..addOption('input',  abbr: 'i', help: 'Path to input Excel file (.xlsx)')
    ..addOption('output', abbr: 'o', help: 'Path for output Excel file (.xlsx)')
    ..addFlag('help',    abbr: 'h', negatable: false, help: 'Show usage');

  final results = parser.parse(args);

  if (results['help'] as bool) {
    print('USAGE:');
    print('  dart run bin/main.dart -i input.xlsx -o output.xlsx');
    print('');
    print(parser.usage);
    exit(0);
  }

  // Input path
  String inputPath;
  if (results['input'] != null) {
    inputPath = results['input'] as String;
  } else {
    stdout.write('  Enter path to INPUT Excel file: ');
    inputPath = stdin.readLineSync()?.trim() ?? '';
  }

  if (inputPath.isEmpty || !File(inputPath).existsSync()) {
    print('  [ERROR] File not found: "$inputPath"');
    exit(1);
  }

  // Output path
  String outputPath;
  if (results['output'] != null) {
    outputPath = results['output'] as String;
  } else {
    final dir      = p.dirname(inputPath);
    final baseName = p.basenameWithoutExtension(inputPath);
    outputPath     = p.join(dir, '${baseName}_results.xlsx');
    stdout.write('  Enter OUTPUT path [default: $outputPath]: ');
    final userOut  = stdin.readLineSync()?.trim() ?? '';
    if (userOut.isNotEmpty) outputPath = userOut;
  }

  // Process
  print('');
  print('  Reading: $inputPath');
  final students = readStudents(inputPath);
  print('  Loaded ${students.length} student(s).');
  print('');

  printReport(students);

  print('  Saving results to: $outputPath');
  writeResults(students, outputPath);
  print('  Done! Output saved to: $outputPath');
  print('');
}