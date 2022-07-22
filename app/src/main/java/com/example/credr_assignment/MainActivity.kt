package com.example.credr_assignment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


const val RC_SIGN_IN = 123

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val google : TextView = findViewById(R.id.google)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
       val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id

        }

        val eamil : EditText = findViewById(R.id.emailAddress)
        val pass : EditText = findViewById(R.id.editTextTextPassword)
        val sign : Button = findViewById(R.id.button)
        val correct : ImageView = findViewById(R.id.correct)
        val sinup : TextView = findViewById(R.id.textView2)
        sinup.setOnClickListener{
            //startActivity(Intent(this))
            val intent = Intent(this,SignUpPage::class.java)
            startActivity(intent)
        }

        eamil.addTextChangedListener(object : TextWatcher{
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
            if(TextUtils.isEmpty(pass.text.toString())&&TextUtils.isEmpty(eamil.text.toString())){
                Toast.makeText(this@MainActivity, "please enter your Credentials properly", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Successful", Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }


    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            //updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)

        }
    }
}