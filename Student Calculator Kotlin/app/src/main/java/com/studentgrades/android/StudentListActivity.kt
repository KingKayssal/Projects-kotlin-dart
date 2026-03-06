package com.studentgrades.android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.studentgrades.android.models.StudentRecord
import com.studentgrades.android.utils.ExcelExporter

class StudentListActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var searchView: SearchView
    private lateinit var fabExport: FloatingActionButton
    
    private var allStudents = listOf<StudentRecord>()
    private var filteredStudents = listOf<StudentRecord>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Student List"
        
        initializeViews()
        loadStudentData()
        setupRecyclerView()
        setupSearch()
        setupExport()
    }
    
    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerViewStudents)
        searchView = findViewById(R.id.searchView)
        fabExport = findViewById(R.id.fabExport)
    }
    
    private fun loadStudentData() {
        val prefs = getSharedPreferences("StudentGrades", MODE_PRIVATE)
        val studentCount = prefs.getInt("student_count", 0)
        val caWeight = prefs.getInt("ca_weight", 40).toDouble()
        val examWeight = prefs.getInt("exam_weight", 60).toDouble()
        
        val students = mutableListOf<StudentRecord>()
        for (i in 0 until studentCount) {
            val name = prefs.getString("student_${i}_name", "") ?: ""
            val id = prefs.getString("student_${i}_id", "") ?: ""
            val caMark = prefs.getFloat("student_${i}_ca", 0f).toDouble()
            val examMark = prefs.getFloat("student_${i}_exam", 0f).toDouble()
            
            if (name.isNotEmpty()) {
                students.add(StudentRecord(id, name, caMark, examMark, caWeight, examWeight))
            }
        }
        allStudents = students.sortedBy { it.name }
        filteredStudents = allStudents
    }
    
    private fun setupRecyclerView() {
        adapter = StudentAdapter(filteredStudents) { student ->
            navigateToStudentDetail(student)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    
    private fun setupSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                filterStudents(newText ?: "")
                return true
            }
        })
    }
    
    private fun filterStudents(query: String) {
        filteredStudents = if (query.isEmpty()) {
            allStudents
        } else {
            allStudents.filter { it.name.contains(query, ignoreCase = true) || it.id.contains(query, ignoreCase = true) }
        }
        adapter.updateData(filteredStudents)
    }
    
    private fun setupExport() {
        fabExport.setOnClickListener {
            if (allStudents.isEmpty()) {
                Toast.makeText(this, "No data to export", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val exporter = ExcelExporter(this)
            val success = exporter.exportToExcel(allStudents)
            if (success) {
                Toast.makeText(this, "Exported to Downloads folder", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed to export data", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun navigateToStudentDetail(student: StudentRecord) {
        val intent = Intent(this, StudentDetailActivity::class.java).apply {
            putExtra("student_name", student.name)
            putExtra("student_id", student.id)
            putExtra("ca_mark", student.caMarks.toFloat())
            putExtra("exam_mark", student.examMarks.toFloat())
            putExtra("ca_weight", student.caWeight.toFloat())
            putExtra("exam_weight", student.examWeight.toFloat())
        }
        startActivity(intent)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
