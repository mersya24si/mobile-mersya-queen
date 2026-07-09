package com.example.mersya_queen.Home.tugas9.DataWarga

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mersya_queen.data.entity.WargaEntity
import com.example.mersya_queen.databinding.ItemWargaBinding

class WargaAdapter(context: Context, private val resource: Int, private val items: List<WargaEntity>) :
    ArrayAdapter<WargaEntity>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemWargaBinding
        val view: View

        if (convertView == null) {
            binding = ItemWargaBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ItemWargaBinding
            view = convertView
        }

        val item = items[position]
        binding.tvItemNama.text = item.nama
        binding.tvItemNik.text = "NIK: ${item.nik}"
        binding.chipItemStatus.text = item.status

        return view
    }
}
