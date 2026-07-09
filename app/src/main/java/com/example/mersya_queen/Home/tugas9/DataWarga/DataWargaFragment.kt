package com.example.mersya_queen.Home.tugas9.DataWarga

import android.Manifest
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mersya_queen.R
import com.example.mersya_queen.data.AppDatabase
import com.example.mersya_queen.data.entity.WargaEntity
import com.example.mersya_queen.databinding.FragmentDataWargaBinding
import com.example.mersya_queen.utils.NotificationHelper
import com.example.mersya_queen.utils.PermissionHelper
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class DataWargaFragment : Fragment() {

    private var _binding: FragmentDataWargaBinding? = null
    private val binding get() = _binding!!
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    private lateinit var db: AppDatabase
    private val listWarga = mutableListOf<WargaEntity>()
    private lateinit var wargaAdapter: WargaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataWargaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        setupListView()
        setupAction()
        fetchData()
    }

    private fun setupListView() {
        wargaAdapter = WargaAdapter(requireContext(), R.layout.item_warga, listWarga)
        binding.lvWarga.adapter = wargaAdapter
    }

    private fun fetchData() {
        lifecycleScope.launch {
            val data = db.wargaDao().getAll()
            listWarga.clear()
            listWarga.addAll(data)
            wargaAdapter.notifyDataSetChanged()
        }
    }

    private fun setupAction() {
        binding.btnSimpan.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val nik = binding.etNik.text.toString().trim()
            val selectedChipId = binding.cgStatus.checkedChipId
            val status = if (selectedChipId != -1) {
                binding.root.findViewById<Chip>(selectedChipId).text.toString()
            } else ""

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
                lifecycleScope.launch {
                    val wargaBaru = WargaEntity(nama = nama, nik = nik, status = status)
                    db.wargaDao().insert(wargaBaru)
                    
                    fetchData()
                    clearForm()

                    NotificationHelper.showNotification(
                        requireContext(),
                        "Data Warga",
                        "Halo $nama, data warga berhasil ditambahkan",
                        requireActivity().intent
                    )
                    Toast.makeText(requireContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                    
                    binding.lvWarga.smoothScrollToPosition(listWarga.size - 1)
                }
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
