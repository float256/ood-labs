package weather.display

import display.DisplayElement
import observer.Observer
import observer.Subject
import weather.WeatherMeasurement

class ForecastDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int
) : DisplayElement, Observer<WeatherMeasurement> {
    private var currentPressure = 29.92
    private var lastPressure = 0.0

    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun display() {
        if (currentPressure > lastPressure) {
            println("Improving weather on the way!")
        } else if (currentPressure == lastPressure) {
            println("More of the same")
        } else if (currentPressure < lastPressure) {
            println("Watch out for cooler, rainy weather")
        }
    }

    override fun update(newData: WeatherMeasurement) {
        lastPressure = currentPressure
        currentPressure = newData.pressure
        display()
    }
}