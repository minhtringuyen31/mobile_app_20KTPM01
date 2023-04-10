package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater

import androidx.fragment.app.Fragment
import com.example.myapplication.pages.InitApp
import com.example.myapplication.pages.Login
import com.example.myapplication.pages.fragments.Activities
import com.example.myapplication.pages.fragments.Homepage
import com.example.myapplication.pages.fragments.Order
import com.example.myapplication.pages.fragments.Others
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var appContext: Context;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentStatus = intent
        val status=intentStatus.getStringExtra("status")
        if(status.toString()=="1"){

            setCurrentFragment(Homepage())
            activeNavigationBar();
        }
        else
        {
            val intent = Intent(
                this,
                Login::class.java
            )
            startActivity(intent)
        }

    }
    fun activeNavigationBar(){
        val  navigationView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.icon_home -> {
                    setCurrentFragment(Homepage())
                }
                R.id.icon_oder -> {

                    setCurrentFragment(Order())
                }
                R.id.icon_activity -> {

                    setCurrentFragment(Activities())

                }
                R.id.icon_other -> {
                    setCurrentFragment(Others())
                    println("123")

                }
            }
            true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var inflater: MenuInflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu)
        return true;
    }
    private fun setCurrentFragment(fragment: Fragment)=
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment,fragment)
        commit()
    }

}