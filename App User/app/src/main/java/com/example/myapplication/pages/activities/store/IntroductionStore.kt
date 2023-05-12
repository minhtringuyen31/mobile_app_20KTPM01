package com.example.myapplication.pages.activities.store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class IntroductionStore : AppCompatActivity() {
    lateinit var viewmap:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_store)
        viewmap=findViewById(R.id.viewmap)
        viewmap.setOnClickListener {
            val intent = Intent(
                this,
                MapsActivity::class.java
            )
            startActivity(intent)
        }
    }
}