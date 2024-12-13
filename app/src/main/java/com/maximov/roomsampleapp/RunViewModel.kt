package com.maximov.roomsampleapp

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RunViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RunRepository
    val allRuns: LiveData<List<Run>>

    var date by mutableStateOf("")
    var distance by mutableStateOf("")

    init {
        val runDao = AppDatabase.getInstance(application).runDao()
        repository = RunRepository(runDao)
        allRuns = repository.allRuns
    }

    fun changeDate(value: String) {
        date = value
    }

    fun changeDistance(value: String) {
        distance = value
    }

    fun addRun() {
        val distanceDouble = distance.toDoubleOrNull() ?: 0.0
        viewModelScope.launch {
            repository.insert(Run(date = date, distance = distanceDouble))
        }
    }

    fun deleteRun(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }
}
