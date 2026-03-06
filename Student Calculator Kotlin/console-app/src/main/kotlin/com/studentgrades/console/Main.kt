package com.studentgrades.console

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream

data class StudentRecord(
    val id: String?,
    val name: String?,
    val caMarks: Double?,
    val examMarks: Double?,
    val caWeight: Double = 40.0,
    val examWeight: Double = 60.0
) {
    fun calculateFinalGrade(): Double {
        val ca = caMarks ?: 0.0
        val exam = examMarks ?: 0.0
        return (ca * caWeight / 100.0) + (exam * examWeight / 100.0)
    }

    fun getLetterGrade(): String {
        val finalGrade = calculateFinalGrade()
        return when {
            finalGrade >= 90.0 -> "A"
            finalGrade >= 80.0 -> "B"
            finalGrade >= 70.0 -> "C"
            finalGrade >= 60.0 -> "D"
            else -> "F"
        }
    }

    fun isPassing(): Boolean = calculateFinalGrade() >= 60.0
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please provide the path to the Excel file.")
        return
    }

    val filePath = args[0]
    val file = File(filePath)

    if (!file.exists()) {
        println("File not found: $filePath")
        return
    }

    try {
        FileInputStream(file).use { inputStream ->
            val workbook: Workbook = XSSFWorkbook(inputStream)
            val sheet = workbook.getSheetAt(0)

            // Read Weights from Row 2
            val weightRow = sheet.getRow(1) ?: throw Exception("Invalid format: Missing Weight row (Row 2)")
            val caWeight = getCellValueAsDouble(weightRow.getCell(3)) ?: 40.0
            val examWeight = getCellValueAsDouble(weightRow.getCell(5)) ?: 60.0

            println("\n--- Grade Calculation Results ---")
            println("Using Weights: CA: $caWeight%, Exam: $examWeight%\n")
            println("%-10s %-20s %-10s %-10s %-10s %-10s".format("ID", "Name", "CA", "Exam", "Final", "Grade"))
            println("-".repeat(75))

            var totalGrade = 0.0
            var studentCount = 0
            var passingCount = 0

            for (i in 3..sheet.lastRowNum) {
                val row = sheet.getRow(i) ?: continue
                val name = getCellValueAsString(row.getCell(1))
                if (name.isBlank() || name.uppercase().contains("CLASS SUMMARY")) break

                val id = getCellValueAsString(row.getCell(0))
                val caMark = getCellValueAsDouble(row.getCell(2)) ?: 0.0
                val examMark = getCellValueAsDouble(row.getCell(3)) ?: 0.0

                val student = StudentRecord(id, name, caMark, examMark, caWeight, examWeight)
                val finalGrade = student.calculateFinalGrade()
                val gradeLetter = student.getLetterGrade()

                println("%-10s %-20s %-10.1f %-10.1f %-10.1f %-10s".format(id, name, caMark, examMark, finalGrade, gradeLetter))

                totalGrade += finalGrade
                studentCount++
                if (student.isPassing()) passingCount++
            }

            if (studentCount > 0) {
                println("-".repeat(75))
                println("Total Students: $studentCount")
                println("Class Average:  %.2f%%".format(totalGrade / studentCount))
                println("Pass Rate:      %.1f%%".format((passingCount.toDouble() / studentCount) * 100))
            } else {
                println("No student records found.")
            }

            workbook.close()
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}

fun getCellValueAsString(cell: Cell?): String {
    return when (cell?.cellType) {
        CellType.STRING -> cell.stringCellValue.trim()
        CellType.NUMERIC -> cell.numericCellValue.toInt().toString()
        else -> ""
    }
}

fun getCellValueAsDouble(cell: Cell?): Double? {
    return when (cell?.cellType) {
        CellType.NUMERIC -> cell.numericCellValue
        CellType.STRING -> cell.stringCellValue.trim().toDoubleOrNull()
        else -> null
    }
}
