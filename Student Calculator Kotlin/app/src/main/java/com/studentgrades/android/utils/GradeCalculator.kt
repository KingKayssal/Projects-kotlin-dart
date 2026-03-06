package com.studentgrades.android.utils

import com.studentgrades.android.models.StudentRecord

class GradeCalculator(private val students: List<StudentRecord>) {
    
    /**
     * Live Coding: Filtering and Transforming with Lambdas
     * 
     * This method demonstrates the filtering and transformation operations
     * requested in the exercise, adapted to the GradeCalculator context.
     */
    fun demonstrateFilteringAndTransforming() {
        // Given a list of numbers
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        println("--- Live Coding: Filtering and Transforming ---")
        numbers.filter { it > 5 }        // 1. Filter out numbers <= 5
               .map { it * it }          // 2. Square each remaining number
               .forEach { println(it) }  // 3. Print each squared number
    }

    fun calculateClassAverage(): Double {
        if (students.isEmpty()) return 0.0
        return students.map { it.calculateFinalGrade() }.average()
    }
    
    fun calculateCAAverage(): Double {
        if (students.isEmpty()) return 0.0
        return students.map { it.caMarks ?: 0.0 }.average()
    }
    
    fun calculateExamAverage(): Double {
        if (students.isEmpty()) return 0.0
        return students.map { it.examMarks ?: 0.0 }.average()
    }
    
    fun getStudentsRanked(): List<StudentRecord> {
        return students.sortedByDescending { it.calculateFinalGrade() }
    }
    
    fun getTopStudents(count: Int = 5): List<StudentRecord> {
        return getStudentsRanked().take(count)
    }
    
    fun getFailingStudents(): List<StudentRecord> {
        return students.filter { !it.isPassing() }
    }
    
    fun getPassingStudents(): List<StudentRecord> {
        return students.filter { it.isPassing() }
    }
    
    fun getGradeDistribution(): Map<String, Int> {
        val distribution = mutableMapOf(
            "A" to 0, "B" to 0, "C" to 0, "D" to 0, "F" to 0
        )
        
        students.forEach { student ->
            val letterGrade = student.getLetterGrade()
            distribution[letterGrade] = distribution.getOrDefault(letterGrade, 0) + 1
        }
        
        return distribution
    }
    
    fun getHighestGrade(): Double? {
        return students.maxOfOrNull { it.calculateFinalGrade() }
    }
    
    fun getLowestGrade(): Double? {
        return students.minOfOrNull { it.calculateFinalGrade() }
    }
    
    fun getPassRate(): Double {
        if (students.isEmpty()) return 0.0
        val passingCount = getPassingStudents().size
        return (passingCount.toDouble() / students.size) * 100
    }
}
