package com.example.myapplication.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.example.myapplication.R
import org.w3c.dom.Comment

class RatingActivity : AppCompatActivity() {
    private lateinit var ratingBar: RatingBar
    private lateinit var commentTV: TextView
    private lateinit var submitBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        initUI()
        submitBtn.setOnClickListener {
            submitRatingBtnActionClickListener()

        }
    }

    private fun initUI(){
        ratingBar = findViewById(R.id.ratingStarRB)
        commentTV = findViewById(R.id.commentRatingTV)
        submitBtn = findViewById(R.id.submitRatingBtn)
    }

    private fun submitRatingBtnActionClickListener(){
        val rating = ratingBar.rating
        val comment = commentTV.text.toString()

    }
}