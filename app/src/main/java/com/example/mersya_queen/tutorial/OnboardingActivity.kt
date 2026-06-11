package com.example.mersya_queen.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mersya_queen.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TutorialFragmentAdapter(this)
        binding.tutorialMessageViewPager.adapter = adapter

        // Menghubungkan DotsIndicator ke ViewPager2
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}