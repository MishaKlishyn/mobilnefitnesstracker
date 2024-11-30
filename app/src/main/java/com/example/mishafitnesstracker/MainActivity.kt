package com.example.mishafitnesstracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // paddingi spoko
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTime = findViewById<EditText>(R.id.editTime)
        val editDystans = findViewById<EditText>(R.id.editdystans)
        val editKalorie = findViewById<EditText>(R.id.editkalorie)
        val radioGroup = findViewById<RadioGroup>(R.id.rodzaj)
        val seekBar = findViewById<SeekBar>(R.id.seekbar)
        val percentageText = findViewById<TextView>(R.id.percentageText)


        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
            Log.i("myinfo", "Wybrano: ${radioButton.text}")
        }


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                percentageText.text = "$progress%"  // procenty nonstop
                Log.i("myinfo", "Poziom intensywności: $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val enteredTime = editTime.text.toString()
            val enteredDystans = editDystans.text.toString()
            val enteredKalorie = editKalorie.text.toString()


            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedActivity = findViewById<RadioButton>(checkedRadioButtonId)?.text.toString()


            val intensity = seekBar.progress


            val training = Training(
                time = enteredTime,
                distance = enteredDystans,
                calories = enteredKalorie,
                activityType = selectedActivity,
                intensity = intensity
            )


            Log.i("myinfo", "Wpisany czas: ${training.time}")
            Log.i("myinfo", "Wpisany dystans: ${training.distance}")
            Log.i("myinfo", "Wpisane kalorie: ${training.calories}")
            Log.i("myinfo", "Wybrana aktywność: ${training.activityType}")
            Log.i("myinfo", "Poziom intensywności: ${training.intensity}")
        }
    }
}
