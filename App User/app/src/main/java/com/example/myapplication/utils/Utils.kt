package com.example.myapplication.utils

import android.content.Context
import android.content.Intent

import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.pages.activities.user.Profile
import com.google.gson.GsonBuilder
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat
import java.text.Normalizer
import java.text.NumberFormat
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
        fun activeToolbar(context: Context,view: View){
            val toolBar: Toolbar = view.findViewById(R.id.myToolBar)
            (context as AppCompatActivity).setSupportActionBar(toolBar)
            val avatar: CircleImageView = view.findViewById(R.id.avatarUser)
            val search_icon: View = view.findViewById(R.id.search_icon)
            avatar.setOnClickListener {
                val intent = Intent(
                    context,
                    Profile::class.java
                )
                context.startActivity(intent)

            }
            search_icon.setOnClickListener{
                Toast.makeText(context,
                    "Clicked Search Icon", Toast.LENGTH_LONG).show();
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




    }

}