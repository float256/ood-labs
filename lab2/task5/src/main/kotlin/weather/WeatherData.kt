package weather

import observer.Observer
import observer.ObserverWithPriority
import observer.Subject
import java.util.*
import kotlin.collections.HashSet

class WeatherData : Subject<WeatherMeasurement> {
    private var currentMeasurement: WeatherMeasurement = WeatherMeasurement()
    private var allObservers: SortedSet<ObserverWithPriority<WeatherMeasurement>> = TreeSet()

    fun setMeasurements(weatherMeasurement: WeatherMeasurement) {
        currentMeasurement = weatherMeasurement
        measurementsChanged()
    }

    fun measurementsChanged() {
        notifyObservers()
    }

    override fun registerObserver(observer: Observer<WeatherMeasurement>, priority: Int) {
        removeObserver(observer)
        allObservers.add(ObserverWithPriority(observer, priority))
    }

    override fun removeObserver(observer: Observer<WeatherMeasurement>) {
        allObservers.remove(ObserverWithPriority(observer, 0))
    }

    override fun notifyObservers() {
        allObservers.toSortedSet().forEach {
            it.observer.update(currentMeasurement)
        }
    }
}