package weather.display

import weather.WeatherData
import weather.WeatherMeasurement
import weather.duo.WeatherDataSensorLocation
import weather.duo.WeatherDuoDisplayElement
import kotlin.math.pow

class HeatIndexDisplay(
    inputWeatherData: WeatherData,
    inputPriority: Int,
    outputWeatherData: WeatherData,
    outputPriority: Int
) : WeatherDuoDisplayElement(inputWeatherData, inputPriority, outputWeatherData, outputPriority) {
    private var heatIndexForInputSensor = 0.0
    private var heatIndexForOutputSensor = 0.0

    override fun update(sensorLocation: WeatherDataSensorLocation, newData: WeatherMeasurement) {
        if (sensorLocation == WeatherDataSensorLocation.Input) {
            heatIndexForInputSensor = computeHeatIndex(newData.temperature, newData.humidity)
        } else {
            heatIndexForOutputSensor = computeHeatIndex(newData.temperature, newData.humidity)
        }
        display(sensorLocation)
    }

    override fun display(sensorLocation: WeatherDataSensorLocation) {
        val heatIndex = when(sensorLocation) {
            WeatherDataSensorLocation.Input -> heatIndexForInputSensor
            else -> heatIndexForOutputSensor
        }
        println("${sensorLocation.name} Heat index: $heatIndex")
    }

    private fun computeHeatIndex(temperature: Double, humidity: Double): Double {
        return (16.923 + (0.185212 * temperature)) +
                (5.37941 * humidity) -
                (0.100254 * temperature * humidity) +
                (0.00941695 * (temperature * temperature)) +
                (0.00728898 * (humidity.pow(2))) +
                (0.000345372 * (temperature * temperature * humidity)) -
                (0.000814971 * (temperature * humidity.pow(2))) +
                (0.0000102102 * (temperature * temperature * humidity.pow(2))) -
                (0.000038646 * (temperature.pow(3))) +
                (0.0000291583 * (humidity.pow(3))) +
                (0.00000142721 * (temperature.pow(3) * humidity)) +
                (0.000000197483 * (temperature * humidity.pow(3))) -
                (0.0000000218429 * (temperature.pow(3) * humidity.pow(2))) +
                (0.000000000843296 * (temperature * temperature * humidity.pow(3))) -
                (0.0000000000481975 * (temperature.pow(3) * humidity.pow(3)))
    }
}