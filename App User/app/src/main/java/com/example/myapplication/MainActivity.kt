package com.example.myapplication
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.myapplication.pages.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var toolbar:Toolbar
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var currentFragment: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val intentStatus = intent
        val status=intentStatus.getStringExtra("status")
        setContentView(R.layout.activity_main)
//        setContentView(R.layout.activity_rating)
        toolbar = findViewById(R.id.myToolBar)
        bottomNavigationView = findViewById(R.id.bottom_nav)
        currentFragment= findViewById(R.id.flFragment)
        println("Current thread: ${Thread.currentThread().name}")
        if(status.toString()=="1"){
            showToolbarAndNavigationBar(true)
            setCurrentFragment(Homepage(),"Homepage")

        }else
        {
            showToolbarAndNavigationBar(false)
            setCurrentFragment(splashApp(),"splashApp")

        }
        activeNavigationBar()

    }
    fun showToolbarAndNavigationBar(status:Boolean){
        when(status){
            true->{

                toolbar.visibility = View.VISIBLE
                bottomNavigationView.visibility = View.VISIBLE
            }
            false->{
                toolbar.visibility = View.GONE
                bottomNavigationView.visibility = View.GONE
            }
        }
    }
    fun showNavigationBar(status:Boolean){
        when(status){
            true->{
                bottomNavigationView.visibility = View.VISIBLE
            }
            false->{

                bottomNavigationView.visibility = View.GONE
            }
        }
    }
      private fun activeNavigationBar(){
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.icon_home -> {
                    setCurrentFragment(Homepage(),"Homepage")
                }
                R.id.icon_oder -> {

                    setCurrentFragment(Order(),"Order")
                }
                R.id.icon_activity -> {

                    setCurrentFragment(Activities(),"Activities")

                }
                R.id.icon_other -> {
                    showToolbarAndNavigationBar(false)
                    showNavigationBar(true)
                    setCurrentFragment(Others(),"Order")


                }
            }
            true
        }
    }

    fun setSelectedIcon(index:Int){
        bottomNavigationView.menu.getItem(index).isChecked = true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }
    private fun setCurrentFragment(fragment: Fragment,tag:String)=
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment,fragment,tag)
        commit()
    }
}