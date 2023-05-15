package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.modals.Rating
import com.example.myapplication.modals.User
import com.example.myapplication.viewmodels.product.RatingViewModel
import com.example.myapplication.viewmodels.user.UserViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
    private lateinit var productID:String
    private lateinit var imgToolbarBtnBack : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        productID= intent.getStringExtra("productId").toString()
        initUI()
        getUserViewModelInformation()

        println(productID)

        submitBtn.setOnClickListener {
            submitRatingBtnActionClickListener()
        }
    }

    private fun initUI(){
        imgToolbarBtnBack= findViewById(R.id.imgToolbarBtnBack)
        ratingBar = findViewById(R.id.ratingStarRB)
        commentTV = findViewById(R.id.commentRatingTV)
        submitBtn = findViewById(R.id.submitRatingBtn)
        imgToolbarBtnBack.setOnClickListener {
            super.onBackPressed();
        }
    }

    private fun getUserViewModelInformation(){
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        currentUserId = sharedPreferences.getString("userID","").toString()
        if (currentUserId != null) {
            userViewModel.getUser(currentUserId.toInt())
            userViewModel.user.observe(this) {
                currentUserName = it.getName()
                currentUserAvatar = it.getAvatar()
            }
        };

    }

    private fun submitRatingBtnActionClickListener(){
        val ratingValue = ratingBar.rating
        val comment = commentTV.text.toString()
        val rating = Rating(
            currentUserId,
            currentUserName,
            currentUserAvatar,
            productID.toInt(),
            ratingValue,
            comment,
            "",
            0
        )
        ratingViewModel = ViewModelProvider(this)[RatingViewModel::class.java]
        ratingViewModel.postRating(rating)
        finish()
    }
}