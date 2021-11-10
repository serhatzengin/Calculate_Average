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
    private var allLessonInformation: ArrayList<Lessons> = ArrayList(5)

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


    fun findingSpinnerValueIndex(spinner: Spinner, valueToLookFor: String): Int {

        var index = 0
        for (i in 0..spinner.count)

            if (spinner.getItemAtPosition(i).toString().equals(valueToLookFor)) {
                index = i
                break
            }
        return index
    }


    fun calculate_average(view: android.view.View) {

        var totalGrade: Double = 0.0
        var totalCredit = 0.0

        for (i in 0..rootLayout.childCount - 1) {

            var singleLine = rootLayout.getChildAt(i)

            var temporaryLesson = Lessons(
                singleLine.et_New_Lesson_Name.text.toString(),
                ((singleLine.spn_New_Credit.selectedItemPosition) + 1).toString(),
                singleLine.spn_New_Grade.selectedItem.toString(),
            )
            allLessonInformation.add(temporaryLesson)
        }
        for (currentLesson in allLessonInformation) {


            totalGrade += convertLetterToNote(currentLesson.lessonLetterGrade) * (currentLesson.lessonCredit.toDouble())
            totalCredit += currentLesson.lessonCredit.toDouble()
        }

        Toast.makeText(this, "Average:" + (totalGrade / totalCredit), Toast.LENGTH_LONG).show()
        allLessonInformation.clear()
    }


    fun convertLetterToNote(incomingLetterGradeValue: String): Double {

        var value = 0.0
        when (incomingLetterGradeValue) {
            "AA" -> value = 4.0
            "BA" -> value = 3.5
            "BB" -> value = 3.0
            "CB" -> value = 2.5
            "CC" -> value = 2.0
            "DC" -> value = 1.5
            "DD" -> value = 1.0
            "FD" -> value = 0.5
            "FF" -> value = 0.0

        }
        return value

    }

}