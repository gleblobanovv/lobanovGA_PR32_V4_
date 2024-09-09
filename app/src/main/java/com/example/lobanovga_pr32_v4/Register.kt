package com.example.lobanovga_pr32_v4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val login = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)
        val button = findViewById<Button>(R.id.button)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        button.setOnClickListener {
            val loginText = login.text.toString()
            val passwordText = password.text.toString()

            val savedLogin = sharedPreferences.getString("LOGIN", null)
            val savedPassword = sharedPreferences.getString("PASSWORD", null)

            if (loginText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
            } else {
                if (savedLogin == null && savedPassword == null) {
                    if (loginText == "ects" && passwordText == "ects2023") {
                        saveLoginData(sharedPreferences, loginText, passwordText)
                        val intent = Intent(this, CreditCalc::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show()
                    }
                } else if (loginText == savedLogin && passwordText == savedPassword) {
                    val intent = Intent(this, CreditCalc::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun saveLoginData(
        sharedPreferences: SharedPreferences,
        login: String,
        password: String
    ) {
        val editor = sharedPreferences.edit()
        editor.putString("LOGIN", login)
        editor.putString("PASSWORD", password)
        editor.apply()
    }




}



