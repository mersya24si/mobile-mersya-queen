package com.example.mersya_queen // Sesuaikan dengan folder package Anda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mersya_queen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Deklarasi View Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inisialisasi Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Menangkap data yang dikirim dari WelcomeActivity (Dashboard)
        val judulDariMain = intent.getStringExtra("EXTRA_JUDUL")
        val descDariMain = intent.getStringExtra("EXTRA_DESC")

        // Opsional: Jika Anda punya TextView untuk menampilkan info ini di layout
        // binding.tvInfoHalaman.text = "$judulDariMain\n$descDariMain"

        // --- Logika Hitung Luas Persegi Panjang (P x L) ---
        binding.btnHitungLuas.setOnClickListener {
            val panjang = binding.etPanjang.text.toString().toDoubleOrNull() ?: 0.0
            val lebar = binding.etLebar.text.toString().toDoubleOrNull() ?: 0.0
            val hasilLuas = panjang * lebar
            binding.tvHasilLuas.text = "Hasil Luas: $hasilLuas"
        }

        // --- Logika Hitung Volume Kubus (S x S x S) ---
        binding.btnHitungVolume.setOnClickListener {
            val sisi = binding.etSisi.text.toString().toDoubleOrNull() ?: 0.0
            val hasilVolume = sisi * sisi * sisi
            binding.tvHasilVolume.text = "Hasil Volume: $hasilVolume"
        }

        // --- 4. Tombol Kembali ke Dashboard ---
        binding.btnKembali.setOnClickListener {
            // Menutup activity ini dan kembali ke halaman Welcome
            finish()
        }
    }
}