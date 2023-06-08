package com.example.myapplication.pages.apdaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Rating
import com.example.myapplication.utils.Utils


class RatingListAdapter (
    private var ratingList: ArrayList<Rating>
    ): RecyclerView.Adapter<RatingListAdapter.ViewHolder>(){
        var onItemClick:((Rating)->Unit)?  = null
    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView){
        val ratingUserImageIV : ImageView = listItemView.findViewById(R.id.ratingUserImageIV)
        val ratingUserNameTV : TextView = listItemView.findViewById(R.id.ratingUserNameTV)
        val ratingScore : RatingBar = listItemView.findViewById(R.id.ratingStarRB)
        val ratingDescriptionTV : TextView = listItemView.findViewById(R.id.ratingDescriptionTV)
        val ratingDatePostTV : TextView = listItemView.findViewById(R.id.ratingDatePostTV)
        init{
            listItemView.setOnClickListener{
                onItemClick?.invoke(ratingList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val ratingView = inflater.inflate(R.layout.item_product_rating, parent, false)
        return ViewHolder(ratingView)
    }

    override fun getItemCount(): Int {
        return ratingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rating: Rating = ratingList[position]
        val ratingUserName = holder.ratingUserNameTV
        val ratingUserImage = holder.ratingUserImageIV
        val ratingScore = holder.ratingScore
        val ratingDescription = holder.ratingDescriptionTV
        val ratingDatePost = holder.ratingDatePostTV

        ratingUserName.text = rating.getUserName()
//        Glide.with(holder.itemView)
//            .load(rating.getUserImage()).fitCenter()
//            .into(ratingUserImage)
        ratingScore.rating = rating.getScore()
        ratingDescription.text = rating.getComment()
        ratingDatePost.text =  Utils.convertDate(rating.getDatePost())
    }
}