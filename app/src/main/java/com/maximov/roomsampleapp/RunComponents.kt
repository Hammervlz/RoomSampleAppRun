package com.maximov.roomsampleapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RunList(runs: List<Run>, delete: (Int) -> Unit) {
    LazyColumn(Modifier.fillMaxWidth()) {
        item { RunTitleRow() }
        items(runs) { run -> RunRow(run, delete) }
    }
}

@Composable
fun RunRow(run: Run, delete: (Int) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(5.dp)) {
        Text(run.id.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text(run.date, Modifier.weight(0.2f), fontSize = 22.sp)
        Text("${run.distance} km", Modifier.weight(0.2f), fontSize = 22.sp)
        Text("Delete", Modifier.weight(0.2f).clickable { delete(run.id) }, color = Color(0xFF6650a4), fontSize = 22.sp)
    }
}

@Composable
fun RunTitleRow() {
    Row(Modifier.background(Color.LightGray).fillMaxWidth().padding(5.dp)) {
        Text("Id", color = Color.White, modifier = Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Date", color = Color.White, modifier = Modifier.weight(0.2f), fontSize = 22.sp)
        Text("Distance", color = Color.White, modifier = Modifier.weight(0.2f), fontSize = 22.sp)
        Spacer(Modifier.weight(0.2f))
    }
}
