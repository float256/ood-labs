package test_classes.task2

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

    override fun update(observableSubject: Subject<WeatherMeasurement>, newData: WeatherMeasurement) {
        display()
    }
}