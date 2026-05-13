package com.example.mersya_queen

import android.content.Context
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
import com.example.mersya_queen.tugas3.WelcomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val TAG = "LoginLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity Dibuat")

        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // SharedPref untuk menyimpan sesi login
        val sharedPrefLogin = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // Logika saat tombol Login ditekan
        binding.btnLogin.setOnClickListener {
            val inputUsername = binding.username.text.toString()
            val inputPassword = binding.password.text.toString()

            // Kondisi 1: username == password (Tidak boleh kosong)
            val kondisiSatu = (inputUsername == inputPassword) && inputUsername.isNotEmpty()

            // Jika memenuhi SALAH SATU kondisi di atas
            if (kondisiSatu) {
                // Simpan sesi login
                sharedPrefLogin.edit {
                    putBoolean("isLogin", true)
                    putString("username", inputUsername)
                }

                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                // Arahkan ke halaman Home (BaseActivity)
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("username", inputUsername)
                startActivity(intent)
                finish() // Tutup halaman login agar tidak bisa di-back

            } else {
                // Jika gagal, tampilkan MaterialAlertDialog
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah, atau belum terdaftar. Silakan coba lagi.")
                    .setPositiveButton("Oke") { dialog, _ ->
                        dialog.dismiss() // Menutup dialog
                    }
                    .show()
            }
        }
    }

    // --- Implementasi Lifecycle Activity ---
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity Mulai Terlihat")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity Dihancurkan")
    }
}
