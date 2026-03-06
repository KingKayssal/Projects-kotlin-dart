package com.studentgrades.android

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormulaSettingsActivity : AppCompatActivity() {
    
    private lateinit var caWeightSeekBar: SeekBar
    private lateinit var examWeightSeekBar: SeekBar
    private lateinit var caWeightText: TextView
    private lateinit var examWeightText: TextView
    private lateinit var formulaPreview: TextView
    private lateinit var saveButton: Button
    private lateinit var resetButton: Button
    
    private var caWeight = 40
    private var examWeight = 60
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formula_settings)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Formula Settings"
        
        initViews()
        loadCurrentFormula()
        setupListeners()
        updateFormulaPreview()
    }
    
    private fun initViews() {
        caWeightSeekBar = findViewById(R.id.caWeightSeekBar)
        examWeightSeekBar = findViewById(R.id.examWeightSeekBar)
        caWeightText = findViewById(R.id.caWeightText)
        examWeightText = findViewById(R.id.examWeightText)
        formulaPreview = findViewById(R.id.formulaPreview)
        saveButton = findViewById(R.id.saveButton)
        resetButton = findViewById(R.id.resetButton)
    }
    
    private fun loadCurrentFormula() {
        // Use the same SharedPreferences file "StudentGrades"
        val prefs = getSharedPreferences("StudentGrades", MODE_PRIVATE)
        caWeight = prefs.getInt("ca_weight", 40)
        examWeight = prefs.getInt("exam_weight", 60)
        
        caWeightSeekBar.progress = caWeight
        examWeightSeekBar.progress = examWeight
        
        updateWeightTexts()
    }
    
    private fun setupListeners() {
        caWeightSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    caWeight = progress
                    examWeight = 100 - progress
                    examWeightSeekBar.progress = examWeight
                    updateWeightTexts()
                    updateFormulaPreview()
                }
            }
            
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        examWeightSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    examWeight = progress
                    caWeight = 100 - progress
                    caWeightSeekBar.progress = caWeight
                    updateWeightTexts()
                    updateFormulaPreview()
                }
            }
            
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        saveButton.setOnClickListener {
            saveFormula()
        }
        
        resetButton.setOnClickListener {
            resetToDefault()
        }
    }
    
    private fun updateWeightTexts() {
        caWeightText.text = "CA Weight: $caWeight%"
        examWeightText.text = "Exam Weight: $examWeight%"
    }
    
    private fun updateFormulaPreview() {
        val example = (80 * caWeight / 100.0) + (90 * examWeight / 100.0)
        formulaPreview.text = "Final Grade = (CA × $caWeight%) + (Exam × $examWeight%)\n\n" +
                "Example:\n" +
                "If CA = 80 and Exam = 90\n" +
                "Final = (80 × $caWeight%) + (90 × $examWeight%)\n" +
                "Final = ${80 * caWeight / 100.0} + ${90 * examWeight / 100.0}\n" +
                "Final = ${"%.2f".format(example)}%"
    }
    
    private fun saveFormula() {
        val prefs = getSharedPreferences("StudentGrades", MODE_PRIVATE)
        prefs.edit().apply {
            putInt("ca_weight", caWeight)
            putInt("exam_weight", examWeight)
            apply()
        }
        
        Toast.makeText(this, "Formula saved successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
    
    private fun resetToDefault() {
        caWeight = 40
        examWeight = 60
        caWeightSeekBar.progress = caWeight
        examWeightSeekBar.progress = examWeight
        updateWeightTexts()
        updateFormulaPreview()
        Toast.makeText(this, "Reset to default formula", Toast.LENGTH_SHORT).show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
