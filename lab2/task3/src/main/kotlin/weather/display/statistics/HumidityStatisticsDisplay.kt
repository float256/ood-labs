package weather.display.statistics

import display.StatisticsDisplay
import observer.Subject
import weather.WeatherMeasurement

class HumidityStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int
) :
    StatisticsDisplay<WeatherMeasurement>() {

    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun getParameterName() = "humidity"
    override fun getParameterValue(newData: WeatherMeasurement) = newData.humidity
}