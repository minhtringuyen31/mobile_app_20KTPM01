package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.modals.Rating
import com.example.myapplication.modals.User
import com.example.myapplication.viewmodels.product.RatingViewModel
import com.example.myapplication.viewmodels.user.UserViewModel

class RatingActivity : AppCompatActivity() {
    private lateinit var ratingBar: RatingBar
    private lateinit var commentTV: TextView
    private lateinit var submitBtn : TextView
    private lateinit var view: View
    private lateinit var userViewModel: UserViewModel
    private var currentUserId : String =""
    private var currentUserName : String =""
    private var currentUserAvatar : String =""
    private lateinit var ratingViewModel: RatingViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        initUI()
        getUserViewModelInformation()



        submitBtn.setOnClickListener {
            submitRatingBtnActionClickListener()
        }
    }

    private fun initUI(){
        ratingBar = findViewById(R.id.ratingStarRB)
        commentTV = findViewById(R.id.commentRatingTV)
        submitBtn = findViewById(R.id.submitRatingBtn)
    }

    private fun getUserViewModelInformation(){
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        currentUserId = sharedPreferences.getString("userID","").toString()
        println("Current user id: " + currentUserId)
        if (currentUserId != null) {
            userViewModel.getUser(currentUserId.toInt())
            userViewModel.user.observe(this) {
                currentUserName = it.getName()
                currentUserAvatar = it.getAvatar()
            }
        };
//        userViewModel.getUser(currentUserId.toInt())

    }

    private fun submitRatingBtnActionClickListener(){
        val ratingValue = ratingBar.rating
        val comment = commentTV.text.toString()
        val rating = Rating(
            currentUserId,
            currentUserName,
            currentUserAvatar,
            1,
            ratingValue,
            comment,
            "",
            false
        )
        ratingViewModel = ViewModelProvider(this)[RatingViewModel::class.java]
        println("Here")
        ratingViewModel.postRating(rating)
        finish()
    }
}