package com.example.bootcampproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth


class LoginAcitvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_acitvity)

        val password = findViewById<EditText>(R.id.password)
        val passConfirm = findViewById<EditText>(R.id.passConfirm)
        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val logEmail = findViewById<EditText>(R.id.logEmail)
        val logPass = findViewById<EditText>(R.id.logPass)
        val alreadyHave_acc = findViewById<TextView>(R.id.alreadyHave_acc)
        val sigunp_layout = findViewById<LinearLayout>(R.id.signUp_layout)
        val login_layout = findViewById<LinearLayout>(R.id.login_layout)
        val dont_have_acc = findViewById<TextView>(R.id.dont_have_acc)
        val register_acc = findViewById<TextView>(R.id.register_acc)
        val loginTxt = findViewById<TextView>(R.id.login_acc)
        // Assuming you have a Button for triggering the login process
        val loginButton = findViewById<AppCompatButton>(R.id.login_button)
        val signup_button = findViewById<AppCompatButton>(R.id.signup_button)
        val mAuth = FirebaseAuth.getInstance()

        signup_button.setOnClickListener {
            val sEmail = email.text.toString();
            val sPass = password.text.toString();
            val sPassConfirm = passConfirm.text.toString();
            val sName = name.text.toString();

            if (sName.isNotEmpty() && sEmail.isNotEmpty() && sPass.isNotEmpty() && sPassConfirm.isNotEmpty()) {
                if (sPass == sPassConfirm) {

                    mAuth.createUserWithEmailAndPassword(sEmail, sPass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                .show();
                        }
                    }

                } else {
                    Toast.makeText(this, "Password doesn't match.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Fill Empty Fields.", Toast.LENGTH_SHORT).show();
            }
        }


        loginButton.setOnClickListener {
            val lEmail = logEmail.text.toString();
            val lPass = logPass.text.toString();

            if (lEmail.isNotEmpty() && lPass.isNotEmpty()) {

                mAuth.signInWithEmailAndPassword(lEmail, lPass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        login_layout.visibility = View.GONE
                        sigunp_layout.visibility = View.VISIBLE
                        alreadyHave_acc.visibility = View.VISIBLE
                        dont_have_acc.visibility = View.GONE
                        register_acc.visibility = View.GONE
                        loginTxt.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(this, "Fill Empty Fields.", Toast.LENGTH_SHORT).show();
            }
        }


        alreadyHave_acc.setOnClickListener {
            sigunp_layout.visibility = View.GONE
            login_layout.visibility = View.VISIBLE
            alreadyHave_acc.visibility = View.GONE
            dont_have_acc.visibility = View.VISIBLE
            register_acc.visibility = View.VISIBLE
            loginTxt.visibility = View.GONE
        }

        loginTxt.setOnClickListener {
            sigunp_layout.visibility = View.GONE
            login_layout.visibility = View.VISIBLE
            alreadyHave_acc.visibility = View.GONE
            dont_have_acc.visibility = View.VISIBLE
            register_acc.visibility = View.VISIBLE
            loginTxt.visibility = View.GONE
        }

        dont_have_acc.setOnClickListener {
            login_layout.visibility = View.GONE
            sigunp_layout.visibility = View.VISIBLE
            alreadyHave_acc.visibility = View.VISIBLE
            dont_have_acc.visibility = View.GONE
            register_acc.visibility = View.GONE
            loginTxt.visibility = View.VISIBLE
        }

        register_acc.setOnClickListener {
            login_layout.visibility = View.GONE
            sigunp_layout.visibility = View.VISIBLE
            alreadyHave_acc.visibility = View.VISIBLE
            dont_have_acc.visibility = View.GONE
            register_acc.visibility = View.GONE
            loginTxt.visibility = View.VISIBLE
        }
    }

}


/*

 */