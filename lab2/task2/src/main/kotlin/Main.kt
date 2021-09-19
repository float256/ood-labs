import weather.WeatherData
import weather.WeatherMeasurement
import weather.display.CurrentConditionsDisplay
import weather.display.ForecastDisplay
import weather.display.HeatIndexDisplay
import weather.display.statistics.HumidityStatisticsDisplay
import weather.display.statistics.PressureStatisticsDisplay
import weather.display.statistics.TemperatureStatisticsDisplay

fun main() {
    val weatherData = WeatherData()
    CurrentConditionsDisplay(weatherData, 2)
    ForecastDisplay(weatherData, 1)
    HeatIndexDisplay(weatherData, 3)

    HumidityStatisticsDisplay(weatherData, 6)
    PressureStatisticsDisplay(weatherData, 5)
    TemperatureStatisticsDisplay(weatherData, 4)

    weatherData.setMeasurements(WeatherMeasurement(80.0, 65.0, 30.4))
    println()
    weatherData.setMeasurements(WeatherMeasurement(82.0, 70.0, 29.2))
    println()
    weatherData.setMeasurements(WeatherMeasurement(78.0, 90.0, 29.2))
}