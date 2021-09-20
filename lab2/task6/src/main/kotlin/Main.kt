import weather.WeatherData
import weather.WeatherMeasurement
import weather.display.CurrentConditionsDisplay
import weather.display.HeatIndexDisplay
import weather.display.statistics.*

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
    WindSpeedStatisticsDisplay(inputWeatherData, 20,
        outputWeatherData, 10)
    DirectionStatisticsDisplay(inputWeatherData, 10,
        outputWeatherData, 20)

    inputWeatherData.setMeasurements(WeatherMeasurement(80.0, 65.0, 30.4, 90.0, 12.0))
    println()
    outputWeatherData.setMeasurements(WeatherMeasurement(12.0, 20.0, 19.2, 135.0, 12.0))
    println()
    outputWeatherData.setMeasurements(WeatherMeasurement(12.0, 20.0, 19.2, 45.0, 12.0))
}