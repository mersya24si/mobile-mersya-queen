package com.example.mersya_queen.tugas4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.mersya_queen.databinding.ActivityProfilPengembangBinding

class ProfilPengembangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilPengembangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilPengembangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        // Mengaktifkan tombol panah kembali (Back Arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Aksi klik pada tombol kembali
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Menangkap data yang dikirim dari activity sebelumnya
        val judul = intent.getStringExtra("EXTRA_JUDUL")
        val desc = intent.getStringExtra("EXTRA_DESC")

        // 3. Menampilkan data ke TextView
        binding.tvDataDashboard.text = "Dashboard Info: $judul\n$desc"

        // 4. Aksi tombol kembali (Button di bawah)
        binding.btnKembali.setOnClickListener {
            finish()
        }
    }
}