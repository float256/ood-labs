package weather.display.statistics

import observer.Subject
import weather.WeatherMeasurement

class WindSpeedStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int
) :
    StatisticsDisplay<WeatherMeasurement>() {
    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun getParameterName(): String {
        return "wind speed"
    }

    override fun getParameterValue(newData: WeatherMeasurement): Double {
        return newData.windSpeed
    }
}