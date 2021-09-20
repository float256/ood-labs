package weather.duo

import display.DisplayElement
import observer.Observer
import observer.Subject
import weather.WeatherData
import weather.WeatherMeasurement

abstract class WeatherDuoDisplayElement(
    private val inputWeatherData: WeatherData,
    inputPriority: Int,
    private val outputWeatherData: WeatherData,
    outputPriority: Int
): DisplayElement, Observer<WeatherMeasurement> {
    init {
        inputWeatherData.registerObserver(this, inputPriority)
        outputWeatherData.registerObserver(this, outputPriority)
    }

    protected abstract fun update(sensorLocation: WeatherDataSensorLocation, newData: WeatherMeasurement)
    protected abstract fun display(sensorLocation: WeatherDataSensorLocation)

    final override fun update(observableSubject: Subject<WeatherMeasurement>, newData: WeatherMeasurement) {
        if (observableSubject == inputWeatherData) {
            update(WeatherDataSensorLocation.Input, newData)
        } else if (observableSubject == outputWeatherData) {
            update(WeatherDataSensorLocation.Output, newData)
        } else {
            throw IllegalArgumentException("Updates came from unknown observable subject")
        }
    }

    final override fun display() {
        display(WeatherDataSensorLocation.Input)
        display(WeatherDataSensorLocation.Output)
    }
}