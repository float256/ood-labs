package weather.display

import display.DisplayElement
import observer.Observer
import observer.Subject
import weather.WeatherMeasurement

class CurrentConditionsDisplay(observableSubject: Subject<WeatherMeasurement>) :
    DisplayElement, Observer<WeatherMeasurement> {

    init {
        observableSubject.registerObserver(this)
    }

    private var currentMeasurement: WeatherMeasurement = WeatherMeasurement()

    override fun display() {
        println(
            "Current conditions: ${currentMeasurement.temperature} F degrees " +
                    "and ${currentMeasurement.humidity}% humidity"
        )
    }

    override fun update(newData: WeatherMeasurement) {
        currentMeasurement = newData
        display()
    }
}