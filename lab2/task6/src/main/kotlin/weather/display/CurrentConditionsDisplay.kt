package weather.display

import display.DisplayElement
import observer.Observer
import observer.Subject
import weather.WeatherData
import weather.WeatherMeasurement
import weather.duo.WeatherDataSensorLocation
import weather.duo.WeatherDuoDisplayElement

class CurrentConditionsDisplay(
    inputWeatherData: WeatherData,
    inputPriority: Int,
    outputWeatherData: WeatherData,
    outputPriority: Int
) : WeatherDuoDisplayElement(inputWeatherData, inputPriority, outputWeatherData, outputPriority) {

    private var currentInputMeasurement: WeatherMeasurement = WeatherMeasurement()
    private var currentOutputMeasurement: WeatherMeasurement = WeatherMeasurement()

    override fun display(sensorLocation: WeatherDataSensorLocation) {
        val currentMeasurement = when(sensorLocation) {
            WeatherDataSensorLocation.Input -> currentInputMeasurement
            else -> currentOutputMeasurement
        }
        println(
            "Current ${sensorLocation.name.lowercase()} conditions: ${currentMeasurement.temperature} F degrees " +
                    "and ${currentMeasurement.humidity}% humidity"
        )
    }

    override fun update(sensorLocation: WeatherDataSensorLocation, newData: WeatherMeasurement) {
        if (sensorLocation == WeatherDataSensorLocation.Input) {
            currentInputMeasurement = newData
        } else {
            currentOutputMeasurement = newData
        }
        display(sensorLocation)
    }
}