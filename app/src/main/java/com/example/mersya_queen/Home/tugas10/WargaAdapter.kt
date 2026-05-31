package com.example.mersya_queen.Home.tugas10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mersya_queen.R

class WargaAdapter(private val list: List<WargaModel>) :
    RecyclerView.Adapter<WargaAdapter.WargaViewHolder>() {

    class WargaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama = view.findViewById<TextView>(R.id.tvNama)
        val alamat = view.findViewById<TextView>(R.id.tvAlamat)
        val umur = view.findViewById<TextView>(R.id.tvUmur)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WargaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_warga, parent, false)
        return WargaViewHolder(view)
    }

    override fun onBindViewHolder(holder: WargaViewHolder, position: Int) {
        val item = list[position]
        holder.nama.text = item.nama
        holder.alamat.text = item.alamat
        holder.umur.text = item.umur.toString()
    }

    override fun getItemCount() = list.size
}