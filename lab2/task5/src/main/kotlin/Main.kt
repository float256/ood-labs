import weather.WeatherData
import weather.WeatherMeasurement
import weather.display.CurrentConditionsDisplay
import weather.display.ForecastDisplay
import weather.display.HeatIndexDisplay
import weather.display.statistics.*

fun main() {
    val weatherData = WeatherData()
    CurrentConditionsDisplay(weatherData, 2)
    ForecastDisplay(weatherData, 1)
    HeatIndexDisplay(weatherData, 3)

    HumidityStatisticsDisplay(weatherData, 6)
    PressureStatisticsDisplay(weatherData, 5)
    TemperatureStatisticsDisplay(weatherData, 4)
    WindSpeedStatisticsDisplay(weatherData, 10)
    DirectionStatisticsDisplay(weatherData, 20)

    weatherData.setMeasurements(WeatherMeasurement(80.0, 65.0, 30.4, 180.0, 12.0))
    weatherData.setMeasurements(WeatherMeasurement(78.0, 90.0, 29.2, 0.0, 12.0))
}