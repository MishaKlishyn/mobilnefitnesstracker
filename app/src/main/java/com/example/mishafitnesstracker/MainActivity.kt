package com.example.mishafitnesstracker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTime = findViewById<EditText>(R.id.editTime )
        val editDystans = findViewById<EditText>(R.id.editdystans )
        val editkalorie = findViewById<EditText>(R.id.editkalorie )
        val radioGroup = findViewById<RadioGroup>(R.id.rodzaj)
        radioGroup.setOnCheckedChangeListener{_, checkedid->
            val radioButton = findViewById<RadioButton>(checkedid)
//            myTextView.text="wybrałeś ${radioButton.text}"
        }

        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val enteredTime = editTime.text.toString()

            //Logcat dziala

            Log.i("myinfo","Wpisany tekst info: $enteredTime")


        }


    }



}