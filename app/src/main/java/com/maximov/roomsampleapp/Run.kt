package com.maximov.roomsampleapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runs")
data class Run (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "runId")
    val id: Int = 0,
    var date: String,
    var distance: Double
)