package org.d3if4062.assessment3.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PajakDao {
    @Insert
    fun insert(pajak: PajakEntity)

    @Query("SELECT * FROM pajak ORDER BY id DESC")
    fun getLastPajak(): LiveData<List<PajakEntity>>

    @Query("DELETE FROM pajak")
    fun clearData()
}
