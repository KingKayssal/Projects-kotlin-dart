package com.studentgrades.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.studentgrades.android.models.StudentRecord

class StudentAdapter(
    private var students: List<StudentRecord>,
    private val onItemClick: (StudentRecord) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: MaterialCardView = itemView.findViewById(R.id.cardStudent)
        val tvName: TextView = itemView.findViewById(R.id.tvStudentName)
        val tvCaMark: TextView = itemView.findViewById(R.id.tvCaMark)
        val tvExamMark: TextView = itemView.findViewById(R.id.tvExamMark)
        val tvFinalGrade: TextView = itemView.findViewById(R.id.tvFinalGrade)
        val tvLetterGrade: TextView = itemView.findViewById(R.id.tvLetterGrade)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        
        holder.tvName.text = student.name
        holder.tvCaMark.text = String.format("CA: %.1f", student.caMarks)
        holder.tvExamMark.text = String.format("Exam: %.1f", student.examMarks)
        holder.tvFinalGrade.text = String.format("%.2f%%", student.calculateFinalGrade())
        holder.tvLetterGrade.text = student.getLetterGrade()
        
        // Update background color instead of text color to match circle background
        val gradeColor = student.getGradeColor()
        holder.tvLetterGrade.background.setTint(gradeColor)
        
        // Set click listener
        holder.cardView.setOnClickListener {
            onItemClick(student)
        }
    }
    
    override fun getItemCount(): Int = students.size
    
    fun updateData(newStudents: List<StudentRecord>) {
        students = newStudents
        notifyDataSetChanged()
    }
}
