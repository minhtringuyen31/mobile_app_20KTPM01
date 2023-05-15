package com.example.myapplication.pages.activities.notification

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.Notification
import com.example.myapplication.pages.apdaters.NotificationListAdapter
import com.example.myapplication.pages.apdaters.OrderProductListAdapter
import com.example.myapplication.pages.fragments.Activities
import com.example.myapplication.viewmodels.notification.NotificationViewModel

class NotificationList : AppCompatActivity() {
//    private lateinit var notificationSubTitleTV : TextView
//    private lateinit var notificationTimeTV : TextView
//    private lateinit var notificationTitleTV : TextView
//    private lateinit var notificationDescriptionTV : TextView
//    private lateinit var notificationImageIV : ImageView
    private lateinit var backBtn : ImageButton
    private lateinit var notificationListRV : RecyclerView
    private lateinit var notificationViewModel : NotificationViewModel
    private lateinit var notificationListAdapter: NotificationListAdapter

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
            notificationListAdapter.addNotification(it as ArrayList<Notification>)
            notificationListAdapter.notifyDataSetChanged()


        }
    }
}