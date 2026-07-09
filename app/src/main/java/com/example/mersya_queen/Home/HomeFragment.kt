package com.example.mersya_queen.Home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mersya_queen.Home.photo.PhotoAdapter
import com.example.mersya_queen.Home.pertemuan_13.ThirteenthActivity
import com.example.mersya_queen.Home.tugas10.LayananActivity
import com.example.mersya_queen.Home.tugas4.ProfilAplikasiActivity
import com.example.mersya_queen.Home.tugas9.DataWarga.DataWargaFragment
import com.example.mersya_queen.LoginActivity
import com.example.mersya_queen.MainActivity
import com.example.mersya_queen.R
import com.example.mersya_queen.data.api.PhotoApiClient
import com.example.mersya_queen.databinding.FragmentHomeBinding
import com.example.mersya_queen.tugas5.WebViewActivity
import kotlinx.coroutines.launch

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
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = "Mersya Queen"
        }

        binding.tvNamaUser.text = arguments?.getString("EXTRA_USERNAME") ?: "Pengguna"

        // Setup RecyclerView
        binding.rvGallery.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }

        // Navigasi
        binding.cardBangunRuang.setOnClickListener { startActivity(Intent(requireContext(), MainActivity::class.java)) }
        binding.cardProfilAplikasi.setOnClickListener { startActivity(Intent(requireContext(), ProfilAplikasiActivity::class.java)) }
        binding.cardLayanan.setOnClickListener { startActivity(Intent(requireContext(), LayananActivity::class.java)) }
        binding.cardPertemuan13.setOnClickListener { startActivity(Intent(requireContext(), ThirteenthActivity::class.java)) }
        binding.cardWebView.setOnClickListener { startActivity(Intent(requireContext(),
            WebViewActivity::class.java)) }
        binding.cardDataWarga.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DataWargaFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.btnLogout.setOnClickListener { showLogoutConfirmation() }
        binding.btnRefresh.setOnClickListener { loadPhoto() }

        loadPhoto()
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                binding.btnRefresh.isEnabled = false
                Log.d("HomeFragment", "Fetching photos...")
                val photos = PhotoApiClient.apiService.getPhotos()
                Log.d("HomeFragment", "Success: Fetched ${photos.size} photos")
                
                if (photos.isNotEmpty()) {
                    binding.rvGallery.adapter = PhotoAdapter(photos)
                }
            } catch (e: Exception) {
                Log.e("HomeFragment", "Failed to load photos", e)
                Toast.makeText(requireContext(), "Gagal memuat gambar: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.btnRefresh.isEnabled = true
            }
        }
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Iya") { _, _ ->
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
