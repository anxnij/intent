package com.example.a19prac

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        val btnReturnWithResult = findViewById<Button>(R.id.btnReturnWithResult)

        // Проверяем, есть ли переданные данные
        val message = intent.getStringExtra("message")
        val number = intent.getIntExtra("number", -1)

        // Проверяем, передан ли Parcelable объект
        val user = intent.getParcelableExtra<User>("user")

        val displayText = buildString {
            if (!message.isNullOrEmpty()) {
                append("Сообщение: $message\n")
            }
            if (number != -1) {
                append("Число: $number\n")
            }
            if (user != null) {
                append("Пользователь: ${user.name}, ${user.email}, ${user.age} лет\n")
            }
            if (message.isNullOrEmpty() && number == -1 && user == null) {
                append("Данные не переданы")
            }
        }

        dataTextView.text = displayText

        // Если данные были переданы, показываем Toast
        if (!message.isNullOrEmpty()) {
            Toast.makeText(this, "Данные успешно получены!", Toast.LENGTH_SHORT).show()
        }

        // Кнопка для простого возврата
        btnReturn.setOnClickListener {
            finish()
        }

        // Кнопка для возврата с результатом
        btnReturnWithResult.setOnClickListener {
            returnWithResult()
        }
    }

    private fun returnWithResult() {
        val returnIntent = Intent()
        returnIntent.putExtra("returned_data", "Данные из SecondActivity!")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}