package com.example.mersya_queen.Home.tugas10.Dokumen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mersya_queen.databinding.FragmentDokumenBinding

class DokumenFragment : Fragment() {

    private var _binding: FragmentDokumenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDokumenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar Navigation
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Data Dummy Dokumen
        val listDokumen = listOf(
            DokumenModel(1, "Laporan Keuangan Q3", "Dokumen berisi rincian arus kas periode Juli - September.", "15 Okt 2023", android.R.drawable.ic_menu_agenda),
            DokumenModel(2, "Sertifikat Pelatihan", "Sertifikat kelulusan pelatihan Senior Android Developer.", "12 Sep 2023", android.R.drawable.ic_menu_camera),
            DokumenModel(3, "Kontrak Kerjasama", "Dokumen kontrak antara Mersya Queen dengan vendor IT.", "05 Agu 2023", android.R.drawable.ic_menu_edit),
            DokumenModel(4, "Panduan Aplikasi", "Buku manual penggunaan sistem internal versi 2.0.", "20 Jul 2023", android.R.drawable.ic_menu_info_details),
            DokumenModel(5, "Izin Domisili", "Surat keterangan izin domisili perusahaan tahun 2023.", "10 Jan 2023", android.R.drawable.ic_menu_myplaces)
        )

        // Inisialisasi Adapter dan LayoutManager
        val adapter = DokumenAdapter(listDokumen)
        binding.rvDokumen.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}