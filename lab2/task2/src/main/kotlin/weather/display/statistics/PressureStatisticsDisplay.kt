package weather.display.statistics

import display.StatisticsDisplay
import observer.Subject
import weather.WeatherMeasurement

class PressureStatisticsDisplay(observableSubject: Subject<WeatherMeasurement>) :
    StatisticsDisplay<WeatherMeasurement>() {

    init {
        observableSubject.registerObserver(this)
    }

    override fun getParameterName() = "pressure"
    override fun getParameterValue(newData: WeatherMeasurement) = newData.pressure
}