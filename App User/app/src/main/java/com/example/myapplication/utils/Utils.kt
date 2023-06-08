package com.example.myapplication.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.myapplication.pages.activities.notification.NotificationList
import com.example.myapplication.pages.activities.promotion.ListPromotion
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat
import java.text.Normalizer
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class Utils : AppCompatActivity() {
    companion object {

        private const val  URL="http://10.0.2.2:3000/api/"

        fun getRetrofit(): Retrofit {
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        fun convertDate(chuoiThoiGian: String): String {
            val dinhDangNhap = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val dinhDangXuat = SimpleDateFormat("HH:mm 'ngày' dd/MM/yyyy", Locale.getDefault())

            val thoiGian = dinhDangNhap.parse(chuoiThoiGian)

            return dinhDangXuat.format(thoiGian)
        }
        fun activeToolbar(context: Context,view: View){
            val toolBar: AppBarLayout = view.findViewById(com.example.myapplication.R.id.myToolBar)
//            (context as AppCompatActivity).setSupportActionBar(toolBar)
//            val avatar: CircleImageView = view.findViewById(R.id.avatarUser)
//            val search_icon: View = view.findViewById(R.id.search_icon)
//            avatar.setOnClickListener {
//                val intent = Intent(
//                    context,
//                    Profile::class.java
//                )
//                context.startActivity(intent)
//
//            }
//            search_icon.setOnClickListener{
//                Toast.makeText(context,
//                    "Clicked Search Icon", Toast.LENGTH_LONG).show();
//            }

            val welcomeTitle : TextView = view.findViewById(com.example.myapplication.R.id.welcomeTV)
            val promotionBtn : CardView = view.findViewById(com.example.myapplication.R.id.promotionBtn)
            val notificationBtn : CardView = view.findViewById(com.example.myapplication.R.id.notificationBtn)
            promotionBtn.setOnClickListener{
                val intent= Intent(
                    context,
                    ListPromotion::class.java
                )
                context.startActivity(intent)
            }
            notificationBtn.setOnClickListener {
                val intent= Intent(
                    context,
                    NotificationList::class.java
                )
                context.startActivity(intent)
            }
        }
        fun getScreenWidth(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }

        fun getScreenHeight(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }

        fun formatCurrency(total: Double): String {
            val formatter: NumberFormat = DecimalFormat("#,###")
            return formatter.format(total);
        }
        fun getDigitInString(target: String): Double {
            return target.filter { it.isDigit() }.toDouble();
        }

        fun removeAccent(s: String): String {
            val temp: String = Normalizer.normalize(s, Normalizer.Form.NFD)
            val pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
            return pattern.matcher(temp).replaceAll("")
        }

        class CustomLoadingDialog(context: Context?) {
            private val dialog: Dialog
            private val progressBar: ProgressBar

            init {
                dialog = Dialog(context!!)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
                dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(com.example.myapplication.R.layout.custom_loading)
                progressBar = dialog.findViewById(com.example.myapplication.R.id.progressBar)
            }

            fun show() {
                progressBar.visibility = View.VISIBLE
                dialog.show()
            }

            fun dismiss() {
                dialog.dismiss()
            }
        }






    }

}