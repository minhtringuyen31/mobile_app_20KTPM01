package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.pages.*
import com.example.myapplication.utils.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var appContext: Context;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val intent = Intent(
            this@MainActivity,
           Login::class.java
        )
        appContext  = this;

        startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var inflater: MenuInflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu)
        return true;
    }

}