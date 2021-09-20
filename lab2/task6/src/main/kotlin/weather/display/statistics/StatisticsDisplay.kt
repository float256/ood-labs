package weather.display.statistics

import weather.WeatherData
import weather.WeatherMeasurement
import weather.display.statistics.average_computation.AverageCalculator
import weather.display.statistics.average_computation.DefaultAverageCalculator
import weather.duo.WeatherDataSensorLocation
import weather.duo.WeatherDuoDisplayElement
import kotlin.math.max
import kotlin.math.min


abstract class StatisticsDisplay(
    inputWeatherData: WeatherData,
    inputPriority: Int,
    outputWeatherData: WeatherData,
    outputPriority: Int,
    averageCalculatorConstructor: () -> AverageCalculator = { DefaultAverageCalculator() }
) : WeatherDuoDisplayElement(inputWeatherData, inputPriority, outputWeatherData, outputPriority) {
    private var inputMeasurement = StatisticsMeasurement()
    private var outputMeasurement = StatisticsMeasurement()
    private var inputAverageCalculator = averageCalculatorConstructor()
    private var outputAverageCalculator = averageCalculatorConstructor()

    abstract fun getParameterName(): String
    abstract fun getParameterValue(newData: WeatherMeasurement): Double

    override fun update(sensorLocation: WeatherDataSensorLocation, newData: WeatherMeasurement) {
        val newValue = getParameterValue(newData)
        val currMeasurement = getMeasurement(sensorLocation)
        val currAverageCalculator = getAverageCalculator(sensorLocation)

        currAverageCalculator.addNewValue(getParameterValue(newData))
        currMeasurement.maxValue = max(newValue, currMeasurement.maxValue)
        currMeasurement.minValue = min(newValue, currMeasurement.minValue)
        display(sensorLocation)
    }

    override fun display(sensorLocation: WeatherDataSensorLocation) {
        val currMeasurement = getMeasurement(sensorLocation)
        val avg = getAverageCalculator(sensorLocation).calculate()
        val max = currMeasurement.minValue
        val min = currMeasurement.maxValue
        println("${sensorLocation.name}: Avg/Max/Min ${getParameterName()} = $avg/$max/$min")
    }

    private fun getMeasurement(sensorLocation: WeatherDataSensorLocation): StatisticsMeasurement {
        return when (sensorLocation) {
            WeatherDataSensorLocation.Input -> inputMeasurement
            else -> outputMeasurement
        }
    }

    private fun getAverageCalculator(sensorLocation: WeatherDataSensorLocation): AverageCalculator {
        return when (sensorLocation) {
            WeatherDataSensorLocation.Input -> inputAverageCalculator
            else -> outputAverageCalculator
        }
    }
}