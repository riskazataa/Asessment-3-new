package org.d3if4062.assessment3.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if4062.assessment3.R
import org.d3if4062.assessment3.databinding.ItemHistoriBinding
import org.d3if4062.assessment3.db.PajakEntity
import org.d3if4062.assessment3.model.hitung
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<PajakEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PajakEntity>() {
                override fun areItemsTheSame(
                    oldData: PajakEntity, newData: PajakEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: PajakEntity, newData: PajakEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        fun bind(item: PajakEntity) = with(binding) {
            val hasilPajak = item.hitung()


            tanggalTextView.text = dateFormatter.format(Date(item.Tanggal))
            pajakTextView.text = root.context.getString(
                R.string.data_x,
                hasilPajak.total)
        }

    }
}