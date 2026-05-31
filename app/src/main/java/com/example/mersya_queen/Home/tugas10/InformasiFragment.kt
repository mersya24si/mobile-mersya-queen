package com.example.mersya_queen.Home.tugas10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mersya_queen.R
import com.example.mersya_queen.databinding.FragmentInformasiBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InformasiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InformasiFragment : Fragment() {

    private var _binding: FragmentInformasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformasiBinding.inflate(inflater, container, false)

        binding.tvVisi.text = "Menjadi desa maju dan mandiri"
        binding.tvMisi.text = "Meningkatkan pendidikan dan ekonomi masyarakat"
        binding.tvJumlah.text = "Penduduk: 1500 orang"

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}