package com.example.mersya_queen.Home.tugas10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mersya_queen.Home.tugas10.Dokumen.DokumenFragment
import com.example.mersya_queen.Home.tugas9.DataWarga.DataWargaFragment

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 2

    // Menentukan Fragment mana yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DataWargaFragment()
            1 -> DokumenFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}