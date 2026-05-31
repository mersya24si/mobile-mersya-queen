package com.example.mersya_queen.Home.tugas10.Dokumen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mersya_queen.databinding.ItemDokumenBinding

class DokumenAdapter(private val listDokumen: List<DokumenModel>) :
    RecyclerView.Adapter<DokumenAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDokumenBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDokumenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listDokumen[position]
        holder.binding.apply {
            tvNamaDokumen.text = item.nama
            tvDeskripsiDokumen.text = item.deskripsi
            tvTanggalDokumen.text = item.tanggal
            ivDokumen.setImageResource(item.imageResId)
        }
    }

    override fun getItemCount(): Int = listDokumen.size
}