import test_classes.task2.DisplayElementWithUnsubscribingInUpdateMethod
import org.junit.Assert
import org.junit.Test
import weather.WeatherData
import weather.WeatherMeasurement

class Task2Tests {
    @Test(expected = Test.None::class)
    fun testUnsubscribingInUpdateMethod() {
        val systemOutput = interceptSystemOutput {
            val weatherData = WeatherData()
            DisplayElementWithUnsubscribingInUpdateMethod(weatherData)
            weatherData.setMeasurements(WeatherMeasurement(80.0, 65.0, 30.4))
        }
        Assert.assertEquals("Before removing\nAfter removing\n", systemOutput)
    }
}