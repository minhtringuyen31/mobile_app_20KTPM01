package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.Rating
import com.example.myapplication.viewmodels.product.ProductViewModel
import com.example.myapplication.viewmodels.product.RatingViewModel
import com.example.myapplication.viewmodels.user.UserViewModel

class RatingActivity : AppCompatActivity() {
    private lateinit var ratingBar: RatingBar
    private lateinit var commentTV: TextView
    private lateinit var submitBtn : TextView
    private lateinit var view: View
    private lateinit var userViewModel: UserViewModel
    private lateinit var productViewModel: ProductViewModel
    private var currentProductName = ""
    private var currentProductImg = ""
    private var currentUserId : String =""
    private var currentUserName : String =""
    private var currentUserAvatar : String =""
    private lateinit var ratingViewModel: RatingViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var productID:String
    private lateinit var orderID:String
    private lateinit var imgToolbarBtnBack : ImageView
    private lateinit var productRatingImgIV: ImageView
    private lateinit var productRatingNameTV : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        productID= intent.getStringExtra("productId").toString()
        initUI()
        getUserViewModelInformation()

        getProductViewModelInformation(productID.toInt())
        submitBtn.setOnClickListener {
            submitRatingBtnActionClickListener()
        }
    }

    private fun initUI(){
        productRatingImgIV = findViewById(R.id.productRatingImageIV)
        productRatingNameTV = findViewById(R.id.productRatingNameTV)
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

    @SuppressLint("NotifyDataSetChanged")
    private fun getProductViewModelInformation(productId: Int){
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        productViewModel.getProduct(productId)
        productViewModel.product.observe(this){

            currentProductImg= it.getImage()
            currentProductName = it.getName()
            productRatingNameTV.text = currentProductName

            Glide.with(this)
                .load(currentProductImg).fitCenter()
                .into(productRatingImgIV)
        }


    }


    private fun submitRatingBtnActionClickListener(){
        val ratingValue = ratingBar.rating
        val comment = commentTV.text.toString()
        val rating = Rating(
            currentUserId.toInt(),
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
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish()
    }
}