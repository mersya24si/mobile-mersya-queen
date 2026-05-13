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
import com.example.mersya_queen.LoginActivity
import com.example.mersya_queen.MainActivity
import com.example.mersya_queen.databinding.FragmentHomeBinding
import com.example.mersya_queen.tugas5.WebViewActivity
import com.google.android.material.snackbar.Snackbar
// Pastikan import class tujuan Anda benar di sini:
// import com.example.mersya_queen.MainActivity
// import com.example.mersya_queen.tugas4.ProfilAplikasiActivity
// import com.example.mersya_queen.tugas4.ProfilPengembangActivity
// import com.example.mersya_queen.WebViewActivity
// import com.example.mersya_queen.LoginActivity

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

        // 1. Setup Toolbar
        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Dashboard"

        // 2. Menangkap username (asumsi dikirim via Bundle ke Fragment)
        val username = arguments?.getString("EXTRA_USERNAME") ?: "Pengguna"
        binding.tvNamaUser.text = username

        // 3. Tombol-tombol navigasi
        binding.btnBangunRuang.setOnClickListener { pindahHalaman(MainActivity::class.java) }
        binding.btnProfilAplikasi.setOnClickListener { pindahHalaman(ProfilAplikasiActivity::class.java) }
        binding.btnProfilPengembang.setOnClickListener { pindahHalaman(ProfilPengembangActivity::class.java) }
        binding.btnWebView.setOnClickListener { pindahHalaman(WebViewActivity::class.java) }

        // 4. Tombol Logout
        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun pindahHalaman(targetActivity: Class<*>) {
        val intent = Intent(requireContext(), targetActivity)
        val judul = binding.tvWelcomeHeader.text.toString()
        val deskripsi = binding.tvDescription.text.toString()

        intent.putExtra("EXTRA_JUDUL", judul)
        intent.putExtra("EXTRA_DESC", deskripsi)
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

        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
            Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
        }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Penting untuk mencegah memory leak di Fragment
    }
}