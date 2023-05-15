package com.example.myapplication.pages.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.myapplication.R
import com.example.myapplication.pages.activities.promotion.ListPromotion
import com.example.myapplication.pages.activities.store.IntroductionStore
import com.example.myapplication.pages.activities.user.ChangePassword
import com.example.myapplication.pages.activities.user.EditProfile
import com.example.myapplication.pages.activities.user.Login
import com.example.myapplication.viewmodels.user.UserViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import java.util.*

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
    private lateinit var UserProfile: UserViewModel
    private lateinit var view: View;
    private lateinit var route_editprofile:TextView
    private lateinit var route_changepassword:TextView
    private lateinit var route_introstore:TextView
    private lateinit var route_listpromotion:TextView
    private lateinit var route_changelanguage:TextView
    private lateinit var button_logout: Button
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var auth:FirebaseAuth

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
        UserProfile = ViewModelProvider(this)[UserViewModel::class.java]
        loadLocate(view)
        initUI(view)

        val sharedPreferences: SharedPreferences =
            view.context.getSharedPreferences("user", MODE_PRIVATE)
        val userID = sharedPreferences.getString("userID", "")
        if (userID != null) {
            UserProfile.getUser(userID.toInt())
        };
        setUpObserver()
        // Inflate the layout for this fragment
        return view
    }
    private fun setLocate(Lang:String){
        val locale= Locale(Lang)
        Locale.setDefault(locale)
        val config= Configuration()
        config.locale=locale
        view.context.resources.updateConfiguration(config,view.context.resources.displayMetrics)

        val editor=view.context.getSharedPreferences("Setting", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang",Lang)
        editor.apply()
    }
    private fun loadLocate(view:View){
        val sharedPreference=view.context.getSharedPreferences("Setting", Activity.MODE_PRIVATE)
        val language=sharedPreference.getString("My_Lang","")
        if (language != null) {
            setLocate(language)
        }
    }
    private fun showChangeLang(view:View){
        val listItems= arrayOf("Tiếng Việt","English")
        val mBuilder=AlertDialog.Builder(view.context)
        mBuilder.setTitle("Chọn ngôn ngữ")
        mBuilder.setSingleChoiceItems(listItems,-1){
            dialog,which->
            if (which==0){
                setLocate("vn")
                requireActivity().recreate()

            }
            else {
                setLocate("en")
                requireActivity().recreate()
            }

        dialog.dismiss() // dismiss the dialog after the user selects an option
        }
        val mDialog = mBuilder.create() // create the AlertDialog
        mDialog.show() // show the AlertDialog
    }
    private fun initUI(view: View) {
        profile_name = view.findViewById(R.id.profile_name)
        profile_phone = view.findViewById(R.id.profile_phone)
        route_editprofile=view.findViewById(R.id.route_editprofile)
        route_changepassword=view.findViewById(R.id.route_changepassword)
        route_changelanguage=view.findViewById(R.id.route_changelanguage)
        route_introstore=view.findViewById(R.id.route_introstore)
        route_listpromotion=view.findViewById(R.id.route_listpromotion)
        button_logout = view.findViewById(R.id.button_logout)

        route_changelanguage.setOnClickListener {
            showChangeLang(view)
        }
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
            startActivity(intent)
        }

    }
    private fun setUpObserver() {
        UserProfile.user.observe(viewLifecycleOwner) {
            profile_name.text = it.getName()
            profile_phone.text = it.getPhone()
        }
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
            preferences.edit().remove("userID").apply()

            //---
            //auth.signOut()

            val intent = Intent(
                view.context,
                Login::class.java
            )
            startActivity(intent)
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
