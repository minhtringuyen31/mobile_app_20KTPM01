package com.example.myapplication.pages.activities.user

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.LoginRequest
import com.example.myapplication.utils.Status
import com.example.myapplication.viewmodels.authen.LoginViewModel
import com.example.myapplication.viewmodels.authen.SignupViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//import io.socket.client.Socket


class Login : AppCompatActivity() {
    private lateinit var buttonLogin: Button
    private lateinit var routeSignUp: TextView
    private lateinit var routeForgotPassword: TextView
    private lateinit var login_phone: EditText
    private lateinit var login_pass: EditText
    private lateinit var loginViewModel: LoginViewModel;
    private lateinit var loginGG : ImageView
    private lateinit var gso:GoogleSignInOptions
    private lateinit var gsc:GoogleSignInClient
    private lateinit var signupViewModel: SignupViewModel
    private var account:GoogleSignInAccount ? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonLogin = findViewById(R.id.button_login)
        routeSignUp = findViewById(R.id.route_signup)
        login_phone = findViewById(R.id.login_phone)
        login_pass = findViewById(R.id.login_pass)
        loginGG = findViewById(R.id.login_gg)
        routeForgotPassword=findViewById(R.id.route_forgotpassword)

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
            val preferences: SharedPreferences = this.getSharedPreferences("user", 0)
            val loginRequest =
                LoginRequest(login_phone.text.toString(), login_pass.text.toString(), 0);
            loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
            loginViewModel.loginUser(loginRequest);
            loginViewModel.statusLogin.observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {

                        Status.SUCCESS -> {
                            Toast.makeText(this,"Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                            if(resource.data!!.getRole()==1)
                            {
                                val intent = Intent(
                                    this,
                                    Dashboard::class.java
                                )
                                val sharedPreferences =
                                    getSharedPreferences("user", MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("userID", resource.data.getUserID().toString()).apply()
                                editor.putString("role", "1").apply()
                                startActivity(intent)
                            }
                            else
                            {
                                val sharedPreferences =
                                    getSharedPreferences("user", MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("userID", resource.data.getUserID().toString()).apply()
                                editor.putString("role", "0").apply()

                                val intent = Intent(
                                    this,
                                    MainActivity::class.java
                                )
                                intent.putExtra("status","1")
                                startActivity(intent)
                                finish();
                            }

                        }
                        Status.ERROR -> {
                            if(login_phone.text.toString()==""){
                                Toast.makeText(this, "Không được để trống email", Toast.LENGTH_SHORT).show()

                            }
                            else if(login_pass.text.toString()==""){
                                Toast.makeText(this, "Không được để trống mật khẩu", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                            }

                        }
                        Status.LOADING -> {
                            Toast.makeText(this, "Đang tiến hành", Toast.LENGTH_SHORT).show()
                        }
                        else -> {

                        }
                    }
                }
            })

        }
            routeForgotPassword.setOnClickListener {
                val intent = Intent(
                    this,
                    // Signup::class.java
                    ForgotPassword_Step1::class.java
                   // ListPromotion::class.java
                )
                startActivity(intent)
            }
            routeSignUp.setOnClickListener {
                val intent = Intent(
                    this,
                   Signup::class.java
               //IntroductionStore::class.java
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

                loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
                infor.idToken?.let {
                    val token= it;
                    loginViewModel.signIn(token)
                    loginViewModel.statusLogin.observe(this){
                        it?.let { resource ->
                            when (resource.status) {

                                Status.SUCCESS -> {
                                    Toast.makeText(this,"Đăng nhập thành công!", Toast.LENGTH_LONG).show()

                                    val sharedPreferences =
                                        getSharedPreferences("user", MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putString("userID", resource.data?.getUserID().toString()).apply()
                                    editor.putString("role", "0").apply()

                                        runBlocking {
                                            delay(2000) // Đợi 2 giây

                                        }
                                        val intent = Intent(
                                            this,
                                            MainActivity::class.java
                                        )

                                        intent.putExtra("status","1")
                                        startActivity(intent)
                                        finish();




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

        finish();
    }

    }