package com.example.calculate_average

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_lesson_layout.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_New_Lesson.setOnClickListener {

            var inflater = LayoutInflater.from(this)

            var newLessonView = inflater.inflate(R.layout.new_lesson_layout, null)


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

            rootLayout.addView(newLessonView)


        }
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