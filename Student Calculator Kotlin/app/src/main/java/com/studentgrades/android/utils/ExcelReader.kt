package com.studentgrades.android.utils

import android.content.Context
import android.net.Uri
import com.studentgrades.android.models.StudentRecord
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream

class ExcelReader(private val context: Context) {
    
    data class ExcelData(
        val students: List<StudentRecord>,
        val caWeight: Int,
        val examWeight: Int
    )

    fun readExcelFile(uri: Uri): ExcelData {
        val students = mutableListOf<StudentRecord>()
        var caWeight = 40
        var examWeight = 60
        
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val workbook: Workbook = XSSFWorkbook(inputStream)
            val sheet = workbook.getSheetAt(0)
            
            // 1. Validate Structure - Check Row 2 for Weights
            val weightRow = sheet.getRow(1) ?: throw Exception("Invalid format: Missing Weight row (Row 2)")
            val caWeightLabel = getCellValueAsString(weightRow.getCell(2)).uppercase()
            val examWeightLabel = getCellValueAsString(weightRow.getCell(4)).uppercase()
            
            if (!caWeightLabel.contains("CA_WEIGHT") || !examWeightLabel.contains("EXAM_WEIGHT")) {
                throw Exception("Invalid format: Row 2 must contain 'CA_WEIGHT' and 'EXAM_WEIGHT'")
            }
            
            caWeight = getCellValueAsDouble(weightRow.getCell(3))?.toInt() ?: 40
            examWeight = getCellValueAsDouble(weightRow.getCell(5))?.toInt() ?: 60
            
            if (caWeight + examWeight != 100) {
                throw Exception("Invalid weights: CA ($caWeight) + Exam ($examWeight) must equal 100")
            }

            // 2. Validate Structure - Check Row 3 for Headers
            val headerRow = sheet.getRow(2) ?: throw Exception("Invalid format: Missing Header row (Row 3)")
            val nameHeader = getCellValueAsString(headerRow.getCell(1)).uppercase()
            val caHeader = getCellValueAsString(headerRow.getCell(2)).uppercase()
            val examHeader = getCellValueAsString(headerRow.getCell(3)).uppercase()
            
            if (!nameHeader.contains("STUDENT NAME") || !caHeader.contains("CA MARK") || !examHeader.contains("EXAM MARK")) {
                throw Exception("Invalid format: Row 3 headers must be 'STUDENT NAME', 'CA MARK', and 'EXAM MARK'")
            }

            // 3. Extract Student Data starting from Row 4
            for (i in 3..sheet.lastRowNum) {
                val row = sheet.getRow(i) ?: continue
                if (isRowEmpty(row)) continue
                
                // Stop if we hit the "CLASS SUMMARY" section
                val firstCellText = getCellValueAsString(row.getCell(1)).uppercase()
                if (firstCellText.contains("CLASS SUMMARY")) break
                
                try {
                    val id = getCellValueAsString(row.getCell(0))
                    val name = getCellValueAsString(row.getCell(1))
                    val caMark = getCellValueAsDouble(row.getCell(2)) ?: 0.0
                    val examMark = getCellValueAsDouble(row.getCell(3)) ?: 0.0
                    
                    if (name.isNotBlank()) {
                        students.add(StudentRecord(id, name, caMark, examMark, caWeight.toDouble(), examWeight.toDouble()))
                    }
                } catch (e: Exception) {
                    // Skip invalid data rows
                }
            }
            
            workbook.close()
        }
        
        if (students.isEmpty()) throw Exception("No student records found in the expected format.")
        
        return ExcelData(students, caWeight, examWeight)
    }
    
    private fun isRowEmpty(row: Row): Boolean {
        for (c in 0 until row.lastCellNum) {
            val cell = row.getCell(c)
            if (cell != null && cell.cellType != CellType.BLANK) {
                if (getCellValueAsString(cell).isNotBlank()) return false
            }
        }
        return true
    }
    
    private fun getCellValueAsString(cell: Cell?): String {
        return when (cell?.cellType) {
            CellType.STRING -> cell.stringCellValue.trim()
            CellType.NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) cell.dateCellValue.toString()
                else cell.numericCellValue.toInt().toString()
            }
            CellType.BOOLEAN -> cell.booleanCellValue.toString()
            CellType.FORMULA -> {
                try {
                    cell.numericCellValue.toString()
                } catch (e: Exception) {
                    cell.stringCellValue.trim()
                }
            }
            else -> ""
        }
    }
    
    private fun getCellValueAsDouble(cell: Cell?): Double? {
        return when (cell?.cellType) {
            CellType.NUMERIC -> cell.numericCellValue
            CellType.STRING -> cell.stringCellValue.trim().toDoubleOrNull()
            CellType.FORMULA -> {
                try {
                    cell.numericCellValue
                } catch (e: Exception) {
                    cell.stringCellValue.trim().toDoubleOrNull()
                }
            }
            else -> null
        }
    }
}
