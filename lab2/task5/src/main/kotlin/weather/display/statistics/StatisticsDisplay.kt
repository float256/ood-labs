package weather.display.statistics

import display.DisplayElement
import observer.Observer
import weather.display.statistics.average_computation.AverageCalculator
import weather.display.statistics.average_computation.DefaultAverageCalculator


abstract class StatisticsDisplay<ObservableDataType>(
    private var averageCalculator: AverageCalculator = DefaultAverageCalculator()
): DisplayElement, Observer<ObservableDataType> {
    private var maxValue = 0.0
    private var minValue = 200.0

    abstract fun getParameterName(): String
    abstract fun getParameterValue(newData: ObservableDataType): Double

    override fun update(newData: ObservableDataType) {
        val newValue = getParameterValue(newData)
        averageCalculator.addNewValue(newValue)

        if (newValue > maxValue) {
            maxValue = newValue
        }
        if (newValue < minValue) {
            minValue = newValue
        }
        display()
    }

    override fun display() {
        println("Avg/Max/Min ${getParameterName()} = ${averageCalculator.calculate()}/$maxValue/$minValue")
    }
}