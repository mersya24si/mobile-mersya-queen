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
import com.example.mersya_queen.Home.tugas10.Tugas10Activity
import com.example.mersya_queen.Home.tugas9.DataWarga.DataWargaFragment
import com.example.mersya_queen.LoginActivity
import com.example.mersya_queen.MainActivity
import com.example.mersya_queen.databinding.FragmentHomeBinding

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

        // Toolbar
        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Mersya Queen"

        // Username
        val username = arguments?.getString("EXTRA_USERNAME") ?: "Pengguna"
        binding.tvNamaUser.text = username

        // Navigasi Activity
        binding.cardBangunRuang.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        binding.cardProfilAplikasi.setOnClickListener {
            startActivity(Intent(requireContext(), ProfilAplikasiActivity::class.java))
        }

        binding.cardProfilPengembang.setOnClickListener {
            startActivity(Intent(requireContext(), ProfilPengembangActivity::class.java))
        }

        // 🟢 FIX YANG KAMU MINTA (TUGAS 10)
        binding.cardTugas10.setOnClickListener {
            startActivity(Intent(requireContext(), Tugas10Activity::class.java))
        }

        // Fragment Data Warga lama (biarkan kalau masih dipakai)
        binding.cardDataWarga.setOnClickListener {
            val fragment = DataWargaFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
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