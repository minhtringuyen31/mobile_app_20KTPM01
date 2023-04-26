package com.example.myapplication.pages.activities.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.viewmodels.UserViewModel

class Profile : AppCompatActivity() {
    private lateinit var profile_name:TextView
    private lateinit var profile_phone:TextView
    private lateinit var UserProfile : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initUI()
        UserProfile.getUser(21);
        setUpObserver()
    }
    private fun initUI(){
        profile_name = findViewById(R.id.profile_name)
        profile_phone = findViewById(R.id.profile_phone)



    }
    private fun setUpObserver() {
        UserProfile.user.observe(this){
            profile_name.text=it.getName()
            profile_phone.text=it.getPhone()
    }

//        UserProfile= ViewModelProvider(this)[UserViewModel::class.java]
//        UserProfile.getUser(UserProfile.getId())



    }
}