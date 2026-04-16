package com.example.mersya_queen.tugas3 // Sesuaikan dengan nama package Anda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mersya_queen.databinding.ActivityWelcomeBinding // Sesuaikan dengan nama package Anda

class WelcomeActivity : AppCompatActivity() {

    // Deklarasi ViewBinding
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data username yang dikirim dari LoginActivity
        // Jika data kosong/null, nilai defaultnya adalah "Pengguna"
        val username = intent.getStringExtra("EXTRA_USERNAME") ?: "Pengguna"

        // Menampilkan username ke TextView
        binding.tvNamaUser.text = username

        // Aksi ketika tombol Logout diklik
        binding.btnLogout.setOnClickListener {
            // Kembali ke halaman Login
            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)

            // Flag ini berguna untuk menghapus history Activity (WelcomeActivity) dari stack
            // Sehingga user tidak bisa kembali ke halaman ini dengan tombol "Back" di HP
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}