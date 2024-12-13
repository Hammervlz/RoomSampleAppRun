package com.maximov.roomsampleapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RunDao {
    @Insert
    suspend fun insertRun(run: Run)

    @Query("SELECT * FROM runs")
    fun getAllRuns(): LiveData<List<Run>>

    @Query("DELETE FROM runs WHERE runId = :id")
    suspend fun deleteRun(id: Int)
}
