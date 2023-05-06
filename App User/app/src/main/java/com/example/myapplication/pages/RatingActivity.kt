package com.example.myapplication.pages

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
    private lateinit var currentUser : User
    private lateinit var currentUserId : String
    private lateinit var ratingViewModel: RatingViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        initUI()
//        getUserViewModelInformation()
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
        sharedPreferences =
            view.context.getSharedPreferences("user", MODE_PRIVATE)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        currentUserId = sharedPreferences.getString("userID","").toString()
        if (currentUserId != null) {
            userViewModel.getUser(currentUserId.toInt())
        };
        userViewModel.user.observe(this){
            currentUser = User(
                it.getName(),
                it.getGender(),
                it.getEmail(),
                it.getPhone(),
                it.getPassword(),
                it.getDOB(),
                it.getAddress(),
                it.getAvatar(),
                it.getRole(),
                0,
                )
        }
    }

    private fun submitRatingBtnActionClickListener(){
        val ratingValue = ratingBar.rating
        val comment = commentTV.text.toString()
        val rating = Rating(
            currentUserId,
            currentUser.getName(),
            currentUser.getAvatar(),
            1,
            ratingValue,
            commentTV.text.toString(),
            "",
            false
        )
        ratingViewModel = ViewModelProvider(this)[RatingViewModel::class.java]
        ratingViewModel.postRating(rating)

    }
}