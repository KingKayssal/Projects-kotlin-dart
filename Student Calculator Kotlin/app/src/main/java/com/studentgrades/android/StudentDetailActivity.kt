package com.studentgrades.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.studentgrades.android.models.StudentRecord

class StudentDetailActivity : AppCompatActivity() {
    
    private lateinit var tvStudentName: TextView
    private lateinit var tvCaMark: TextView
    private lateinit var tvExamMark: TextView
    private lateinit var tvFinalGrade: TextView
    private lateinit var tvLetterGrade: TextView
    private lateinit var tvStatus: TextView
    private lateinit var cardGrade: MaterialCardView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Student Details"
        
        initializeViews()
        displayStudentInfo()
    }
    
    private fun initializeViews() {
        tvStudentName = findViewById(R.id.tvStudentName)
        tvCaMark = findViewById(R.id.tvCaMark)
        tvExamMark = findViewById(R.id.tvExamMark)
        tvFinalGrade = findViewById(R.id.tvFinalGrade)
        tvLetterGrade = findViewById(R.id.tvLetterGrade)
        tvStatus = findViewById(R.id.tvStatus)
        cardGrade = findViewById(R.id.cardGrade)
    }
    
    private fun displayStudentInfo() {
        val name = intent.getStringExtra("student_name") ?: ""
        val id = intent.getStringExtra("student_id") ?: ""
        val caMark = intent.getFloatExtra("ca_mark", 0f).toDouble()
        val examMark = intent.getFloatExtra("exam_mark", 0f).toDouble()
        val caWeight = intent.getFloatExtra("ca_weight", 40f).toDouble()
        val examWeight = intent.getFloatExtra("exam_weight", 60f).toDouble()
        
        val student = StudentRecord(
            id = id,
            name = name,
            caMarks = caMark,
            examMarks = examMark,
            caWeight = caWeight,
            examWeight = examWeight
        )
        
        tvStudentName.text = student.name
        tvCaMark.text = String.format("%.2f", student.caMarks)
        tvExamMark.text = String.format("%.2f", student.examMarks)
        tvFinalGrade.text = String.format("%.2f%%", student.calculateFinalGrade())
        tvLetterGrade.text = student.getLetterGrade()
        
        val isPassing = student.isPassing()
        tvStatus.text = if (isPassing) "PASS" else "FAIL"
        
        // Set grade color
        val gradeColor = student.getGradeColor()
        cardGrade.setCardBackgroundColor(gradeColor)
        
        // Set status color
        val statusColor = if (isPassing) {
            getColor(R.color.success)
        } else {
            getColor(R.color.error)
        }
        tvStatus.setTextColor(statusColor)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
