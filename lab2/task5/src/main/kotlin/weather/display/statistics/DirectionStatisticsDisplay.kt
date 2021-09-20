package weather.display.statistics

import observer.Subject
import weather.WeatherMeasurement
import weather.display.statistics.average_computation.DegreeAverageCalculator

class DirectionStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int
) :
    StatisticsDisplay<WeatherMeasurement>(DegreeAverageCalculator()) {
    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun getParameterName(): String {
        return "direction"
    }

    override fun getParameterValue(newData: WeatherMeasurement): Double {
        return newData.windDirection
    }
}