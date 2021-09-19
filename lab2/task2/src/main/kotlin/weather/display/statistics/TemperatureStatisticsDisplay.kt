package weather.display.statistics

import display.StatisticsDisplay
import observer.Subject
import weather.WeatherMeasurement

class TemperatureStatisticsDisplay(observableSubject: Subject<WeatherMeasurement>) :
    StatisticsDisplay<WeatherMeasurement>() {

    init {
        observableSubject.registerObserver(this)
    }

    override fun getParameterName() = "temperature"
    override fun getParameterValue(newData: WeatherMeasurement) = newData.temperature
}