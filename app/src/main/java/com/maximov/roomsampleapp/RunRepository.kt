package com.maximov.roomsampleapp

import androidx.lifecycle.LiveData

class RunRepository(private val runDao: RunDao) {
    val allRuns: LiveData<List<Run>> = runDao.getAllRuns()

    suspend fun insert(run: Run) {
        runDao.insertRun(run)
    }

    suspend fun delete(id: Int) {
        runDao.deleteRun(id)
    }
}
