package weather.display.statistics.average_computation

class DefaultAverageCalculator: AverageCalculator {
    private var sumOfAllValues = 0.0
    private var numReadings = 0

    override fun addNewValue(newValue: Double) {
        sumOfAllValues += newValue
        numReadings++
    }

    override fun calculate(): Double {
        return sumOfAllValues/numReadings
    }
}