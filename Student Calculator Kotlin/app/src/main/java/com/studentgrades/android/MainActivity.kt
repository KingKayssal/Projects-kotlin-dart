package com.studentgrades.android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.studentgrades.android.utils.ExcelReader
import com.studentgrades.android.models.StudentRecord

class MainActivity : AppCompatActivity() {
    
    private lateinit var btnImportExcel: Button
    private lateinit var btnViewStudents: Button
    private lateinit var btnViewStatistics: Button
    private lateinit var btnFormulaSettings: Button
    private lateinit var tvSubtitle: TextView
    
    private var studentRecords = listOf<StudentRecord>()
    
    private val excelPickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { handleExcelFile(it) }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initializeViews()
        setupClickListeners()
        loadSavedData()
    }
    
    private fun initializeViews() {
        btnImportExcel = findViewById(R.id.btnImportExcel)
        btnViewStudents = findViewById(R.id.btnViewStudents)
        btnViewStatistics = findViewById(R.id.btnViewStatistics)
        btnFormulaSettings = findViewById(R.id.btnFormulaSettings)
        tvSubtitle = findViewById(R.id.tvSubtitle)
    }
    
    private fun setupClickListeners() {
        btnImportExcel.setOnClickListener {
            openExcelPicker()
        }
        
        btnViewStudents.setOnClickListener {
            if (studentRecords.isEmpty()) {
                Toast.makeText(this, getString(R.string.import_file_first), Toast.LENGTH_SHORT).show()
            } else {
                navigateToStudentList()
            }
        }
        
        btnViewStatistics.setOnClickListener {
            if (studentRecords.isEmpty()) {
                Toast.makeText(this, getString(R.string.import_file_first), Toast.LENGTH_SHORT).show()
            } else {
                navigateToStatistics()
            }
        }
        
        btnFormulaSettings.setOnClickListener {
            navigateToFormulaSettings()
        }
    }
    
    private fun openExcelPicker() {
        excelPickerLauncher.launch("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    }
    
    private fun handleExcelFile(uri: Uri) {
        try {
            val excelReader = ExcelReader(this)
            val result = excelReader.readExcelFile(uri)
            
            studentRecords = result.students
            saveStudentData(result)
            
            Toast.makeText(
                this,
                "Successfully imported ${studentRecords.size} students",
                Toast.LENGTH_LONG
            ).show()
            
            updateNavigationButtons()
            
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error reading Excel file: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    
    private fun loadSavedData() {
        val prefs = getSharedPreferences("StudentGrades", MODE_PRIVATE)
        val savedCount = prefs.getInt("student_count", 0)
        
        if (savedCount > 0) {
            val students = mutableListOf<StudentRecord>()
            val caWeight = prefs.getInt("ca_weight", 40).toDouble()
            val examWeight = prefs.getInt("exam_weight", 60).toDouble()
            
            for (i in 0 until savedCount) {
                val name = prefs.getString("student_${i}_name", "") ?: ""
                val id = prefs.getString("student_${i}_id", "ID_$i") ?: "ID_$i"
                val caMark = prefs.getFloat("student_${i}_ca", 0f).toDouble()
                val examMark = prefs.getFloat("student_${i}_exam", 0f).toDouble()
                
                if (name.isNotEmpty()) {
                    students.add(
                        StudentRecord(
                            id = id,
                            name = name,
                            caMarks = caMark,
                            examMarks = examMark,
                            caWeight = caWeight,
                            examWeight = examWeight
                        )
                    )
                }
            }
            
            if (students.isNotEmpty()) {
                studentRecords = students
                updateNavigationButtons()
            }
        }
    }
    
    private fun saveStudentData(result: ExcelReader.ExcelData) {
        val prefs = getSharedPreferences("StudentGrades", MODE_PRIVATE)
        val editor = prefs.edit()
        
        editor.putInt("student_count", result.students.size)
        // Keep weights as Int for simplicity in storage, but read as Double
        editor.putInt("ca_weight", result.caWeight)
        editor.putInt("exam_weight", result.examWeight)
        
        result.students.forEachIndexed { index, student ->
            editor.putString("student_${index}_id", student.id)
            editor.putString("student_${index}_name", student.name)
            editor.putFloat("student_${index}_ca", student.caMarks.toFloat())
            editor.putFloat("student_${index}_exam", student.examMarks.toFloat())
        }
        
        editor.apply()
    }
    
    private fun updateNavigationButtons() {
        val hasData = studentRecords.isNotEmpty()
        btnViewStudents.isEnabled = hasData
        btnViewStatistics.isEnabled = hasData
        
        if (hasData) {
            tvSubtitle.text = "Loaded ${studentRecords.size} students"
        }
    }
    
    private fun navigateToStudentList() {
        val intent = Intent(this, StudentListActivity::class.java)
        startActivity(intent)
    }
    
    private fun navigateToStatistics() {
        val intent = Intent(this, StatisticsActivity::class.java)
        startActivity(intent)
    }
    
    private fun navigateToFormulaSettings() {
        val intent = Intent(this, FormulaSettingsActivity::class.java)
        startActivity(intent)
    }
}
