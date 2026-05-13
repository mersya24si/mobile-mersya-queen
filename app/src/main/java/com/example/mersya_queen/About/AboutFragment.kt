package com.example.mersya_queen.About

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mersya_queen.R
import com.example.mersya_queen.databinding.FragmentAboutBinding
import com.example.mersya_queen.databinding.FragmentHomeBinding

class AboutFragment : Fragment() {


    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }
}