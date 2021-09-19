package weather.display

import display.DisplayElement
import observer.Observer
import observer.Subject
import weather.WeatherMeasurement
import kotlin.math.pow

class HeatIndexDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int
) : DisplayElement, Observer<WeatherMeasurement> {
    private var heatIndex = 0.0

    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun update(newData: WeatherMeasurement) {
        heatIndex = computeHeatIndex(newData.temperature, newData.humidity)
        display()
    }

    override fun display() {
        println("Heat index: $heatIndex")
    }

    private fun computeHeatIndex(temperature: Double, humidity: Double): Double {
        return (16.923 + (0.185212 * temperature)) +
                (5.37941 * humidity) -
                (0.100254 * temperature * humidity) +
                (0.00941695 * (temperature * temperature)) +
                (0.00728898 * (humidity.pow(2))) +
                (0.000345372 * (temperature * temperature * humidity)) -
                (0.000814971 * (temperature * humidity.pow(2))) +
                (0.0000102102 * (temperature * temperature * humidity.pow(2))) -
                (0.000038646 * (temperature.pow(3))) +
                (0.0000291583 * (humidity.pow(3))) +
                (0.00000142721 * (temperature.pow(3) * humidity)) +
                (0.000000197483 * (temperature * humidity.pow(3))) -
                (0.0000000218429 * (temperature.pow(3) * humidity.pow(2))) +
                (0.000000000843296 * (temperature * temperature * humidity.pow(3))) -
                (0.0000000000481975 * (temperature.pow(3) * humidity.pow(3)))
    }
}