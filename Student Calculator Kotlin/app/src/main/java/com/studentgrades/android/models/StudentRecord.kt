package com.studentgrades.android.models

import java.io.Serializable

data class StudentRecord(
    val id: String,
    val name: String,
    val caMarks: Double,
    val examMarks: Double,
    val caWeight: Double = 40.0,
    val examWeight: Double = 60.0
) : Serializable {
    
    fun calculateFinalGrade(): Double {
        return (caMarks * caWeight / 100.0) + (examMarks * examWeight / 100.0)
    }
    
    fun getLetterGrade(): String {
        val finalGrade = calculateFinalGrade()
        return when {
            finalGrade >= 90 -> "A"
            finalGrade >= 80 -> "B"
            finalGrade >= 70 -> "C"
            finalGrade >= 60 -> "D"
            else -> "F"
        }
    }
    
    fun isPassing(): Boolean = calculateFinalGrade() >= 60.0
    
    fun getGradeColor(): Int {
        return when (getLetterGrade()) {
            "A" -> 0xFF4CAF50.toInt() // Green
            "B" -> 0xFF8BC34A.toInt() // Light Green
            "C" -> 0xFFFFC107.toInt() // Amber
            "D" -> 0xFFFF9800.toInt() // Orange
            else -> 0xFFF44336.toInt() // Red
        }
    }
}
