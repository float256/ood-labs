package weather.display.statistics

import observer.Subject
import weather.WeatherData
import weather.WeatherMeasurement
import weather.display.statistics.average_computation.DegreeAverageCalculator

class WindSpeedStatisticsDisplay(
    inputWeatherData: WeatherData,
    inputPriority: Int,
    outputWeatherData: WeatherData,
    outputPriority: Int
) : StatisticsDisplay(inputWeatherData, inputPriority, outputWeatherData, outputPriority) {
    override fun getParameterName(): String {
        return "wind speed"
    }

    override fun getParameterValue(newData: WeatherMeasurement): Double {
        return newData.windSpeed
    }
}