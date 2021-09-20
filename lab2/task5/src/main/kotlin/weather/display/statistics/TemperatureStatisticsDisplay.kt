package weather.display.statistics

import observer.Subject
import weather.WeatherMeasurement

class TemperatureStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int
) :
    StatisticsDisplay<WeatherMeasurement>() {

    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun getParameterName() = "temperature"
    override fun getParameterValue(newData: WeatherMeasurement) = newData.temperature
}