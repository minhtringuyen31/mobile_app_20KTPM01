package com.example.myapplication.pages.activities.notification

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.Notification
import com.example.myapplication.pages.apdaters.NotificationListAdapter
import com.example.myapplication.viewmodels.notification.NotificationViewModel

class NotificationList : AppCompatActivity() {
    private lateinit var backBtn : ImageButton
    private lateinit var notificationListRV : RecyclerView
    private lateinit var notificationViewModel : NotificationViewModel
    private lateinit var notificationListAdapter: NotificationListAdapter
    private lateinit var imagEmpty:ImageView
    private lateinit var textEmpty:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        val sharedPreferencesUser =
            this.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
        val userId = sharedPreferencesUser.getString("userID", "").toString().toInt()
        notificationListAdapter = NotificationListAdapter(arrayListOf())
        initViewModel()
        initUI()
        setUpObserve(userId)
    }

    private fun initViewModel(){
        notificationViewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
    }

    private fun initUI(){
        imagEmpty = findViewById(R.id.imageEmpty)
        textEmpty = findViewById(R.id.textEmpty)
        backBtn =findViewById(R.id.notificationBackBtn)
        notificationListRV = findViewById(R.id.notificationListRV)
        notificationListRV.layoutManager = LinearLayoutManager(this)
        notificationListRV.adapter = notificationListAdapter

        notificationListRV.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        notificationListAdapter.onItemClick = {notification ->
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("FragmentToLoad", "Activities")
            startActivity(intent)
        }

        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("FragmentToLoad", "Homepage")
            startActivity(intent)
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserve(userId: Int){
        notificationViewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        notificationViewModel.getNotification(userId)
        notificationViewModel.notifications.observe(this){
            if(it!=null)
            {
                notificationListAdapter.addNotification(it as ArrayList<Notification>)
                if(notificationListAdapter.itemCount==0)
                {
                    imagEmpty.visibility = View.VISIBLE
                    textEmpty.visibility = View.VISIBLE
                    notificationListRV.visibility = View.GONE
                }
                notificationListRV.visibility = View.VISIBLE
                notificationListAdapter.notifyDataSetChanged()

            }


        }
    }
}