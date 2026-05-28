package com.example.mersya_queen.Home.tugas9.DataWarga

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mersya_queen.R
import com.example.mersya_queen.databinding.FragmentDataWargaBinding
import com.google.android.material.chip.Chip

class DataWargaFragment : Fragment() {

    private var _binding: FragmentDataWargaBinding? = null
    private val binding get() = _binding!!

    // List data menggunakan model Warga
    private val listWarga = mutableListOf<Warga>()
    private lateinit var wargaAdapter: WargaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataWargaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Perbaikan: Tambahkan listener untuk tombol back di toolbar agar bisa kembali
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        setupListView()
        setupAction()
    }

    private fun setupListView() {
        // Inisialisasi Custom Adapter
        wargaAdapter = WargaAdapter(requireContext(), R.layout.item_warga, listWarga)
        binding.lvWarga.adapter = wargaAdapter

        // Data Dummy Awal (Hanya jika list masih kosong)
        if (listWarga.isEmpty()) {
            listWarga.add(Warga("Budi Santoso", "3201234567890001", "Kepala Keluarga"))
            listWarga.add(Warga("Siti Aminah", "3201234567890002", "Istri"))
        }
        wargaAdapter.notifyDataSetChanged()
    }

    private fun setupAction() {
        binding.btnSimpan.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val nik = binding.etNik.text.toString().trim()
            
            // Mengambil status dari Chip yang dipilih
            val selectedChipId = binding.cgStatus.checkedChipId
            val status = if (selectedChipId != -1) {
                binding.root.findViewById<Chip>(selectedChipId).text.toString()
            } else ""

            // Validasi Input
            var isValid = true
            
            if (nama.isEmpty()) {
                binding.tilNama.error = "Nama tidak boleh kosong"
                isValid = false
            } else {
                binding.tilNama.error = null
            }

            if (nik.isEmpty()) {
                binding.tilNik.error = "NIK tidak boleh kosong"
                isValid = false
            } else if (nik.length < 16) {
                binding.tilNik.error = "NIK harus 16 digit"
                isValid = false
            } else {
                binding.tilNik.error = null
            }

            if (status.isEmpty()) {
                Toast.makeText(requireContext(), "Pilih status hubungan keluarga", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (isValid) {
                // Simpan ke List dan Update Adapter
                val wargaBaru = Warga(nama, nik, status)
                listWarga.add(wargaBaru)
                wargaAdapter.notifyDataSetChanged()

                // Reset Form
                clearForm()
                Toast.makeText(requireContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                
                // Scroll ke data terbaru
                binding.lvWarga.smoothScrollToPosition(listWarga.size - 1)
            }
        }
    }

    private fun clearForm() {
        binding.etNama.text?.clear()
        binding.etNik.text?.clear()
        binding.cgStatus.clearCheck()
        binding.tilNama.error = null
        binding.tilNik.error = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
