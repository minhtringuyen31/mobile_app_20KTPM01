package com.example.myapplication.pages.activities.user

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle

import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.R

import com.example.myapplication.modals.LoginRequest
import com.example.myapplication.socket.SocketHandler
import com.example.myapplication.utils.Status
import com.example.myapplication.viewmodels.authen.LoginViewModel
import com.example.myapplication.viewmodels.authen.SignupViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.socket.client.Socket


class Login : AppCompatActivity() {
    private lateinit var buttonLogin: Button
    private lateinit var routeSignUp: TextView
    private lateinit var login_phone: EditText
    private lateinit var login_pass: EditText
    private lateinit var loginViewModel: LoginViewModel;
    private lateinit var loginGG : ImageView
    private lateinit var gso:GoogleSignInOptions
    private lateinit var gsc:GoogleSignInClient
    private lateinit var signupViewModel: SignupViewModel
    private var account:GoogleSignInAccount ? = null
    private lateinit var mSocket:Socket

//    verride fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        ZaloPaySDK.getInstance().onResult(intent)
//    }o
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonLogin = findViewById(R.id.button_login)
        routeSignUp = findViewById(R.id.route_signup)
        login_phone = findViewById(R.id.login_phone)
        login_pass = findViewById(R.id.login_pass)
        loginGG = findViewById(R.id.login_gg)



        SocketHandler.setSocket()
        SocketHandler.establishConnection()
        mSocket = SocketHandler.getSocket()

//        mSocket.on("send-data") { args ->
//            if (args[0] != null) {
//                val counter = args[0]
//                println(counter)
//
//            }
//        }





         gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
             .requestIdToken("315513977204-r4d598sk0sv9fifrhefveulu7ksi8fsg.apps.googleusercontent.com")
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        gsc = GoogleSignIn.getClient(this, gso);
         account = GoogleSignIn.getLastSignedInAccount(this)
        if(account!=null){

            gotoHomePage();


        }
        loginGG.setOnClickListener {
           gotoSignIn()

        }



        buttonLogin.setOnClickListener {
            val loginRequest =
                LoginRequest(login_phone.text.toString(), login_pass.text.toString(), 0);
            loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
            loginViewModel.loginUser(loginRequest);
            loginViewModel.statusLogin.observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {

                        Status.SUCCESS -> {
                            Toast.makeText(this,"Đăng nhập thành công!", Toast.LENGTH_LONG).show()
                            val intent = Intent(
                                this,
                                MainActivity::class.java
                            )
                            intent.putExtra("status","1")
                            startActivity(intent)
                            val sharedPreferences =
                                getSharedPreferences("user", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("userID", resource.data?.getUserID().toString())
                            editor.apply()
                        }
                        Status.ERROR -> {

                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            Toast.makeText(this, "Dang doc database", Toast.LENGTH_LONG).show()
                        }
                        else -> {

                        }
                    }
                }
            })

        }
            routeSignUp.setOnClickListener {
                val intent = Intent(
                    this,
                    Signup::class.java
                )
                startActivity(intent)
            }

        }
        fun gotoSignIn(){
            val signInIntent: Intent = gsc.getSignInIntent()
            startActivityForResult(signInIntent, 1000)

        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1000) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val infor= task.getResult(ApiException::class.java)
                print(infor.idToken)
                loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
                infor.idToken?.let {
                    val token= it;
                    loginViewModel.signIn(token)
                    loginViewModel.statusLogin.observe(this){
                        it?.let { resource ->
                            when (resource.status) {

                                Status.SUCCESS -> {
                                    Toast.makeText(this,"Đăng nhập thành công!", Toast.LENGTH_LONG).show()
                                    val intent = Intent(
                                        this,
                                        MainActivity::class.java
                                    )
                                    intent.putExtra("status","1")
                                    startActivity(intent)
                                    val sharedPreferences =
                                        getSharedPreferences("user", MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putString("userID", resource.data?.getUserID().toString())
                                    editor.apply()
                                    mSocket.emit("login",resource.data?.getUserID().toString())


                                }
                                Status.ERROR -> {

                                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                                }
                                Status.LOADING -> {
                                    Toast.makeText(this, "Dang doc database", Toast.LENGTH_LONG).show()
                                }
                                else -> {

                                }
                            }
                        }



                    }


                }
                Toast.makeText(this,"Đăng nhập thành công!", Toast.LENGTH_LONG).show()
                gotoHomePage()

            }catch (e:Exception){
                println(e.message)
                Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun gotoHomePage(){
        val intent = Intent(
            this,
            MainActivity::class.java
        )
        intent.putExtra("status","1")
        startActivity(intent)
    }

    }