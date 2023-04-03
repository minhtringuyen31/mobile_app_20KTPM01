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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Utils : AppCompatActivity() {
    companion object {
        val  URL="http://10.0.2.2:3000/api/"

        fun activeRetrofit():Retrofit{
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit
        }
        fun activeToolbar(context: Context,view: View){
            var toolBar: Toolbar = view.findViewById(R.id.myToolBar)
            (context as AppCompatActivity).setSupportActionBar(toolBar)
            var avatar: CircleImageView = view.findViewById(R.id.avatarUser)
            var search_icon: View = view.findViewById(R.id.search_icon)
            avatar.setOnClickListener {
                Toast.makeText(context,
                    "Clicked Avatar User", Toast.LENGTH_LONG).show();

            }
            search_icon.setOnClickListener{
                Toast.makeText(context,
                    "Clicked Search Icon", Toast.LENGTH_LONG).show();
            }
        }

        fun activeNavigationBar(context: Context,view: View){
            var  navigationView: BottomNavigationView = view.findViewById(R.id.bottom_nav)
            navigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.icon_home -> {
                        Toast.makeText(context,
                            "Press Home", Toast.LENGTH_LONG).show();
                    }
                    R.id.icon_oder -> {
                        Toast.makeText(context,
                            "Press Order", Toast.LENGTH_LONG).show();
                    }
                    R.id.icon_activity -> {
                        Toast.makeText(context,
                            "Press Activity", Toast.LENGTH_LONG).show();

                    }
                    R.id.icon_other -> {
                        Toast.makeText(context,
                            "Press other", Toast.LENGTH_LONG).show();

                    }
                }
                true
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



    }

}