package org.d3if4062.assessment3.model

import org.d3if4062.assessment3.db.PajakEntity

fun PajakEntity.hitung(): HasilPajak {
    val ppn = (Harga * Jumlah) * 10/100
    val total = ppn + (Harga * Jumlah)
    return HasilPajak(ppn, total)
}