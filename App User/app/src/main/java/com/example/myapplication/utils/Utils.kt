package com.example.myapplication.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.pages.EditProfile
import com.example.myapplication.pages.Homepage
import com.example.myapplication.pages.Profile
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils : AppCompatActivity() {
    companion object {
        enum class Status {
            SUCCESS,
            ERROR,
            LOADING
        }
        data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
            companion object {
                fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

                fun <T> error(data: T?, message: String): Resource<T> =
                    Resource(status = Status.ERROR, data = data, message = message)

                fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
            }
        }
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

        fun activeNavigationBar(context: Context,view: View){
            val  navigationView: BottomNavigationView = view.findViewById(R.id.bottom_nav)
            navigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.icon_home -> {
                        val intent = Intent(
                            context,
                            Homepage::class.java
                        )
                        context.startActivity(intent)
                    }
                    R.id.icon_oder -> {

                        val intent = Intent(
                            context,
                            Homepage::class.java
                        )
                        context.startActivity(intent)
                    }
                    R.id.icon_activity -> {

                        val intent = Intent(
                            context,
                            Homepage::class.java
                        )
                        context.startActivity(intent)

                    }
                    R.id.icon_other -> {
                        val intent = Intent(
                            context,
                            Profile::class.java
                        )
                        context.startActivity(intent)

                    }
                }
                true
            }
        }
        fun readImageBase64(encodedImage: String): Bitmap {
            val startIndex =
                encodedImage.indexOf(',') + 1 // find the start index of the base64-encoded data
            val imageData = encodedImage.substring(startIndex) // extract the base64-encoded data
            val decodedBytes = android.util.Base64.decode(
                imageData,
                android.util.Base64.DEFAULT
            ) // decode the base64-encoded data to bytes
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size);
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