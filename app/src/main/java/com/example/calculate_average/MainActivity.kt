package com.example.calculate_average

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_lesson_layout.view.*

class MainActivity : AppCompatActivity() {

    private val LESSON = arrayOf("Math", "Turkish", "Literature", "Algorithm", "History")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, LESSON)

        et_Lesson_Name.setAdapter(adapter)

        if (rootLayout.childCount == 0) {
            btn_Calculate_Avarage.visibility = View.INVISIBLE
        } else btn_Calculate_Avarage.visibility = View.VISIBLE

        btn_New_Lesson.setOnClickListener {

            if (!et_Lesson_Name.text.isNullOrEmpty()) {
                var inflater = LayoutInflater.from(this)

                var newLessonView = inflater.inflate(R.layout.new_lesson_layout, null)

                newLessonView.et_New_Lesson_Name.setAdapter(adapter)


                //statik alandan kullanıcını girdiği değerleri alalım
                var lessonName = et_Lesson_Name.text.toString()
                var lessonCredit = spn_Credit.selectedItem.toString()
                var lessonGrade = spn_Grade.selectedItem.toString()

                newLessonView.et_New_Lesson_Name.setText(lessonName)

                newLessonView.spn_New_Credit.setSelection(
                    findingSpinnerValueIndex(
                        spn_Credit,
                        lessonCredit
                    )
                )

                newLessonView.spn_New_Grade.setSelection(
                    findingSpinnerValueIndex(
                        spn_Grade,
                        lessonGrade
                    )
                )
                newLessonView.btn_Delete_Lesson.setOnClickListener {
                    rootLayout.removeView(newLessonView)

                    if (rootLayout.childCount == 0) {
                        btn_Calculate_Avarage.visibility = View.INVISIBLE
                    } else btn_Calculate_Avarage.visibility = View.VISIBLE
                }

                rootLayout.addView(newLessonView)
                reset()

                btn_Calculate_Avarage.visibility = View.VISIBLE
            } else {

                Toast.makeText(this, "Please Enter Lesson Name", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun reset() {
        et_Lesson_Name.setText("")
        spn_Credit.setSelection(0)
        spn_Grade.setSelection(0)
    }

    fun calculate_average(view: android.view.View) {}

    fun findingSpinnerValueIndex(spinner: Spinner, valueToLookFor: String): Int {

        var index = 0
        for (i in 0..spinner.count)

            if (spinner.getItemAtPosition(i).toString().equals(valueToLookFor)) {
                index = i
                break
            }
        return index
    }
}