package com.example.myapplication.pages.activities.promotion

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.modals.Promotion
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PromotionAdapterList(var promotionList: List<Promotion>):RecyclerView.Adapter<PromotionAdapterList.PromotionViewHolder>(){
    var onItemClick:((Promotion) -> Unit)? = null
    inner class PromotionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
      //  fun bind(promotion: Promotion) {
            val name_promotion: TextView = itemView.findViewById(R.id.name_promotion)
            val discount_promotion: TextView = itemView.findViewById(R.id.discount_promotion)
            val start_date: TextView = itemView.findViewById(R.id.start_date)
            val end_date: TextView = itemView.findViewById(R.id.end_date)
            val img_promotion: ImageView = itemView.findViewById(R.id.img_promotion)
            val countDay :TextView = itemView.findViewById(R.id.countDay)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(promotionList[adapterPosition])
            }
        }
//
//            itemView.setOnClickListener {
//                listener?.onItemClick(promotion)
//            }
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_promotion_list,parent,false)
        return PromotionViewHolder(view)
    }
    fun getRemainingDays(endDate: LocalDate): Long {
        val startDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return ChronoUnit.DAYS.between(startDate, endDate)
    }


    override fun getItemCount(): Int {
        return promotionList.size
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        val item: Promotion = getItem(position)
        holder.name_promotion.text = item.getName()
        holder.start_date.text = item.getBeginDay()
        holder.end_date.text = item.getEndDay()
        holder.discount_promotion.text=item.getDiscount().toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.countDay.text = "Còn "+getRemainingDays( LocalDate.parse(item.getEndDay())).toString() + " ngày"
        }

        Glide.with(holder.itemView)
            .load(promotionList[position].getImage())
            .into(holder.img_promotion)


//            holder.itemView.setOnClickListener{
//                onItemClick?.invoke(item)
//            }
//        val currentPromotion = promotionList[position]
//        holder.bind(currentPromotion)

    }

    fun getItem(position: Int): Promotion {
        return promotionList.get(position)
    }
}
//
//class PromotionAdapterList(var promotionList: ArrayList<Promotion>):RecyclerView.Adapter<PromotionAdapterList.PromotionViewHolder>(){
//    class PromotionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
//        val name_promotion:TextView=itemView.findViewById(R.id.name_promotion)
//        val discount_promotion:TextView=itemView.findViewById(R.id.discount_promotion)
//        val start_date:TextView=itemView.findViewById(R.id.start_date)
//        val end_date:TextView=itemView.findViewById(R.id.end_date)
//        val img_promotion:ImageView=itemView.findViewById(R.id.img_promotion)
//    }
//
//    fun getItem(position: Int): Promotion {
//        return promotionList[position]
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
//       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_promotion_list,parent,false)
//        return PromotionViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return promotionList.size
//    }
//    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
//
//        val item: Promotion = getItem(position)
//        holder.name_promotion.text = item.getName()
//        holder.start_date.text = item.getBeginDay()
//        holder.end_date.text = item.getEndDay()
//        holder.discount_promotion.text=item.getDiscount().toString()
//
//        Glide.with(holder.itemView)
//            .load(promotionList[position].getImage())
//            .into(holder.img_promotion)
//    }
//
//
//
//}
//class PromotionAdapterList(private var items: ArrayList<Promotion>,
//                           private val listener: Promotion,
//                           mContext: Context
//):  ArrayAdapter<Any?>(mContext, R.layout.item_promotion_list, items as ArrayList<*>) {
//    private lateinit var view: View
//
//    private class ViewHolder {
//        lateinit var name_promotion: TextView
//        lateinit var start_date: TextView
//        lateinit var discount_promotion:TextView
//        lateinit var img_promotion: ImageView
//        lateinit var end_date: TextView
//
//    }
//
//    override fun getCount(): Int {
//        return items.size
//    }
//
//    override fun getItem(position: Int): Promotion {
//        return items[position]
//    }
//
//
//    @SuppressLint("ViewHolder")
//    override fun getView(
//        position: Int,
//        convertView: View?,
//        parent: ViewGroup
//    ): View {
//        var convertView = convertView
//        val viewHolder: ViewHolder
//        val result: View
//
//        view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout, parent, false)
//        if (convertView == null) {
//            viewHolder = ViewHolder()
//
//            viewHolder.name_promotion =
//                view.findViewById(R.id.name_promotion)
//            viewHolder.start_date =
//                view.findViewById(R.id.start_date)
//            viewHolder.end_date =
//                view.findViewById(R.id.end_date)
//            viewHolder.img_promotion =
//                view.findViewById(R.id.img_promotion)
//            viewHolder.discount_promotion= view.findViewById(R.id.discount_promotion)
//            result = view
//            view.tag = viewHolder
//        } else {
//            viewHolder = convertView.tag as ViewHolder
//            result = convertView
//        }
//
//        val item: Promotion = getItem(position)
//        viewHolder.name_promotion.text = item.getName()
//        viewHolder.start_date.text = item.getBeginDay()
//        viewHolder.end_date.text = item.getEndDay()
//      //  viewHolder.discount_promotion=item.getDiscount().toString()
//
//
//        Glide.with(view)
//            .load(item.getImage()).fitCenter()
//            .into(viewHolder.img_promotion)
//
//
//
//
//        return result
//    }
//
//    fun addItems(promotions: ArrayList<Promotion>) {
//        this.items.apply {
//            clear()
//            addAll(promotions)
//            notifyDataSetChanged()
//        }
//    }
//
//}