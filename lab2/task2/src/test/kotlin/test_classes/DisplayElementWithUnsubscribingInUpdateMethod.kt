package test_classes

import display.DisplayElement
import observer.Observer
import observer.Subject
import weather.WeatherMeasurement

class DisplayElementWithUnsubscribingInUpdateMethod(
    private val observableSubject: Subject<WeatherMeasurement>
): DisplayElement, Observer<WeatherMeasurement> {

    init {
        observableSubject.registerObserver(this)
    }

    override fun display() {
        println("Before removing")
        observableSubject.removeObserver(this)
        println("After removing")
    }

    override fun update(newData: WeatherMeasurement) {
        display()
    }
}