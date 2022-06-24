package org.d3if4062.assessment3.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pajak")
data class PajakEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var Tanggal : Long = System.currentTimeMillis(),
    var Nama: String,
    var Harga: Double,
    var Jumlah: Double
)