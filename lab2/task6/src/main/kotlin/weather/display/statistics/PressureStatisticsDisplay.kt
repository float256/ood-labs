package weather.display.statistics

import observer.Subject
import weather.WeatherData
import weather.WeatherMeasurement

class PressureStatisticsDisplay(
    inputWeatherData: WeatherData,
    inputPriority: Int,
    outputWeatherData: WeatherData,
    outputPriority: Int
) : StatisticsDisplay(inputWeatherData, inputPriority, outputWeatherData, outputPriority) {
    override fun getParameterName() = "pressure"
    override fun getParameterValue(newData: WeatherMeasurement) = newData.pressure
}