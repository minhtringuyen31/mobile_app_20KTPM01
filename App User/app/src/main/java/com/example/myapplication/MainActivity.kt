package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.pages.Checkout
import com.example.myapplication.pages.Homepage
import com.example.myapplication.utils.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var appContext: Context;
    }
    private lateinit var toolBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bottom sheet
        // some code
        val view = findViewById<View>(android.R.id.content)


        val myIntent: Intent = Intent(this, Checkout::class.java)
        startActivity(myIntent)
        val context: Context = this
        appContext = context;




//
//


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var inflater: MenuInflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu)
        return true;
    }
}