package com.example.mersya_queen.tugas3 // Sesuaikan dengan nama package Anda

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mersya_queen.databinding.ActivityLoginBinding // Sesuaikan dengan nama package Anda

class LoginActivity : AppCompatActivity() {

    // 1. Deklarasi variabel ViewBinding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inisialisasi ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Memberikan aksi ketika tombol Login diklik
        binding.btnLogin.setOnClickListener {
            // Mengambil nilai dari EditText
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi sederhana: Pastikan input tidak kosong
            if (username.isEmpty()) {
                binding.etUsername.error = "Username tidak boleh kosong"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password tidak boleh kosong"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            // 4. Jika terisi, pindah ke WelcomeActivity menggunakan Intent
            val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)

            // (Opsional & Nilai Plus) Membawa data username ke halaman Welcome
            intent.putExtra("EXTRA_USERNAME", username)

            startActivity(intent)

            // Hapus komentar pada baris di bawah ini jika Anda ingin menutup halaman login
            // setelah berhasil masuk, sehingga user tidak bisa menekan tombol 'Back' ke halaman login lagi.
            // finish()
        }
    }
}