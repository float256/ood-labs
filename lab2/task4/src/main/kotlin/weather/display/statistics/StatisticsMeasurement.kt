package weather.display.statistics

class StatisticsMeasurement(
    var maxValue: Double = Double.MIN_VALUE,
    var minValue: Double = Double.MAX_VALUE,
    var sumOfAllValues: Double = 0.0,
    var numReadings: Int = 0
) {}