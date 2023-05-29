package com.aiprc.mycareer.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aiprc.mycareer.MainActivity
import com.aiprc.mycareer.R
import com.aiprc.mycareer.Singleton_Class.MySharedPref
import com.aiprc.mycareer.ViewModelFactory.LoginViewModelFactory
import com.aiprc.mycareer.ViewmodelClass.LoginViewModel

class Login_activity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText


    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login_button = findViewById<Button>(R.id.SignInLogin);
        val register_button = findViewById<Button>(R.id.Register);
        email = findViewById(R.id.login_Email)
        password = findViewById(R.id.login_Password)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        login_button.setOnClickListener(View.OnClickListener {
            var email: String = email.text.toString()
            var password: String = password.text.toString()
            if(checkValidation(email,password)){
                val viewModel = ViewModelProviders.of(this,LoginViewModelFactory(applicationContext)).get(LoginViewModel::class.java)
                     viewModel.Login(email,password).observe(this, Observer {
                         if(it.equals("success")){
                             MySharedPref(applicationContext).setPreferences(MySharedPref.login,email)
                             val intent = Intent(this, MainActivity::class.java)
                             startActivity(intent)
                             finish()
                         }
                     })
            }

        })
        register_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Registration_activity::class.java)
            startActivity(intent)
            finish()

        })
    }
    private fun checkValidation(email: String, password: String): Boolean {
         if(email.isEmpty()){
          Toast.makeText(applicationContext,"please enter your email",Toast.LENGTH_SHORT).show()
         }else if(password.isEmpty()){
             Toast.makeText(applicationContext,"please enter your password",Toast.LENGTH_SHORT).show()

         }else{
             return true
         }
        return false
    }
}