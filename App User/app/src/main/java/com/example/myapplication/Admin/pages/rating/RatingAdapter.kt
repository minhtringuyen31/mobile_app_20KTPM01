package com.example.myapplication.Admin.pages.rating

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Admin.controllers.ProductController
import com.example.myapplication.Admin.controllers.RatingController
import com.example.myapplication.Admin.modals.Rating
import com.example.myapplication.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RatingAdapter(private var context: Context, private var list: List<Rating>) :
    RecyclerView.Adapter<RatingAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ratingUserName = itemView.findViewById<TextView>(R.id.ratingUserName)
        val ratingUserImage = itemView.findViewById<ImageView>(R.id.ratingUserImage)
        val ratingScore = itemView.findViewById<RatingBar>(R.id.ratingScore)
        val ratingComment = itemView.findViewById<TextView>(R.id.ratingComment)
        val isDisableRating = itemView.findViewById<ImageView>(R.id.isDisableRating)
        val ratingDate = itemView.findViewById<TextView>(R.id.ratingDate)
        val ratingProductImage = itemView.findViewById<ImageView>(R.id.ratingProductImage)
        val ratingProductName = itemView.findViewById<TextView>(R.id.ratingProductName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val ratingView = inflater.inflate(R.layout.custom_rating, parent, false)
        return ViewHolder(ratingView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rating: Rating = list[position]
        val ratingUserName = holder.ratingUserName
        ratingUserName.text = rating.getUser_name()
        val ratingUserImage = holder.ratingUserImage
        Glide.with(context).load(rating.getUser_image()).into(ratingUserImage)
        val ratingScore = holder.ratingScore
        ratingScore.rating = rating.getScore()?.toFloat() ?: 0.0F
        val ratingComment = holder.ratingComment
        ratingComment.text = rating.getComment()
        val isDisableRating = holder.isDisableRating
        if (rating.getIs_disable() == 1) {
            isDisableRating.setImageResource(R.drawable.baseline_disable_24)
        } else {
            isDisableRating.setImageResource(R.drawable.baseline_able_24)
        }
        val ratingDate = holder.ratingDate
        ratingDate.text =
            LocalDateTime.parse(rating.getCreate_at().toString(), DateTimeFormatter.ISO_DATE_TIME)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val productViewProvider =
            ViewModelProvider(context as AppCompatActivity)[ProductController::class.java]
        productViewProvider.getProduct(rating.getProduct_id()!!)
            .observe(context as AppCompatActivity) {
                val ratingProductImage = holder.ratingProductImage
                Glide.with(context).load(it.getImage()).into(ratingProductImage)
                val ratingProductName = holder.ratingProductName
                ratingProductName.text = it.getName()
            }
        holder.isDisableRating.setOnClickListener {
            val ratingViewProvider =
                ViewModelProvider(context as AppCompatActivity)[RatingController::class.java]
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage("Bạn có muốn thay đổi trạng thái đánh giá này không?")
            builder.setCancelable(true)
            builder.setNegativeButton("Có") { dialog, which ->
                if (rating.getIs_disable() == 1) {
                    ratingViewProvider.enableRating(rating.getId()!!)
                        .observe(context as AppCompatActivity) {
                            if (it) {
                                isDisableRating.setImageResource(R.drawable.baseline_able_24)
                            }
                        }
                } else {
                    ratingViewProvider.disableRating(rating.getId()!!)
                        .observe(context as AppCompatActivity) {
                            if (it) {
                                isDisableRating.setImageResource(R.drawable.baseline_disable_24)
                            }
                        }
                }
                (context as AppCompatActivity).recreate()
            }
            builder.setPositiveButton("Không") { dialog, which ->
                dialog.cancel()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}