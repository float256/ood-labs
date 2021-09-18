import additional_classes.DisplayElementWithUnsubscribingInUpdateMethod
import org.junit.Assert
import org.junit.Test
import weather.WeatherData
import weather.WeatherMeasurement
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class Task2Tests {
    @Test(expected = Test.None::class)
    fun testUnsubscribingInUpdateMethod() {
        val byteOutputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(byteOutputStream))

        val weatherData = WeatherData()
        DisplayElementWithUnsubscribingInUpdateMethod(weatherData)
        weatherData.setMeasurements(WeatherMeasurement(80.0, 65.0, 30.4))

        Assert.assertEquals("Before removing\nAfter removing\n", byteOutputStream.toString())
    }
}