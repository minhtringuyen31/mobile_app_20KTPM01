package com.example.myapplication
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.pages.fragments.*
import com.example.myapplication.socket.SocketHandler
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.socket.client.Socket
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar:AppBarLayout
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var currentFragment: FrameLayout
    private lateinit var mSocket: Socket
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val intentStatus = intent
        val status=intentStatus.getStringExtra("status")
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.myToolBar)
        bottomNavigationView = findViewById(R.id.bottom_nav)
        currentFragment= findViewById(R.id.flFragment)
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
        mSocket = SocketHandler.getSocket()
                mSocket.on("server-send-message") { args ->
            if (args[0] != null) {
                val counter = args[0]

            }
        }
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE)
        val userID = sharedPreferences.getString("userID", "")
        if (userID != null&& userID.isNotEmpty()) {

            mSocket.emit("login",userID)
            setCurrentFragment(Homepage(),"Homepage")
            activeNavigationBar()
        }else
        {
            if(status.toString()=="1"){
                showToolbarAndNavigationBar(true)
                setCurrentFragment(Homepage(),"Homepage")
            }else {
                showToolbarAndNavigationBar(false)
                setCurrentFragment(splashApp(),"splashApp")
            }
            activeNavigationBar()
        }
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val fragment = supportFragmentManager.findFragmentByTag("checkout")
        if (fragment is Checkout) {
            fragment.handleNewIntent(intent)
        }
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
                    // Build a GoogleSignInClient with the options specified by gso.
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
    override fun onDestroy() {
        super.onDestroy()
        // Đóng kết nối socket ở đây
        try {
            mSocket.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}