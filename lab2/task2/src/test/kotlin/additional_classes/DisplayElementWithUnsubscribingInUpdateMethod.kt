package additional_classes

import display.DisplayElement
import observer.Observer
import observer.Subject
import weather.WeatherMeasurement
import java.io.ByteArrayOutputStream
import java.io.PrintStream

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