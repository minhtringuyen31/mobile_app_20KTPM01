package com.example.myapplication.pages.apdaters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modals.Notification

class NotificationListAdapter(
    private var notificationList: ArrayList<Notification>
): RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {
    var onItemClick: ((Notification) -> Unit)? = null
     inner class ViewHolder(listItemView: View):RecyclerView.ViewHolder(listItemView){
         val notificationSubTitleTV: TextView = listItemView.findViewById(R.id.notificationSubTitleTV)
         val notificationTimeTV: TextView = listItemView.findViewById(R.id.notificationTimeTV)
         val notificationTitleTV: TextView = listItemView.findViewById(R.id.notificationTitleTV)
         val notificationDescriptionTV: TextView = listItemView.findViewById(R.id.notificationDescriptionTV)
         val notificationImageIV: ImageView =listItemView.findViewById(R.id.notificationImageIV)
         init {
             listItemView.setOnClickListener{
                 onItemClick?.invoke(notificationList[position])
             }
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val notificationView = inflater.inflate(R.layout.item_notification, parent, false)
        return ViewHolder(notificationView)
    }


    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return notificationList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val notification: Notification = notificationList[position]
        val notificationSubTitle = holder.notificationSubTitleTV
        val notificationTime = holder.notificationTimeTV
        val notificationTitle = holder.notificationTitleTV
        val notificationDescription = holder.notificationDescriptionTV

        notificationSubTitle.text = notification.getSubTitle()
        notificationTime.text = notification.getTime().take(10)
        notificationTitle.text = notification.getTitle()
        notificationDescription.text = notification.getDescription()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNotification(notification:ArrayList<Notification>){
        notificationList.apply {
            clear()
            notificationList.addAll(notification)
            notifyDataSetChanged()
            println("add notification item in adapter $notification")
        }
    }


}