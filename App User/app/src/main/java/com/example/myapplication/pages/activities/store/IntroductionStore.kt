package com.example.myapplication.pages.activities.store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.pages.activities.user.Login

class IntroductionStore : AppCompatActivity() {
    lateinit var viewmap:Button
    private lateinit var btn_back:AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_store)
        btn_back=findViewById(R.id.back_btn)
        btn_back.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("FragmentToLoad", "Others")
            startActivity(intent)
        }

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