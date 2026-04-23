package com.example.mersya_queen.tugas4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mersya_queen.R
import com.example.mersya_queen.databinding.ActivityProfilAplikasiBinding

class ProfilAplikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilAplikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilAplikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Menangkap data yang dikirim dari WelcomeActivity
        val judulDariMain = intent.getStringExtra("EXTRA_JUDUL")
        val descDariMain = intent.getStringExtra("EXTRA_DESC")

        // 2. Menampilkan data tersebut ke TextView (Sesuai instruksi tugas)
        binding.tvJudulTerima.text = "Dari Main: $judulDariMain"
        binding.tvDescTerima.text = descDariMain

        // 3. Aksi tombol kembali
        binding.btnKembali.setOnClickListener {
            // finish() akan menutup activity ini dan otomatis kembali ke halaman sebelumnya (Dashboard)
            finish()
        }
    }
}