package weather

import observer.Observer
import observer.Subject

class WeatherData : Subject<WeatherMeasurement> {
    private var currentMeasurement: WeatherMeasurement = WeatherMeasurement()
    private var allObservers: MutableSet<Observer<WeatherMeasurement>> = HashSet<Observer<WeatherMeasurement>>()


    fun setMeasurements(weatherMeasurement: WeatherMeasurement) {
        currentMeasurement = weatherMeasurement
        measurementsChanged()
    }

    fun measurementsChanged() {
        notifyObservers()
    }

    override fun registerObserver(observer: Observer<WeatherMeasurement>) {
        allObservers.add(observer)
    }

    override fun removeObserver(observer: Observer<WeatherMeasurement>) {
        allObservers.remove(observer)
    }

    override fun notifyObservers() {
        allObservers.forEach {
            it.update(currentMeasurement)
        }
    }
}