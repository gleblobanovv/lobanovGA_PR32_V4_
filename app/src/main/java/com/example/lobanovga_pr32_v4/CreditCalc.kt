package com.example.lobanovga_pr32_v4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

class CreditCalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_credit_calc)

        val creditSlider: Slider = findViewById(R.id.slider)
        val sliderValue: TextView = findViewById(R.id.slider_value_text)
        val creditTime: EditText = findViewById(R.id.credit_time)
        val monthMoney: EditText = findViewById(R.id.month_money)
        val buttonRaschet: Button = findViewById(R.id.button_raschet)

        creditSlider.addOnChangeListener { _, value, _ ->
            val sum = value.toInt()
            sliderValue.text = "$sum"
        }

        buttonRaschet.setOnClickListener {
            val creditSliderValue = creditSlider.value.toInt() + 30000
            val creditOfTime = creditTime.text.toString().toIntOrNull()

            if (creditOfTime != null) {
                val monthMoneyValue = monthMoney.text.toString().toDoubleOrNull() ?: 0.0

                val intent = Intent(this, Credit::class.java)
                intent.putExtra("creditAmount", creditSliderValue)
                intent.putExtra("creditTerm", creditOfTime)
                intent.putExtra("monthMoney", monthMoneyValue)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите срок кредита", Toast.LENGTH_LONG).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}