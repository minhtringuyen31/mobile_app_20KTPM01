package com.example.myapplication.Admin.pages.user

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.modals.User
import com.example.myapplication.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class UserAdapter(private val context: Context, private val items: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val iconImageView = listItemView.findViewById<ImageView>(R.id.accountImage)
        val nameTextView = listItemView.findViewById<TextView>(R.id.accountName)
        val descTextView = listItemView.findViewById<TextView>(R.id.accountEmail)
        val createdTextView = listItemView.findViewById<TextView>(R.id.accountDob)
        val phoneTextView = listItemView.findViewById<TextView>(R.id.accountPhone)
        val disableTextView = listItemView.findViewById<ImageView>(R.id.isDisableAccount)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.custom_user, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: User = items[position]
        val imageView = holder.iconImageView
        imageView.setImageResource(R.drawable.profile)
        val nameTv = holder.nameTextView
        nameTv.text = item.getName()
        val descTv = holder.descTextView
        descTv.text = item.getEmail()
        val dateTv = holder.createdTextView
        dateTv.text = LocalDateTime.parse(
            item.getDob().toString(),
            DateTimeFormatter.ISO_DATE_TIME
        )
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val phoneTv = holder.phoneTextView
        phoneTv.text = item.getPhone()
        val disableIv = holder.disableTextView
        if (item.getIsDisable() == 0) {
            disableIv.setImageResource(R.drawable.baseline_able_24)
        } else {
            disableIv.setImageResource(R.drawable.baseline_disable_24)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UserDetail::class.java)
            intent.putExtra("user_id", item.getId()!!.toString())
            context.startActivity(intent)
        }
    }
}