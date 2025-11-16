package com.example.a19prac

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    // Контракт для получения результата из SecondActivity
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val returnedData = data?.getStringExtra("returned_data")
            resultTextView.text = "Получены данные: $returnedData"
            Toast.makeText(this, "Данные успешно получены!", Toast.LENGTH_SHORT).show()
        }
    }

    // Контракт для фотографии
    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            Toast.makeText(this, "Фото сделано!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Кнопка для задания 1 - простое открытие SecondActivity
        findViewById<Button>(R.id.btnOpenSecondActivity).setOnClickListener {
            openSecondActivity()
        }

        // Кнопка для задания 2 - передача данных в SecondActivity
        findViewById<Button>(R.id.btnSendData).setOnClickListener {
            sendDataToSecondActivity()
        }

        // Кнопка для задания 3 - получение данных из SecondActivity
        findViewById<Button>(R.id.btnGetResult).setOnClickListener {
            getResultFromSecondActivity()
        }

        // Кнопка для задания 4 - передача объекта через Parcelable
        findViewById<Button>(R.id.btnSendObject).setOnClickListener {
            sendParcelableObject()
        }

        // Кнопка для задания 5 - открытие ссылки в браузере
        findViewById<Button>(R.id.btnOpenBrowser).setOnClickListener {
            openBrowser()
        }

        // Кнопка для задания 6 - открытие камеры
        findViewById<Button>(R.id.btnOpenCamera).setOnClickListener {
            openCamera()
        }
    }

    // Задание 1: Простое открытие SecondActivity
    private fun openSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    // Задание 2: Передача данных в SecondActivity
    private fun sendDataToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("message", "Привет из MainActivity!")
        intent.putExtra("number", 42)
        startActivity(intent)
    }

    // Задание 3: Получение результата из SecondActivity
    private fun getResultFromSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        resultLauncher.launch(intent)
    }

    // Задание 4: Передача объекта через Parcelable
    private fun sendParcelableObject() {
        val user = User("Иван Иванов", "ivan@example.com", 25)
        val intent = Intent(this, SecondActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("user", user)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    // Задание 5: Открытие ссылки в браузере
    private fun openBrowser() {
        val webpage = Uri.parse("https://www.google.com")
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
    }

    // Задание 6: Открытие камеры для фото
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureLauncher.launch(null)
    }
}