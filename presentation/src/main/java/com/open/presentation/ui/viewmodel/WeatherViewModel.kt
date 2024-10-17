package com.open.presentation.ui.viewmodel


import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.open.data.network.UiState
import com.open.data.proto.MyData
import com.open.data.proto.MyDataSerializer
import com.open.domain.Weather
import com.open.domain.usecases.GetWeatherUseCase
import com.open.presentation.ui.components.Extention
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase, private val dataStore: DataStore<MyData>
) : ViewModel() {


    private val _weatherState = MutableStateFlow<UiState<Weather>>(UiState.Empty)
    val weatherState: StateFlow<UiState<Weather>> get() = _weatherState
    private val _nameFlow = MutableStateFlow("")
    private val _temp = MutableStateFlow(0)



    init {
        loadData()
    }

    // Expose the Flow to the UI
    val nameFlow: Flow<String> get() = _nameFlow

    val temp: Flow<Int> get() = _temp
    fun getWeather(cityName: String) {
        if (cityName.isBlank()) {
            return
        }


        _weatherState.value = UiState.Loading



        viewModelScope.launch {
            try {
                getWeatherUseCase(cityName).collect { weather ->
                    _weatherState.emit(UiState.Success(weather))
                    weather.main?.temp?.toInt()?.let { saveData(cityName, it) }
                }
            } catch (e: Exception) {
                _weatherState.emit(
                    UiState.Error(
                        e.localizedMessage ?: "Unknown error"
                    )
                )
            }
        }
    }


    fun saveData(name: String, id: Int) {
        val data = MyData.newBuilder().setName(name).setId(id).build()
        viewModelScope.launch {
            dataStore.updateData { data }
        }
    }

    fun loadData() {
        viewModelScope.launch {
            // Collect from DataStore, and update the LiveData when data is fetched
            dataStore.data.collect { startupParams ->
                val fetchedName = startupParams.name
                _nameFlow.emit(fetchedName)
                _temp.emit(startupParams.id)
                Log.e("Name:", fetchedName)
            }
        }
    }
}






