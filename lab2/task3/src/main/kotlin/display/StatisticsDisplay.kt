package display

import observer.Observer


abstract class StatisticsDisplay<ObservableDataType>: DisplayElement, Observer<ObservableDataType> {
    private var maxValue = 0.0
    private var minValue = 200.0
    private var sumOfAllValues = 0.0
    private var numReadings = 0

    abstract fun getParameterName(): String
    abstract fun getParameterValue(newData: ObservableDataType): Double

    override fun update(newData: ObservableDataType) {
        val newValue = getParameterValue(newData)
        sumOfAllValues += newValue
        numReadings++
        if (newValue > maxValue) {
            maxValue = newValue
        }
        if (newValue < minValue) {
            minValue = newValue
        }
        display()
    }

    override fun display() {
        println("Avg/Max/Min ${getParameterName()} = ${sumOfAllValues/numReadings}/$maxValue/$minValue")
    }
}