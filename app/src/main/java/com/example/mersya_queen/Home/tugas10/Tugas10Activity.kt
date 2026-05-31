package com.example.mersya_queen.Home.tugas10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mersya_queen.R
import com.example.mersya_queen.databinding.ActivityTugas10Binding
import com.google.android.material.tabs.TabLayoutMediator

class Tugas10Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTugas10Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTugas10Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Profil"
                1 -> "Informasi"
                else -> "Warga"
            }
        }.attach()
    }
}