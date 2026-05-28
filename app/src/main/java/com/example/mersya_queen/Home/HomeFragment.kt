package com.example.mersya_queen.Home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mersya_queen.Home.tugas4.ProfilAplikasiActivity
import com.example.mersya_queen.Home.tugas4.ProfilPengembangActivity
import com.example.mersya_queen.Home.tugas9.DataWarga.DataWargaFragment
import com.example.mersya_queen.LoginActivity
import com.example.mersya_queen.MainActivity
import com.example.mersya_queen.R
import com.example.mersya_queen.databinding.FragmentHomeBinding
import com.example.mersya_queen.tugas5.WebViewActivity
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Mersya Queen"

        // Menampilkan username
        val username = arguments?.getString("EXTRA_USERNAME") ?: "Pengguna"
        binding.tvNamaUser.text = username

        // 1. Navigasi ke Activity yang sudah ada
        binding.cardBangunRuang.setOnClickListener { pindahHalaman(MainActivity::class.java) }
        binding.cardProfilAplikasi.setOnClickListener { pindahHalaman(ProfilAplikasiActivity::class.java) }
        binding.cardProfilPengembang.setOnClickListener { pindahHalaman(ProfilPengembangActivity::class.java) }
        
        // 2. Navigasi ke DataWargaFragment (Fragment Baru)
        binding.cardDataWarga.setOnClickListener {
            val fragment = DataWargaFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment) // Ganti R.id.fragment_container dengan ID container di activity_base/main Anda
                .addToBackStack(null)
                .commit()
        }

        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun pindahHalaman(targetActivity: Class<*>) {
        val intent = Intent(requireContext(), targetActivity)
        startActivity(intent)
    }

    private fun showLogoutConfirmation() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Konfirmasi Logout")
        builder.setMessage("Apakah Anda yakin ingin logout?")
        builder.setPositiveButton("Iya") { _, _ ->
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
        builder.setNegativeButton("Tidak") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}