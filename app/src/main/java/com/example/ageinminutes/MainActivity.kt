package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view->
            clickDatePicker(view)
            //Toast.makeText(this, "Button works", Toast.LENGTH_LONG).show()
        }
    }

    fun clickDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        //val myCalender = Calendar.getInstance()             //Calender class object
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        val dialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selecteddayOfMonth ->

                    Toast.makeText(this,
                            "The chosen year is $selectedYear, the month is $selectedMonth, and the day is $selecteddayOfMonth",
                            Toast.LENGTH_SHORT).show()
                    val selectedDate = "$selecteddayOfMonth/${selectedMonth + 1}/$selectedYear"

                    tvSelectedDate.setText(selectedDate)


                    //simple date format - sdf
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                    //parse means converting string(selectedDate) into dateformat (in sdf )
                    //theDate is the object of Date class
                    val theDate = sdf.parse(selectedDate)


                    //time returns the milli-seconds of the time
                    //!! not null assertion operator - it converts any value to a non-null type and throws expection if the value is null
                    //milli sec to seconds - 1000
                    //milli sec to minutes - 60000
                    val selectedDateInMinutes = theDate!!.time / 60000

                    //now calculating for Current Date.
                    //sdf.format is for converting the current time into Date format.
                    //sdf.parse is to create the Data Class Object
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    //now converting the current time into minutes.
                    val currentDatetoMinutes = currentDate!!.time / 60000

                    val differenceInMinutes = currentDatetoMinutes - selectedDateInMinutes

                    finalDateInMinutes.setText(differenceInMinutes.toString())


                }, year, month, day)

        // dialog is the Object of DatePickerDialog created in line 32.
        //datePicker is the functionality of DatePickerDialog Class. SetMaxDate is also the function.
        //86400000 is the total seconds in a day.
        //this is done to not exceed the date selected beyond the current time.

        dialog.datePicker.setMaxDate(Date().time - 86400000)
        dialog.show()
    }

}

