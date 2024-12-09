package com.example.mishafitnesstracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Edge-to-edge padding
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
        val submitButton = findViewById<Button>(R.id.submitButton)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        val trainingList = mutableListOf<Training>()
        val adapter = TrainingAdapter(trainingList) { training ->
            Toast.makeText(this, "Wybrano: ${training.activityType}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
            Log.i("myinfo", "Wbrałeś aktywność: ${radioButton.text}")
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                percentageText.text = "$progress%"
                Log.i("myinfo", "intensywność: $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        submitButton.setOnClickListener {
            val enteredTime = editTime.text.toString().trim()
            val enteredDystans = editDystans.text.toString().trim()
            val enteredKalorie = editKalorie.text.toString().trim()

            if (enteredTime.isEmpty() || enteredDystans.isEmpty() || enteredKalorie.isEmpty()) {
                Toast.makeText(this, "Wypełnij wszystkie pola!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
            if (checkedRadioButtonId == -1) {
                Toast.makeText(this, "Wybierz rodzaj aktywności!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val selectedActivity = findViewById<RadioButton>(checkedRadioButtonId)?.text.toString()

            val intensity = seekBar.progress

            val training = Training(
                time = enteredTime,
                distance = enteredDystans,
                calories = enteredKalorie,
                activityType = selectedActivity,
                intensity = intensity
            )

            trainingList.add(training)
            adapter.notifyDataSetChanged()

            Log.i("myinfo", training.toString())
        }
    }
}
