package com.example.mersya_queen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mersya_queen.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // Tag Logcat
    private val TAG = "LoginLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: Activity Dibuat")

        binding = ActivityLoginBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // SharedPreferences Login
        val sharedPref =
            getSharedPreferences("user_pref", MODE_PRIVATE)

        // =========================
        // LOGIN
        // =========================

        binding.btnLogin.setOnClickListener {

            val username =
                binding.username.text.toString().trim()

            val password =
                binding.password.text.toString().trim()

            // SharedPreferences Register
            val registerPref =
                getSharedPreferences("register_pref", MODE_PRIVATE)

            val savedUsername =
                registerPref.getString("username", "")

            val savedPassword =
                registerPref.getString("password", "")

            // Kondisi login praktikum
            val loginPraktikum =
                username == password &&
                        username.isNotEmpty() &&
                        password.isNotEmpty()

            // Kondisi login dari register
            val loginRegister =
                username == savedUsername &&
                        password == savedPassword

            // Jika salah satu benar
            if (loginPraktikum || loginRegister) {

                sharedPref.edit {

                    putBoolean("isLogin", true)

                    putString("username", username)
                }

                Toast.makeText(
                    this,
                    "Login Berhasil!",
                    Toast.LENGTH_SHORT
                ).show()

                val intent =
                    Intent(this, BaseActivity::class.java)

                intent.putExtra("username", username)

                startActivity(intent)

            } else {

                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal!")
                    .setMessage("Username atau Password salah")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        // =========================
        // REGISTER WITH GMAIL
        // =========================

        binding.btnRegis.setOnClickListener {

            val intent =
                Intent(this, InputEmailActivity::class.java)

            startActivity(intent)
        }
    }

    // =========================
    // LIFECYCLE
    // =========================

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart: Activity Mulai Terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy: Activity Dihancurkan")
    }
}