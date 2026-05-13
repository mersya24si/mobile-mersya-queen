package com.example.mersya_queen.Home.tugas4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar // Pastikan import ini ada
import com.example.mersya_queen.databinding.ActivityProfilAplikasiBinding

class ProfilAplikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilAplikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilAplikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        // Mengambil view toolbar dari binding dan menjadikannya sebagai ActionBar
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        // Menambahkan tombol kembali (arrow) di kiri atas toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Aksi klik pada tombol kembali toolbar
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Menangkap data yang dikirim dari WelcomeActivity
        val judulDariMain = intent.getStringExtra("EXTRA_JUDUL")
        val descDariMain = intent.getStringExtra("EXTRA_DESC")

        // 3. Menampilkan data tersebut
        binding.tvJudulTerima.text = "Dari Main: $judulDariMain"
        binding.tvDescTerima.text = descDariMain

        // 4. Aksi tombol kembali (tombol bawah)
        binding.btnKembali.setOnClickListener {
            finish()
        }
    }
}