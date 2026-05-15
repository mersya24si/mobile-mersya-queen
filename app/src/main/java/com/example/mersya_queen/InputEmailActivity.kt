package com.example.mersya_queen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InputEmailActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_input_email)

        etEmail = findViewById(R.id.etEmail)
        btnNext = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {

            val email = etEmail.text.toString().trim()

            when {

                email.isEmpty() -> {

                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("Email tidak boleh kosong")
                        .setPositiveButton("OK", null)
                        .show()
                }

                !email.endsWith("@gmail.com") -> {

                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("Email harus menggunakan @gmail.com")
                        .setPositiveButton("OK", null)
                        .show()
                }

                else -> {

                    val intent =
                        Intent(this, RegisterActivity::class.java)

                    intent.putExtra("email", email)

                    startActivity(intent)
                }
            }
        }
    }
}