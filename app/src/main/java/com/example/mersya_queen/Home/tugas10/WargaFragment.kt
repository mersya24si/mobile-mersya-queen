package com.example.mersya_queen.Home.tugas10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mersya_queen.databinding.FragmentWargaBinding

class WargaFragment : Fragment() {

    private var _binding: FragmentWargaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWargaBinding.inflate(inflater, container, false)

        val list = listOf(
            WargaModel("Budi", "RT 01", 30),
            WargaModel("Siti", "RT 02", 25),
            WargaModel("Andi", "RT 03", 40)
        )

        binding.rvWarga.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWarga.adapter = WargaAdapter(list)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}