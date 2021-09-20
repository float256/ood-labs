package weather.display.statistics.average_computation

import kotlin.math.*

class DegreeAverageCalculator: AverageCalculator {
    private var sumOfAllCos = 0.0
    private var sumOfAllSin = 0.0

    override fun addNewValue(newValue: Double) {
        val valueInRad = degreeToRad(newValue)
        sumOfAllSin += sin(valueInRad)
        sumOfAllCos += cos(valueInRad)
    }

    override fun calculate(): Double {
        if ((sumOfAllCos == sumOfAllSin) && (sumOfAllCos == 0.0)) {
            return 0.0
        } else {
            return radToDegree(atan2(sumOfAllSin,  sumOfAllCos))
        }
    }
}

private fun degreeToRad(degrees: Double): Double {
    return degrees * PI / 180
}

private fun radToDegree(rad: Double): Double {
    return rad * 180 / PI
}