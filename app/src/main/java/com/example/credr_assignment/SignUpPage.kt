package com.example.credr_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible

class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        val eamil : EditText = findViewById(R.id.editTextTextEmailAddress)
        val pass : EditText = findViewById(R.id.editTextTextPassword)
        val sign : Button = findViewById(R.id.button)
        val correct : ImageView = findViewById(R.id.correct)
        eamil.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(eamil.text.toString()).matches()){
                    correct.isVisible = true
                }else{
                    eamil.setError("Invalid")
                    correct.isVisible = false
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        sign.setOnClickListener{
            if(TextUtils.isEmpty(pass.text.toString())&& TextUtils.isEmpty(eamil.text.toString())){
                Toast.makeText(this@SignUpPage, "please enter your Credentials properly", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@SignUpPage, "Successful", Toast.LENGTH_SHORT).show()
            }
        }
    }
}