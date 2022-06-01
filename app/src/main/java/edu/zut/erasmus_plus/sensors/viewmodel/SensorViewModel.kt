package edu.zut.erasmus_plus.sensors.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.zut.erasmus_plus.sensors.model.SensorData
import edu.zut.erasmus_plus.sensors.model.SensorDataLiveData

class SensorViewModel(application: Application): AndroidViewModel(application) {
  private val _sensor = SensorDataLiveData(application)
  private var _pauseReading = MutableLiveData<Boolean>()

  val sensor: LiveData<SensorData>
    get() = _sensor

  fun getPauseReading(): MutableLiveData<Boolean> {
    return _pauseReading
  }

  fun changeButtonStatus()
  {
    if(_pauseReading.value==true) _sensor.registerListeners()
    else _sensor.unregisterListeners()
    _pauseReading.value?.let {
      _pauseReading.value = !it
    }
  }
  init {
      _pauseReading = MutableLiveData(false)
  }
}