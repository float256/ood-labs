import org.junit.Assert
import org.junit.Test
import test_classes.task3.PrintPriorityDisplayElement
import weather.WeatherData
import weather.WeatherMeasurement

class Task3Tests {
    @Test
    fun testObserverPriorityIsCorrect() {
        val weatherData = WeatherData()
        PrintPriorityDisplayElement(weatherData, 3)
        PrintPriorityDisplayElement(weatherData, 2)
        PrintPriorityDisplayElement(weatherData, Int.MIN_VALUE)
        PrintPriorityDisplayElement(weatherData, 4)
        PrintPriorityDisplayElement(weatherData, 6)
        PrintPriorityDisplayElement(weatherData, 4)
        PrintPriorityDisplayElement(weatherData, Int.MAX_VALUE)
        PrintPriorityDisplayElement(weatherData, 12)

        val actualResult = interceptSystemOutput {
            weatherData.setMeasurements(WeatherMeasurement(10.0, 10.0, 10.0))
        }
        val expectedResult = "${Int.MAX_VALUE}\n" +
                "12\n" +
                "6\n" +
                "4\n" +
                "4\n" +
                "3\n" +
                "2\n" +
                "${Int.MIN_VALUE}\n"
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun testChangePriority() {
        val weatherData = WeatherData()
        val firstDisplay = PrintPriorityDisplayElement(weatherData, 3)
        val secondDisplay = PrintPriorityDisplayElement(weatherData, 4)

        val actualResult = interceptSystemOutput {
            weatherData.setMeasurements(WeatherMeasurement(10.0, 10.0, 10.0))
            firstDisplay.registerObserverAgain(10)
            secondDisplay.registerObserverAgain(9)
            weatherData.setMeasurements(WeatherMeasurement(10.0, 10.0, 10.0))
        }
        val expectedResult = "4\n3\n10\n9\n"
        Assert.assertEquals(expectedResult, actualResult)
    }
}
