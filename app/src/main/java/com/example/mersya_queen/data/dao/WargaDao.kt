package com.example.mersya_queen.data.dao

import androidx.room.*
import com.example.mersya_queen.data.entity.WargaEntity

@Dao
interface WargaDao {
    @Query("SELECT * FROM warga")
    suspend fun getAll(): List<WargaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(warga: WargaEntity): Long

    @Delete
    suspend fun delete(warga: WargaEntity): Int
}
