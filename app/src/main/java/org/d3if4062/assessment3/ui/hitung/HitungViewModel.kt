package org.d3if4062.assessment3.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4062.assessment3.db.PajakDao
import org.d3if4062.assessment3.db.PajakEntity
import org.d3if4062.assessment3.model.HasilPajak
import org.d3if4062.assessment3.model.hitung

class HitungViewModel(private val db: PajakDao) : ViewModel() {
    private val hasilPajak = MutableLiveData<HasilPajak?>()

    fun hitung (nama: String, harga: Double, jumlah: Double) {
      val dataPajak = PajakEntity(
          Nama = nama,
          Harga = harga,
          Jumlah = jumlah
      )
       hasilPajak.value = dataPajak.hitung()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataPajak)
            }
        }

    }
    fun getHasilPajak(): LiveData<HasilPajak?> = hasilPajak
}