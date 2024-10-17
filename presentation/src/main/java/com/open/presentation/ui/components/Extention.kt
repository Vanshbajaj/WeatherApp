package com.open.presentation.ui.components

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.open.data.proto.MyData
import com.open.data.proto.MyDataSerializer

class Extention {
    private val android.content.Context.dataStore: DataStore<MyData> by dataStore("my_data_store", MyDataSerializer)
}