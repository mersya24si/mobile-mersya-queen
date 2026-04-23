package com.example.mersya_queen.tugas4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mersya_queen.databinding.ActivityProfilPengembangBinding

class ProfilPengembangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilPengembangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilPengembangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judul = intent.getStringExtra("EXTRA_JUDUL")
        val desc = intent.getStringExtra("EXTRA_DESC")

        //Tampilkan data tersebut (misal digabung dalam satu TextView)
        binding.tvDataDashboard.text = "Dashboard Info: $judul\n$desc"

        //Tombol Kembali
        binding.btnKembali.setOnClickListener {
            finish()
        }
    }
}