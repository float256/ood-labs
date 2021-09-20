package weather.display.statistics

import observer.Subject
import weather.WeatherData
import weather.WeatherMeasurement
import weather.display.statistics.average_computation.DegreeAverageCalculator

class DirectionStatisticsDisplay(
    inputWeatherData: WeatherData,
    inputPriority: Int,
    outputWeatherData: WeatherData,
    outputPriority: Int
) : StatisticsDisplay(inputWeatherData, inputPriority, outputWeatherData, outputPriority, {DegreeAverageCalculator()}) {
    override fun getParameterName(): String {
        return "direction"
    }

    override fun getParameterValue(newData: WeatherMeasurement): Double {
        return newData.windDirection
    }
}