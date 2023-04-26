package com.example.myapplication.pages.activities.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.modals.EditInfoRequest
import com.example.myapplication.utils.Status
import com.example.myapplication.viewmodels.EditInfoViewModel

class EditProfile : AppCompatActivity() {
    private lateinit var button_saveedit:Button;
    private lateinit var button_canceledit:Button;
    private lateinit var edit_name:EditText;
    private lateinit var edit_email:EditText;
    private lateinit var edit_gender:EditText;
    private lateinit var  edit_dob:EditText;
    private lateinit var edit_address:EditText;
    private lateinit var editInfoViewModel: EditInfoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        button_canceledit=findViewById(R.id.button_canceledit)
        button_saveedit=findViewById(R.id.button_saveedit)
        edit_name=findViewById(R.id.edit_name)
        edit_email=findViewById(R.id.edit_email)
        edit_gender=findViewById(R.id.edit_gender)
        edit_dob=findViewById(R.id.edit_dob)
        edit_address=findViewById(R.id.edit_address)



        button_saveedit.setOnClickListener {
            // {  name,email,gender,date_of_birth,address }
            val editInfoRequest=
                EditInfoRequest(edit_name.text.toString(),
                                edit_email.text.toString(),
                                edit_gender.text.toString(),
                                edit_dob.text.toString(),
                                edit_address.text.toString())

            editInfoViewModel= ViewModelProvider(this)[EditInfoViewModel::class.java]
            editInfoViewModel.EditInfo(22,editInfoRequest) // phai tim duoc id cua nguoi dung dang dang nhap
            editInfoViewModel.editInfo.observe(this, Observer{
                it?.let { resource ->
                    when (resource.status) {

                        Status.SUCCESS -> {
                            Toast.makeText(this,"Cập nhật thành công!", Toast.LENGTH_LONG).show()
                            // CHUYEN HUONG VE FRAGMENT OTHERS
//                            val fragment = Others()
//                            val fragmentManager: FragmentManager = supportFragmentManager
//                            val fragmentTransaction: FragmentTransaction =
//                                fragmentManager.beginTransaction()
//                            fragmentTransaction.add(R.id.flFragment, fragment)
//                            fragmentTransaction.commit()
                        }
                        Status.ERROR -> {

                            Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
                        }
                        else -> {

                        }
                    }
                }

            })
        }

        button_canceledit.setOnClickListener {
            // chuyen huong ve fragment Others
        }




    }
}