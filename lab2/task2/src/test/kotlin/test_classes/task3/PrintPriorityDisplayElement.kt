package test_classes.task3

import display.DisplayElement
import observer.Observer
import observer.Subject
import weather.WeatherMeasurement

class PrintPriorityDisplayElement(
    private var observableSubject: Subject<WeatherMeasurement>,
    private var priority: Int
): DisplayElement, Observer<WeatherMeasurement> {
    init {
        registerObserverAgain(priority)
    }

    override fun display() {
        println(priority)
    }

    override fun update(newData: WeatherMeasurement) {
        display()
    }

    fun registerObserverAgain(priority: Int) {
        this.priority = priority
        observableSubject.registerObserver(this, priority)
    }
}