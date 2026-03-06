package com.studentgrades.android.utils

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import com.studentgrades.android.models.StudentRecord
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.OutputStream

class ExcelExporter(private val context: Context) {
    
    fun exportToExcel(students: List<StudentRecord>): Boolean {
        try {
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Calculated Grades")
            
            // Create Header Row
            val headerRow = sheet.createRow(0)
            val headers = listOf("ID", "Student Name", "CA Mark (/100)", "Exam Mark (/100)", "Final Grade (%)", "Letter Grade", "Status")
            headers.forEachIndexed { index, title ->
                headerRow.createCell(index).setCellValue(title)
            }
            
            // Fill Data Rows
            students.forEachIndexed { index, student ->
                val row = sheet.createRow(index + 1)
                row.createCell(0).setCellValue(student.id ?: "")
                row.createCell(1).setCellValue(student.name ?: "Unknown")
                row.createCell(2).setCellValue(student.caMarks ?: 0.0)
                row.createCell(3).setCellValue(student.examMarks ?: 0.0)
                row.createCell(4).setCellValue(student.calculateFinalGrade())
                row.createCell(5).setCellValue(student.getLetterGrade())
                row.createCell(6).setCellValue(if (student.isPassing()) "PASS" else "FAIL")
            }
            
            val fileName = "Student_Grades_${System.currentTimeMillis()}.xlsx"
            
            // Modern way to save files to Downloads using MediaStore
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.MediaColumns.RELATIVE_PATH, "Download")
                }
            }
            
            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            
            if (uri != null) {
                val outputStream: OutputStream? = resolver.openOutputStream(uri)
                outputStream?.use {
                    workbook.write(it)
                }
                workbook.close()
                return true
            }
            return false
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}
