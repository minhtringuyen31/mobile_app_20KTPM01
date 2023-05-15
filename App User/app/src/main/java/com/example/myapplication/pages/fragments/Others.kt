package com.example.myapplication.pages.fragments

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.pages.activities.promotion.ListPromotion
import com.example.myapplication.pages.activities.store.IntroductionStore
import com.example.myapplication.pages.activities.user.ChangePassword
import com.example.myapplication.pages.activities.user.EditProfile
import com.example.myapplication.pages.activities.user.Login
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Others.newInstance] factory method to
 * create an instance of this fragment.
 */
class Others : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var profile_name: TextView
    private lateinit var profile_phone: TextView
    private lateinit var view: View;
    private lateinit var route_editprofile:TextView
    private lateinit var route_changepassword:TextView
    private lateinit var route_introstore:TextView
    private lateinit var route_listpromotion:TextView
    private lateinit var button_logout: Button
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
//    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_others, container, false)

        initUI(view)


        setUpObserver()
        // Inflate the layout for this fragment
        return view
    }
    private fun initUI(view: View) {
        profile_name = view.findViewById(R.id.profile_name)
        profile_phone = view.findViewById(R.id.profile_phone)
        route_editprofile=view.findViewById(R.id.route_editprofile)
        route_changepassword=view.findViewById(R.id.route_changepassword)
        route_introstore=view.findViewById(R.id.route_introstore)
        route_listpromotion=view.findViewById(R.id.route_listpromotion)
        button_logout = view.findViewById(R.id.button_logout)
        route_editprofile.setOnClickListener {
            val intent = Intent(
                view.context,
                EditProfile::class.java
            )
            startActivity(intent)
        }
        route_changepassword.setOnClickListener {
            val intent = Intent(
                view.context,
                ChangePassword::class.java
            )
            startActivity(intent)
        }
        route_introstore.setOnClickListener {
            val intent=Intent(
                view.context,
                IntroductionStore::class.java
            )
            startActivity(intent)
        }
        route_listpromotion.setOnClickListener {
            val intent=Intent(
                view.context,
                ListPromotion::class.java
            )
            intent.putExtra("source","Others")
            startActivity(intent)
        }

    }
    private fun setUpObserver() {
        val sharedPreferences: SharedPreferences = view.context.getSharedPreferences("user", MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val phone = sharedPreferences.getString("phone", "")
        profile_name.text = name
        profile_phone.text = phone
        //---
        //auth= Firebase.auth
        button_logout.setOnClickListener {
            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            gsc = GoogleSignIn.getClient(view.context, gso);
            val account = GoogleSignIn.getLastSignedInAccount(view.context)
            gsc.signOut();
            val preferences: SharedPreferences = view.context.getSharedPreferences("user", 0)
            val sharedPreferences = view.context.getSharedPreferences("cart", AppCompatActivity.MODE_PRIVATE)
            preferences.edit().remove("userID").apply()
            preferences.edit().remove("name").apply()
            preferences.edit().remove("phone").apply()
            sharedPreferences.edit().remove("productID").apply()

            //---
            //auth.signOut()

            val intent = Intent(
                view.context,
                Login::class.java
            )
            startActivity(intent)
            requireActivity().finish();
        }
    }
        companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment Others.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                Others().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
    }
