package com.example.mersya_queen.Settings

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mersya_queen.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    // Menggunakan View Binding untuk mengakses komponen UI
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar: Menangani tombol navigasi kembali
        binding.toolbarSettings.setNavigationOnClickListener {
            finish() // Menutup activity dan kembali ke halaman sebelumnya
        }

        // Data dummy untuk menu pengaturan
        val settingsMenu = arrayOf(
            "Akun",
            "Notifikasi",
            "Privasi dan Keamanan",
            "Tema Aplikasi",
            "Bantuan & Tentang"
        )

        // Inisialisasi ArrayAdapter dengan layout standar Android
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            settingsMenu
        )

        // Menghubungkan adapter ke ListView
        binding.lvSettings.adapter = adapter

        // Menangani klik pada item ListView
        binding.lvSettings.setOnItemClickListener { _, _, position, _ ->
            val selectedMenu = settingsMenu[position]
            // Menampilkan Toast singkat berisi menu yang diklik
            Toast.makeText(this, "Membuka menu $selectedMenu", Toast.LENGTH_SHORT).show()
        }
    }
}