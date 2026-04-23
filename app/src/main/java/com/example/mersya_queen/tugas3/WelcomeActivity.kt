package com.example.mersya_queen.tugas3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mersya_queen.MainActivity
import com.example.mersya_queen.tugas4.ProfilAplikasiActivity
import com.example.mersya_queen.databinding.ActivityWelcomeBinding
import com.example.mersya_queen.tugas4.ProfilPengembangActivity
import com.google.android.material.snackbar.Snackbar


class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Mengambil data username dari LoginActivity
        val username = intent.getStringExtra("EXTRA_USERNAME") ?: "Pengguna"
        binding.tvNamaUser.text = username

        // 2. Tombol Bangun Ruang
        binding.btnBangunRuang.setOnClickListener {
            pindahHalaman(MainActivity::class.java)
        }

        // 3. Tombol Profil Aplikasi
        binding.btnProfilAplikasi.setOnClickListener {
            pindahHalaman(ProfilAplikasiActivity::class.java)
        }

        // 4. Tombol Profil Pengembang
        binding.btnProfilPengembang.setOnClickListener {
            pindahHalaman(ProfilPengembangActivity::class.java)
        }

        // 5. Tombol Logout dengan Konfirmasi Dialog & SnackBar
        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    /**
     * Fungsi Helper untuk pindah halaman sambil membawa data judul dan deskripsi
     */
    private fun pindahHalaman(targetActivity: Class<*>) {
        val intent = Intent(this, targetActivity)

        // Mengambil teks dari TextView yang ada di halaman utama
        val judul = binding.tvWelcomeHeader.text.toString()
        val deskripsi = binding.tvDescription.text.toString()

        // Mengirim data ke activity tujuan
        intent.putExtra("EXTRA_JUDUL", judul)
        intent.putExtra("EXTRA_DESC", deskripsi)

        startActivity(intent)
    }

    /**
     * Fungsi untuk menampilkan konfirmasi logout
     */
    private fun showLogoutConfirmation() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Logout")
        builder.setMessage("Apakah Anda yakin ingin logout?")

        builder.setPositiveButton("Iya") { _, _ ->
            // Jika Iya, pindah ke LoginActivity dan bersihkan stack
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("Tidak") { dialog, _ ->
            // Jika Tidak, tutup dialog dan munculkan SnackBar
            dialog.dismiss()
            Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}