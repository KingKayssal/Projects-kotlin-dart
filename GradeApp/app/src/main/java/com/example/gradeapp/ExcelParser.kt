package com.example.gradeapp

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExcelParser(private val context: Context) {

    suspend fun parse(uri: Uri): List<Student> = withContext(Dispatchers.IO) {
        val students = mutableListOf<Pair<String, Int>>()
        try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                val workbook = XSSFWorkbook(inputStream)
                val sheet = workbook.getSheetAt(0)
                for (row in sheet) {
                    if (row.rowNum == 0) continue // Skip header

                    val nameCell = row.getCell(0)
                    val scoreCell = row.getCell(1)

                    if (nameCell != null && scoreCell != null) {
                        val name = when (nameCell.cellType) {
                            CellType.STRING -> nameCell.stringCellValue
                            else -> ""
                        }
                        val score = when (scoreCell.cellType) {
                            CellType.NUMERIC -> scoreCell.numericCellValue.toInt()
                            else -> -1
                        }

                        if (name.isNotBlank() && score != -1) {
                            students.add(name to score)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle exceptions appropriately
        }
        students.sortWith(compareByDescending { it.second })
        students.mapIndexed { index, (name, score) ->
            Student(
                rank = index + 1,
                name = name,
                score = score,
                grade = calculateGrade(score)
            )
        }
    }

    private fun calculateGrade(score: Int): String {
        return when {
            score >= 90 -> "A"
            score >= 80 -> "B"
            score >= 70 -> "C"
            score >= 60 -> "D"
            score >= 50 -> "E"
            else -> "F"
        }
    }
}