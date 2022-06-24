package org.d3if4062.assessment3.ui.objek_non_ppn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if4062.assessment3.R
import org.d3if4062.assessment3.databinding.ItemObjekNonPpnBinding
import org.d3if4062.assessment3.model.ObjekNonPpn
import org.d3if4062.assessment3.network.ObjekNonPpnApi

class ObjekNonPpnAdapter : RecyclerView.Adapter<ObjekNonPpnAdapter.ViewHolder>() {
    private val data = mutableListOf<ObjekNonPpn>()
    fun updateData(newData: List<ObjekNonPpn>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ItemObjekNonPpnBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(objekNonPpn: ObjekNonPpn) = with(binding) {
            nama.text = objekNonPpn.nama
            Glide.with(imageView.context)
                .load(ObjekNonPpnApi.getObjekNonPpnUrl(objekNonPpn.gambar))
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemObjekNonPpnBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}