package com.example.Age_Calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.Age_Calculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
            //Toast.makeText( this, "It works", Toast.LENGTH_SHORT).show()
        }
        }

    private fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->

            //Toast.makeText(
              //      this,   "The chosen year is $selectedYear, the month is $selectedMonth and the day is $selectedDay",
                //Toast.LENGTH_SHORT)

                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                binding.selectedDateView.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes= theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                val differenceInDays = differenceInMinutes / 1440
                binding.ageInMinutesView.text = differenceInMinutes.toString()
                binding.ageInDaysView.text = differenceInDays.toString()

             },year, month, dayOfMonth).show()


    }
    }

