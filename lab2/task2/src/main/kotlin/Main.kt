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
    CurrentConditionsDisplay(weatherData)
    ForecastDisplay(weatherData)
    HeatIndexDisplay(weatherData)

    HumidityStatisticsDisplay(weatherData)
    PressureStatisticsDisplay(weatherData)
    TemperatureStatisticsDisplay(weatherData)

    weatherData.setMeasurements(WeatherMeasurement(80.0, 65.0, 30.4))
    println()
    weatherData.setMeasurements(WeatherMeasurement(82.0, 70.0, 29.2))
    println()
    weatherData.setMeasurements(WeatherMeasurement(78.0, 90.0, 29.2))
}