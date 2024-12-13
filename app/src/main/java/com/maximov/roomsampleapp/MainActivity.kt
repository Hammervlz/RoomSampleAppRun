package com.maximov.roomsampleapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current

            owner?.let {
                val viewModel: RunViewModel = viewModel(
                    it,
                    "RunViewModel",
                    RunViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                Main(viewModel)
            }
        }
    }
}

@Composable
fun Main(vm: RunViewModel = viewModel()) {
    val allRuns by vm.allRuns.observeAsState(initial = emptyList())
    Column {
        OutlinedTextField(vm.date, modifier = Modifier.padding(8.dp), label = { Text("Date") },
            onValueChange = { vm.changeDate(it) })
        OutlinedTextField(vm.distance, modifier = Modifier.padding(8.dp), label = { Text("Distance (km)") },
            onValueChange = { vm.changeDistance(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = { vm.addRun() }, modifier = Modifier.padding(8.dp)) { Text("Add Run", fontSize = 22.sp) }
        RunList(runs = allRuns, delete = { vm.deleteRun(it) })
    }
}
