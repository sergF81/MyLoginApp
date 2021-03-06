package com.example.myloginapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squareup.picasso.Picasso

class SecondActivity : AppCompatActivity() {
    var gso: GoogleSignInOptions? = null
    var gsc: GoogleSignInClient? = null
    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var buttonSignOut: Button
    lateinit var imageViewPhoto: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        imageViewPhoto = findViewById(R.id.imageViewPhoto)
        buttonSignOut = findViewById(R.id.buttonSignOut)
        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().build()
        gsc = GoogleSignIn.getClient(this, gso!!)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            var personName = acct.displayName
            var personEmail = acct.email
            var personPhoto = acct.photoUrl
            name.setText(personName)
            email.setText(personEmail)

            Picasso.with(this)
            .load(personPhoto)
            .into(imageViewPhoto)

            println(personName)
            println(personPhoto)

        }
        buttonSignOut.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                signOut()
            }
        })
    }

    fun signOut() {
        gsc!!.signOut().addOnCompleteListener {
            finish()
            startActivity(Intent(this@SecondActivity, MainActivity::class.java))
        }
    }
}