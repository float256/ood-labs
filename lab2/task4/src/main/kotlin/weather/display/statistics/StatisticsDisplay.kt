package weather.display.statistics

import weather.WeatherData
import weather.WeatherMeasurement
import weather.duo.WeatherDataSensorLocation
import weather.duo.WeatherDuoDisplayElement
import kotlin.math.max
import kotlin.math.min


abstract class StatisticsDisplay(
    inputWeatherData: WeatherData,
    inputPriority: Int,
    outputWeatherData: WeatherData,
    outputPriority: Int
) : WeatherDuoDisplayElement(inputWeatherData, inputPriority, outputWeatherData, outputPriority) {
    private var inputMeasurement = StatisticsMeasurement()
    private var outputMeasurement = StatisticsMeasurement()

    abstract fun getParameterName(): String
    abstract fun getParameterValue(newData: WeatherMeasurement): Double

    override fun update(sensorLocation: WeatherDataSensorLocation, newData: WeatherMeasurement) {
        val newValue = getParameterValue(newData)
        val currMeasurement = getMeasurement(sensorLocation)

        currMeasurement.sumOfAllValues += newValue
        currMeasurement.numReadings++
        currMeasurement.maxValue = max(newValue, currMeasurement.maxValue)
        currMeasurement.minValue = min(newValue, currMeasurement.minValue)
        display(sensorLocation)
    }

    override fun display(sensorLocation: WeatherDataSensorLocation) {
        val currMeasurement = getMeasurement(sensorLocation)
        val avg = currMeasurement.sumOfAllValues/currMeasurement.numReadings
        val max = currMeasurement.minValue
        val min = currMeasurement.maxValue
        println("${sensorLocation.name}: Avg/Max/Min ${getParameterName()} = $avg/$max/$min")
    }

    private fun getMeasurement(sensorLocation: WeatherDataSensorLocation): StatisticsMeasurement {
        return when(sensorLocation) {
            WeatherDataSensorLocation.Input -> inputMeasurement
            else -> outputMeasurement
        }
    }
}