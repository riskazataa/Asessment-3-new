package org.d3if4062.assessment3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PajakEntity::class], version = 2, exportSchema = false)
abstract class PajakDb : RoomDatabase() {
    abstract val dao: PajakDao
    companion object {
        @Volatile
        private var INSTANCE: PajakDb? = null
        fun getInstance(context: Context): PajakDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PajakDb::class.java,
                        "pajak.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}