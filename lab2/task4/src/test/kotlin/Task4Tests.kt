import org.junit.Assert
import org.junit.Test
import weather.WeatherData
import weather.WeatherMeasurement
import weather.display.CurrentConditionsDisplay
import weather.display.HeatIndexDisplay
import weather.display.statistics.HumidityStatisticsDisplay
import weather.display.statistics.PressureStatisticsDisplay
import weather.display.statistics.TemperatureStatisticsDisplay

class Task4Tests {
    @Test
    fun testObserverUsesCorrectSubjectStateWhileReceivesNotification() {
        val inputWeatherData = WeatherData()
        val outputWeatherData = WeatherData()

        CurrentConditionsDisplay(inputWeatherData, 1,
            outputWeatherData, 1)
        HeatIndexDisplay(inputWeatherData, 2,
            outputWeatherData, 2)
        HumidityStatisticsDisplay(inputWeatherData, 3,
            outputWeatherData, 3)
        PressureStatisticsDisplay(inputWeatherData, 4,
            outputWeatherData, 4)
        TemperatureStatisticsDisplay(inputWeatherData, 5,
            outputWeatherData, 5)

        val actualResult = interceptSystemOutput {
            inputWeatherData.setMeasurements(WeatherMeasurement(10.0, 10.0, 10.0))
            outputWeatherData.setMeasurements(WeatherMeasurement(20.0, 20.0, 20.0))
        }
        val expectedResult = "Input: Avg/Max/Min temperature = 10.0/10.0/10.0\n" +
                "Input: Avg/Max/Min pressure = 10.0/10.0/10.0\n" +
                "Input: Avg/Max/Min humidity = 10.0/10.0/10.0\n" +
                "Input Heat index: 63.851527072100005\n" +
                "Current input conditions: 10.0 F degrees and 10.0% humidity\n" +
                "Output: Avg/Max/Min temperature = 20.0/20.0/20.0\n" +
                "Output: Avg/Max/Min pressure = 20.0/20.0/20.0\n" +
                "Output: Avg/Max/Min humidity = 20.0/20.0/20.0\n" +
                "Output Heat index: 92.78681790720002\n" +
                "Current output conditions: 20.0 F degrees and 20.0% humidity\n"
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun testObserverSetsCorrectPriorityValue() {
        val inputWeatherData = WeatherData()
        val outputWeatherData = WeatherData()

        CurrentConditionsDisplay(inputWeatherData, 1,
            outputWeatherData, 5)
        HeatIndexDisplay(inputWeatherData, 2,
            outputWeatherData, 4)
        HumidityStatisticsDisplay(inputWeatherData, 3,
            outputWeatherData, 3)
        PressureStatisticsDisplay(inputWeatherData, 4,
            outputWeatherData, 2)
        TemperatureStatisticsDisplay(inputWeatherData, 5,
            outputWeatherData, 1)

        val actualResult = interceptSystemOutput {
            inputWeatherData.setMeasurements(WeatherMeasurement(10.0, 10.0, 10.0))
            outputWeatherData.setMeasurements(WeatherMeasurement(20.0, 20.0, 20.0))
        }
        val expectedResult = "Input: Avg/Max/Min temperature = 10.0/10.0/10.0\n" +
                "Input: Avg/Max/Min pressure = 10.0/10.0/10.0\n" +
                "Input: Avg/Max/Min humidity = 10.0/10.0/10.0\n" +
                "Input Heat index: 63.851527072100005\n" +
                "Current input conditions: 10.0 F degrees and 10.0% humidity\n" +
                "Current output conditions: 20.0 F degrees and 20.0% humidity\n" +
                "Output Heat index: 92.78681790720002\n" +
                "Output: Avg/Max/Min humidity = 20.0/20.0/20.0\n" +
                "Output: Avg/Max/Min pressure = 20.0/20.0/20.0\n" +
                "Output: Avg/Max/Min temperature = 20.0/20.0/20.0\n"
        Assert.assertEquals(expectedResult, actualResult)
    }
}