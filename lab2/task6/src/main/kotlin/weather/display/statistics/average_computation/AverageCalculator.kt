package weather.display.statistics.average_computation

interface AverageCalculator {
    fun addNewValue(newValue: Double)
    fun calculate(): Double
}