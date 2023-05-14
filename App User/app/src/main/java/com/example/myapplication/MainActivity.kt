package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.modals.*
import com.example.myapplication.pages.fragments.*
import com.example.myapplication.pages.fragments.Order
import com.example.myapplication.socket.SocketHandler
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import io.socket.client.Socket
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var toolbar:AppBarLayout
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var currentFragment: FrameLayout
    private lateinit var mSocket: Socket


    private fun handleTokenFirebase(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            // Log and toast
            println(token)
        })
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test Notification"
            val descriptionText = "Decription for test"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("123", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val REQUEST_CODE_NOTIFICATION_PERMISSION =11111
        when (requestCode) {
            REQUEST_CODE_NOTIFICATION_PERMISSION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Quyền được cấp, tiến hành hiển thị notification
                    showNotification()
                } else {
                    // Quyền bị từ chối, thông báo cho người dùng và thoát ứng dụng
                    Toast.makeText(
                        this,
                        "Bạn cần cấp quyền truy cập notification để sử dụng tính năng này",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
                return
            }
        }
    }
    private fun showNotification(){
//         Create an explicit intent for an Activity in your app
        val intent = Intent(this, MainActivity::class.java).apply {
            intent.putExtra("FragmentToOpen", "Activities") // Truyền tên Fragment cần mở
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        createNotificationChannel()
        val builder = NotificationCompat.Builder(this,"123")
            .setSmallIcon(R.drawable.baseline_notifications_active_24)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 11111)
            }
            notify(0, builder.build())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()


        setContentView(R.layout.activity_main)
        val intentStatus = intent


        val status=intentStatus.getStringExtra("status")

        toolbar = findViewById(R.id.myToolBar)
        bottomNavigationView = findViewById(R.id.bottom_nav)
        currentFragment= findViewById(R.id.flFragment)
//        handleTokenFirebase()
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
        mSocket = SocketHandler.getSocket()
        mSocket.on("server-send-message") { args ->
            if (args[0] != null) {
                val counter = args[0]
                println(counter);
            }
        }
        mSocket.on("statusOrder") { args ->;
            if (args[0] != null) {
                val counter = args[0]
                showNotification()
            }
        }

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("user", MODE_PRIVATE)
        val userID = sharedPreferences.getString("userID", null)
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
        if (intent.extras != null) {
            val fragmentToLoad = intent.extras!!.getString("FragmentToLoad")
            if (fragmentToLoad == "Others") {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.flFragment, Others())
                    .commit()
            }
        }










    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val fragment = supportFragmentManager.findFragmentByTag("checkout")
        if (fragment is Checkout) {
            fragment.handleNewIntent(intent)
        }

        if (intent != null) {
            println(intent.getStringExtra("FragmentToOpen"))
        }
        if (intent!!.getStringExtra("FragmentToOpen")=="Activities") {
            setCurrentFragment(Activities(),"Activities")
        }
        val percent=intent.getStringExtra("percent")
        val des=intent.getStringExtra("to")
        val idPromotion=intent.getStringExtra("idPromotion")

        if(des=="Orders")
        {
            setCurrentFragment(Order(),"Orders")
        }
        else if(des=="Checkout")
        {
            val fm = supportFragmentManager
            val fragment: Checkout =
                fm.findFragmentByTag("checkout") as Checkout
            fragment.updateVoucher(percent!!,idPromotion!!)
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
                    setCurrentFragment(Others(),"Others")
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
     fun setCurrentFragment(fragment: Fragment,tag:String)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment,tag).addToBackStack(null)
            commit()
        }
    override fun onDestroy() {
        super.onDestroy()
//        // Đóng kết nối socket ở đây
//        try {
//            mSocket.close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
    }




}