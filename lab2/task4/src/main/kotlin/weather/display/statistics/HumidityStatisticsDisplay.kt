package weather.display.statistics

import observer.Subject
import weather.WeatherData
import weather.WeatherMeasurement
import weather.duo.WeatherDuoDisplayElement

class HumidityStatisticsDisplay(
    inputWeatherData: WeatherData,
    inputPriority: Int,
    outputWeatherData: WeatherData,
    outputPriority: Int
) : StatisticsDisplay(inputWeatherData, inputPriority, outputWeatherData, outputPriority) {
    override fun getParameterName() = "humidity"
    override fun getParameterValue(newData: WeatherMeasurement) = newData.humidity
}