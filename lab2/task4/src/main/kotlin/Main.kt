import weather.WeatherData
import weather.WeatherMeasurement
import weather.display.CurrentConditionsDisplay
import weather.display.HeatIndexDisplay
import weather.display.statistics.HumidityStatisticsDisplay
import weather.display.statistics.PressureStatisticsDisplay
import weather.display.statistics.TemperatureStatisticsDisplay

fun main() {
    val inputWeatherData = WeatherData()
    val outputWeatherData = WeatherData()

    CurrentConditionsDisplay(inputWeatherData, 1,
        outputWeatherData, 5)
    HeatIndexDisplay(inputWeatherData, 2,
        outputWeatherData, 4)
    HumidityStatisticsDisplay(inputWeatherData, 3,
        outputWeatherData, 3)
    PressureStatisticsDisplay(inputWeatherData, 4,
        outputWeatherData, 2)
    TemperatureStatisticsDisplay(inputWeatherData, 5,
        outputWeatherData, 1)

    inputWeatherData.setMeasurements(WeatherMeasurement(80.0, 65.0, 30.4))
    println()
    outputWeatherData.setMeasurements(WeatherMeasurement(12.0, 20.0, 19.2))
}