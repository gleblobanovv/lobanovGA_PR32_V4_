package com.example.lobanovga_pr32_v4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Credit : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_credit)

        val registerButton: Button = findViewById(R.id.register_button)
        val sumCredit:EditText=findViewById(R.id.sum_credit)
        val creditTime:EditText=findViewById(R.id.credit_time)
        val monthMoney:EditText=findViewById(R.id.month_money)

        val creditAmount = intent.getIntExtra("creditAmount", 0)
        val creditTerm = intent.getIntExtra("creditTerm", 0)
        val monthMoneyValue = intent.getDoubleExtra("monthMoney", 0.0)

        if (creditAmount > 0 && creditTerm > 0) {

            val calculatedMoney = mounthPay(creditAmount, creditTerm)

            sumCredit.setText("Сумма кредита: $creditAmount")
            creditTime.setText("Срок кредита: $creditTerm")
            monthMoney.setText("Ежемесяный платеж: $calculatedMoney")

            Toast.makeText(this, "Ежемесячный платёж: $calculatedMoney тыс. руб.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Ошибка в данных", Toast.LENGTH_LONG).show()
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
    private fun mounthPay(amount: Int, term: Int): Double {
        val s1 = amount / term + amount * 0.059 // до 1 года
            return when {
                term <= 12 -> s1
                term <= 24 -> s1 + (amount - s1 * 12) * 0.051 // до 2 лет
                else -> s1 + (amount - s1 * term) * 0.042 // больше 2 лет
            }
    }
