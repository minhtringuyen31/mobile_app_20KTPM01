package com.example.myapplication


import android.content.Context
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
    companion object {
        lateinit var appContext: Context;
    }
    private lateinit var toolbar:Toolbar;
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var currentFragment: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentStatus = intent
        val status=intentStatus.getStringExtra("status")
        setContentView(R.layout.activity_main)
        toolbar = findViewById<Toolbar>(R.id.myToolBar)
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        currentFragment= findViewById<FrameLayout>(R.id.flFragment)
        if(status.toString()=="1"){
            showToolbarAndNavigationBar(true)
            setCurrentFragment(Homepage())

        }else
        {
            showToolbarAndNavigationBar(false)
            setCurrentFragment(splashApp())

        }
        activeNavigationBar();

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
                val params = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                params.setMargins(0, 0, 0, 0);
                currentFragment.setLayoutParams(params);
            }
        }


    }
    fun activeNavigationBar(){
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
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


                }
            }
            true
        }
    }
    fun setSelectedIcon(index:Int){
        bottomNavigationView.getMenu().getItem(index).setChecked(true);
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