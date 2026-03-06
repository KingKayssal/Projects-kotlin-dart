package com.studentgrades.android

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.studentgrades.android.utils.GradeCalculator
import com.studentgrades.android.models.StudentRecord

class StatisticsActivity : AppCompatActivity() {
    
    private lateinit var tvTotalStudents: TextView
    private lateinit var tvClassAverage: TextView
    private lateinit var tvHighestGrade: TextView
    private lateinit var tvLowestGrade: TextView
    private lateinit var tvPassRate: TextView
    private lateinit var pieChart: PieChart
    private lateinit var barChart: BarChart
    
    private var students = listOf<StudentRecord>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Class Statistics"
        
        initializeViews()
        loadStudentData()
        displayStatistics()
        setupCharts()
    }
    
    private fun initializeViews() {
        tvTotalStudents = findViewById(R.id.tvTotalStudents)
        tvClassAverage = findViewById(R.id.tvClassAverage)
        tvHighestGrade = findViewById(R.id.tvHighestGrade)
        tvLowestGrade = findViewById(R.id.tvLowestGrade)
        tvPassRate = findViewById(R.id.tvPassRate)
        pieChart = findViewById(R.id.pieChart)
        barChart = findViewById(R.id.barChart)
    }
    
    private fun loadStudentData() {
        val prefs = getSharedPreferences("StudentGrades", MODE_PRIVATE)
        val studentCount = prefs.getInt("student_count", 0)
        val caWeight = prefs.getInt("ca_weight", 40).toDouble()
        val examWeight = prefs.getInt("exam_weight", 60).toDouble()
        
        val studentList = mutableListOf<StudentRecord>()
        
        for (i in 0 until studentCount) {
            val name = prefs.getString("student_${i}_name", "") ?: ""
            val id = prefs.getString("student_${i}_id", "") ?: ""
            val caMark = prefs.getFloat("student_${i}_ca", 0f).toDouble()
            val examMark = prefs.getFloat("student_${i}_exam", 0f).toDouble()
            
            if (name.isNotEmpty()) {
                studentList.add(StudentRecord(id, name, caMark, examMark, caWeight, examWeight))
            }
        }
        students = studentList
    }
    
    private fun displayStatistics() {
        if (students.isEmpty()) return
        
        val calculator = GradeCalculator(students)
        val passingCount = students.count { it.isPassing() }
        val failingCount = students.size - passingCount
        
        tvTotalStudents.text = students.size.toString()
        tvClassAverage.text = String.format("%.2f%%", calculator.calculateClassAverage())
        tvHighestGrade.text = String.format("%.2f", calculator.getHighestGrade() ?: 0.0)
        tvLowestGrade.text = String.format("%.2f", calculator.getLowestGrade() ?: 0.0)
        
        val passRate = (passingCount.toDouble() / students.size) * 100
        tvPassRate.text = String.format("%.0f%%", passRate)
    }
    
    private fun setupCharts() {
        if (students.isEmpty()) return
        
        val calculator = GradeCalculator(students)
        val distribution = calculator.getGradeDistribution()
        
        // Pie Chart
        val pieEntries = mutableListOf<PieEntry>()
        distribution.forEach { (grade, count) ->
            if (count > 0) pieEntries.add(PieEntry(count.toFloat(), grade))
        }
        
        val pieDataSet = PieDataSet(pieEntries, "")
        pieDataSet.colors = listOf(
            Color.parseColor("#4CAF50"), // A
            Color.parseColor("#8BC34A"), // B
            Color.parseColor("#FFC107"), // C
            Color.parseColor("#FF9800"), // D
            Color.parseColor("#F44336")  // F
        )
        pieDataSet.valueTextSize = 16f
        pieDataSet.valueTextColor = Color.WHITE
        
        pieChart.data = PieData(pieDataSet)
        pieChart.description.isEnabled = false
        pieChart.animateY(1000)
        pieChart.invalidate()
        
        // Bar Chart
        val barEntries = mutableListOf<BarEntry>()
        val grades = listOf("A", "B", "C", "D", "F")
        grades.forEachIndexed { index, grade ->
            barEntries.add(BarEntry(index.toFloat(), (distribution[grade] ?: 0).toFloat()))
        }
        
        val barDataSet = BarDataSet(barEntries, "Students per Grade")
        barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        barChart.data = BarData(barDataSet)
        barChart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = grades.getOrNull(value.toInt()) ?: ""
        }
        barChart.description.isEnabled = false
        barChart.animateY(1000)
        barChart.invalidate()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
