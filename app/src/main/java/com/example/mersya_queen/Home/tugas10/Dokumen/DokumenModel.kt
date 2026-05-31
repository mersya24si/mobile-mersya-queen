package com.example.mersya_queen.Home.tugas10.Dokumen

data class DokumenModel(
    val id: Int,
    val nama: String,
    val deskripsi: String,
    val tanggal: String,
    val imageResId: Int // Menggunakan resource ID untuk gambar dummy
)